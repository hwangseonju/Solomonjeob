package com.ssafy.solomon.api.service;

import com.ssafy.solomon.api.dto.QnasDto;
import com.ssafy.solomon.db.entity.QnasEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnasServiceImpl implements QnasService{
    @Override
    public QnasEntity createQnaS(QnasDto dto) {
        return null;
    }

    @Override
    public QnasEntity readQnaS(Long id) {
        return null;
    }

    @Override
    public QnasEntity updateQnaS(Long id, QnasDto dto) {
        return null;
    }

    @Override
    public void deleteQnaS(Long id) {

    }

    @Override
    public List<QnasEntity> QnaSList(Long id) {
        return null;
    }
}
