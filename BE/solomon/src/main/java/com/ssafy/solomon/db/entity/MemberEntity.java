package com.ssafy.solomon.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue
    private long memberIdx;
    private String memberId;

    @Column(length = 50)
    private String memberPwd;
    @Column(length = 50)
    private String memberKey;
    @Column(length = 50)
    private String memberSns;

    //하나의 유저는 여러 문제집을 가질 수 있음
    @JsonBackReference
    @OneToMany(mappedBy="user" ,fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QnasEntity> qnasCode;
}
