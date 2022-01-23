package com.ssafy.solomon.api.controller;

import com.ssafy.solomon.api.service.QnasService;

import com.ssafy.solomon.db.entity.QnasEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/qnas")
@RequiredArgsConstructor
public class QnasController {

    private final QnasService qnas;

    //질문문제집 생성
    @PostMapping("/")
    public ResponseEntity<QnasEntity> insertQnas(){
        return null;
    }

    //질문 문제집 한개선택( 채팅방으로 가져갈때 필요할듯..?)
    @PatchMapping("/{qnasId}")
    public ResponseEntity<QnasEntity> updateQnas(){
        return null;
    }

    //질문문제집명 수정
    @GetMapping("/{qnasId}")
    public ResponseEntity<QnasEntity> selectQnas(){
        return null;
    }

    //질문 문제집 삭제
    @DeleteMapping("/{qnasId}")
    public ResponseEntity<QnasEntity> deleteQnas(){
        return null;
    }

    //질문 문제집 리스트 (왼쪽에 쭉 띄우기 위해서)
    @GetMapping("/")
    public ResponseEntity<QnasEntity> qnasList(){
        return  null;
    }

}

