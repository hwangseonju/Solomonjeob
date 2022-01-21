package com.ssafy.solomon.api.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.solomon.api.dto.MemberDto;
import com.ssafy.solomon.api.service.MemberService;
import com.ssafy.solomon.db.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MemberController {

    private final MemberService memberService;

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody MemberDto memberDto) throws SQLException {
        MemberEntity result = memberService.insertMember(memberDto);
        if(result.getMemberId()==memberDto.getMemberId()) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
}
