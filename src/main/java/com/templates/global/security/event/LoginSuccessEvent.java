package com.templates.global.security.event;

import java.time.*;

public record LoginSuccessEvent(
    Long memberSeq,
    LocalDateTime loginAt
) {
    public LoginSuccessEvent(Long memberSeq) {
        this(memberSeq, LocalDateTime.now());
    }
}