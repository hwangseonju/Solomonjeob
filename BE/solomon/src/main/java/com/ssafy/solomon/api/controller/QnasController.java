package com.ssafy.solomon.api.controller;

import com.ssafy.solomon.api.dto.QnaDto;
import com.ssafy.solomon.api.dto.QnasDto;

import com.ssafy.solomon.api.service.QnasServiceImpl;
import com.ssafy.solomon.db.entity.QnasEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/qnas")
@RequiredArgsConstructor
public class QnasController {

    private final QnasServiceImpl qnas;

    //질문문제집 생성
    @PostMapping
    public ResponseEntity<QnasEntity> insertQnas(@RequestBody QnasDto dto){
        return ResponseEntity.ok(qnas.createQnaS(dto));
    }


    //질문 문제집 한개선택( 채팅방으로 가져갈때 필요할 수 있음)
    @GetMapping("/{qnasId}")
    public ResponseEntity<QnasEntity> selectQnas(@PathVariable Long qnasId){
        return ResponseEntity.ok(qnas.readQnaS(qnasId));
    }
    //질문문제집명 수정
    @PatchMapping("/{qnasId}")
    public ResponseEntity<QnasEntity> updateQnas(@RequestBody QnasDto dto ,@PathVariable Long qnasId){
        return ResponseEntity.ok(qnas.updateQnaS(qnasId,dto));
    }

    //질문 문제집 삭제
    @DeleteMapping("/{qnasId}")
    public ResponseEntity<QnasEntity> deleteQnas(@PathVariable Long qnasId){
        qnas.deleteQnaS(qnasId);
        return ResponseEntity.ok().build();
    }

    //질문 문제집 리스트 (화면 왼쪽면에 리스트 띄울때 필요)
    @GetMapping("/")
    public ResponseEntity<QnasEntity> qnasList(){
        return  null;
    }

}

