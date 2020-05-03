package com.ttech.jpe.metric.prometheus.observe;

import com.ttech.jpe.metric.prometheus.type.*;
import io.prometheus.client.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Component
public class MetricsRegistry {


    private Map<CounterMetric, Counter> counterMetricsList;
    private Map<GaugeMetric, Gauge> gaugeMetricsList;
    private Map<SummaryMetric, Summary> summaryMetricsList;
    private Map<HistogramMetric, Histogram> histogramMetricsList;


    public MetricsRegistry(CollectorRegistry registry) {


        counterMetricsList = new HashMap<>();
        stream(CounterMetric.values()).forEach(metric -> {

            Counter.Builder counterBuilder = Counter.build()
                    .name(metric.getParams().get(MetricParamType.NAME))
                    .help(metric.getParams().get(MetricParamType.HELPTEXT));


            String labels = metric.getParams().get(MetricParamType.LABELNAME);
            if (!StringUtils.isEmpty(labels))
                counterBuilder.labelNames(metric.getParams().get(MetricParamType.LABELNAME).split(";"));

            Counter counter = counterBuilder.register(registry);
            counterMetricsList.put(metric, counter);


        });


        gaugeMetricsList = new HashMap<>();
        stream(GaugeMetric.values()).forEach(metric -> {
            Gauge.Builder gaugeBuilder = Gauge.build()
                    .name(metric.getParams().get(MetricParamType.NAME))
                    .help(metric.getParams().get(MetricParamType.HELPTEXT));


            String labels = metric.getParams().get(MetricParamType.LABELNAME);
            if (!StringUtils.isEmpty(labels))
                gaugeBuilder.labelNames(metric.getParams().get(MetricParamType.LABELNAME).split(";"));

            Gauge gauge = gaugeBuilder.register(registry);

            gaugeMetricsList.put(metric, gauge);
        });


        summaryMetricsList = new HashMap<>();
        stream(SummaryMetric.values()).forEach(metric -> {
            List<String[]> quantileList = stream(metric.getParams().get(MetricParamType.QUANTILE).split(";"))
                    .map(s -> s.split(","))
                    .collect(Collectors.toList());
            Summary.Builder summaryBuilder = Summary.build()
                    .name(metric.getParams().get(MetricParamType.NAME))
                    .help(metric.getParams().get(MetricParamType.HELPTEXT))
                    .maxAgeSeconds(Long.parseLong(metric.getParams().get(MetricParamType.TIMEWINDOWSECS)));
            String labels = metric.getParams().get(MetricParamType.LABELNAME);
            if (!StringUtils.isEmpty(labels))
                summaryBuilder.labelNames(labels.split(";"));
            quantileList.forEach(quantile -> summaryBuilder.quantile(Double.parseDouble(quantile[0]), Double.parseDouble(quantile[1])));
            Summary summary = summaryBuilder.register(registry);
            summaryMetricsList.put(metric, summary);
        });


        histogramMetricsList = new HashMap<>();
        stream(HistogramMetric.values()).forEach(metric -> {
            double[] buckets = stream(metric.getParams().get(MetricParamType.BUCKET).split(","))
                    .mapToDouble(Double::parseDouble)
                    .toArray();

            Histogram.Builder histogramBuilder = Histogram.build()
                    .name(metric.getParams().get(MetricParamType.NAME))
                    .help(metric.getParams().get(MetricParamType.HELPTEXT))
                    .buckets(buckets);


            String labels = metric.getParams().get(MetricParamType.LABELNAME);
            if (!StringUtils.isEmpty(labels))
                histogramBuilder.labelNames(metric.getParams().get(MetricParamType.LABELNAME).split(";"));

            Histogram histogram = histogramBuilder.register(registry);

            histogramMetricsList.put(metric, histogram);
        });

    }


    public void observe(IMetric metric, double val, String... labels) {

        boolean hasLabels = labels != null;

        switch (metric.getMetricType()) {
            case GAUGE:
                if (hasLabels)
                    gaugeMetricsList.get(metric).labels(labels).set(val);
                else
                    gaugeMetricsList.get(metric).set(val);
                break;
            case HISTOGRAM:
                if (hasLabels)
                    histogramMetricsList.get(metric).labels(labels).observe(val);
                else
                    histogramMetricsList.get(metric).observe(val);
                break;
            case SUMMARY:
                if (hasLabels)
                    summaryMetricsList.get(metric).labels(labels).observe(val);
                else
                    summaryMetricsList.get(metric).observe(val);
                break;
            case COUNTER:
                if (hasLabels)
                    counterMetricsList.get(metric).labels(labels).inc(val);
                else
                    counterMetricsList.get(metric).inc(val);
                break;
        }

    }

    public double getCounterValue(CounterMetric metric, String... labels) {

        if (labels != null)
            return counterMetricsList.get(metric).labels(labels).get();
        else
            return counterMetricsList.get(metric).get();
    }

    public void clearMetric(CounterMetric metric) {
        counterMetricsList.get(metric).clear();
    }
}
