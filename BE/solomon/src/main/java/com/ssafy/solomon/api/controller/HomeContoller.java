package com.ssafy.solomon.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeContoller {


    @GetMapping("")
    public String test() { return "test!!!"; }

}
