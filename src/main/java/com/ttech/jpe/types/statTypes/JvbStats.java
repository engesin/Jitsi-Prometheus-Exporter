/**
 * @author Engin Esin
 */
package com.ttech.jpe.types.statTypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JvbStats {
    private String host = "";
    private boolean health = false;

    private long inactive_endpoints;
    private long inactive_conferences;
    private long total_ice_succeeded_relayed;
    private long total_loss_degraded_participant_seconds;
    private long bit_rate_download;
    private long muc_clients_connected;
    private long total_participants;
    private long total_packets_received;
    private double rtt_aggregate;
    private long packet_rate_upload;
    private long p2p_conferences;
    private long total_loss_limited_participant_seconds;
    private long octo_send_bitrate;
    private long total_dominant_speaker_changes;
    private long receive_only_endpoints;
    private long total_colibri_web_socket_messages_received;
    private long octo_receive_bitrate;
    private double loss_rate_upload;
    private String version;
    private long total_ice_succeeded;
    private long total_colibri_web_socket_messages_sent;
    private long total_bytes_sent_octo;
    private long total_data_channel_messages_received;
    private double loss_rate_download;
    private long total_conference_seconds;
    private long bit_rate_upload;
    private long total_conferences_completed;
    private long octo_conferences;
    private long num_eps_no_msg_transport_after_delay;
    private long endpoints_sending_video;
    private long packet_rate_download;
    private long muc_clients_configured;
    private List<Integer> conference_sizes = new ArrayList<>(Collections.nCopies(22, 0));
    private long total_packets_sent_octo;
    private List<Integer> conferences_by_video_senders = new ArrayList<>(Collections.nCopies(22, 0));
    private double stress_level;
    private double jitter_aggregate;
    private long total_ice_succeeded_tcp;
    private long octo_endpoints;
    private String current_timestamp;
    private long total_packets_dropped_octo;
    private long conferences;
    private long participants;
    private long largest_conference;
    private long total_packets_sent;
    private long total_data_channel_messages_sent;
    private long total_bytes_received_octo;
    private long octo_send_packet_rate;
    private List<Integer> conferences_by_audio_senders = new ArrayList<>(Collections.nCopies(22, 0));
    private long total_conferences_created;
    private long total_ice_failed;
    private long threads;
    private long videochannels;
    private long total_packets_received_octo;
    private boolean graceful_shutdown;
    private long octo_receive_packet_rate;
    private long total_bytes_received;
    private double rtp_loss;
    private long total_loss_controlled_participant_seconds;
    private long total_partially_failed_conferences;
    private long endpoints_sending_audio;
    private long dtls_failed_endpoints;
    private long total_bytes_sent;
    private long mucs_configured;
    private long total_failed_conferences;
    private long mucs_joined;


    public void setConference_sizes(Object value) {
        if (value instanceof List && ((List) value).size() > 0)
            this.conference_sizes = (List<Integer>) value;
        else
            this.conference_sizes = new ArrayList<>(Collections.nCopies(22, 0));;

    }

    public void setConferences_by_video_senders(Object value) {
        if (value instanceof List && ((List) value).size() > 0)
            this.conferences_by_video_senders = (List<Integer>) value;
        else
            this.conferences_by_video_senders = new ArrayList<>(Collections.nCopies(22, 0));;
    }

    public void setConferences_by_audio_senders(Object value) {
        if (value instanceof List && ((List) value).size() > 0)
            this.conferences_by_audio_senders = (List<Integer>) value;
        else
            this.conferences_by_audio_senders = new ArrayList<>(Collections.nCopies(22, 0));;
    }

}
