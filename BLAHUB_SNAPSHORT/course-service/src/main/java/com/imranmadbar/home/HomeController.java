package com.imranmadbar.home;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value="/")
    public String appIndex(){
        return "Welcome to Course Application";
    }

    @GetMapping(value="/home")
    public String appHome(){
        return "Welcome to Course Application";
    }

}
