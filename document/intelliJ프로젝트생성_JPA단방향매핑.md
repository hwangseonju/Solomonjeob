# IntelliJ로 Spring project 생성 및 단방향 연관관계 매핑

## 1. 프로젝트 생성

- [https://start.spring.io/](https://start.spring.io/) 를 이용해 프로젝트 생성

| Project | language | spring boot | packaging | java-version |
| --- | --- | --- | --- | --- |
| gradle | java | 2.6.2 | jar | 8 |
- Dependencies
    - lombok
    - spring web
    - mysql Driver
    - Spring Data JPA
    - Spring Security (안해도 무관)
- 설정 완료 후 generate 클릭해 프로젝트를 다운로드 받는다
- 다운로드된 프로젝트를 압축해제 후 > 마우스 우클릭 > intelliJ로 열기
- build.gradle에서 추가된 의존성을 확인할 수 있다.

### 2. 데이터베이스 connection 추가하기

- src/main/resource/application.properties
    
    ```groovy
    spring.datasource.url=jdbc:mysql://127.0.0.1:3306/dbname
    spring.datasource.username=ssafy
    spring.datasource.password=ssafy123
    spring.jpa.hibernate.ddl-auto=update
    ```
    
    - spring.jpa.hibernate.ddl-auto : 데이터 베이스 스키마에 영향을 줌
        - create : spring boot 실행시 테이블을 지우고 새로 만듬, 기존에 테이블에 저장된 데이터는 삭제됨
        - create-drop : spring boot가 종료되면 테이블을 삭제
        - update : entity 클래스와 DB스키마를 비교하여 DB에 생성되지 않은 테이블, 칼럼을 추가하며 제거는 하지 않음
        - validate : Entity 클래스와 DB스키마를 비교하여 다르면 예외를 발생시킴
        - none : 아무것도 실행하지 않음
- 최근에는 yml을 통해 가독성이 좋게 관리를 함
    
    src/main/resource/application.yml
    
    ⇒ 초기 생성시 없으므로 같은 경로에 생성하면됨
    
    ```groovy
    spring:
      datasource:
        url: jdbc:mysql://127.0.0.1:3306/covenant
        username: root
        password: password
      jpa:
        hibernate:
          ddl-auto: create
    ```
    

## 2. 연관관계 단방향 매핑

- youtube 강의 : [[토크ON세미나] JPA 프로그래밍 기본기 다지기 4강 - 연관관계 매핑 | T아카데미 - YouTube](https://www.youtube.com/watch?v=bEtTpCviSc4&list=PL9mhQYIlKEhfpMVndI23RwWTL9-VL-B7U&index=4)
1. 객체의 참조와 테이블의 외래 키를 매핑

```java
//하나의 팀당 여러명의 멤버인 경우
@Entity
public class Member{
	@Id @GeneratedValue
	private Long id;
	private int age;
	@Column(name="USERNAME")
	private String name;
	private int age;
//	@Column(name ="TEAM_ID")
//	private Long teamId;
	@ManyToOne
	@JoinColumn(name = "TEAM_ID")
	private Team team;
	...
}
@Entity class Team{
	@Id @GeneratedValue
	private Long id;
	private String name;
...
}
```

→ @ManyToOne : 객체 연관관계의 Member 객체의 team변수 와 

  테이블 연관관계에서 MEMBER 테이블의 TEAM_ID(FK)와 연관관계를 매핑 시켜줌

1. 연관관계 저장

```java
			//팀 저장
			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);
			//회원 저장
			Member member = new Member();
			member.setName("member1");
			//member.setTeamId(team.getId()); X
			member.setTeam(team); //단방향 연관관계 설정, 참조 저장
			em.persist(member);
```

1. 참조로 연관관계 조회 - 객체 그래프 탐색

```java
//조회
Member findMember = em.find(Member.class,member.getId);
//참조를 사용해서 연관관계 조회
Team findTema = findMember.getTeam();
```

- em.flush() : 쿼리를 다 보내고
- em.clear() : 캐시를 다 비움

→ 두개의 명령어를 사용하면 사용된 쿼리 확인 가능

- @ManyToOne(fetch = FetchType.LAZY) 권장 옵션 다른 옵션을 사용하려면
    
    쿼리를 날리는 시점에 꼭필요한것만 가져오는 경우가 있는데 그거만 쓰는 것을 권장.
    
1. 연관관계 수정

```java
//새로운 팀 B
Team teamB = new Team();
teamB.setName("TeamB");
em.persist(teamB);
//회원1에 새로운 팀B 설정
member.setTeam(teamB);
```