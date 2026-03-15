package com.cohad.activityservice.repository;

import com.cohad.activityservice.document.ActivityEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ActivityRepository extends MongoRepository<ActivityEvent, String> {
    List<ActivityEvent> findByUserId(String userId);
    List<ActivityEvent> findByEventType(String eventType);
}
