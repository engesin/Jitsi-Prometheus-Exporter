/**
 * @author Yakup TURAN
 */

package com.ttech.jpe.metric.prometheus.type;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.prometheus.client.Collector.Type;

public enum CounterMetric implements IMetric {


    JITSI_JVB_TOTAL_ICE_SUCCEEDED_RELAYED("jitsi_jvb_total_ice_succeeded_relayed", "jitsi_jvb_total_ice_succeeded_relayed", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_LOSS_DEGRADED_PARTICIPANT_SECONDS("jitsi_jvb_total_loss_degraded_participant_seconds", "jitsi_jvb_total_loss_degraded_participant_seconds", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_PARTICIPANTS("jitsi_jvb_total_participants", "jitsi_jvb_total_participants", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_PACKETS_RECEIVED("jitsi_jvb_total_packets_received", "jitsi_jvb_total_packets_received", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_LOSS_LIMITED_PARTICIPANT_SECONDS("jitsi_jvb_total_loss_limited_participant_seconds", "jitsi_jvb_total_loss_limited_participant_seconds", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_DOMINANT_SPEAKER_CHANGES("jitsi_jvb_total_dominant_speaker_changes", "jitsi_jvb_total_dominant_speaker_changes", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_COLIBRI_WEB_SOCKET_MESSAGES_RECEIVED("jitsi_jvb_total_colibri_web_socket_messages_received", "jitsi_jvb_total_colibri_web_socket_messages_received", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_ICE_SUCCEEDED("jitsi_jvb_total_ice_succeeded", "jitsi_jvb_total_ice_succeeded", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_COLIBRI_WEB_SOCKET_MESSAGES_SENT("jitsi_jvb_total_colibri_web_socket_messages_sent", "jitsi_jvb_total_colibri_web_socket_messages_sent", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_BYTES_SENT_OCTO("jitsi_jvb_total_bytes_sent_octo", "jitsi_jvb_total_bytes_sent_octo", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_DATA_CHANNEL_MESSAGES_RECEIVED("jitsi_jvb_total_data_channel_messages_received", "jitsi_jvb_total_data_channel_messages_received", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_CONFERENCE_SECONDS("jitsi_jvb_total_conference_seconds", "jitsi_jvb_total_conference_seconds", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_CONFERENCES_COMPLETED("jitsi_jvb_total_conferences_completed", "jitsi_jvb_total_conferences_completed", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_PACKETS_SENT_OCTO("jitsi_jvb_total_packets_sent_octo", "jitsi_jvb_total_packets_sent_octo", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_ICE_SUCCEEDED_TCP("jitsi_jvb_total_ice_succeeded_tcp", "jitsi_jvb_total_ice_succeeded_tcp", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_PACKETS_DROPPED_OCTO("jitsi_jvb_total_packets_dropped_octo", "jitsi_jvb_total_packets_dropped_octo", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_PACKETS_SENT("jitsi_jvb_total_packets_sent", "jitsi_jvb_total_packets_sent", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_DATA_CHANNEL_MESSAGES_SENT("jitsi_jvb_total_data_channel_messages_sent", "jitsi_jvb_total_data_channel_messages_sent", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_BYTES_RECEIVED_OCTO("jitsi_jvb_total_bytes_received_octo", "jitsi_jvb_total_bytes_received_octo", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_CONFERENCES_CREATED("jitsi_jvb_total_conferences_created", "jitsi_jvb_total_conferences_created", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_ICE_FAILED("jitsi_jvb_total_ice_failed", "jitsi_jvb_total_ice_failed", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_PACKETS_RECEIVED_OCTO("jitsi_jvb_total_packets_received_octo", "jitsi_jvb_total_packets_received_octo", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_BYTES_RECEIVED("jitsi_jvb_total_bytes_received", "jitsi_jvb_total_bytes_received", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_LOSS_CONTROLLED_PARTICIPANT_SECONDS("jitsi_jvb_total_loss_controlled_participant_seconds", "jitsi_jvb_total_loss_controlled_participant_seconds", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_PARTIALLY_FAILED_CONFERENCES("jitsi_jvb_total_partially_failed_conferences", "jitsi_jvb_total_partially_failed_conferences", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_BYTES_SENT("jitsi_jvb_total_bytes_sent", "jitsi_jvb_total_bytes_sent", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_TOTAL_FAILED_CONFERENCES("jitsi_jvb_total_failed_conferences", "jitsi_jvb_total_failed_conferences", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),


    JITSI_JICOFO_TOTAL_SIP_CALL_FAILURES("jitsi_jicofo_total_sip_call_failures", "jitsi_jicofo_total_sip_call_failures", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JICOFO_TOTAL_PARTICIPANTS("jitsi_jicofo_total_participants", "jitsi_jicofo_total_participants", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JICOFO_TOTAL_CONFERENCES_CREATED("jitsi_jicofo_total_conferences_created", "jitsi_jicofo_total_conferences_created", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JICOFO_TOTAL_RECORDING_FAILURES("jitsi_jicofo_total_recording_failures", "jitsi_jicofo_total_recording_failures", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JICOFO_TOTAL_LIVE_STREAMING_FAILURES("jitsi_jicofo_total_live_streaming_failures", "jitsi_jicofo_total_live_streaming_failures", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JICOFO_TOTAL_LEAST_LOADED_IN_REGION("jitsi_jicofo_total_least_loaded_in_region", "jitsi_jicofo_total_least_loaded_in_region", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JICOFO_TOTAL_SPLIT_DUE_TO_LOAD("jitsi_jicofo_total_split_due_to_load", "jitsi_jicofo_total_split_due_to_load", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JICOFO_TOTAL_NOT_LOADED_IN_REGION_IN_CONFERENCE("jitsi_jicofo_total_not_loaded_in_region_in_conference", "jitsi_jicofo_total_not_loaded_in_region_in_conference", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JICOFO_TOTAL_LEAST_LOADED_IN_REGION_IN_CONFERENCE("jitsi_jicofo_total_least_loaded_in_region_in_conference", "jitsi_jicofo_total_least_loaded_in_region_in_conference", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JICOFO_TOTAL_NOT_LOADED_IN_REGION("jitsi_jicofo_total_not_loaded_in_region", "jitsi_jicofo_total_not_loaded_in_region", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JICOFO_TOTAL_SPLIT_DUE_TO_REGION("jitsi_jicofo_total_split_due_to_region", "jitsi_jicofo_total_split_due_to_region", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JICOFO_TOTAL_LEAST_LOADED_IN_CONFERENCE("jitsi_jicofo_total_least_loaded_in_conference", "jitsi_jicofo_total_least_loaded_in_conference", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JICOFO_TOTAL_LEAST_LOADED("jitsi_jicofo_total_least_loaded", "jitsi_jicofo_total_least_loaded", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),

    JITSI_JPE_UNSUCCESSFUL_DATA_FETCH("jitsi_jpe_unsuccessful_data_fetch", "JPE cannot get data from remote", MetricLabels.getCommaSeperated(MetricLabels.Names.host, MetricLabels.Names.reason, MetricLabels.Names.heath));



    private HashMap<MetricParamType, String> params;

    private CounterMetric(String name, String helpText) {
        this(name, helpText, null);
    }

    private CounterMetric(String name, String helpText, String labels) {
        params = new HashMap<MetricParamType, String>();
        params.put(MetricParamType.NAME, name);
        params.put(MetricParamType.HELPTEXT, helpText);
        if (Objects.nonNull(labels) && !labels.isEmpty()) {
            params.put(MetricParamType.LABELNAME, labels);
        }
    }

    public Type getMetricType() {
        return Type.COUNTER;
    }

    public Map<MetricParamType, String> getParams() {
        return params;
    }

}
