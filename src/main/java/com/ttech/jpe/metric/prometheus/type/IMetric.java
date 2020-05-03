package com.ttech.jpe.metric.prometheus.type;

import java.util.Map;

import static io.prometheus.client.Collector.Type;

public interface IMetric {

    Map<MetricParamType, String> getParams();

    Type getMetricType();
}
