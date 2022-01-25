package com.ssafy.solomon.api.controller;

import com.ssafy.solomon.api.dto.EmailAuthDto;
import com.ssafy.solomon.api.dto.MemberDto;
import com.ssafy.solomon.api.service.MemberService;
import com.ssafy.solomon.db.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MemberController {

    private final MemberService memberService;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @PostMapping("/signup/member")
    public ResponseEntity<String> registerMember(@RequestBody MemberDto memberDto) {
        MemberEntity result = memberService.insertMember(memberDto);
        if(result.getMemberId()==memberDto.getMemberId()) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/email/auth")
    public ResponseEntity<String> emailCheck(@ModelAttribute EmailAuthDto emailAuthDto) throws Exception {
        memberService.checkEmail(emailAuthDto);
        //System.out.println("여기");
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/check/{memberId}")
    public ResponseEntity<String> idCheck(@PathVariable String memberId) throws Exception {
        Long result = memberService.checkMember(memberId);
        if(result==0L) {
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);

        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/sns/{memberKey}")
    public ResponseEntity<String> snsCheck(@PathVariable String memberKey) throws Exception {
        Long result = memberService.checkSnsMember(memberKey);
        if(result==0L) {
            return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @PostMapping("/signup/sns")
    public ResponseEntity<String> registerSnsMember(@RequestBody MemberDto memberDto) {
        MemberEntity result = memberService.insertSnsMember(memberDto);
        if(result.getMemberKey()==memberDto.getMemberKey()) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }
}
