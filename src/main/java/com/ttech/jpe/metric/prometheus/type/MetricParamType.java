package com.ttech.jpe.metric.prometheus.type;

public enum MetricParamType {
    NAME,
    HELPTEXT,
    LABELNAME,
    BUCKET,
    QUANTILE,
    TIMEWINDOWSECS;

    private MetricParamType() {
    }
}
