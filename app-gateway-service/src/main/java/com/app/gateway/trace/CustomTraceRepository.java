/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.gateway.trace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author schigullapally
 */
@Slf4j
@Repository
public class CustomTraceRepository extends InMemoryHttpTraceRepository{
    public CustomTraceRepository() {
        super();
    }
    
    @Override
    public void add(HttpTrace trace) {
        if(trace==null) return;
        super.add(trace);
        if(trace.getRequest()!=null) {
            log.info("HttpTrace Request Method: {}", trace.getRequest().getMethod() );
            log.info("HttpTrace Request Headers: {}", trace.getRequest().getHeaders() );
            log.info("HttpTrace Request RemoteAddress: {}", trace.getRequest().getRemoteAddress() );
            log.info("HttpTrace Request URI: {}", trace.getRequest().getUri() );
        }
        if(trace.getResponse()!=null) {
            log.info("HttpTrace Response Headers: {}", trace.getResponse().getHeaders() );
            log.info("HttpTrace Response Status: {}", trace.getResponse().getStatus() );
        }
        log.info("HttpTrace Time Taken: {}", trace.getTimeTaken());
    }
}
