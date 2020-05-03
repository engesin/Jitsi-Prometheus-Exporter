/**
 * @author Yakup TURAN
 */

package com.ttech.jpe.metric.prometheus.type;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.prometheus.client.Collector.Type;

public enum HistogramMetric implements IMetric {


    JITSI_JVB_CONFERENCE_SIZES_HISTOGRAM("jitsi_jvb_conference_sizes_histogram", "jitsi_jvb_conference_sizes", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_CONFERENCES_BY_VIDEO_SENDERS_HISTOGRAM("jitsi_jvb_conferences_by_video_senders_histogram", "jitsi_jvb_conferences_by_video_senders", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),
    JITSI_JVB_CONFERENCES_BY_AUDIO_SENDERS_HISTOGRAM("jitsi_jvb_conferences_by_audio_senders_histogram", "jitsi_jvb_conferences_by_audio_senders", MetricLabels.getCommaSeperated(MetricLabels.Names.host)),

    JITSI_JICOFO_CONFERENCE_SIZES_HISTOGRAM("jitsi_jicofo_conference_sizes_histogram", "jitsi_jicofo_conference_sizes", MetricLabels.getCommaSeperated(MetricLabels.Names.host));


    private HashMap<MetricParamType, String> params;

    HistogramMetric(String name, String helpText, String labels, String buckets) {
        params = new HashMap<MetricParamType, String>();
        params.put(MetricParamType.NAME, name);
        params.put(MetricParamType.HELPTEXT, helpText);
        params.put(MetricParamType.BUCKET, buckets);
        if (Objects.nonNull(labels) && !labels.isEmpty()) {
            params.put(MetricParamType.LABELNAME, labels);
        }
    }

    HistogramMetric(String name, String helpText) {
        this(name, helpText, null);
    }

    HistogramMetric(String name, String helpText, String labels) {
        this(name, helpText, labels, "0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21");
    }

    public Type getMetricType() {
        return Type.HISTOGRAM;
    }

    public Map<MetricParamType, String> getParams() {
        return params;
    }


}
