package com.ssafy.solomon.db.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="qna")
public class QnaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qna_id;                //식별자 랜덤값
    private String qna_content;       //질문 내용
    //    @Temporal(TemporalType.TIMESTAMP)
    private int qna_time;              //시간설정
    private String qna_answer;          //답변

    //1개의 문제집당 여러 문제등록 가능
    @ManyToOne
    @JoinColumn(name="qnas_id")
    @JsonManagedReference
    private QnasEntity qna_list;
}
