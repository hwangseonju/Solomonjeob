package com.ssafy.solomon.db.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

@NoRepositoryBean
public interface JPARepository <T, ID> extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T> {
}
