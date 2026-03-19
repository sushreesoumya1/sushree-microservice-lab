package com.cohad.activityservice.repository;

import com.cohad.activityservice.document.ActivityEvent;
import org.springframework.data.domain.Page;import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ActivityRepository extends MongoRepository<ActivityEvent, String> {
    Page<ActivityEvent> findByUserId(String userId, Pageable pageable);
    Page<ActivityEvent> findByEventType(String eventType, Pageable Pageable);
}
