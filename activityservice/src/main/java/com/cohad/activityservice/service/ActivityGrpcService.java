package com.cohad.activityservice.service;

import com.cohad.activityservice.document.ActivityEvent;
import com.cohad.activityservice.repository.ActivityRepository;
import com.example.activity.grpc.Activity;
import com.example.activity.grpc.ActivityRequest;
import com.example.activity.grpc.ActivityResponse;
import com.example.activity.grpc.ActivityServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
public class ActivityGrpcService extends ActivityServiceGrpc.ActivityServiceImplBase{
    private final ActivityRepository activityRepository;

    public ActivityGrpcService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public void getRecentActivities(ActivityRequest request,
                                    StreamObserver<ActivityResponse> responseObserver) {

        List<ActivityEvent> events = activityRepository.findAll();

        List<Activity> grpcActivities = events.stream()
                .map(e -> Activity.newBuilder()
                        .setId(e.id())
                        .setUserId(e.userId())
                        .setEventType(e.eventType())
                        .setTimestamp(e.timestamp().toString())
                        .build())
                .toList();

        ActivityResponse response = ActivityResponse.newBuilder()
                .addAllActivities(grpcActivities)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
