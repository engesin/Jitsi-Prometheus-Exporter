/**
 * @author Engin Esin
 */

package com.ttech.jpe.service.mapper;

import com.ttech.jpe.metric.prometheus.type.CounterMetric;
import com.ttech.jpe.metric.prometheus.type.GaugeMetric;
import com.ttech.jpe.metric.prometheus.type.HistogramMetric;
import com.ttech.jpe.types.statTypes.JicofoStats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.stream.IntStream;


@Service
@Slf4j
public class JicofoPromExporter extends PromExporterAbstract<JicofoStats> {

    private static final JicofoStats JICOFO_STATS = new JicofoStats();

    @Override
    JicofoStats metricMapper(String metricJsonBody, boolean hostHealthy, URL sourceURL) {


        JicofoStats stats = null;
        try {

            stats = objectMapper.readValue(metricJsonBody, JicofoStats.class);

            stats.setHealth(hostHealthy);
            stats.setHost(setHostParam(sourceURL));

        } catch (IOException e) {
            log.error("Object Mapping Exception for url: " + sourceURL + " body: " + metricJsonBody, e);
        }


        return stats;
    }

    @Override
    public void feedMetrics(JicofoStats statistics) {

        if (StringUtils.isEmpty(statistics.getHost()))
            return;

        counters(statistics);

        gauges(statistics);

        histogram(statistics);

    }

    private void histogram(JicofoStats jicofoStats) {


        IntStream.range(0, jicofoStats.getConference_sizes().size())
                .forEach(size -> IntStream.range(0, jicofoStats.getConference_sizes().get(size))
                        .forEach(group -> metricsRegistry.observe(HistogramMetric.JITSI_JICOFO_CONFERENCE_SIZES_HISTOGRAM,
                                size, jicofoStats.getHost())));
    }

    @Override
    JicofoStats getDefaultInstance(URL sourceURL) {
        JICOFO_STATS.setHost(setHostParam(sourceURL));
        return JICOFO_STATS;
    }


    private void gauges(JicofoStats jicofoStats) {

        metricsRegistry.observe(GaugeMetric.JITSI_JICOFO_LARGEST_CONFERENCE, jicofoStats.getLargest_conference(), jicofoStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JICOFO_CONFERENCES, jicofoStats.getConferences(), jicofoStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JICOFO_PARTICIPANTS, jicofoStats.getParticipants(), jicofoStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JICOFO_BRIDGE_COUNT, jicofoStats.getBridge_selector().getBridge_count(), jicofoStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JICOFO_OPERATIONAL_BRIDGE_COUNT, jicofoStats.getBridge_selector().getOperational_bridge_count(), jicofoStats.getHost());

        IntStream.range(0, jicofoStats.getConference_sizes().size())
                .forEach(index -> {
                    String confSize = index == jicofoStats.getConference_sizes().size() - 1 ? index + "+" : index + "";
                    metricsRegistry.observe(GaugeMetric.JITSI_JICOFO_CONFERENCE_SIZES, jicofoStats.getConference_sizes().get(index), jicofoStats.getHost(), confSize);
                });

        metricsRegistry.observe(GaugeMetric.JITSI_JICOFO_HEALTH_STATUS, jicofoStats.isHealth() ? 1 : 0, jicofoStats.getHost());


    }

    private void counters(JicofoStats jicofoStats) {


        checkAndCount(CounterMetric.JITSI_JICOFO_TOTAL_SIP_CALL_FAILURES, jicofoStats.getTotal_sip_call_failures(), jicofoStats.getHost());
        checkAndCount(CounterMetric.JITSI_JICOFO_TOTAL_PARTICIPANTS, jicofoStats.getTotal_participants(), jicofoStats.getHost());
        checkAndCount(CounterMetric.JITSI_JICOFO_TOTAL_CONFERENCES_CREATED, jicofoStats.getTotal_conferences_created(), jicofoStats.getHost());
        checkAndCount(CounterMetric.JITSI_JICOFO_TOTAL_RECORDING_FAILURES, jicofoStats.getTotal_recording_failures(), jicofoStats.getHost());
        checkAndCount(CounterMetric.JITSI_JICOFO_TOTAL_LIVE_STREAMING_FAILURES, jicofoStats.getTotal_live_streaming_failures(), jicofoStats.getHost());
        checkAndCount(CounterMetric.JITSI_JICOFO_TOTAL_LEAST_LOADED_IN_REGION, jicofoStats.getBridge_selector().getTotal_least_loaded_in_region(), jicofoStats.getHost());
        checkAndCount(CounterMetric.JITSI_JICOFO_TOTAL_SPLIT_DUE_TO_LOAD, jicofoStats.getBridge_selector().getTotal_split_due_to_load(), jicofoStats.getHost());
        checkAndCount(CounterMetric.JITSI_JICOFO_TOTAL_NOT_LOADED_IN_REGION_IN_CONFERENCE, jicofoStats.getBridge_selector().getTotal_not_loaded_in_region_in_conference(), jicofoStats.getHost());
        checkAndCount(CounterMetric.JITSI_JICOFO_TOTAL_LEAST_LOADED_IN_REGION_IN_CONFERENCE, jicofoStats.getBridge_selector().getTotal_least_loaded_in_region_in_conference(), jicofoStats.getHost());
        checkAndCount(CounterMetric.JITSI_JICOFO_TOTAL_NOT_LOADED_IN_REGION, jicofoStats.getBridge_selector().getTotal_not_loaded_in_region(), jicofoStats.getHost());
        checkAndCount(CounterMetric.JITSI_JICOFO_TOTAL_SPLIT_DUE_TO_REGION, jicofoStats.getBridge_selector().getTotal_split_due_to_region(), jicofoStats.getHost());
        checkAndCount(CounterMetric.JITSI_JICOFO_TOTAL_LEAST_LOADED_IN_CONFERENCE, jicofoStats.getBridge_selector().getTotal_least_loaded_in_conference(), jicofoStats.getHost());
        checkAndCount(CounterMetric.JITSI_JICOFO_TOTAL_LEAST_LOADED, jicofoStats.getBridge_selector().getTotal_least_loaded(), jicofoStats.getHost());
    }
}
