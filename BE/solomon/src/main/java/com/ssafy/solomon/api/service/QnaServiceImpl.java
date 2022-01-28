package com.ssafy.solomon.api.service;

import com.ssafy.solomon.api.dto.QnaDto;
import com.ssafy.solomon.db.entity.QnaEntity;
import com.ssafy.solomon.db.entity.QnasEntity;
import com.ssafy.solomon.db.repository.QnaRepo;
import com.ssafy.solomon.db.repository.QnasRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("qnaservice")
@RequiredArgsConstructor
public class QnaServiceImpl{

    private final QnaRepo qnarepo;
    private final QnasRepo qnasrepo;

    public QnaEntity createQna(QnaDto dto) {
        //질문문제집 아이디 알아오기
        Optional<QnasEntity> qnasId = qnasrepo.findById(dto.getQnasCode());
        //문제집 없으면 예외처리
        if(!qnasId.isPresent()){
            throw new EntityNotFoundException( "질문등록을 하려는 문제집이 없습니다.");
        }
        //저장할 엔티티 객체 생성
        QnaEntity createQna = new QnaEntity();
        BeanUtils.copyProperties(dto,createQna); //createQna에 qnasId복붙
        createQna.setQnaList(qnasId.get());        //외래키정보 붙이기
        return qnarepo.save(createQna);
    }

    public QnaEntity readQna(Long id) {
        Optional<QnaEntity> qna = qnarepo.findById(id);
        //조회한 값을 optional로 감싸고 isPresent()메서드를 반환해 boolean값으로 if문 채우기
        if(qna.isPresent()){    //true면
            return qna.get();
        }
        throw new EntityNotFoundException("잘문정보를 찾을 수 없습니다.");
    }

    public QnaEntity updateQna(Long id, QnaDto dto) {
        Optional<QnaEntity> qna = qnarepo.findById(id);
        if(!qna.isPresent()){
            throw  new EntityNotFoundException("수정할 질문이 없습니다.");
        }
        QnaEntity qnaEntity = qna.get();
        qnaEntity.setQnaContent(dto.getQnaContent());
        qnaEntity.setQnaAnswer(dto.getQnaAnswer());
        return qnarepo.save(qnaEntity);
    }

    @Transactional
    public void deleteQna(Long id) {
        Optional<QnaEntity> qna = qnarepo.findById(id);
        qnarepo.delete(qna.get());
    }

    //id : findBy 문제집 코드 select All
    public List<QnaEntity> QnaList(Long qnasid) {
        List <QnaEntity> list = qnarepo.findByQnaList_QnasId(qnasid);
        return list;
    }

}
