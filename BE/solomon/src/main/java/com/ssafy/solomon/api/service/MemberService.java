package com.ssafy.solomon.api.service;

import com.ssafy.solomon.api.dto.MemberDto;

import java.util.List;

public interface MemberService {

    public MemberDto signin(String memberId, String memberPwd) throws Exception;

    public String getServerInfo() throws Exception;

    public MemberDto insertMember(MemberDto member) throws Exception;

    public List<MemberDto> getAllMembers() throws Exception;

    public MemberDto getMemberByUserId(String memberId) throws Exception;

    public void updateMemberPw(String memberId, MemberDto member) throws Exception;

    public void deleteMember(String memberId) throws Exception;
}

