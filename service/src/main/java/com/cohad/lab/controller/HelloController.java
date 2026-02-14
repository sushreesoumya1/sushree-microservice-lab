package com.cohad.lab.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;


@RestController
public class HelloController {
    @Value("${app.message}")
    private String message;
    @Value("${API_KEY:}")
    private String apiKey;

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String getMessage() throws UnknownHostException {
        String hostname = InetAddress.getLocalHost().getHostName();
        log.info(message + " | Log Pod: " + hostname);
        return message + " | Pod: " + hostname;
    }
    @GetMapping("/secret-check")
    public String secretCheck() {
        return apiKey.isBlank()
                ? "API_KEY missing ❌"
                : "API_KEY loaded ✅ (length=" + apiKey.length() + ")";
    }

}
