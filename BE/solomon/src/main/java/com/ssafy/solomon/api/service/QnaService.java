package com.ssafy.solomon.api.service;

import com.ssafy.solomon.api.dto.QnaDto;
import com.ssafy.solomon.db.entity.QnaEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QnaService {
    /*
    * 질문 CRUD
    * + selectAll
    * + findby CODE
    * */
    public QnaEntity createQna(QnaDto dto);
    public QnaEntity readQna(Long id);  //질문 한개만 select
    public QnaEntity updateQna(Long id, QnaDto dto);
    public void deleteQna(Long id);
    public List<QnaEntity> QnaList(Long id);   //해당하는 문제집 아이디에 맞춰서 list

}
