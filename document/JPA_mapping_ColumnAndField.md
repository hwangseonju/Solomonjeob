# JPA 필드와 컬럼 매핑

## 1. 데이터베이스 스키마 자동 생성하기

- DDL을 어플리케이션 실행 시점에 자동 생성
- 테이블 중심 → 객체 중심
- DB방언을 활용해서 DB에 맞는 적절한 DDL 생성
- 이렇게 생성된 DDL은 개발 장비에서만 사용
- 생성된 DDL은 운영서버에서는 사용하지 않거나 적절히 다듬은 후 사용.
- hibername.hbm2ddl.auto의 옵션
    - create : 기존 테이블 삭제 후 다시 생성(Drop+Create) ,운영장비 절대 사용금지
    - create-drop : create와 같으나 종료 시점에 테이블 DROP(테스트 코드 작성시 도움됨, 운영장비 사용 금지)
    - update : 변경분만 반영(컬럼 추가시 alter로 추가됨, 운영 DB에는 사용하면 안됨 테이블 잘못되면 락걸림..)
    - validate: 엔티티와 테이블이 정상 매핑되었는지만 확인
    - none : 사용하지 않음
- 스키마 자동생성 주의
    - 운영장비에 create, create-drop, update 사용하면 안됨
    - 개발 초기단계는 create 또는 update
    - 테스트 서버는 update또는 validate
    - 스테이징과 운영서버는 validate 또는 none

```java
//persistence.xml
<property name="hibernate.hbm2ddl.auto" value="create" />
<property name="hibernate.hbm2ddl.auto" value="create-drop" />
```

## 2. 매핑 어노테이션

```java
@Entity
public class Member{
	@Id @GeneratorValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="USERNAME")
	private String name;
	@Enumerated(EnumType.STRING)
	private MemberType memberType;
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;
	@Enumerated(EnumType.STRING)
	private MemberType memberType;
	@Lob
	private String lobString;
	@Lob
	private byte[] lobByte;
}
/////////////////////////////////////
public enum MemberType{
	USER, ADMIN
}
```

- @Column
    - 가장많이 사용됨
    - name: 필드와 매핑할 테이블의 컬럼 이름
    - insertable, updatable : 읽기 전용
    - nullable : null 허용 여부 결정, DDL 생성시 사용
    - unique : 유니크 제약 조건, DDL 생성시 사용
    - columnDefinition, length, precision, scale(DDL)
    - length :길이...
- @Temporal
    - 날짜 타입 매핑
    - DATE, TIME, TIMESTAMP (ex . TemporalType.TIMESTAMP)
- @Enumerated
    - 열거형 매핑
    - EnumType.ORDINAL : 순서를 저장(기본 값)
    - EnumType.STRING : 열거형 이름을 그대로 저장, 가급적 이것을 사용
    - 현업에서는 무조건 STRING으로 쓸 것 ,아니면 0,1,2같은 순서 정수로 표현되는데 중간에 다른 변수가 생기면 전체 다꼬이게 됨
- @Lob : 컨텐츠 길이가 길어서 바이너리로 사용시 씀
    - CLOB,BLOB 매핑
    - CLOB : String ,char[], java.sql.CLOB
    - BLOB : byte[], java.sql.BLOB
- @Transient
    - 매핑은 안하지만 객체에 넣어두고 싶을 때 사용
    - 임시 플래그 같은 것들
    - 자주 쓰지않는 것을 추천

## 3. 식별자 매핑 어노테이션

- @Id (직접 매핑) ⇒ 방언에 따라서 자동으로 매핑된다.
    - IDENTITY : 데이터베이스에 위임, MYSQL
    - SEQUENCE : DB 시퀀스 오브젝트 사용, ORACLE
        - @SequenceGenerator 필요
    - TABLE : 키 생성용 테이블 사용, 모든 DB에서 사용
        - @TableGenerator 필요
    - AUTO : 방언에 따라 자동 지정, 기본 값
- 권장하는 식별자 전략
    - 기본키 제약 조건 : null 아님, 유일, 변하면 안됨
    - 미래까지 이 조건을 만족하는 자연키는 찾기 어려움, 대리키(대체키)를 사용할 것
    - 예를 들어 주민등록번호도 기본 키로 적절하지 않음
    - 권장 : Long + 대체키 + 키 생성전략 사용
        - Long ⇒ int사용시 10억사용자면 금방 차버림
        - 대체키(비즈니스와 관계없는 거나 유효 아이디) 유효아이디도 중복은 확인해야됨..