package com.ssafy.solomon.api.service;

import com.ssafy.solomon.api.dto.MemberDto;
import com.ssafy.solomon.api.dto.QnasDto;
import com.ssafy.solomon.db.entity.MemberEntity;
import com.ssafy.solomon.db.entity.QnaEntity;
import com.ssafy.solomon.db.entity.QnasEntity;
import com.ssafy.solomon.db.repository.MemeberRepo;
import com.ssafy.solomon.db.repository.QnasRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
@RequiredArgsConstructor
public class QnasServiceImpl{

    private final QnasRepo qnasrepo;
    private final MemeberRepo memberrepo;

    public QnasEntity createQnaS(QnasDto dto) {
        //멤버 아이디 알아오기
        Optional<MemberEntity> member = memberrepo.findById(dto.getQnasMemberId());
        //저장할 엔티티 객체 생성
        QnasEntity qnasCreate = new QnasEntity();
        BeanUtils.copyProperties(dto,qnasCreate);   //정보복사\
        //유저 정보 셋팅
        qnasCreate.setUser(member.get());
        //저장
        return qnasrepo.save(qnasCreate);
    }

    public QnasEntity readQnaS(Long id) {
        Optional<QnasEntity> qnas = qnasrepo.findById(id);
        if(qnas.isPresent())
        {
            return qnas.get();
        }
        throw new EntityNotFoundException("문제집을 찾을 수 없습니다.");
    }

    //질문문제집 제목 수정
    public QnasEntity updateQnaS(Long id, QnasDto dto) {
        Optional <QnasEntity> qnas = qnasrepo.findById(id);
        QnasEntity updateQnas = qnas.get();
        updateQnas.setQnasTitle(dto.getQnasTitle());
        return qnasrepo.save(updateQnas);
    }

    public void deleteQnaS(Long id) {
        Optional <QnasEntity> qnas = qnasrepo.findById(id);
        qnasrepo.delete(qnas.get());
    }

    public List<QnasEntity> QnaSList(Long id) {

        return null;
    }
}
