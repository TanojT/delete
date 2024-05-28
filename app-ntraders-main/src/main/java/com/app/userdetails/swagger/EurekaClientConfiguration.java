package com.app.userdetails.swagger;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(value= {"cloud"})
@EnableEurekaClient
public class EurekaClientConfiguration {
    //your configuration
}
