package com.cohad.lab.controller;

import com.cohad.lab.responseDTO.ActivityResponseDTO;
import com.cohad.lab.service.ActivityGrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lab")
public class LabGrpcController {

    private final ActivityGrpcClient activityGrpcClient;

    public LabGrpcController(ActivityGrpcClient activityGrpcClient) {
        this.activityGrpcClient = activityGrpcClient;
    }

    @GetMapping("/activities/grpc")
    public List<ActivityResponseDTO> getActivities() {
        return activityGrpcClient.getActivities();
    }
}
