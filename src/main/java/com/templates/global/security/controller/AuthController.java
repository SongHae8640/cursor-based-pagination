package com.templates.global.security.controller;

import com.templates.global.security.annotation.Auth;
import com.templates.global.security.controller.dto.LoginRequest;
import com.templates.global.security.controller.dto.MemberInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "AuthController", description = "인증 API")
public class AuthController {

  private final AuthenticationManager authenticationManager;

  @PostMapping("/login")
  public void login(@RequestBody @Validated LoginRequest request, HttpSession session) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.loginId(), request.password())
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
  }

  @PostMapping("/logout")
  public void logout(HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null) {
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }
  }


  @PostMapping("/check")
  public MemberInfo checkAuthentication(@Auth MemberInfo memberInfo) {
    return memberInfo;
  }
}