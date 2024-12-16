package com.templates.global.security.controller.dto;

import com.templates.domains.member.domain.*;
import com.templates.global.security.service.CustomUserDetailsService.CustomUserDetails;
import io.swagger.v3.oas.annotations.Hidden;
import java.time.*;

@Hidden
public record MemberInfo(
    Long seq,
    String loginId,
    String name,
    LocalDateTime lastActiveAt
) {

  public static MemberInfo of(CustomUserDetails member) {
    return new MemberInfo(member.getSeq(), member.getUsername(), member.getName(), null);
  }

  public static MemberInfo of(Member member) {
    return new MemberInfo(member.getSeq(), member.getLoginId(), member.getName(),
        member.getLastActiveAt());
  }
}