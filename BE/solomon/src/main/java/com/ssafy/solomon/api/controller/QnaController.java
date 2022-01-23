package com.ssafy.solomon.api.controller;


import com.ssafy.solomon.api.dto.QnaDto;
import com.ssafy.solomon.api.service.QnaService;
import com.ssafy.solomon.db.entity.QnaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/qna")
@RequiredArgsConstructor //@autowired는 필드 주입이지만 이어노테이션은 생성자를 주입함 boot 4.3부터 필드주입 지양
public class QnaController {

    private final QnaService qnaService;

    //문제 등록
    @PostMapping("/")
    public ResponseEntity<QnaEntity> insertQna(@RequestParam QnaDto dto){
        QnaEntity qna = qnaService.createQna(dto);
        return ResponseEntity.ok(qna);
    }

    //질문 한개 선택 (문제 읽어줄때 필요할듯..?)
    @GetMapping("/{qnaId}")
    public ResponseEntity<QnaEntity> selectQna(@PathVariable Long qnaId){
        return ResponseEntity.ok(qnaService.readQna(qnaId));
    }
    //질문 수정
    @PatchMapping("/{qnaId}")
    public ResponseEntity<QnaEntity> updateQna(@RequestParam QnaDto dto , @PathVariable Long qnaId){
        return ResponseEntity.ok(qnaService.updateQna(qnaId,dto));
    }

    //질문 삭제
    @DeleteMapping("/{qnaId}")
    public ResponseEntity<QnaEntity> deleteQna(@PathVariable Long qnaId){
        qnaService.deleteQna(qnaId);
        return ResponseEntity.ok().build();
    }

    //질문 리스트
    @GetMapping("/")
    public ResponseEntity<QnaEntity> qnaList(){
        return  null;
    }
}
