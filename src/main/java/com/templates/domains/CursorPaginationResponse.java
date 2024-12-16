package com.templates.domains;

import java.time.*;
import java.util.*;

public record CursorPaginationResponse<T>(
    List<T> data,
    LocalDateTime nextCursor,
    boolean isLast
) {
    public CursorPaginationResponse {
        if (data == null) {
            throw new IllegalArgumentException("data는 null일 수 없습니다.");
        }
    }
}