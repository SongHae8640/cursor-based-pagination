package com.templates.domains.member.service;

import com.templates.domains.member.repository.*;
import com.templates.global.security.event.*;
import java.time.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.context.event.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class LastActiveAtUpdateService {
    private final MemberRepository memberRepository;

    @EventListener
    public void subscribeLoginSuccessEvent(LoginSuccessEvent event) {
        log.info("LoginSuccessEvent: " + event);
        updateLastActiveAt(event.memberSeq(), event.loginAt());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateLastActiveAt(Long memberSeq, LocalDateTime localDateTime) {
        memberRepository.findById(memberSeq)
            .ifPresent(member -> {
                member.updateLastActiveAt(localDateTime);
                memberRepository.save(member);
            });
    }
}