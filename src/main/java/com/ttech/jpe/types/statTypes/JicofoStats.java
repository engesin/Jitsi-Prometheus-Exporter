/**
 * @author Engin Esin
 */
package com.ttech.jpe.types.statTypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JicofoStats {
    private String host = "";
    private boolean health = false;

    private Long largest_conference = 0L;
    private Long total_sip_call_failures = 0L;
    private Long total_participants = 0L;
    private List<Integer> conference_sizes = new ArrayList<>();
    private Bridge_selector bridge_selector = new Bridge_selector();
    private Long total_conferences_created = 0L;
    private Long total_recording_failures = 0L;
    private Long conferences = 0L;
    private Long total_live_streaming_failures = 0L;
    private Long participants = 0L;
}
