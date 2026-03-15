package com.cohad.activityservice.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Document(collection = "events")
public record ActivityEvent(

        @Id
        String id,

        String userId,

        String eventType,

        Instant timestamp,

        Map<String, Object> metadata
) {}