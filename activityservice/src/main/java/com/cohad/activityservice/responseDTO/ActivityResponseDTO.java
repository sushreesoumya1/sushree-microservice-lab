package com.cohad.activityservice.responseDTO;

import java.time.Instant;
import java.util.Map;

public record ActivityResponseDTO(
        String id,
        String userId,
        String eventType,
        Instant timestamp,
        Map<String, Object> metadata
){}
