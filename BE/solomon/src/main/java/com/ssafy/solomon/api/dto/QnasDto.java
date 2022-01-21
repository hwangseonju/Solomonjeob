package com.ssafy.solomon.api.dto;

import lombok.Data;

@Data
public class QnasDto {
    private long qnasMemberId;  //회원 코드
    private long qnasCodee;     //질문모음집 코드
    private String qnasTitle;   //빌문모읍집 제목
}
