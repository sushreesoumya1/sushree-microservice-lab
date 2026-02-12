package com.cohad.lab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getMessage() throws UnknownHostException {
        String hostname = InetAddress.getLocalHost().getHostName();
        int n=0;
        for(int i=0;i<50;i++){
            n+=i;
        }
        return n + "COHAD microservice lab - v3 🚀 | Pod: " + hostname;
    }

}
