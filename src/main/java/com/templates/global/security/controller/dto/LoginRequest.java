package com.templates.global.security.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
    @NotBlank String loginId,
    @NotBlank String password
) {
}