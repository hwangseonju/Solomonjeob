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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("qnaservice")
@RequiredArgsConstructor
public class QnaServiceImpl{

    private final QnaRepo qnarepo;
    private final QnasRepo qnasrepo;

    public QnaEntity createQna(QnaDto dto) {
        Optional<QnasEntity> qnasId = qnasrepo.findById(dto.getQnasCode()); //질문문제집 아이디
        if(!qnasId.isPresent()){
            throw new EntityNotFoundException( "질문등록을 하려는 문제집이 없습니다.");
        }
        QnaEntity createQna = new QnaEntity();
        BeanUtils.copyProperties(qnasId,createQna); //createQna에 qnasId복붙
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

    public void deleteQna(Long id) {
        qnarepo.deleteById(id);
    }

    //id : findBy 문제집 코드 select All
    public List<QnaDto> QnaList(Long qnasid) {
        Optional<QnaEntity> qnaslist= qnarepo.findByQnaList(qnasid);
        List<QnaDto> resultlist = new ArrayList<>();
        if(!qnaslist.isPresent()){
            throw new EntityNotFoundException("해당하는 문제집 정보를 찾을 수 업습니다.");
        }
       // for(QnaEntity qna : qnaslist){}
        return null;
    }

}
