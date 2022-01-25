package com.ssafy.solomon.db.repository;

import com.ssafy.solomon.db.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemeberRepo extends JPARepo<MemberEntity,Long>{

}
