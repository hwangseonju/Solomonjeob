# :cherries: 데이터베이스 연동 - JPA

## :tulip: JPA

-   자바 진영의 표준 인터페이스
-   본 프로젝트에서는 jpa의 hibernate 만 사용할 예정
-   객체와 ORM의로 구성됨

### jpa entity 코딩

```java
package hello.hellospring.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Member {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;
 public Long getId() {
 return id;
 }
 public void setId(Long id) {
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

### 회원 리포지토리 설정

```java
package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
public class JpaMemberRepository implements MemberRepository {
 private final EntityManager em;
 public JpaMemberRepository(EntityManager em) {
 this.em = em;
 }
 public Member save(Member member) {
 em.persist(member);
 return member;
 }
 public Optional<Member> findById(Long id) {
 Member member = em.find(Member.class, id);
 return Optional.ofNullable(member);
 }
 public List<Member> findAll() {
 return em.createQuery("select m from Member m", Member.class)
 .getResultList();
 }
 public Optional<Member> findByName(String name) {
 List<Member> result = em.createQuery("select m from Member m where
m.name = :name", Member.class)
 .setParameter("name", name)
 .getResultList();
 return result.stream().findAny();
 }
}
```

### 스프링 설정 변경

-   Jpa를 사용하려면 의존관계를 형성해주어야한다.

```java
package hello.hellospring;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
@Configuration
public class SpringConfig {
 private final DataSource dataSource;
 private final EntityManager em;
 public SpringConfig(DataSource dataSource, EntityManager em) {
 this.dataSource = dataSource;
 this.em = em;
 }
 @Bean
 public MemberService memberService() {
 return new MemberService(memberRepository());
 }
 @Bean
 public MemberRepository memberRepository() {
// return new MemoryMemberRepository();
// return new JdbcMemberRepository(dataSource);
// return new JdbcTemplateMemberRepository(dataSource);
 return new JpaMemberRepository(em);
 }
}
```

## :tulip: 스프링 데이터 JPA

-   스프링 부트와 JPA만 사용해도 개발 생산성이 정말 많이 증가하고, 개발해야할 코드도 확연히 줄어듭니다.
    여기에 스프링 데이터 JPA를 사용하면, 기존의 한계를 넘어 마치 마법처럼, 리포지토리에 구현 클래스 없이
    인터페이스 만으로 개발을 완료할 수 있습니다. 그리고 반복 개발해온 기본 CRUD 기능도 스프링 데이터
    JPA가 모두 제공합니다.

-   JPA 회원 리포지토리

```java
package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface SpringDataJpaMemberRepository extends JpaRepository<Member,
Long>, MemberRepository {
 Optional<Member> findByName(String name);
}
```

-   스프링 데이터 JPA를 사용할 수 있도록 설정

```
package hello.hellospring;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SpringConfig {
 private final MemberRepository memberRepository;
 public SpringConfig(MemberRepository memberRepository) {
 this.memberRepository = memberRepository;
 }
 @Bean
 public MemberService memberService() {
 return new MemberService(memberRepository);
 }
}
```
