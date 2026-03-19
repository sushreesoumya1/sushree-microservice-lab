package com.cohad.lab.responseDTO;

import java.util.List;

public record ActivityPageResponseDTO(
        List<ActivityResponseDTO> content,
        boolean empty,
        boolean first,
        boolean last,
        int number,
        int numberOfElements,
        int size,
        long totalElements,
        int totalPages
) {
}
