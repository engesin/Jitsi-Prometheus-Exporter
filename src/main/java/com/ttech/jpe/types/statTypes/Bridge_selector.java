/**
 * @author Engin Esin
 */
package com.ttech.jpe.types.statTypes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bridge_selector {
    private Long total_least_loaded_in_region = 0L;
    private Long total_split_due_to_load = 0L;
    private Long total_not_loaded_in_region_in_conference = 0L;
    private Long total_least_loaded_in_region_in_conference = 0L;
    private Long total_not_loaded_in_region = 0L;
    private Long total_split_due_to_region = 0L;
    private Long bridge_count = 0L;
    private Long operational_bridge_count = 0L;
    private Long total_least_loaded_in_conference = 0L;
    private Long total_least_loaded = 0L;
}
