package com.ssafy.solomon.api.service;

import com.ssafy.solomon.api.dto.QnaDto;
import com.ssafy.solomon.db.entity.QnaEntity;
import com.ssafy.solomon.db.repository.QnaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("qnaservice")
public class QnaServiceImpl implements QnaService{
    @Autowired
    private final QnaRepo qnarepo;

    public QnaServiceImpl(QnaRepo qnarepo) {
        this.qnarepo = qnarepo;
    }


    @Override
    public QnaEntity createQna(QnaDto dto) {
        //1.
        QnaEntity qnae = new QnaEntity();
        //qnae.setQna_id(dto.getQnaCode());
        qnae.setQna_content(dto.getQnaContent());
       ////////////////////////////////////////////////////
        //2.
       // Optional<QnaEntity> qnae2=QnaRepo.findById(dto.getQnaId());
        return qnarepo.save(qnae);
    }

    @Override
    public QnaEntity readQna(Long id) {
        return null;
    }

    @Override
    public QnaEntity updateQna(Long id, QnaDto dto) {
        return null;
    }

    @Override
    public void deleteQna(Long id) {

    }

    @Override
    public List<QnaEntity> QnaList(Long id) {
        return null;
    }

}
