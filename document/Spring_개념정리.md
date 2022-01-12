## Spring



##### | Spring이란?

- 자바 엔터프라이즈 애플리케이션 개발에 사용되는 애플리케이션 프레임워크
- 애플리케이션의 기본 틀 : 스프링 컨테이너=애플리케이션 컨텍스트=스프링 런타임 엔진
- 공통 프로그래밍 모델 : IoC/DI, 서비스 추상화, AOP
  - IoC/DI - 오브젝트의 생명주기와 의존관계에 대한 프로그래밍 모델
  - 서비스 추상화 - 구체적인 기술과 환경에 종속되지 않도록 유연한 추상 계층을 두는 방법
  - AOP - 부가적인 기능을 독립적으로 모듈화하는 프로그래밍 모델
- 방대한 양의 기술 API 제공



##### | DAO

- Data Access Object
- DB를 사용해 데이터를 조회하거나 조작하는 기능을 전담하도록 만든 오브젝트



> **빈(Bean)**
>
> 1. 디폴트 생성자 : 파라미터가 없는 생성자로써 툴이나 프레임워크에서 리플렉션을 이용해 오브젝트를 생성하기 때문에 필요하다.
> 2. 프로퍼티 : 빈이 노출하는 이름을 가진 속성으로, set으로 시작하는 수정자 메소드 setter와 get으로 시작하는 접근자 메소드 getter를 이용해 수정 또는 조회가 가능하다.



##### | JDBC 이용 작업 순서 (자바 개발자라면 이렇게 하면 안됨..)

1. DB 연결을 위한 Connection 가져오기
2. SQL을 담은 Statement(PreparedStatement) 만들기
3. Statement 실행
4. 실행결과는 ResultSet으로 받아서 정보를 저장할 오브젝트에 옮기기
5. 작업 중 생성된 리소스는 작업을 마친 후 반드시 닫기
6. JDBC API가 만들어낸 예ㄹ외를 잡아 직접 처리하거나, 메소드에 throws를 선언해서 예외가 발생하면 메소드 밖으로 던지기

```java
public class UserDAO {
    // 사용자 정보 받아서 저장할 오브젝트에 옮기기
    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost/springbook", "userid", "userpw");
        
        PreparedStatement ps = c.prepareStatement("select * from user where id= ?");
        ps.setString(1, id);
        
        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        
        rs.close();
        ps.close();
        c.closs();
        
        return user;
    }
}
```



##### | 관심사의 분리

- 관심이 같은 것끼리는 하나의 객체 또는 친한 객체로 모이게 하고, 관심이 다른 것은 가능한 따로 떨어져 서로 영향을 주지 않도록 분리하는 것
- 장점 : 특정 관심에 효과적으로 집중이 가능
- 중복코드 메소드 추출 -> 변경사항에 대한 검증(리팩토링 AND 테스트) -> 상속을 통한 확장



[^리팩토링]: 기존의 코드를 외부의 동작방식에는 변화없이 내부 구조를 변경해서 재구성하는 작업 또는 기술, 생산성 향상, 품질향상, 유지보수 용이, 견고하면서도 유연한 제품 개발 가능



##### | 디자인 패턴

- 소프트웨어 설계 시 특정 상황에서 자주 만나는 문제를 해결하기 위해 사용할 수 있는 재사용 가능한 솔루션
- 객체지향적 설계 원칙을 이용하여 문제 해결
- 설계 구조 = 클래스 상속 + 오브젝트 합성
- 중요한 요소 : 패턴을 적용할 상황, 해결해야할 문제, 솔루션의 구조



##### | 템플릿 메소드 패턴

- 상속을 통해 슈퍼클래스의 기능을 확장할 때 사용하는 가장 대표적인 방법
- 슈퍼클래스 - 변하지않는 기능
- 서브클래스 - 자주 변경되며 확장할 기능



##### | 팩토리 메소드 패턴

- 상속을 통해 기능을 확장하는 패턴(템플릿 메소드 패턴과 유사)
- 슈퍼클래스 - 서브클래스에서 구현할 메소드를 호출해 필요한 타입의 오브젝트를 가져와 사용
- 서브클래스 - 다양한 방법으로 오브젝트를 생성하는 메소드 재정의 가능
- 오브젝트를 생성하는 기능을 가진 메소드