/**
 * @author Engin Esin
 */

package com.ttech.jpe.service.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttech.jpe.metric.prometheus.observe.MetricsRegistry;
import com.ttech.jpe.metric.prometheus.type.CounterMetric;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;
import java.net.URL;


@Slf4j
public abstract class PromExporterAbstract<T> {

    Logger logger = LoggerFactory.getLogger("TransactionLogger");


    @Autowired
    MetricsRegistry metricsRegistry;

    ObjectMapper objectMapper;


    @PostConstruct
    public void init() {

        objectMapper = new ObjectMapper();

    }


    /**
     * return metric java object from input json string.
     * sourceUrl is used to distinguish metrics comes from different sources
     *
     * @param metricJsonBody : json string
     * @param hostHealthy    : true if target healthy
     * @param sourceURL      :source url to insert metric class
     *                       to distinguish metrics comes from different sources
     * @return
     */
    abstract T metricMapper(String metricJsonBody, boolean hostHealthy, URL sourceURL);


    /**
     * @param resEntityMetric  :       response of metric rest request
     * @param resEntityHealthy :    response of healthy rest request
     * @param sourceURL        : response source url
     * @return return metric object if response status is 2xx. Else returns default metric type
     */
    public T getMetric(ResponseEntity<String> resEntityMetric, ResponseEntity<String> resEntityHealthy, URL sourceURL) {

        T metricObject;
        boolean hostHealthy = resEntityHealthy.getStatusCode().is2xxSuccessful();

        if (resEntityMetric.getStatusCode().is2xxSuccessful()) {
            metricObject = metricMapper(resEntityMetric.getBody(), hostHealthy, sourceURL);
        } else {
            metricObject = getDefaultInstance(sourceURL,hostHealthy);
        }

        if (metricObject != null)
            logger.debug(metricObject.toString());

        return metricObject;
    }


    /**
     * feeds prometheus metric types from source metric object
     *
     * @param statistics: metric object of jvb pr jicofo
     */
    public abstract void feedMetrics(T statistics);


    /**
     * check previous value of the metric.
     * If changed, then feeds counter metric else does nothing..
     * hint: if diff is 0 and counter->observe method is called with value 0, it seems that
     * counter value increase by 1. So if there is no change, we do not use observe counter.
     *
     * @param metric       : metric type
     * @param observedData : observed metric data to be set
     * @param label        : Label/s of the metric
     */
    void checkAndCount(CounterMetric metric, long observedData, String... label) {

        double prevVal = 0.0;

        try {
            prevVal = metricsRegistry.getCounterValue(metric, label);
        } catch (Exception e) {
            prevVal = 0.0;
            log.error("cannot get metric value", e);
        }

        prevVal = Double.isNaN(prevVal) ? 0 : prevVal; //initial value of the metric is NAN

        long diff = (long) (observedData - prevVal);

        if (diff > 0) {
            metricsRegistry.observe(metric, diff, label);
        } else if (diff < 0) {
            metricsRegistry.clearMetric(metric); //this means that source application resets its value
            metricsRegistry.observe(metric, diff, label); //set first value of the metric after reset
        }

    }

    /**
     * @param sourceURL
     * @param hostHealthy
     * @return default metric type to be used for exceptional cases
     */
    abstract T getDefaultInstance(URL sourceURL, boolean hostHealthy);

    String setHostParam(URL url) {

        return url.getHost().concat(url.getPort() != -1 ? ":" + url.getPort() : url.getPath());
    }

}
