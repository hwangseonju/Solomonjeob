### JPA(Java Persistence API)

---



##### :one: SQL을 직접 다룰 때 발생하는 문제점

- 객체를 DB에 CRUD하려면 너무 많은 SQL과 JDBC API를 코드로 작성해야 함 -> 비슷한 일 반복 -> 지루함 + 생산성 감소
- DAO를 통해 어떤 SQL이 실행되고 있고, 어떠한 결과가 나오는지 일일이 확인해야함
- 강한 의존 관계 -> 연관성 증가
- 처음 실행하는 SQL에 따라 객체 그래프를 어디까지 탐색할 수 있는지 제한됨



##### :two: 패러다임 불일치

- 객체와 RDBMS가 지향하는 목적이 달라 둘의 기능과 표현 방법이 다름에서 발생하는 문제
- 객체 구조를 테이블 구조에 정하는데 한계가 존재함
- ex) 상속, 연관관계(객체=참조, 테이블=FK) 등



##### :three: JPA란?

- 자바 진영의 ORM 기술 표준
- 인터페이스의 모음!
- 자바 애플리케이션과 JDBC 사이에서 동작

![jpa 구조](./photo/jpa 구조.png)



##### :four: ORM이란?

- Object-Relational Mapping
- 객체는 객체대로, RDBMS는 RDBMS대로 설계 -> ORM 프레임워크가 중간에서 매핑
- 객체와 테이블을 매핑해서 **패러다임의 불일치 문제 해결**
- 하이버네이트 : 대부분의 패런다임 불일치 문제를 해결해주는 성숙한 ORM 프레임워크(가장 많이 사용)



##### :five: JPA 사용 이유

1. 생산성
   - 반복적인 작업(쿼리 작성+JDBC API 사용) 감소

2. 유지보수
   - 의존성 감소로 인해 수정할 코드 라인수 감소

3. 패러다임 불일치 해결
4. 성능
   - 애플리케이션과 DB 사이에서 다양한 성능 최적화 기회 제공
5. 데이터 접근 추상화와 벤더 독립성
   - 애플리케이션과 DB 사이에 추상화된 데이터 접근 계층 제공 -> 특정 DB 기술에 종속되지않음



##### :six: JPA가 제공하는 CRUD API

1. 저장 기능
   - persist() : 객체를 DB에 저장, INSERT 쿼리
2. 조회 기능
   - find() : 객체 하나를 DB에서 조회, SELECT 쿼리
3. 수정 기능
   - 별도의 수정 메소드 제공하지 않음
   - 객체를 조회해서 값을 변경하면 트랜잭션을 커밋할 때 DB에 적절한 UPDATE 쿼리 전달
4. 연관 객체 조회
5. 삭제
   - remove() : DB에서 객체 삭제, DELETE 쿼리



##### :seven: JPA의 성능 최적화 기능

- 1차 캐시와 동일성 보장 : 같은 트랜잭션 안에서는 같은 엔티티 반환 -> 조회 기능 향상
- 트랜잭션을 지원하는 쓰기 지연
- 지연 로딩(Lazy Loading) : 객체가 실제 사용될 때 로딩됨(객체를 가져온다고 해서 쿼리가 바로 실행되는게 아님!)


<br>


### JPA 기초 & 매핑

---



##### :one: 객체 매핑하기

- @Entity : JPA가 관리할 객체 (엔티티)
- @Id : DB PK와 매핑할 필드

[ JPA ]

```java
@Entity
public class Member {
	
	@Id
	private String id;
	private String password;
	
}
```

[ SQL 결과 ]

```sql
create table User {
	id varchar(255),
	password varchar(255),
	primary key(id)
}
```



##### :two: persistence.xml

- JPA 설정 파일
- 위치 : /META-INF/persistence.xml



##### :three: DB 방언

- JPA는 특정 DB에 종속적이지 않은 기술
- 각 DB가 제공하는 SQL 문법과 함수가 조금씩 다름

![db방언 구조](./photo/db_방언.png)



##### :four: 애플리케이션 개발

1. 엔티티 매니저 팩토리 설정
   - 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에 공유해야됨
2. 엔티티 매니저 설정
   - 엔티티 메니저는 스레드 간에 공유하면 안됨(사용하고 버리기!)
3. 트랜잭션
   - JPA의 모든 데이터 변경 발생하는 곳
4. 비즈니스 로직(CRUD)


<br>


### 필드와 컬럼 매핑

---



##### :one: DB 스키마 자동 생성

- DDL을 애플리케이션 실행 시점에 자동 생성
- 테이블 중심 -> 객체 중심
- DB 방언을 활용해 DB에 맞는 적절한 DDL 생성 -> 생성된 DDL은 **개발 장비에서만 사용**
- 생성된 DDL은 운영서버에서는 사용하지않거나 적절히 다듬은 후 사용



##### :two: DB 스키마 자동 생성 기능 (hibernate.hbm2ddl.auto)

- create : 기존테이블 삭제 후 다시 생성(DROP + CREATE)
- create-drop : create와 같으나 <u>종료시점에 테이블 DROP</u>
- update : 변경된 부분만 반영(운영DB에는 사용X)
- validate : 엔티티와 테이블이 정상 매핑되어있는지만 확인
- none : 사용하지 않음



​	:hand: <span style="color:red">주의사항</span>

		- 운영장비에서는 절대 create, create-drop, update 사용하면 안됨!
	
		- 개발 초기 단계 : create OR update
		- 테스트 서버 : update OR validate
		- 스테이징과 운영 서버 : validate OR none



##### :three: 매핑 어노테이션

- @Column(value ="값 지정") : 테이블 컬럼과 객체를 매핑
  - name : 객체명과 별개로 테이블 컬럼명 따로 지정
  - insertable, updateable : 읽기 전용
  - nullable : null 허용여부 결정, DDL 생성시 사용(not null 설정하는것)
  - upique : 유니크 제약 조건, DDL 생성시 사용
  - columnDefinition, length, precision, scale ...
- @Temporal(TemporalType.value) : 시간 기능 지원
  - TIMESTAMP : 날짜 + 시간
  - DATE : 날짜
  - TIME : 시간
- @Enumerated (EnumType.value) : 열거형 매핑, value는 STRING을 적극 권장함 why? 수정시 꼬이게 될 수 있음..
  - ORDINAL : 순서를 저장(default)
  - STRING(권장) : 열거형 이름을 그대로 저장
- @Lob : CLOB, BLOB 매핑
  - CLOB : String, char[]..
  - BLOB : byte[]..
- @Transient : 해당 필드는 매핑하지않음, 애플리케이션에서 DB에 저장하지않는 필드

[ 실습 ]

```java
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)	// 값 자동 생성
    private int idx;
    private String id;
    @Column(name = "PW", nullable = false, length = 20)	// 컬럼명은 PW, not null 제약조건 추가, 길이는 20
    private String password;
    private String name;
    private int age;

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
```

```java
 		// hello라는 persistence-name으로 설정한다는 의미
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();    // 트랜잭션 얻어오기
        tx.begin();     // 트랜잭션 시작

        try {
            // 등록
            Member member = new Member();
            member.setId("ssafy");
            member.setPassword("ssafy123");

            em.persist(member);     // 저장
            tx.commit();            // 커밋
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
```

![ddl 실습](./photo/h2_ddl_query.PNG)
![dml 결과](./photo/h2_dml.PNG)


[ 결과 ]

![ddl 결과](./photo/h2_ddl.PNG)




##### :four: 식별자 매핑 어노테이션

- @Id @GeneratedValue(stratey = GenerationType.value)
  - IDENTITIY : DB에 위임, MYSQL 
  - SEQUENCE : DB 시퀀스 오브젝트 사용, ORACLE ( @SequenceGenerator 필요 )
  - TABLE : 키 생성용 테이블 사용, 모든 DB 사용 가능 ( @TableGenerator 필요 )
  - AUTO : DB 방언에 따라 자동 지정(위의 3가지 중 하나를 자동 지정해줌), DEFAULT


<br>


### :star: 연관관계 매핑1 :star: 

---



##### :one: 단방향 매핑

- @ManyToOne : 다대일 관계에서 사용
- @JoinColumn : FK를 매핑할 때 사용

[ 실습 ]

```java
@Entity
public class Member {

    @Id @GeneratedValue
    private int id;

    @Column(name = "USERNAME")
    private String name;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "TEAM_ID", referencedColumnName = "id")	// JoinColumn은 생략가능한 어노테이션이지만 현재 사용하면 오류 발생..
    private Team team;

//    @Column(name = "TEAM_ID")
//    private int teamId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
```

```java
@Entity
public class Team {

    @Id @GeneratedValue
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        // hello라는 persistence-name으로 설정한다는 의미
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();    // 트랜잭션 얻어오기
        tx.begin();     // 트랜잭션 시작

        try {
            // 팀 저장
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            // 회원 저장
            Member member = new Member();
            member.setName("test");
            member.setTeam(team);   // 단방향 연관관계 설정, 참조 저장
            em.persist(member);
            
            em.flush();     // DB에 쿼리 전송
            em.clear();     // 캐쉬 비우기

            // 회원 조회
            Member findMember = em.find(Member.class, member.getId());
            // 참조를 사용해서 연관관계 조회
            Team findTeam = findMember.getTeam();

            findTeam.getName();

            tx.commit();            // 커밋
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
```



[ 결과 ]

![실습 결과1](./photo/result1.PNG)



<br>



### :star: 연관관계 매핑2 :star: 

---



##### :one: 객체의 양방향 매핑

- 객체의 양방향 관계는 사실 양방향 관계가 아니라 <u>서로 다른 단방향 관계가 2개인 것!</u>

- 객체를 양방향으로 참조하려면 단방향 연관관계를 2개 만들어야 함

  ex) A → B (a.getB()), B → A(b.getA())



##### :two: 테이블의 양방향 매핑

- 테이블은 <u>FK 하나</u>로 두 테이블의 연관관계를 관리 가능
- FK를 이용하여 양쪽 테이블 조인!



##### :three: 양방향 매핑 규칙

1. 객체의 두 관계 중 하나를 연관관계의 주인으로 지정
2. 연관관계의 주인만이 FK 관리! 
3. 주인이 아닌 쪽은 읽기만 가능
4. 주인은 mappedBy 속성 사용 X
5. 주인이 아니면 mappedBy 속성으로 주인 지정



##### :four: 연관관계 주인 정하는 방법

- FK가 있는 곳



##### :five: 양방향 매핑의 장점

- 단방향 매핑만으로도 이미 연관관계 매핑은 완료
- 양방향 매핑은 반대 방향으로 조회 기능이 추가된 것 뿐!
- JPQL에서 역방향으로 탐색할 일 많음
- 단방향 매핑을 잘하고 양방향은 필요할 때 추가해도 됨(결국 양방향은 단방향 매핑이 2개인 것이기 때문) → 테이블에 영향 X



##### :six: 연관관계 매핑 어노테이션

- 다대일 : @ManyToOne
- 일대다 : @OneToMany
- 일대일 : @OneToOne
- 다대다 : @ManyToMany
- @JoinColumn, @JoinTable



##### :seven: 상속 관계 매핑 어노테이션

- @Inheritance
- @DiscriminatorColumn
- @DiscriminatorValue
- @MappedSuperclass(매핑 속성만 상속)



##### :eight: 복합키 어노테이션

- @IdClass
- @EmbeddedId
- @Embeddable
- @MapId



<br>



### JPA 내부구조

---



#####  :one: EntitiyManagerFactory & EntityManager

![EntityManager 구조](./photo/entitymanager_구조.PNG)



##### :two: 영속성 컨텍스트 :star2:

- JAP를 이해하는데 가장 중요!!
- "<u>Entitiy를 영구 저장하는 환경</u>"
- EntityManager.persist(entity)

- 논리적인 개념(눈에 보이지 않음) → EntityManager를 통해 접근



##### :three: 엔티티의 생명주기

- 비영속(new/transient)

  - 영속성 컨텍스트와 전혀 관계가 없는 상태

  ex ) 

  ```java
  // 객체를 생성한 상태
  Member member = new Member();
  member.setId("member1");
  member.setUsername("회원1");
  ```

- 영속(managed)

  - 영속성 컨텍스트에 저장된 상태

  ex)

  ```java
  // 객체를 생성한 상태
  Member member = new Member();
  member.setId("member1");
  member.setUsername("회원1");
  
  EntityManager em = emf.createEntityMangaer();
  em.getTransaction().begin();
  
  // 객체를 저장한 상태
  em.persist(member);
  ```

- 준영속(detached)

  - 영속성 컨텍스트에 저장되었다가 분리된 상태

- 삭제(removed)

  - 삭제된 상태



##### :four: 영속성 컨텍스트의 장점

- 1차 캐시

  ⇒ 조회를 할때 캐시를 찾아보고 없으면 그때 DB에서 조회해봄

- 동일성 보장

  ⇒ 1차 캐시로 반복 가능한 읽기 등급의 트랜잭션 격리 수준을 DB가 아닌 애플리케이션 차원에서 제공

- 트랜잭션을 지원하는 쓰기 지연

  ⇒ 쿼리가 발생할 때마다 DB에 접근하는 것이 아닌 커밋 전까지 버퍼 개념처럼 쿼리를 쌓아뒀다가 한번에 반영!

  ⇒ transaction.commit();

- 변경 감지(Dirty Checking)

  ⇒ update 메소드가 따로 없음

  ⇒

- 지연 로딩



##### :five: 플러시

- 영속성 컨텍스트의 변경내용을 DB에 반영
- 변경 감지
- 수정된 Entity 쓰기 지연 SQL 저장소에 등록
- 쓰기 지연 SQL 저장소의 쿼리를 DB에 전송
- 플러시 호출 방법
  1. em.flush() : 직접 호출
  2. 트랜잭션 커밍 : 플러시 자동 호출
  3. JPQL 쿼리 실행 : 플러시 자동 호출

- 영속성 컨텍스트를 비우지 개념 X  → 비우는 것은 clear()라고 따로 개념이 존재함

  ⇒ 즉, 영속성 컨텍스트의 변경내용을 DB에 동기화 하는것이 플러쉬!

- 트랜잭션이라는 작업 단위 중요 → **커밋 직전에만 동기화하면 됨**



##### :six: 프록시와 즉시로딩 주의

- 가급적 지연 로딩 사용(LAZY) → @어노테이션(fetch = FetchType.LAZY)

- 즉시로딩을 적용하면 예상치 못한 SQL 발생하는 문제 생성

- @ManyToOne, @OneToOne은 default가 즉시로딩이기 때문에 LAZY로 따로 설정해줘야함

  (@OneToMany, @ManyToMany는 default가 지연로딩)



<br>



### JPA와 객체지향 쿼리

---



##### :one: JPQL

- JPA를 사용하면 Entity 객체를 중심으로 개발
- 검색을 할 때도 테이블이 아닌 Entity 객체를 대상으로 검색
- 모든 DB 데이터를 객체로 변환해서 검색하는 것은 불가능!
- 애플리케이션이 필요한 데이터만 DB에서 불러오려면 결국 검색 조건이 포함된 SQL 필요
- <u>객체지향 SQL</u>



##### :two: JPQL 문법

- 엔티티와 속성은 대소문자 구분(ex. Member, username)
- JPQL 키워드는 대소문자 구분 안함(ex. SELECT, from)
- 엔티티 이름을 사용(테이블 이름 X)
- 별칭은 필수!
- query.getResultList() : 결과가 하나 이상, 리스트 반환
- query.getSingleResult() : 결과가 정확히 하나, 단일 객체 반환



##### :three: ​페이징 API

- setFirstResult(int startPosition) : 조회 시작 위치(0부터 시작)
- setMaxResults(int maxResult) : 조회할 데이터 수



##### :four: 조인

- innerJoin
- outerJoin
- 세터조인
- **fetchJoin**



##### :five: Named 쿼리

- 미리 정의해서 이름을 부여해두고 사용하는 JPQL
- 어노테이션, XML에 정의
- 애플리케이션 로딩 시점에 초기화 후 재사용
- 애플리케이션 로딩 시점에 쿼리를 검증

ex)

```java
// 정의 방법
@Entity
@NamedQuery(
	name = "Member.findByUsername",
	query="select m from Member m where m.username = :username")
public class Member {	
	...
}

// 사용 방법
List<Member> resultList =
	em.createNamedQuery("Member.findByUsername", Member.class)
		.setParameter("username", "회원1")
		.getResultList();
```


