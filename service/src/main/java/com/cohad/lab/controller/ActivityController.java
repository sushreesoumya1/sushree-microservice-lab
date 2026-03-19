package com.cohad.lab.controller;

import com.cohad.lab.responseDTO.ActivityPageResponseDTO;
import com.cohad.lab.service.ActivityClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class ActivityController {

    private final ActivityClient activityClient;

    public ActivityController(ActivityClient activityClient) {
        this.activityClient = activityClient;
    }

    @GetMapping
    public ActivityPageResponseDTO getEvents() {
        return activityClient.getEvents();
    }
}
