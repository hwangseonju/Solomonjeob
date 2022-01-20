- JPA는 애플리케이션과 JDBC 사이에서 동작

image - 추후 수정

- JPA 동작 - 저장

image - 추후 수정

- JPA 소개

image - 추후 수정

- 생산성 - CRUD

```java
저장 : jpa.persist(member)
조회 : Member member = jpa.find(memeberId)
수정 : member.setName("변경할 이름")
삭제 : jpa.remove(member)
```

- 유지보수

기존 : 필드 변경 시 모든 SQL 수정

JPA : 필드만 추가하면 됨, SQL은 JPA가 처리

- JPA와 패러다임의 불일치 해결
    1. JPA와 상속
    2. JPA와 연관관계
    3. JPA와 객체 그래프 탐색
    4. JPA와 비교하기
    
    - JPA와 상속 - 저장
    
    image - 추후 수정
    
    - JPA와 상속 - 조회
        - 개발자가 할일
          
            Album album = jpa.find(Album.class, albumId);
            
        - 나머진 JPA가 처리
          
            SELECT I.*, A.*
            
            FROM ITEM I
            
            JOIN ALBUM A ON I.ITEM_ID = A.ITEM_ID
        
    - JPA와 연관관계, 객체 그래프 탐색
        - 연관관계 저장
          
            member.setTeam(team);
            
            jpa.persist(member);
            
        - 객체 그래프 탐색
          
            Member member = jpa.find(Member.class, memberId);
            
            Team team = member.getTeam();
        
    - JPA와 비교하기
        - String memberId = “100”;
          
            Member member1 = jpa.find(Member.class, memberId);
            
            Member member2 = jpa.find(Member.class, memberId);
            
            member 1 == member2; // 같다.
            
            동일한 트랜잭션에서 조회하는 엔티티는 같음을 보장
            
    
- JPA의 성능 최적화 기능
    1. 1차 캐시와 동일성(identity)보장
    2. 트랜잭션을 지원하는 쓰기 지연(transactional write-behind)
    3. 지연 로딩(laze loading)
    

> JPA 기초와 매핑
> 
- 객체 매핑하기
    - @Entity: JPA가 관리할 객체
      
        → 엔티티 한다.
        
    - @Id: DB PK와 매핑할 필드

```java
@Entity
public class Member{

    @Id
    private Long id;
    private String name;
    ...
}
```

```java
create table Member (
	id bigint not null,
	name varchar(255),
	primary key(id)
)
```

- persistence.xml
    - JPA 설정 파일
    - /META-INF/persistence.xml 위치
    - javax.persistence로 시작: JPA 표준 속성
      
        ```java
        <?xml version="1.0" encoding="UTF-8"?>
        <persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence" version="2.2">
        
        	<persistence-unit name="hello">
        	<properties>
        
                    <!-- 필수 속성 -->
                    <property name="javax.persistence.jdbc.driver" value="org.h2.Driver"/>
                    <property name="javax.persistence.jdbc.user" value="sa"/>
                    <property name="javax.persistence.jdbc.password" value=""/>
                    <property name="javax.persistence.jdbc.url" value="jdbc:h2:tcp://localhost/~/jpabasic"/>
                    <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect"/>
        
                    <!-- 옵션 -->
                    <property name="hibernate.show_sql" value="true"/>
                    <property name="hibernate.format_sql" value="true"/>
                    <property name="hibernate.use_sql_comments" value="true"/>
                    <property name="hibernate.hbm2ddl.auto" value="create" />
                </properties>
        						<!-- <property name="hibernate.hbm2ddl.auto" value="create" /> -->
        	</persistence-unit>
        </persistence>
        ```
        
        - hibernate.dialect : dialect(방언)
    
- 엔티티 매니저 설정

image - 추후 수정

> 필드와 매핑
> 
- 데이터베이스 스키마 자동 생성하기 주의
    - **운영장비에는 절대 create, create-drop, update 사용하면 안된다.**
    - 개발 초기 단계에는 create 또는 update
    - 테스트 서버는 update 또는 validate
    - 스테이징과 운영 서버는 validate 또는 none

⇒ create TEAM을 한번 하고, 나중에 컬럼을 추가하면 기존의 테이블은 drop을 시켜버리고 다시 create를 하기 때문에 위험하다.

- 매핑 어노테이션
    - @Column
      
        ```java
        @Entity
        public class Member{
        
            @Id @GeneratedValue
            private Long id;
        
            @Column(name = "USERNAME")
            private String name;
            
            private int age;
        
            @Temporal(TemporalType.TIMESTAMP)
            private Date regDate;
        
            @Enumerated(EnumType.STRING)
            private MemberType memberType;
            ...
        }
        ```
        
        → 컬럼명 정의
        
        - 가장 많이 사용됨
        - name: 필드와 매핑할 테이블의 컬럼 이름
        - insertable, updatable: 읽기 전용
        - nullable: null 허용여부 결정, DDL 생성시 사용
        - unique: 유니크 제약 조건, DDL 생성시 사용
        - columnDefinition, length, precision, scale (DDL)
    
    - @Temporal
        - 날짜 타입 매핑
        - @Temporal (Temporal Type.DATE)
          
            private Date date; // 날짜
            
        - @Temporal (Temporal Type.DATE)
          
            private Date time; // 시간
            
        - @Temporal (Temporal Type.DATE)
          
            private Date timestamp; // 날짜와 시간
            
    
    - @Enumerated
        - 열거형 매핑
        - EnumType.ORDINAL: 순서를 저장(기본값)
        - EnumType.STRING: 열거형 이름을 그대로 저장, 가급적 이것을 사용
        - @Enumerated (EnumType.STRING)
          
            private RoleType roleType;
            
    
    enum file 생성 → public enum MemberType {
    
    USER, ADMIN
    
    }과 같은 (열거형) 연관된 상수들의 집합
    
    ```java
    @Entity
    public class Member{
    
        @Id @GeneratedValue
        private Long id;
    
        @Column(name = "USERNAME")
        private String name;
        private int age;
    
        @Enumerated(EnumType.STRING)
        private MemberType memberType;
    
        public Long getId() { reurn id; }
    
        public void setId(Long id) { this.id = id; }
    
        public String getName() { reurn name; }
    
        public void setName(String name) { this.name = name; }
    
        public int getAge() { reurn age; }
    
        public MemberType getMemberType { 
        	return memberType; 
        }
    }
    ```
    
    → 커서 위치와 같이 반드시 STRING으로 해야한다. 
    
    예를 들어,  @Enumerated(EnumType.ORDINAL)으로 했을 경우 1번째인 ADMIN을 선택 시 1로 들어간다 하지만 만약 enum에 USER, VIP, ADMIN처럼 추가가 되면 1번째는 VIP로 바뀌게 되기 때문이다.
    
    - @Lob
        - CLOB, BLOB 매핑
        - CLOB: String, char[], java.sql.CLOB
        - BLOB: byte[], java.sql.BLOB
          
            @Lob
            
            private String lobString;
            
            @Lob
            
            private byte[ ] lobByte;
            
    
    - @Transient
        - 이 필드는 매핑하지 않는다.
        - 애플리케이션에서 DB에 저장하지 않는 필드
    
- 식별자 매핑 어노테이션
  
    ```java
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    ```
    
    - @Id
    - @GeneratedValue
        - @Id(직접매핑)
        - IDENTITY: 데이터베이스에 위임, MYSQL
        - SEQUENCE: 데이터베이스 시퀀스 오브젝트 사용, ORACLE
            - @SequenceGenerator 필요
        - TABLE: 키 생성용 테이블 사용, 모든 DB에서 사용
            - @TableGenerator 필요
        - AUTO: 방언에 따라 자동 지정, 기본값