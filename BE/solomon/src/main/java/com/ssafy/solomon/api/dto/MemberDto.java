package com.ssafy.solomon.api.dto;

import com.ssafy.solomon.db.entity.MemberEntity;
import com.ssafy.solomon.db.entity.QnasEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MemberDto {

    private long memberIdx;
    private String memberId;
    private String memberPwd;
    private String memberKey;
    private String memberSns;
    private List<QnasEntity> qnasCode;

    @Builder
    public MemberDto(String memberId, String memberPwd) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
    }

}
