package com.ttech.jpe.metric.prometheus.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ttahkarakaya on 18/04/2018.
 */

@Component
@Endpoint(id = "lbcheck")
public class LbCheck {


    @Autowired
    HttpServletRequest request;

    @ReadOperation
    public String getMetrics() {
        return "UP";
    }

    @WriteOperation
    public void writeOperation(@Selector String name) {
        //perform write operation
    }

    @DeleteOperation
    public void deleteOperation(@Selector String name) {
        //delete operation
    }

}



