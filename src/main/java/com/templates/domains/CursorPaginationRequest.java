package com.templates.domains;

import java.time.*;

public record CursorPaginationRequest(
    Integer size,
    LocalDateTime cursor
) {
    public CursorPaginationRequest {
        if (size == null || size < 1) {
            size = 10;
        }
    }
}