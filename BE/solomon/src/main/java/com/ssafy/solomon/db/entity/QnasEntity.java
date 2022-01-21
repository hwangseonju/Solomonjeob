package com.ssafy.solomon.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "qnas")
public class QnasEntity {
    //질문모음집 생성날짜필드 생성 : 정렬 위해
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnas_id;
    private String qnas_title;

    @JsonBackReference
    @OneToMany(mappedBy="qna_list" ,fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QnaEntity> quas_list;

    @ManyToOne
    @JoinColumn(name="memberId")
    @JsonManagedReference
    private MemberEntity user;
}

