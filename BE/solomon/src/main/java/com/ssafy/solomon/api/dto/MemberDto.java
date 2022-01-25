package com.ssafy.solomon.api.dto;

import com.ssafy.solomon.db.entity.MemberEntity;
import com.ssafy.solomon.db.entity.QnasEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Data
@NoArgsConstructor
public class MemberDto {

    private long memberIdx;
    private String memberId;
    private String memberPwd;
    private String memberKey;
    private String memberSns;
    private Boolean emailAuth;

    @Builder
    public MemberDto(String memberId, String memberPwd, Boolean emailAuth) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.emailAuth = emailAuth;
    }

    public MemberDto(String memberId, String memberPwd, String memberKey, String memberSns, Boolean emailAuth) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberKey = memberKey;
        this.memberSns = memberSns;
        this.emailAuth = emailAuth;
    }

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .memberId(memberId)
                .memberPwd(new BCryptPasswordEncoder().encode(memberPwd))
                .emailAuth(false)    // 0 : false, 1 : true
                .build();
    }

    public MemberEntity toSnsEntity() {
        return MemberEntity.builder()
                .memberKey(memberKey)
                .memberSns(memberSns)
                .emailAuth(true)    // 0 : false, 1 : true
                .build();
    }
}
