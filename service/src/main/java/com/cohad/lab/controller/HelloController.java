package com.cohad.lab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getMessage(){
        return "COHAD microservice lab - v1";
    }

}
