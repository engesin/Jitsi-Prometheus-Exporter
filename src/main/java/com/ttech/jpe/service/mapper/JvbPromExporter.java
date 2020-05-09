/**
 * @author Engin Esin
 */

package com.ttech.jpe.service.mapper;

import com.ttech.jpe.metric.prometheus.type.CounterMetric;
import com.ttech.jpe.metric.prometheus.type.GaugeMetric;
import com.ttech.jpe.metric.prometheus.type.HistogramMetric;
import com.ttech.jpe.types.statTypes.JvbStats;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.stream.IntStream;

@Service
@Slf4j
public class JvbPromExporter extends PromExporterAbstract<JvbStats> {

    private static final JvbStats JVB_STATS = new JvbStats();


    @Override
    JvbStats metricMapper(String metricJsonBody, boolean hostHealthy, URL sourceURL) {

        JvbStats stats = null;
        try {

            stats = objectMapper.readValue(metricJsonBody, JvbStats.class);

            stats.setHealth(hostHealthy);
            stats.setHost(setHostParam(sourceURL));

        } catch (IOException e) {
            log.error("Object Mapping Exception for url: " + sourceURL + " body: " + metricJsonBody, e);
        }


        return stats;
    }


    @Override
    public void feedMetrics(JvbStats statistics) {

        if(statistics == JVB_STATS){
            metricsRegistry.observe(GaugeMetric.JITSI_JVB_HEALTH_STATUS, statistics.isHealth() ? 1 : 0, statistics.getHost());
            metricsRegistry.observe(CounterMetric.JITSI_JPE_UNSUCCESSFUL_DATA_FETCH, 1,  statistics.getHost(), "JVB" ,String.valueOf(statistics.isHealth() ));
            return;
        }

        counters(statistics);

        gauges(statistics);

        histogram(statistics);

    }

    private void histogram(JvbStats jvbStats) {

        IntStream.range(0, jvbStats.getConference_sizes().size())
                .forEach(size -> IntStream.range(0, jvbStats.getConference_sizes().get(size))
                        .forEach(group -> metricsRegistry.observe(HistogramMetric.JITSI_JVB_CONFERENCE_SIZES_HISTOGRAM,
                                size, jvbStats.getHost())));

        IntStream.range(0, jvbStats.getConferences_by_video_senders().size())
                .forEach(size -> IntStream.range(0, jvbStats.getConferences_by_video_senders().get(size))
                        .forEach(group -> metricsRegistry.observe(HistogramMetric.JITSI_JVB_CONFERENCES_BY_VIDEO_SENDERS_HISTOGRAM,
                                size, jvbStats.getHost())));

        IntStream.range(0, jvbStats.getConferences_by_audio_senders().size())
                .forEach(size -> IntStream.range(0, jvbStats.getConferences_by_audio_senders().get(size))
                        .forEach(group -> metricsRegistry.observe(HistogramMetric.JITSI_JVB_CONFERENCES_BY_AUDIO_SENDERS_HISTOGRAM,
                                size, jvbStats.getHost())));


    }

    @Override
    JvbStats getDefaultInstance(URL sourceURL, boolean hostHealthy) {

        JVB_STATS.setHost(setHostParam(sourceURL));
        JVB_STATS.setHealth(hostHealthy);
        return JVB_STATS;
    }

    private void gauges(JvbStats jvbStats) {


        metricsRegistry.observe(GaugeMetric.JITSI_JVB_INACTIVE_ENDPOINTS, jvbStats.getInactive_endpoints(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_INACTIVE_CONFERENCES, jvbStats.getInactive_conferences(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_BIT_RATE_DOWNLOAD, jvbStats.getBit_rate_download(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_MUC_CLIENTS_CONNECTED, jvbStats.getMuc_clients_connected(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_RTT_AGGREGATE, jvbStats.getRtt_aggregate(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_PACKET_RATE_UPLOAD, jvbStats.getPacket_rate_upload(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_P2P_CONFERENCES, jvbStats.getP2p_conferences(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_OCTO_SEND_BITRATE, jvbStats.getOcto_send_bitrate(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_RECEIVE_ONLY_ENDPOINTS, jvbStats.getReceive_only_endpoints(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_OCTO_RECEIVE_BITRATE, jvbStats.getOcto_receive_bitrate(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_LOSS_RATE_UPLOAD, jvbStats.getLoss_rate_upload(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_LOSS_RATE_DOWNLOAD, jvbStats.getLoss_rate_download(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_BIT_RATE_UPLOAD, jvbStats.getBit_rate_upload(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_OCTO_CONFERENCES, jvbStats.getOcto_conferences(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_NUM_EPS_NO_MSG_TRANSPORT_AFTER_DELAY, jvbStats.getNum_eps_no_msg_transport_after_delay(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_ENDPOINTS_SENDING_VIDEO, jvbStats.getEndpoints_sending_video(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_PACKET_RATE_DOWNLOAD, jvbStats.getPacket_rate_download(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_MUC_CLIENTS_CONFIGURED, jvbStats.getMuc_clients_configured(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_VIDEOSTREAMS, jvbStats.getVideostreams(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_JITTER_AGGREGATE, jvbStats.getJitter_aggregate(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_OCTO_ENDPOINTS, jvbStats.getOcto_endpoints(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_CONFERENCES, jvbStats.getConferences(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_PARTICIPANTS, jvbStats.getParticipants(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_LARGEST_CONFERENCE, jvbStats.getLargest_conference(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_OCTO_SEND_PACKET_RATE, jvbStats.getOcto_send_packet_rate(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_THREADS, jvbStats.getThreads(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_VIDEOCHANNELS, jvbStats.getVideochannels(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_OCTO_RECEIVE_PACKET_RATE, jvbStats.getOcto_receive_packet_rate(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_RTP_LOSS, jvbStats.getRtp_loss(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_ENDPOINTS_SENDING_AUDIO, jvbStats.getEndpoints_sending_audio(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_MUCS_CONFIGURED, jvbStats.getMucs_configured(), jvbStats.getHost());
        metricsRegistry.observe(GaugeMetric.JITSI_JVB_MUCS_JOINED, jvbStats.getMucs_joined(), jvbStats.getHost());


        //iterate through array and feed related conf size to gauge metric
        IntStream.range(0, jvbStats.getConference_sizes().size())
                .forEach(index -> {
                    String confSize = index == jvbStats.getConference_sizes().size() - 1 ? index + "+" : index + "";
                    metricsRegistry.observe(GaugeMetric.JITSI_JVB_CONFERENCE_SIZES, jvbStats.getConference_sizes().get(index), jvbStats.getHost(), confSize);
                });

        //iterate through array and feed related conf size to gauge metric
        IntStream.range(0, jvbStats.getConferences_by_video_senders().size())
                .forEach(index -> {
                    String confSize = index == jvbStats.getConferences_by_video_senders().size() - 1 ? index + "+" : index + "";
                    metricsRegistry.observe(GaugeMetric.JITSI_JVB_CONFERENCES_BY_VIDEO_SENDERS, jvbStats.getConferences_by_video_senders().get(index), jvbStats.getHost(), confSize);
                });

        //iterate through array and feed related conf size to gauge metric
        IntStream.range(0, jvbStats.getConferences_by_audio_senders().size())
                .forEach(index -> {
                    String confSize = index == jvbStats.getConferences_by_audio_senders().size() - 1 ? index + "+" : index + "";
                    metricsRegistry.observe(GaugeMetric.JITSI_JVB_CONFERENCES_BY_AUDIO_SENDERS, jvbStats.getConferences_by_audio_senders().get(index), jvbStats.getHost(), confSize);
                });

        metricsRegistry.observe(GaugeMetric.JITSI_JVB_HEALTH_STATUS, jvbStats.isHealth() ? 1 : 0, jvbStats.getHost());


    }

    private void counters(JvbStats jvbStats) {

        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_ICE_SUCCEEDED_RELAYED, jvbStats.getTotal_ice_succeeded_relayed(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_LOSS_DEGRADED_PARTICIPANT_SECONDS, jvbStats.getTotal_loss_degraded_participant_seconds(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_PARTICIPANTS, jvbStats.getTotal_participants(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_PACKETS_RECEIVED, jvbStats.getTotal_packets_received(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_LOSS_LIMITED_PARTICIPANT_SECONDS, jvbStats.getTotal_loss_limited_participant_seconds(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_DOMINANT_SPEAKER_CHANGES, jvbStats.getTotal_dominant_speaker_changes(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_COLIBRI_WEB_SOCKET_MESSAGES_RECEIVED, jvbStats.getTotal_colibri_web_socket_messages_received(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_ICE_SUCCEEDED, jvbStats.getTotal_ice_succeeded(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_COLIBRI_WEB_SOCKET_MESSAGES_SENT, jvbStats.getTotal_colibri_web_socket_messages_sent(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_BYTES_SENT_OCTO, jvbStats.getTotal_bytes_sent_octo(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_DATA_CHANNEL_MESSAGES_RECEIVED, jvbStats.getTotal_data_channel_messages_received(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_CONFERENCE_SECONDS, jvbStats.getTotal_conference_seconds(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_CONFERENCES_COMPLETED, jvbStats.getTotal_conferences_completed(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_PACKETS_SENT_OCTO, jvbStats.getTotal_packets_sent_octo(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_ICE_SUCCEEDED_TCP, jvbStats.getTotal_ice_succeeded_tcp(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_PACKETS_DROPPED_OCTO, jvbStats.getTotal_packets_dropped_octo(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_PACKETS_SENT, jvbStats.getTotal_packets_sent(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_DATA_CHANNEL_MESSAGES_SENT, jvbStats.getTotal_data_channel_messages_sent(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_BYTES_RECEIVED_OCTO, jvbStats.getTotal_bytes_received_octo(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_ICE_FAILED, jvbStats.getTotal_ice_failed(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_PACKETS_RECEIVED_OCTO, jvbStats.getTotal_packets_received_octo(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_BYTES_RECEIVED, jvbStats.getTotal_bytes_received(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_LOSS_CONTROLLED_PARTICIPANT_SECONDS, jvbStats.getTotal_loss_controlled_participant_seconds(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_PARTIALLY_FAILED_CONFERENCES, jvbStats.getTotal_partially_failed_conferences(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_BYTES_SENT, jvbStats.getTotal_bytes_sent(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_FAILED_CONFERENCES, jvbStats.getTotal_failed_conferences(), jvbStats.getHost());
        checkAndCount(CounterMetric.JITSI_JVB_TOTAL_CONFERENCES_CREATED, jvbStats.getTotal_conferences_created(), jvbStats.getHost());

    }


}

