# :cherries: RESTful service

---

## :tulip: 개요

### :one: Web Service & Web Application

-   네트어크 상에서 서로 다른 종류의 컴퓨터들 간에 상호작용하기 위한 소프트웨어 시스템

### :two: RESTful

-   Soap방식에 대체된 방법
-   Resource의 Representation에 의한 상태 전달
-   HTTP Method를 통해 Resource를 처리하기 위한 아키텍처
    -   REST는 기본적으로 웹의 기존 기술과 HTTP 프로토콜을 그대로 활용하기 때문에 웹의 장점을 최대한 활용할 수 있는 아키텍처 스타일이다.
    -   REST는 네트워크 상에서 Client와 Server 사이의 통신 방식 중 하나이다.

#### :smile: 자원이란?

-   해당 소프트웨어가 관리하는 모든것
-   ex) 문서, 그림, 데이터, 해당 소프트웨어 자체 등

#### :smile: 자원의 표현이란?

-   자원의 표현 : 그 자원을 표현하기 위한 이름
-   DB의 학생정보가 자원일 때, 'student'를 자원의 표현으로 정한다.

#### :smile: 상태 정보 전달

-   데이터가 요청되어지는 시점에서 자원의 상태를 전달한다.
-   Json이나 XML을 통해 데이터를 주고 받는 것이 일반적이다.

#### :smile: SOAP vs REST

-   restrictions vs architectural approach
-   Data Exchange Format
-   Service Definition
-   Transport
-   Ease of implementation

## :tulip: spring boot

### :one: spring boot

-   packaging: 끝나고 압축할 때 어떤파일로 할지, JAR or WAR 설정

### :two: restfulAPi 맛보기

-user -> posts
![restful](https://user-images.githubusercontent.com/51367515/104756760-8e13a200-579f-11eb-8e77-deba5c22b3c4.PNG)

### :three:

-   packaging: 끝나고 압축할 때 어떤파일로 할지, JAR or WAR 설정
-   사용 dependency: spring boot devTool, Lombook, Spring Web, Spring Data JPA, H2 Database -> pom.xml에서 확인 가능
-   LifeCycle
    -   clean: 지금 까지 빌드 혹은 패키징 했던 모든 파일을 지움
    -   compile : complie 후 클래스 생성
    -   package : jar 또는 war 파일로 packaging
    -   install : local 서버에 배포
-   실행시 Embedded tomcat 실행-> 8080 포트번호로 실행
    -   만약 포트번호를 바꾸고 싶다면?->application.yml(요즘 더 선호) or application.property 에서 변경가능

```
server:
    port: 8088
```

### :four: HelloController 실행

-   Controller가 아닌 RestController 사용
-   @GetMapping(path = "/hello-world") 형식으로 GET 함수 사용
    -   (= @RequestMapping(Method=RequestMethod.GET, path="/hello-world")
-   postMan으로 출력 결과 확인 가능
    ![postman](https://user-images.githubusercontent.com/51367515/104822442-dc02d580-5885-11eb-9f7e-6b564fc16cab.PNG)

### :five: HelloController 실행

```java
@GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World");

    }
```

-   위와 같은 코드 작성 후 HelloWorldBean이라는 클래스가 존재하지 않음을 알 수 있으므로 생성 해준다
    -   lombok dependency를 사용할 때 setter, getter, 생성자 등을 자동으로 생성해준다->@Data,@AllArgsConstructor annotation으로 사용가능
        -   :smile: intelliJ에서 lombok설치 필수

```java
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldBean {
    private String message;

}
```

-   RestController에서 java bean형태로 반환할 경우 json 형식으로 변환에서 반환

### :six: DispatcherServlet과 프로젝트 동작의 이해

-   Spring boot 동작원리
    -   application.yml or application.properties 를 사용해서 관리가능
        -   application.property -> 설정이름=값
        -   application.yml -> 설정이름:값
        -   ```
            logging:
                level:
                    org.springframework: DEBUG
            ```
        ```

        ```
    -   DisplatcherServlet -> '/'
        -   클라이언트의 모든 요청을 한곳으로 받아서 처리(일종의 Gate 역할)
        -   요청에 맞는 handler로 요청을 전달
        -   handler의 실행 결과를 Http Response 형태로 만들어서 반환
    -   RestController
        -   Spring4부터 @RestController 지원
        -   @Controller + @ResponseBody
        -   View를 갖지 않는 REST DATA(JSON/XML)을 반환
            ![RestController](https://user-images.githubusercontent.com/51367515/104823130-26d31c00-588b-11eb-9a11-6cf9bdc33e49.PNG)

### :seven: Path Variable

-   가변 데이터로 사용할 수 있음

## :tulip: user Service API 구현

### :one: user 도메인 클래스 생성

-   user class 생성

```java
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private Date joinDate;
}
```

-   controller 수정

```java
package com.example.restfulwebservice.user;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoService {
    private static List<User> users= new ArrayList<>();
    private static int usersCount=3;
    static {
        users.add(new User(1, "Kenneth", new Date()));
        users.add(new User(2, "Alice", new Date()));
        users.add(new User(3, "Elena", new Date()));
    }

        public List<User> findAll(){
            return users;
        }
        public User save(User user){
            if(user.getId()==null){
                user.setId(++usersCount);
            }
            users.add(user);
            return user;

        }

        public User findOne(int id){
            for (User user : users){
                if (user.getId()==id){
                    return user;
                }
            }
            return null;
        }
    }
```

### :two: 사용자 목록 조회 구현

-   User controller 구축
    -   의존성 주입 필요(생성자 이용)
    -   :smile: 스프링에 등록되서 사용되는 인스턴스를 '빈'이라고 부른다
    -   @component를 이용하여 bean 등록

```java
package com.example.restfulwebservice.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service=service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        return service.findOne(id);
    }
}

```

### :three: 사용자 등록 구현

-   post 값을 사용해서 사용자를 등록 할 수 있다.

```java
 @PostMapping("/users")
    public void createUser(@RequestBody User user){
        User savedUser = service.save(user);


    }
```

-   postman에서 이를 실행해서 users를 추가할 수 있음

```json
{
    "name": "New User",
    "joinDate": "2021-01-17T14:47:04.621+00:00"
}
```

### :four: HTTP Status Code 제어

```java
@PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest(). //사용자에게 요청값을 반환해주기 위함
                path("/{id}").
                buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
```

-   위와 같은 User 보내주면
    ![http-status](https://user-images.githubusercontent.com/51367515/104848181-2720f500-5927-11eb-84a5-3796fde82b78.PNG)

-   만약 등록되지 않은 ID를 조회한다해도 status 코드는 200Ok가 나온다
    ![200](https://user-images.githubusercontent.com/51367515/104848298-ac0c0e80-5927-11eb-9c36-827f596564c0.PNG)

-   참조가 되지 않는 ID를 조회해도 OK라고 나온 것은 오류를 점검하기에 맞지 않으므로 exception handling기능을 추가하는 코드를 넣어준다

```java
 @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = service.findOne(id);
        if(user==null){
            throw new UserNotFoundException(String.format("ID[%s} not found",id));
        }
        return user;
    }
```

![오류](https://user-images.githubusercontent.com/51367515/104848616-36a13d80-5929-11eb-8ef4-5b262c23e066.PNG)

-   그외에도 여러가지 오류를 확인할 수 있다.
-   예외처리한 클래스를 클라이언트에게 보냈을 경우 보안상의 문제가 있을수도 있으므로 status code를 변경해야한다.
    -   :smile: HTTP Status code
        -   2xx ->OK
        -   4xx -> client의 오류
        -   500 -> Server의 오류

```java
package com.example.restfulwebservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
```

![404](https://user-images.githubusercontent.com/51367515/104849281-2a6aaf80-592c-11eb-9241-39be6735927d.PNG
