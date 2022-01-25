package com.ssafy.solomon.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.solomon.db.entity.EmailAuthEntity;
import com.ssafy.solomon.db.entity.QEmailAuthEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class EmailAuthCustomRepositoryImpl implements EmailAuthCustomRepository{

    JPAQueryFactory jpaQueryFactory;

    public EmailAuthCustomRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<EmailAuthEntity> findEmailAuthByEmail(String email, String authToken, LocalDateTime current) {
        EmailAuthEntity emailAuthEntity = jpaQueryFactory
                .selectFrom(QEmailAuthEntity.emailAuthEntity)
                .where(QEmailAuthEntity.emailAuthEntity.email.eq(email),
                        QEmailAuthEntity.emailAuthEntity.authToken.eq(authToken),
                        QEmailAuthEntity.emailAuthEntity.expireDate.goe(current),
                        QEmailAuthEntity.emailAuthEntity.expired.eq(false))
                .fetchFirst();

        return Optional.ofNullable(emailAuthEntity);
    }
}
