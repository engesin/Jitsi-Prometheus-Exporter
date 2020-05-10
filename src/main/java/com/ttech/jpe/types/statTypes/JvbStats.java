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

    private long inactive_endpoints = 0L;
    private long inactive_conferences = 0L;
    private long total_ice_succeeded_relayed = 0L;
    private long total_loss_degraded_participant_seconds = 0L;
    private long bit_rate_download = 0L;
    private long muc_clients_connected = 0L;
    private long total_participants = 0L;
    private long total_packets_received = 0L;
    private long rtt_aggregate = 0L;
    private long packet_rate_upload = 0L;
    private long p2p_conferences = 0L;
    private long total_loss_limited_participant_seconds = 0L;
    private long octo_send_bitrate = 0L;
    private long total_dominant_speaker_changes = 0L;
    private long receive_only_endpoints = 0L;
    private long total_colibri_web_socket_messages_received = 0L;
    private long octo_receive_bitrate = 0L;
    private long loss_rate_upload = 0L;
    private String version = "";
    private long total_ice_succeeded = 0L;
    private long total_colibri_web_socket_messages_sent = 0L;
    private long total_bytes_sent_octo = 0L;
    private long total_data_channel_messages_received = 0L;
    private long loss_rate_download = 0L;
    private long total_conference_seconds = 0L;
    private long bit_rate_upload = 0L;
    private long total_conferences_completed = 0L;
    private long octo_conferences = 0L;
    private long num_eps_no_msg_transport_after_delay = 0L;
    private long endpoints_sending_video = 0L;
    private long packet_rate_download = 0L;
    private long muc_clients_configured = 0L;
    private List<Integer> conference_sizes = new ArrayList<>(Collections.nCopies(22, 0));
    private long total_packets_sent_octo = 0L;
    private List<Integer> conferences_by_video_senders = new ArrayList<>(Collections.nCopies(22, 0));
    private long videostreams = 0L;
    private long jitter_aggregate = 0L;
    private long total_ice_succeeded_tcp = 0L;
    private long octo_endpoints = 0L;
    private String current_timestamp = "";
    private long total_packets_dropped_octo = 0L;
    private long conferences = 0L;
    private long participants = 0L;
    private long largest_conference = 0L;
    private long total_packets_sent = 0L;
    private long total_data_channel_messages_sent = 0L;
    private long total_bytes_received_octo = 0L;
    private long octo_send_packet_rate = 0L;
    private List<Integer> conferences_by_audio_senders = new ArrayList<>(Collections.nCopies(22, 0));
    private long total_conferences_created = 0L;
    private long total_ice_failed = 0L;
    private long threads = 0L;
    private long videochannels = 0L;
    private long total_packets_received_octo = 0L;
    private boolean graceful_shutdown = false;
    private long octo_receive_packet_rate = 0L;
    private long total_bytes_received = 0L;
    private long rtp_loss = 0L;
    private long total_loss_controlled_participant_seconds = 0L;
    private long total_partially_failed_conferences = 0L;
    private long endpoints_sending_audio = 0L;
    private long total_bytes_sent = 0L;
    private long mucs_configured = 0L;
    private long total_failed_conferences = 0L;
    private long mucs_joined = 0L;

}
