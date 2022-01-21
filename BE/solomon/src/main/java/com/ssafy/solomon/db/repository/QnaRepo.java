package com.ssafy.solomon.db.repository;

import com.ssafy.solomon.db.entity.QnaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaRepo extends JPARepo<QnaEntity,Long>{
}
