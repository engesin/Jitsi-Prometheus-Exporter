/**
 * @author Engin Esin
 */

package com.ttech.jpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class JpeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpeApplication.class, args);
    }


}
