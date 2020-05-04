/**
 * @author Yakup TURAN
 */

package com.ttech.jpe.metric.prometheus.type;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.prometheus.client.Collector.Type;

public enum GaugeMetric implements IMetric {


    JITSI_JVB_INACTIVE_ENDPOINTS("jitsi_jvb_inactive_endpoints", "jitsi_jvb_inactive_endpoints", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_INACTIVE_CONFERENCES("jitsi_jvb_inactive_conferences", "jitsi_jvb_inactive_conferences", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_BIT_RATE_DOWNLOAD("jitsi_jvb_bit_rate_download", "jitsi_jvb_bit_rate_download", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_MUC_CLIENTS_CONNECTED("jitsi_jvb_muc_clients_connected", "jitsi_jvb_muc_clients_connected", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_RTT_AGGREGATE("jitsi_jvb_rtt_aggregate", "jitsi_jvb_rtt_aggregate", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_PACKET_RATE_UPLOAD("jitsi_jvb_packet_rate_upload", "jitsi_jvb_packet_rate_upload", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_P2P_CONFERENCES("jitsi_jvb_p2p_conferences", "jitsi_jvb_p2p_conferences", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_OCTO_SEND_BITRATE("jitsi_jvb_octo_send_bitrate", "jitsi_jvb_octo_send_bitrate", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_RECEIVE_ONLY_ENDPOINTS("jitsi_jvb_receive_only_endpoints", "jitsi_jvb_receive_only_endpoints", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_OCTO_RECEIVE_BITRATE("jitsi_jvb_octo_receive_bitrate", "jitsi_jvb_octo_receive_bitrate", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_LOSS_RATE_UPLOAD("jitsi_jvb_loss_rate_upload", "jitsi_jvb_loss_rate_upload", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),

    JITSI_JVB_LOSS_RATE_DOWNLOAD("jitsi_jvb_loss_rate_download", "jitsi_jvb_loss_rate_download", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_BIT_RATE_UPLOAD("jitsi_jvb_bit_rate_upload", "jitsi_jvb_bit_rate_upload", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_OCTO_CONFERENCES("jitsi_jvb_octo_conferences", "jitsi_jvb_octo_conferences", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_NUM_EPS_NO_MSG_TRANSPORT_AFTER_DELAY("jitsi_jvb_num_eps_no_msg_transport_after_delay", "jitsi_jvb_num_eps_no_msg_transport_after_delay", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_ENDPOINTS_SENDING_VIDEO("jitsi_jvb_endpoints_sending_video", "jitsi_jvb_endpoints_sending_video", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_PACKET_RATE_DOWNLOAD("jitsi_jvb_packet_rate_download", "jitsi_jvb_packet_rate_download", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_MUC_CLIENTS_CONFIGURED("jitsi_jvb_muc_clients_configured", "jitsi_jvb_muc_clients_configured", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_VIDEOSTREAMS("jitsi_jvb_videostreams", "jitsi_jvb_videostreams", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_JITTER_AGGREGATE("jitsi_jvb_jitter_aggregate", "jitsi_jvb_jitter_aggregate", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_OCTO_ENDPOINTS("jitsi_jvb_octo_endpoints", "jitsi_jvb_octo_endpoints", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_CURRENT_TIMESTAMP("jitsi_jvb_current_timestamp", "jitsi_jvb_current_timestamp", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_CONFERENCES("jitsi_jvb_conferences", "jitsi_jvb_conferences", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_PARTICIPANTS("jitsi_jvb_participants", "jitsi_jvb_participants", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_LARGEST_CONFERENCE("jitsi_jvb_largest_conference", "jitsi_jvb_largest_conference", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_OCTO_SEND_PACKET_RATE("jitsi_jvb_octo_send_packet_rate", "jitsi_jvb_octo_send_packet_rate", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_THREADS("jitsi_jvb_threads", "jitsi_jvb_threads", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_VIDEOCHANNELS("jitsi_jvb_videochannels", "jitsi_jvb_videochannels", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_OCTO_RECEIVE_PACKET_RATE("jitsi_jvb_octo_receive_packet_rate", "jitsi_jvb_octo_receive_packet_rate", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_RTP_LOSS("jitsi_jvb_rtp_loss", "jitsi_jvb_rtp_loss", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_ENDPOINTS_SENDING_AUDIO("jitsi_jvb_endpoints_sending_audio", "jitsi_jvb_endpoints_sending_audio", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_MUCS_CONFIGURED("jitsi_jvb_mucs_configured", "jitsi_jvb_mucs_configured", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_MUCS_JOINED("jitsi_jvb_mucs_joined", "jitsi_jvb_mucs_joined", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),


    JITSI_JVB_CONFERENCE_SIZES("jitsi_jvb_conference_sizes", "jitsi_jvb_conference_sizes", MetricLabels.getCommaSeperated(MetricLabels.Names.host, MetricLabels.Names.conferenceSize)),
    JITSI_JVB_CONFERENCES_BY_VIDEO_SENDERS("jitsi_jvb_conferences_by_video_senders", "jitsi_jvb_conferences_by_video_senders", MetricLabels.getCommaSeperated(MetricLabels.Names.host, MetricLabels.Names.conferenceSize)),
    JITSI_JVB_CONFERENCES_BY_AUDIO_SENDERS("jitsi_jvb_conferences_by_audio_senders", "jitsi_jvb_conferences_by_audio_senders", MetricLabels.getCommaSeperated(MetricLabels.Names.host, MetricLabels.Names.conferenceSize)),

    JITSI_JVB_HEALTH_STATUS("jitsi_jvb_health_status", "jitsi_jvb_conferences_by_audio_senders", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),


    JITSI_JICOFO_LARGEST_CONFERENCE("jitsi_jicofo_largest_conference", "jitsi_jicofo_largest_conference", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JICOFO_CONFERENCES("jitsi_jicofo_conferences", "jitsi_jicofo_conferences", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JICOFO_PARTICIPANTS("jitsi_jicofo_participants", "jitsi_jicofo_participants", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JICOFO_BRIDGE_COUNT("jitsi_jicofo_bridge_count", "jitsi_jicofo_bridge_count", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JICOFO_OPERATIONAL_BRIDGE_COUNT("jitsi_jicofo_operational_bridge_count", "jitsi_jicofo_operational_bridge_count", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),

    JITSI_JICOFO_CONFERENCE_SIZES("jitsi_jicofo_conference_sizes", "jitsi_jicofo_conference_sizes", MetricLabels.getCommaSeperated(MetricLabels.Names.host, MetricLabels.Names.conferenceSize)),

    JITSI_JICOFO_HEALTH_STATUS("jitsi_jicofo_health_status", "jitsi_jicofo_health_status", MetricLabels.getCommaSeperated(MetricLabels.Names.host));


    private HashMap<MetricParamType, String> params;

    private GaugeMetric(String name, String helpText, String labels) {
        params = new HashMap<MetricParamType, String>();
        params.put(MetricParamType.NAME, name);
        params.put(MetricParamType.HELPTEXT, helpText);
        if (Objects.nonNull(labels) && !labels.isEmpty()) {
            params.put(MetricParamType.LABELNAME, labels);
        }
    }

    private GaugeMetric(String name, String helpText) {
        this(name, helpText, null);
    }

    public Type getMetricType() {
        return Type.GAUGE;
    }

    public Map<MetricParamType, String> getParams() {
        return params;
    }

}