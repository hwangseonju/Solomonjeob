package com.ssafy.solomon.api.controller;

import com.ssafy.solomon.api.dto.EmailAuthDto;
import com.ssafy.solomon.api.dto.MemberDto;
import com.ssafy.solomon.api.security.SecurityService;
import com.ssafy.solomon.api.service.MemberService;
import com.ssafy.solomon.db.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MemberController {

    @Autowired
    private SecurityService securityService;

    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private static final String NOT_CONFIRM_EMAIL = "notconfirmemail";

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

    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> signin(@RequestBody MemberDto memberDto, HttpServletResponse res) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {
            MemberEntity memberEntity = memberService.signin(memberDto);
            if(memberEntity != null) {
                // memberId가 있을 때
                if (memberEntity.getMemberId().equals(memberDto.getMemberId()) && passwordEncoder.matches(memberDto.getMemberPwd(), memberEntity.getMemberPwd())) {
                    // 이메일 인증을 받았을 때
                    if (memberEntity.getEmailAuth()) {
                        // jwt 토큰 발급
                        String token = securityService.createToken(memberDto);
                        // header에 jwt-auth-token이라고 설정해주어 interceptor에서 jwt-auth-token을 찾게해줌
                        res.setHeader("jwt-auth-token", token);
//            resultMap.put("signinId", memberDto.getMemberId());
//                        resultMap.put("signinIdx", memberEntity.getMemberIdx());
//                        resultMap.put("jwt-auth-token", token);
                        resultMap.put("status", true);
                        status = HttpStatus.ACCEPTED;
                        return new ResponseEntity<Map<String, Object>>(resultMap, status);
                    }
                    resultMap.put("email", memberDto.getMemberId());
                    resultMap.put("email_confirm", "emailconfim");
                    status = HttpStatus.OK;
                    return new ResponseEntity<Map<String, Object>>(resultMap, status);
                }
            }
        } catch (RuntimeException e){
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 로그인 안됨
        resultMap.put("notfound", FAIL);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

// token을 이용하여 사용자 정보 조회
//    @PostMapping("/info")
//    public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req, @RequestBody MemberDto memberDto){
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status = null;
//        try {
//            String info = memberService.getServerInfo();
////            resultMap.putAll(securityService.get(req.getHeader("jwt-auth-token")));
//            String token = securityService.createToken(memberDto);
//            resultMap.putAll(securityService.get(token));
//            resultMap.put("status", true);
//            resultMap.put("info", info);
//            resultMap.put("request_body", memberDto.getMemberIdx());
//            status = HttpStatus.ACCEPTED;
//        }catch (RuntimeException e){
//            resultMap.put("message", e.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<Map<String, Object>>(resultMap, status);
//    }
}
