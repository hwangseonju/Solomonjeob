package com.ssafy.solomon.db.repository;

import com.ssafy.solomon.db.entity.QnaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QnaRepo extends JPARepo<QnaEntity,Long>{
    List<QnaEntity> findByQnaList_QnasId(Long Id);
}
