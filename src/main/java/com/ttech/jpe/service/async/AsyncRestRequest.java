/**
 * @author Engin Esin
 */

package com.ttech.jpe.service.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class AsyncRestRequest {

    @Value("${connectionTimeout:2000}")
    int connectionTimeout;

    @Value("${readTimeout:2000}")
    int readTimeout;

    private RestTemplate restTemplate;


    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory factory = ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory());
        factory.setConnectTimeout(connectionTimeout);
        factory.setReadTimeout(readTimeout);
    }

    /**
     * Does async Get Request
     *
     * @param url: url of the rest api
     * @return future job
     */
    @Async("AsyncRestExecutor")
    public CompletableFuture<ResponseEntity<String>> asyncRestRequest(URL url) {

        try {
            return CompletableFuture.completedFuture(restTemplate.exchange(url.toString(), HttpMethod.GET, null, String.class));
        } catch (RestClientException e) {
            log.error("RestClientException at URL: " + url.toString(), e);
            return CompletableFuture.completedFuture(new ResponseEntity<String>(HttpStatus.REQUEST_TIMEOUT));
        }
    }

    /**
     * dummy function for testing async mechanism
     *
     * @param delay: milliseconds
     * @return future job
     */
    @Async("AsyncRestExecutor")
    public CompletableFuture<Integer> jobWithDelay(int delay) {

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //log.info("done!");
        return CompletableFuture.completedFuture(delay);
    }

    public ResponseEntity<String> getStringResponseEntity(CompletableFuture<ResponseEntity<String>> res) {

        ResponseEntity<String> httpResponse;

        try {
            httpResponse = res.get();
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            httpResponse = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ExecutionException e) {
            log.error(e.getMessage());
            httpResponse = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return httpResponse;
    }


}
