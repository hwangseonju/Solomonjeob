package com.ssafy.solomon.db.repository;

import com.ssafy.solomon.db.entity.QnasEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QnasRepo extends JPARepo<QnasEntity,Long>{

}
