package com.ssafy.solomon.api.controller;


import com.ssafy.solomon.api.service.QnaService;
import com.ssafy.solomon.db.entity.QnaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/qna")
@RequiredArgsConstructor //@autowired는 필드 주입이지만 이어노테이션은 생성자를 주입함 boot 4.3부터 필드주입 지양
public class QnaController {
    private final QnaService qna;

    //문제 등록
    @PostMapping("/")
    public ResponseEntity<QnaEntity> insertQna(){
        return null;
    }

    //질문 한개 선택 (문제 읽어줄때 필요할듯..?)
    @GetMapping("/{qnaId}")
    public ResponseEntity<QnaEntity> selectQna(){ return null;}
    //질문 수정
    @PatchMapping("/{qnaId}")
    public ResponseEntity<QnaEntity> updateQna(){
        return null;}
    //질문 삭제
    @DeleteMapping("/{qnaId}")
    public ResponseEntity<QnaEntity> deleteQna(){
        return null;
    }

    //질문 리스트
    @GetMapping("/")
    public ResponseEntity<QnaEntity> qnaList(){return  null;}
}
