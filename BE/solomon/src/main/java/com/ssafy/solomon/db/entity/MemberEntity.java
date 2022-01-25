package com.ssafy.solomon.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberIdx;
    private String memberId;
    private String memberPwd;
    private String memberKey;
    @Column(length = 50)
    private String memberSns;
    private Boolean emailAuth;

    //하나의 유저는 여러 문제집을 가질 수 있음
    @JsonBackReference
    @OneToMany(mappedBy="user" ,fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QnasEntity> qnaCodes;



    @Builder
    public MemberEntity(String memberId, String memberPwd, String memberKey, String memberSns, Boolean emailAuth) {
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.memberKey = memberKey;
        this.memberSns = memberSns;
        this.emailAuth = emailAuth;
    }
//    public MemberEntity(String memberId, String memberPwd, Boolean emailAuth) {
//        this.memberId = memberId;
//        this.memberPwd = memberPwd;
//        this.emailAuth = emailAuth;
//    }
    
    // email 인증 처리
    public void emailVerified() {
        emailAuth = true;
    }
}
