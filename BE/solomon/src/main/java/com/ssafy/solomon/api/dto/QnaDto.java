package com.ssafy.solomon.api.dto;

import lombok.Data;

@Data
public class QnaDto {
    private long qnaId;       //질문코드
    private long qnaMemberId;   //회원코드 
    private long qnasCode;      //질문리스트 코드
    private String qnaContent;  //질문 내용
    private String qnaAnswer;   //답변

}
