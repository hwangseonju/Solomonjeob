package com.ssafy.solomon.db.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
//@Repository 어노테이션은 springframework.data.repository.Repository 상속시 생략 가능 (자동으로 등록 해줌)
@NoRepositoryBean
public interface JPARepo<T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {
}
