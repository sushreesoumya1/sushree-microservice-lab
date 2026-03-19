package com.cohad.lab.service;

import com.cohad.lab.responseDTO.ActivityPageResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ActivityClient {
    private final RestTemplate restTemplate;

    public ActivityClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ActivityPageResponseDTO getEvents() {
        String url = "http://activity-service:80/events?page=0&size=5&sort=timestamp,desc";
        return restTemplate.getForObject(url, ActivityPageResponseDTO.class);
    }
}
