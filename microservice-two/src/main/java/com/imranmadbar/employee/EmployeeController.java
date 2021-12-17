package com.imranmadbar.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private RestTemplate restTemplate;

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/")
    public String index() {
        System.out.println("Welcome to Mic2 Employee !");
        return "Welcome to Mic2 Employee !";
    }

    @GetMapping("/home")
    public String helloFromHome() {
        System.out.println("Welcome to Mic2 Employee Home !");
        return "Welcome to Mic2 Employee Home !";
    }

}
