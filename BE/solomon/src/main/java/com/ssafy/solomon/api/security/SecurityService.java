package com.ssafy.solomon.api.security;

import com.ssafy.solomon.api.dto.MemberDto;
import com.ssafy.solomon.api.service.MemberService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class SecurityService {

    private static final String SECRET_KEY = "OurteamwillwinaprizeSoletsdoourbestljyksskdyksangwhsj";

    // 로그인 성공 시 사용자 정보를 JWTTOken을 생성해서 반환한다.
    // 전달받은 토큰이 제대로 생성된것인지 확인하고 문제가 있다면 Runtime 예외 발생..?
    // JWT 토큰을 분석해서 필요한 정보를 반환한다.

    // 로그인 서비스 던질 때 같이~~
//    public String createToken(String subject , long expTime){
//        if(expTime <= 0){
//            throw new RuntimeException("만료시간이 0보다 커야함~");
//        }
//
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
//        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
//
//        // SecretKeySpec 이용해서 secretKeyBytes를 내가 지정해놓은 알고리즘으로 변경
//       Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
//
//       // return을 이렇게 하기도 함 expiretime은 현재시간 + expTime을 해야하기 때문에 저렇게 설정한다.
//       return Jwts.builder()
//               .setSubject(subject)
//               .signWith(signingKey, signatureAlgorithm)
//               .setExpiration(new Date(System.currentTimeMillis() + expTime))
//               .compact();
//
//    }
    public final long expTime = 10 * 1000 * 60;
    public String createToken(MemberDto memberDto){

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);

        // SecretKeySpec 이용해서 secretKeyBytes를 내가 지정해놓은 알고리즘으로 변경
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());

        String id = memberDto.getMemberId();
        String pass= memberDto.getMemberPwd();
        String subject="";
        if(id.equals("kang") && pass.equals("1234"))subject="1";

//        subject = String.valueOf(memberService.checkMember(memberDto.getMemberId()));

        // return을 이렇게 하기도 함 expiretime은 현재시간 + expTime을 해야하기 때문에 저렇게 설정한다.
        return Jwts.builder()
                .setSubject(subject)
//               .setHeaderParam("typ", "JWT")
                .signWith(signingKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();

    }

    // getsubject, 토큰을 넣어서 원래의 값으로 디코딩
    public String getSubject(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // 토큰 검증하는 메소드를 만들어서 boolean 타입으로 return
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            System.out.println("잘못된 JWT 서명입니다.");
//            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            System.out.println("만료된 JWT 토큰입니다.");
//            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            System.out.println("지원되지 않는 JWT 토큰입니다.");
//            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            System.out.println("JWT 토큰이 잘못되었습니다.");
//            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public Map<String, Object> get(String token){
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                    .build()
                    .parseClaimsJws(token);
        }catch (final Exception e){
            throw new RuntimeException();
        }

        return claims.getBody();
    }
}
