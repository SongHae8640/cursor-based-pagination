package com.templates.domains.member.service;

import com.templates.domains.member.controller.dto.SignupRequestDto;
import com.templates.domains.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupService {
    private final MemberRepository memberRepository;

    @Transactional
    public void signup(SignupRequestDto dto) {
        memberRepository.save(dto.toEntity());
    }
}