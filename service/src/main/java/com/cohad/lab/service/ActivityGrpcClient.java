package com.cohad.lab.service;

import com.cohad.lab.responseDTO.ActivityResponseDTO;

import com.example.service.grpc.ActivityResponse;
import com.example.service.grpc.ActivityServiceGrpc;
import com.example.service.grpc.ActivityRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityGrpcClient {

    @GrpcClient("activity-service")
    private ActivityServiceGrpc.ActivityServiceBlockingStub stub;

    public List<ActivityResponseDTO> getActivities() {

        ActivityRequest request = ActivityRequest.newBuilder()
                .setPage(0)
                .setSize(5)
                .build();

        ActivityResponse response = stub.getRecentActivities(request);

        return response.getActivitiesList().stream()
                .map(a -> new ActivityResponseDTO(
                        a.getId(),
                        a.getUserId(),
                        a.getEventType(),
                        a.getTimestamp(),
                        null
                ))
                .toList();
    }
}
