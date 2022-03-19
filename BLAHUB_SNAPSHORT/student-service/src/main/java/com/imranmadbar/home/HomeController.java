package com.imranmadbar.home;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping(value="/")
    public String appIndex(){
        logger.info("Welcome to Student Application");
        return "Welcome to Student Application";
    }

    @GetMapping(value="/home")
    public String appHome(){
        logger.info("Welcome to Student Application Home");
        return "Welcome to Student Application Home";
    }

}
