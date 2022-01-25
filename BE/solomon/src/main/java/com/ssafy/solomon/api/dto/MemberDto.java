package com.ssafy.solomon.api.dto;

import lombok.*;

import javax.persistence.Column;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
//    private long memberIdx;
    private String memberId;
    private String memberPwd;
    private String memberKey;
    private String memberSns;

    public MemberDto(String memberId, String memberPwd) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
    }
}
