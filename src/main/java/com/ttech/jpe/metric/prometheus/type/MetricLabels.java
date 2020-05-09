package com.ttech.jpe.metric.prometheus.type;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MetricLabels {
    public static final String getCommaSeperated(Names... names) {
        StringBuilder sb = new StringBuilder();
        for (Names name : names) {
            sb.append(name.toString()).append(";");
        }
        return sb.toString();
    }

    public enum Names {
        host,
        conferenceSize,
        reason,
        heath
    }

}



