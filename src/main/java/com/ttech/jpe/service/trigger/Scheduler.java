/**
 * @author Engin Esin
 */
package com.ttech.jpe.service.trigger;

import com.ttech.jpe.metric.prometheus.observe.MetricsRegistry;
import com.ttech.jpe.service.async.AsyncRestRequest;
import com.ttech.jpe.service.mapper.JicofoPromExporter;
import com.ttech.jpe.service.mapper.JvbPromExporter;
import com.ttech.jpe.types.statTypes.JicofoStats;
import com.ttech.jpe.types.statTypes.JvbStats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


/**
 * All magic is started from this Scheduler
 */
@Service
@Slf4j
public class Scheduler {

    @Value("#{'${jpe.jvb.metricUrls}'.split(',')}")
    private List<URL> jvbHosts;

    @Value("${jpe.jvb.metric.path:/colibri/stats}")
    private String jvbMetricPath;

    @Value("${jpe.jvb.health.path:/about/health}")
    private String jvbHealthPath;


    @Value("#{'${jpe.jicofo.metricUrls}'.split(',')}")
    private List<URL> jicofoHosts;

    @Value("${jpe.jicofo.metric.path:/stats}")
    private String jicofoMetricPath;

    @Value("${jpe.jicofo.health.path:/about/health}")
    private String jicofoHealthPath;

    List<CompletableFuture<ResponseEntity<String>>> allFutures = new ArrayList<>();


    @Autowired
    JvbPromExporter jvbPromExporter;

    @Autowired
    JicofoPromExporter jicofoPromExporter;

    @Autowired
    AsyncRestRequest asyncRestRequest;

    @Autowired
    MetricsRegistry customeCounter;

    @Autowired
    MetricsRegistry metricsRegistry;


    private List<URL> jvbMetricUrls;
    private List<URL> jvbHealthUrls;

    private List<URL> jicofoMetricUrls;
    private List<URL> jicofoHeathUrls;

    int i = 0;

    @PostConstruct
    public void init() {


        //add all url to make parallel rest calls in the scheduler

        jvbHealthUrls = createURL2(jvbHosts, jvbHealthPath);

        jvbMetricUrls = createURL2(jvbHosts, jvbMetricPath);

        jicofoHeathUrls = createURL2(jicofoHosts, jicofoHealthPath);

        jicofoMetricUrls = createURL2(jicofoHosts, jicofoMetricPath);

    }

    @Scheduled(fixedRateString = "${jpe.stats.check.miliseconds:15000}")
    public void check4Metrics() {

        long start = System.currentTimeMillis();

        //make simultaneous rest request for metrics and collect futures
        List<CompletableFuture<ResponseEntity<String>>> futureJvbHealth = getFutureJob(jvbHealthUrls);
        List<CompletableFuture<ResponseEntity<String>>> futureJicofoHealth = getFutureJob(jicofoHeathUrls);
        List<CompletableFuture<ResponseEntity<String>>> futureJvbMetrics = getFutureJob(jvbMetricUrls);
        List<CompletableFuture<ResponseEntity<String>>> futureJicofoMetrics = getFutureJob(jicofoMetricUrls);


        allFutures.clear();

        allFutures.addAll(futureJvbHealth);
        allFutures.addAll(futureJicofoHealth);
        allFutures.addAll(futureJvbMetrics);
        allFutures.addAll(futureJicofoMetrics);

        //wait for all requests are ended asynchronously
        CompletableFuture.allOf(allFutures.toArray(new CompletableFuture[0])).join();

        ResponseEntity<String> responseEntityMetric;
        ResponseEntity<String> responseEntityHealth;

        //feed jvb metrics
        for (int index = 0; index < jvbMetricUrls.size(); index++) {

            //get metric
            responseEntityMetric = asyncRestRequest.getStringResponseEntity(futureJvbMetrics.get(index));
            //getHealth
            responseEntityHealth = asyncRestRequest.getStringResponseEntity(futureJvbHealth.get(index));

            JvbStats jvbStats = jvbPromExporter.getMetric(responseEntityMetric, responseEntityHealth, jvbMetricUrls.get(index));

            jvbPromExporter.feedMetrics(jvbStats);
        }

        //feed jicofo metrics
        for (int index = 0; index < jicofoMetricUrls.size(); index++) {

            //get metric
            responseEntityMetric = asyncRestRequest.getStringResponseEntity(futureJicofoMetrics.get(index));
            //getHealth
            responseEntityHealth = asyncRestRequest.getStringResponseEntity(futureJicofoHealth.get(index));

            JicofoStats jicofoStats = jicofoPromExporter.getMetric(responseEntityMetric, responseEntityHealth, jicofoMetricUrls.get(index));

            jicofoPromExporter.feedMetrics(jicofoStats);
        }


        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
    }

    private List<CompletableFuture<ResponseEntity<String>>> getFutureJob(List<URL> urlList) {

        return urlList.stream()
                .map(url -> asyncRestRequest.asyncRestRequest(url))
                .collect(Collectors.toList());
    }


    /**
     * creates urls for metrics and healthy requests consecutively
     *
     * @param metricUrls: host url like http://example.com
     * @param path:       metric path of the url
     * @return List<URL>: list of URLs
     */
    private static List<URL> createURL2(List<URL> metricUrls, String path) {

        List<URL> returnList = new ArrayList<>();
        for (URL metricUrl : metricUrls) {
            try {
                returnList.add(new URL(metricUrl.getProtocol(), metricUrl.getHost(), metricUrl.getPort(), path));
            } catch (MalformedURLException e) {
                log.error("Wrong url Format: " + metricUrl, e);
            }
        }

        return returnList;
    }

}
