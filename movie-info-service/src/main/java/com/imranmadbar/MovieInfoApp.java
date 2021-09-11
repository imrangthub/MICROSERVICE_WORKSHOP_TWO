package com.imranmadbar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class MovieInfoApp {

    public static void main(String[] args) {
        SpringApplication.run(MovieInfoApp.class, args);
    }
}


