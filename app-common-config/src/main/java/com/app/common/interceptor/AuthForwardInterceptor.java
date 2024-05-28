package com.app.common.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Request interceptor to forward authorization header to another service through Feign client 
 * @author wyoon
 *
 */
@Component
public class AuthForwardInterceptor implements RequestInterceptor {

    Logger logger = LoggerFactory.getLogger(AuthForwardInterceptor.class);
    
    @Override
    public void apply(RequestTemplate template) {
        logger.debug("Request Template Interceptor >>> ");
        try {
            if(RequestContextHolder.getRequestAttributes() != null) {
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                template.header(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            }
        }catch(Exception e) {
            logger.error("Failed to set Authorization header: ", e);
        }

    }

}
