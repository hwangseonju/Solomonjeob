
# JWT

JWT code 업데이트 전 공부

- JWT build.gradle

```
implementation 'javax.xml.bind:jaxb-api'
implementation 'io.jsonwebtoken:jjwt-api:0.11.2'
implementation 'io.jsonwebtoken:jjwt-impl:0.11.2'
implementation'io.jsonwebtoken:jjwt-jackson:0.11.2'
```

- JWT SecurityService

```
package com.ssafy.solomon.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service
public class SecurityService {
    private static final String SECRET_KEY = "aefaewaedsgawliefjilaweeilwaaewfewweaewasgddaf";

    // 로그인 서비스 던질 때 같이~~
    public String createToken(String subject , long expTime){
        if(expTime <= 0){
            throw new RuntimeException("만료시간이 0보다 커야함~");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);

        // SecretKeySpec 이용해서 secretKeyBytes를 내가 지정해놓은 알고리즘으로 변경경
       Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

       // return을 이렇게 하기도 함 expiretime은 현재시간 + expTime을 해야하기 때문에 저렇게 설정한다.
       return Jwts.builder()
               .setSubject(subject)
               .signWith(signingKey, signatureAlgorithm)
               .setExpiration(new Date(System.currentTimeMillis() + expTime))
               .compact();

    }

    // 토큰 검증하는 메소드를 만들어서 boolean 타입으로 return
    public String getSubject(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}

```

- JWT SecurityController

```
package com.ssafy.solomon.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    private SecurityService securityService;

    @GetMapping("/create/token")
    public Map<String, Object> createToken(@RequestParam(value = "subject") String subject){
        String token = securityService.createToken(subject, (2*1000*60));
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", token);
        return map;
    }

    @GetMapping("/get/subject")
    public Map<String, Object> getSubject(@RequestParam(value = "token") String token){
        String subject = securityService.getSubject(token);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", subject);
        return map;
    }
}

```

- 특별한 이슈사항
build.gradle에서 runtime '~'가 있는데
gradle 7+부터는 runtime을 제공하지 않음 우리 프로젝트는 7+ 버전이기 때문에
runtime 대신 implementation으로 바꾸어주었음 
implementation 'io.jsonwebtoken:jjwt-impl:0.11.2'
implementation'io.jsonwebtoken:jjwt-jackson:0.11.2'
