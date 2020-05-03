/**
 * @author Yakup TURAN
 */

package com.ttech.jpe.metric.prometheus.type;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.prometheus.client.Collector.Type;

public enum SummaryMetric implements IMetric {

    VOIP_START2RINGIN_TIME("voip_Start2Ring_response_time", "Reason of ended dialogs");

    private HashMap<MetricParamType, String> params;

    private SummaryMetric(String name, String helpText, String labels, String quantiles, String timeWindow) {
        params = new HashMap<MetricParamType, String>();
        params.put(MetricParamType.NAME, name);
        params.put(MetricParamType.HELPTEXT, helpText);
        params.put(MetricParamType.QUANTILE, quantiles);
        params.put(MetricParamType.TIMEWINDOWSECS, timeWindow);
        if (Objects.nonNull(labels) && !labels.isEmpty()) {
            params.put(MetricParamType.LABELNAME, labels);
        }
    }

    private SummaryMetric(String name, String helpText) {
        this(name, helpText, null);
    }

    private SummaryMetric(String name, String helpText, String labels) {
        this(name, helpText, labels, "0.05,0.001; 0.1,0.05; 0.5,0.05; 0.9,0.01; 0.99,0.001", "300");
    }

    public Map<MetricParamType, String> getParams() {
        return params;
    }

    public Type getMetricType() {
        return Type.SUMMARY;
    }

}
