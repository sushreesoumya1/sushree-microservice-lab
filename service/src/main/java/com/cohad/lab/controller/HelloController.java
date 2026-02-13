package com.cohad.lab.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;


import java.net.InetAddress;
import java.net.UnknownHostException;


@RestController
public class HelloController {
    @Value("${app.message}")
    private String message;
    @Value("${API_KEY:}")
    private String apiKey;

    @GetMapping("/hello")
    public String getMessage() throws UnknownHostException {
        String hostname = InetAddress.getLocalHost().getHostName();
        return message + " | Pod: " + hostname;
    }
    @GetMapping("/secret-check")
    public String secretCheck() {
        return apiKey.isBlank()
                ? "API_KEY missing ❌"
                : "API_KEY loaded ✅ (length=" + apiKey.length() + ")";
    }

}
