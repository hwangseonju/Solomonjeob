package com.ssafy.solomon.api.security;

import com.ssafy.solomon.api.dto.MemberDto;
import com.ssafy.solomon.api.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    private SecurityService securityService;

//    @GetMapping("/create/token")
//    public Map<String, Object> createToken(@RequestParam(value = "subject") String subject) {
//        String token = securityService.createToken(subject, (2 * 1000 * 60));
//        Map<String, Object> map = new LinkedHashMap<>();
//        map.put("result", token);
//        return map;
//    }

//    @PostMapping("/create/token/{memberId}")
//    public Map<String, Object> createToke(@PathVariable String memberId) {
//        String token = securityService.createToken(memberId, (2 * 1000 * 60));
//        Map<String, Object> map = new LinkedHashMap<>();
//        map.put("result", token);
//        return map;
//    }

    @GetMapping("/get/subject")
    public Map<String, Object> getSubject(@RequestParam(value = "token") String token) {
        String subject = securityService.getSubject(token);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", subject);
        return map;
    }

    @GetMapping("/get/check")
    public boolean validation(@RequestParam(value = "token") String token) {
        boolean check = securityService.validateToken(token);
        return check;
    }


}