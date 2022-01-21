package com.ssafy.solomon.api.service;

import com.ssafy.solomon.api.dto.MemberDto;
import com.ssafy.solomon.db.entity.MemberEntity;

import java.sql.SQLException;

public interface MemberService {
    public MemberEntity insertMember(MemberDto memberDto) throws SQLException;
}
