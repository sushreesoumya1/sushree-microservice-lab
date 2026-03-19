package com.cohad.activityservice.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;import org.springframework.data.mongodb.core.index.Indexed;import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Document(collection = "events")
@CompoundIndex(name = "user_time_idx", def = "{'userId': 1, 'timestamp': -1}")
public record ActivityEvent(

        @Id
        String id,

        @Indexed
        String userId,

        String eventType,

        Instant timestamp,

        Map<String, Object> metadata
) {}