package com.imranmadbar;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class HomeController {

    @Autowired
    private RestTemplate restTemplate;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/")
    public String index() {
        System.out.println("Welcome to Microservice Service One !");
        return "Welcome to Microservice Service One !";
    }

    @GetMapping("/home")
    public String helloFromHome() {
        System.out.println("Hello from Home !");
        return "Hello from Home !";
    }

}
