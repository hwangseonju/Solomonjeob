package com.ssafy.solomon.db.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "emailAuth")
public class EmailAuthEntity {

    // 이메일 만료 시간 = 10분(고정)
    private static final Long MAX_EXPIRE_TIME = 10L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String authToken;
    private Boolean expired;
    private LocalDateTime expireDate;

    @Builder
    public EmailAuthEntity (String email, String authToken, Boolean expired) {
        this.email = email;
        this.authToken = authToken;
        this.expired = expired;
        this.expireDate = LocalDateTime.now().plusMinutes(MAX_EXPIRE_TIME);
    }

    public void useToken() {
        expired = true;
    }
}
