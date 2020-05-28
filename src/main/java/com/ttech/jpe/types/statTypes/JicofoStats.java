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
public class JicofoStats {
    private String host = "";
    private boolean health = false;

    private Long largest_conference = 0L;
    private Long total_sip_call_failures = 0L;
    private Long total_participants = 0L;
    private List<Integer> conference_sizes = new ArrayList<>(Collections.nCopies(22, 0));
    private Bridge_selector bridge_selector = new Bridge_selector();
    private Long total_conferences_created = 0L;
    private Long total_recording_failures = 0L;
    private Long conferences = 0L;
    private Long total_live_streaming_failures = 0L;
    private Long participants = 0L;

    public void setConference_sizes(Object value) {
        if (value instanceof List && ((List) value).size() == 0)
            this.conference_sizes = (List<Integer>) value;
        else
            this.conference_sizes = new ArrayList<>(Collections.nCopies(22, 0));
    }
}
