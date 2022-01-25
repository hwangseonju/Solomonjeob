package com.ssafy.solomon.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/member")
public class TestController {

    @RequestMapping("/kakao")
    public String home(@RequestParam(value = "code", required = false) String code) throws Exception{
        //System.out.println("#########" + code);
        return "login";
    }
}
