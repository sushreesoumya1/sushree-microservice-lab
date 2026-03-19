package com.cohad.lab.responseDTO;

import java.util.Map;

public record ActivityResponseDTO(
        String id,
        String userId,
        String eventType,
        String timestamp,
        Map<String, Object> metadata
) {}
