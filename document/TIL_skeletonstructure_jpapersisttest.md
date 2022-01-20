# JPA 구조 TIL

## 1. 스켈레톤 코드 구조 공부

<aside>
💡 스켈레톤 간략한 구조 공부로 스켈레톤 코드에 대한 주석을 작성했음.

</aside>

```java
.
└── main
    ├── generated
    ├── java
    │   └── com
    │       └── ssafy
    │           ├── GroupCallApplication.java
    │           ├── api  /* REST API 요청관련 컨트롤러, 서비스, 요청/응답 모델 정의*/
    │           │   ├── controller /* 컨트롤러 기존 방식과 유사 */
    │           │   │   ├── AuthController.java
    │           │   │   └── UserController.java
    │           │   ├── request /* lombok사용시 @DATA어노테이션을 쓰는 곳 DTO와 유사*/
    │           │   │   ├── UserLoginPostReq.java
    │           │   │   └── UserRegisterPostReq.java
    │           │   ├── response /* controller에서 응답바디 설정할때 쓰이는것을 확인 가능*/
    │           │   │   ├── UserLoginPostRes.java //로그인에 대한 토큰 인증
    │           │   │   └── UserRes.java  //본인정보 확인하는 응답return
    │           │   └── service /*기존과 같이 비즈니스 로직을 작성하는 곳*/
    │           │       ├── UserService.java
    │           │       └── UserServiceImpl.java
    │           ├── common /* 공용 유틸, 응답 모델, 인증, 예외처리 관련 정의*/
    │           │   ├── auth
    │           │   │   ├── JwtAuthenticationFilter.java
    │           │   │   ├── SsafyUserDetailService.java
    │           │   │   └── SsafyUserDetails.java
    │           │   ├── exception
    │           │   │   └── handler
    │           │   │       └── NotFoundHandler.java
    │           │   ├── model
    │           │   │   └── response
    │           │   │       └── BaseResponseBody.java
    │           │   └── util
    │           │       ├── JwtTokenUtil.java
    │           │       └── ResponseBodyWriteUtil.java
    │           ├── config /* WebMvc 및 JPA, Security, Swagger 등의 추가 플러그인 설정 정의*/
    │           │   ├── JpaConfig.java
    │           │   ├── SecurityConfig.java
    │           │   ├── SwaggerConfig.java
    │           │   └── WebMvcConfig.java
    │           └── db /* 디비에 저장될 모델 정의 및 쿼리 구현 */
    │               ├── entity /*Entity를 정의 하는 곳*/
    │               │   ├── BaseEntity.java
    │               │   └── User.java
    │               └── repository /*쿼리를 자동으로 생성해주는곳 (mapper와 비슷) , 필요한 쿼리는 이곳에 구현*/
    │                   ├── UserRepository.java
    │                   └── UserRepositorySupport.java
```

## skeleton 코드 정리

- controller : Mybatis 프로젝트 당시 컨트롤러와 기능이 같음
- service : Mybatis 프로젝트 당시 service와 기능이 같다.
- response : controller에서 응답body를 구성 시 호출 (인증 정보 등...)
- request : 기존 프로젝트의 DTO와 유사한 파일들
- entity : 기존mapper 클래스 처럼 entity 모델을 정의 해주는 기능을 모아둠
- repository : 기존 mapper.xml에서 쿼리를 정의했던것 처럼 JPA가 쿼리를 자동 생성하고 쿼리를 날려주는 기능을 모아둠

## 2. 오늘 회고

> `EntityManagerFactory emf = Persistence.*createEntityManagerFactory*("test");`
> 

영속성 테스트를 해보다가 코드에서 문제가 발생

---

Exception in thread "main" javax.persistence.PersistenceException: No Persistence provider for EntityManager named test
at javax.persistence.Persistence.createEntityManagerFactory(Persistence.java:86)
at javax.persistence.Persistence.createEntityManagerFactory(Persistence.java:55)
at com.example.demo.DemoApplication.main(DemoApplication.java:18)

---

- Spring boot 는 persistence.xml을  검색하지 않기 때문에 (전역변수가 아님) localManagerFactory를 따로 생성해야 된다는것 같음( 전통적인 방식으로)
    - 생성할 떄 참고 [https://github.com/spring-projects/spring-boot/blob/v2.6.2/spring-boot-project/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/orm/jpa/JpaBaseConfiguration.java](https://github.com/spring-projects/spring-boot/blob/v2.6.2/spring-boot-project/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/orm/jpa/JpaBaseConfiguration.java)
    - 참조 문서 : [https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-access.use-custom-entity-manager](https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-access.use-custom-entity-manager)
    - 8.11
- SpringBoot 환경에서 작동하지 않음.
- gradle을 사용하는 환경에서는 어떻게 따로 설정해야 되는지 더 공부가 필요할 것 같음..