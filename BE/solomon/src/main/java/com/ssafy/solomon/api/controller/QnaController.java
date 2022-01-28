package com.ssafy.solomon.api.controller;


import com.ssafy.solomon.api.dto.QnaDto;

import com.ssafy.solomon.api.service.QnaServiceImpl;
import com.ssafy.solomon.db.entity.QnaEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/qna")
@RequiredArgsConstructor //@autowired는 필드 주입이지만 이어노테이션은 생성자를 주입함 boot 4.3부터 필드주입 지양
@Api(value = "질문 API", tags = {"Qna"})
public class QnaController {

    private final QnaServiceImpl qnaService;

    //문제 등록
    @PostMapping("/")
    @ApiOperation(value = "질문 등록", notes = "질문 문제집에 문제 등록")
    public ResponseEntity<QnaEntity> insertQna(@RequestBody QnaDto dto){
        //System.out.println(dto.toString());
        return ResponseEntity.ok(qnaService.createQna(dto));
    }

    //질문 한개 선택 (문제 읽어줄때 필요할듯..?)
    @GetMapping("/{qnaId}")
    @ApiOperation(value = "질문 선택", notes = "문제를 하나 선택")
    public ResponseEntity<QnaEntity> selectQna(@PathVariable Long qnaId){
        return ResponseEntity.ok(qnaService.readQna(qnaId));
    }
    //질문 수정
    @PutMapping("/{qnaId}")
    @ApiOperation(value = "질문 수정", notes = "질문 수정")
    public ResponseEntity<QnaEntity> updateQna(@RequestBody QnaDto dto , @PathVariable Long qnaId){
        return ResponseEntity.ok(qnaService.updateQna(qnaId,dto));
    }

    //질문 삭제
    @DeleteMapping("/{qnaId}")
    @ApiOperation(value = "질문 삭제", notes = "질문 문제집에서 문제 삭제")
    public ResponseEntity<QnaEntity> deleteQna(@PathVariable Long qnaId){
        qnaService.deleteQna(qnaId);
        return ResponseEntity.ok().build();
    }

    //질문 리스트
    @GetMapping("/my/{qnasId}")
    @ApiOperation(value = "질문 리스트", notes = "선택한 문제집의 모든 질문목록")
    public ResponseEntity<List<QnaEntity>> qnaList(@PathVariable Long qnasId){
        return  ResponseEntity.ok(qnaService.QnaList(qnasId));
    }
}
