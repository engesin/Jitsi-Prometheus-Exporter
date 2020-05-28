/**
 * @author Engin Esin
 */

package com.ttech.jpe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@ComponentScan
public class AppConfig {
    Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        String CONFIG_HOME = System.getProperty("CONFIG_HOME", "./conf");

        PropertySourcesPlaceholderConfigurer proprty = new PropertySourcesPlaceholderConfigurer();
        proprty.setIgnoreUnresolvablePlaceholders(true);
        proprty.setLocalOverride(true);
        proprty.setLocations(new ClassPathResource("application.properties"),
                new FileSystemResource(CONFIG_HOME + "/app-override.properties"));

        return proprty;
    }

    @Bean(name = "AsyncRestExecutor")
    public Executor getAsyncExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }

}
