package com.templates.domains.member.repository;

import static com.templates.domains.member.domain.QMember.*;

import com.querydsl.jpa.impl.*;
import com.templates.domains.*;
import com.templates.domains.member.domain.*;
import com.templates.global.security.controller.dto.*;
import java.time.*;
import java.util.*;
import lombok.*;
import org.springframework.stereotype.*;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public CursorPaginationResponse<MemberInfo> findMembersByCursor(CursorPaginationRequest request) {
        List<Member> fetchedMembers = fetchMembers(request);

        List<MemberInfo> members = mapToMemberInfo(fetchedMembers, request.size());

        LocalDateTime nextCursor = getNextCursor(fetchedMembers, request.size());
        boolean isLast = fetchedMembers.size() <= request.size();

        return new CursorPaginationResponse<>(members, nextCursor, isLast);
    }

    private List<Member> fetchMembers(CursorPaginationRequest request) {
        JPAQuery<Member> query = queryFactory.selectFrom(QMember.member)
            .orderBy(QMember.member.lastActiveAt.desc())
            .limit(request.size() + 1);

        if (request.cursor() != null) {
            query.where(QMember.member.lastActiveAt.lt(request.cursor()));
        }

        return query.fetch();
    }

    private List<MemberInfo> mapToMemberInfo(List<Member> members, int size) {
        return members.stream()
            .limit(size)
            .map(MemberInfo::of)
            .toList();
    }

    private LocalDateTime getNextCursor(List<Member> members, int size) {
        return members.size() > size ? members.get(size).getLastActiveAt() : null;
    }
}