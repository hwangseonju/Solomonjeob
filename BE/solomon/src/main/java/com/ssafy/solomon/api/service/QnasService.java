package com.ssafy.solomon.api.service;

import com.ssafy.solomon.api.dto.QnasDto;
import com.ssafy.solomon.db.entity.QnasEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QnasService {
    /*
    * 질문 모음집 CRUD
    * + selectAll
    * + findby CODE
    * */
    public QnasEntity createQnaS(QnasDto dto);
    public QnasEntity readQnaS(Long id);  //문제집 한개 select
    public QnasEntity updateQnaS(Long id, QnasDto dto);
    public void deleteQnaS(Long id);
    public List<QnasEntity> QnaSList(Long id);   //해당하는 문제집 아이디에 맞춰서 list
}
