## AOP가 필요한 상황

-   모든 메소드의 호출 시간을 측정하고 싶다면?
-   공통 관심 사항(cross-cutting concern) vs 핵심 관심 사항(core concern)

![AOP로직](https://user-images.githubusercontent.com/51367515/103616819-7acb3000-4f70-11eb-9b1a-07f57c842288.PNG)

## 적용

![스프링 컨테이너](https://user-images.githubusercontent.com/51367515/103617554-caf6c200-4f71-11eb-8b67-2b79081879bc.PNG)

-   시간 측정 AOP 등록

```java
package hello.hellospring.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class TimeTraceAop {
 @Around("execution(* hello.hellospring..*(..))")
 public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
 long start = System.currentTimeMillis();
 System.out.println("START: " + joinPoint.toString());
 try {
 return joinPoint.proceed();
 } finally {
 long finish = System.currentTimeMillis();
 long timeMs = finish - start;
 System.out.println("END: " + joinPoint.toString()+ " " + timeMs +
"ms");
 }
 }
}
```

![스프링 컨테이너](https://user-images.githubusercontent.com/51367515/103618090-b49d3600-4f72-11eb-94f6-235af1d8a509.PNG)
