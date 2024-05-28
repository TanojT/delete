package com.app.userdetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.app"})
@EnableFeignClients(basePackages = {"com.app"})
//@EnableDiscoveryClient
public class NTradersMainServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NTradersMainServiceApplication.class, args);
    }

}
