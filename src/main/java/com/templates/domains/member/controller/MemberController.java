package com.templates.domains.member.controller;

import com.templates.domains.*;
import com.templates.domains.member.repository.*;
import com.templates.global.security.controller.dto.*;
import io.swagger.v3.oas.annotations.tags.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Tag(name = "MemberController", description = "회원 API")
public class MemberController {
    private final MemberRepositoryCustom repositoryCustom;

    @GetMapping
    public CursorPaginationResponse<MemberInfo> findMembersByCursor(CursorPaginationRequest request) {
        return repositoryCustom.findMembersByCursor(request);
    }
}