package com.templates.domains.member.controller.dto;

import com.templates.domains.member.domain.Member;
import jakarta.validation.constraints.NotBlank;

public record SignupRequestDto(
    @NotBlank String loginId,
    @NotBlank String password,
    @NotBlank String name
) {

    public Member toEntity() {
        return Member.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .build();
    }
}