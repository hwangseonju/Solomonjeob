package com.ssafy.solomon.api.controller;

import com.ssafy.solomon.api.dto.QnasDto;
import com.ssafy.solomon.api.service.QnasServiceImpl;
import com.ssafy.solomon.db.entity.QnasEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/qnas")
@RequiredArgsConstructor
@Api(value = "질문 문제집 API", tags = {"Qnas"})
public class QnasController {

    private final QnasServiceImpl qnas;

    //질문문제집 생성
    @PostMapping
    @ApiOperation(value = "질문 문제집 생성", notes = "로그인 후 사용자 아이디와 title값을 받아 문제집을 생성")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<QnasEntity> insertQnas(@RequestBody QnasDto dto){
        return ResponseEntity.ok(qnas.createQnaS(dto));
    }


    //질문 문제집 한개선택( 채팅방으로 가져갈때 필요할 수 있음)
    @GetMapping("/{qnasId}")
    @ApiOperation(value = "질문 문제집 선택", notes = "질문을 추가할 질문문제집 한개를 선택")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<QnasEntity> selectQnas(@PathVariable Long qnasId){
        return ResponseEntity.ok(qnas.readQnaS(qnasId));
    }
    //질문문제집명 수정
    @PutMapping("/{qnasId}")
    @ApiOperation(value = "질문 문제집 수정", notes = "질문 문제집 Title을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<QnasEntity> updateQnas(@RequestBody QnasDto dto ,@PathVariable Long qnasId){
        return ResponseEntity.ok(qnas.updateQnaS(qnasId,dto));
    }

    @DeleteMapping("/{qnasId}")
    @ApiOperation(value = "질문 문제집 삭제", notes = "문제집 선택시 pk 값을 받아 문제집을 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    //질문 문제집 삭제
    public ResponseEntity<QnasEntity> deleteQnas(@PathVariable Long qnasId){
        qnas.deleteQnaS(qnasId);
        return ResponseEntity.ok().build();
    }

    //질문 문제집 리스트 (화면 왼쪽면에 리스트 띄울때 필요)
    @GetMapping("/my/{memberId}")
    @ApiOperation(value = "질문 문제집 리스트", notes = "로그인 후 사용자 아이디값으로 리스트를 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List <QnasEntity>> qnasList(@PathVariable long memberId){
        return ResponseEntity.ok(qnas.QnaSList(memberId));
    }

}


