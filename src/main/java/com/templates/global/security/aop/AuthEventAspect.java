package com.templates.global.security.aop;

import com.templates.global.security.controller.dto.*;
import com.templates.global.security.event.*;
import jakarta.servlet.http.*;
import lombok.*;
import org.aspectj.lang.annotation.*;
import org.springframework.context.*;
import org.springframework.stereotype.*;
import org.springframework.web.context.request.*;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthEventAspect {
    private final ApplicationEventPublisher publisher;

    @AfterReturning(
        pointcut = "execution(* com.templates.global.security.controller.AuthController.login(..))",
        returning = "memberInfo")
    public void afterLogin(MemberInfo memberInfo) {
        publisher.publishEvent(new LoginSuccessEvent(memberInfo.seq()));
    }
}