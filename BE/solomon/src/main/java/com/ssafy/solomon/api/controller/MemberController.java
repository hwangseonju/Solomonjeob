package com.ssafy.solomon.api.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ssafy.solomon.api.dto.EmailAuthDto;
import com.ssafy.solomon.api.dto.MemberDto;
import com.ssafy.solomon.api.security.SecurityService;
import com.ssafy.solomon.api.service.MemberService;
import com.ssafy.solomon.db.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MemberController {

    private final SecurityService securityService;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private static final String NOT_CONFIRM_EMAIL = "notconfirmemail";

    @PostMapping("/signup/member")
    public ResponseEntity<String> registerMember(@RequestBody MemberDto memberDto) {
        MemberEntity result = memberService.insertMember(memberDto);
        if(result.getMemberId()==memberDto.getMemberId()) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/email/auth")
    public ResponseEntity<String> emailCheck(@ModelAttribute EmailAuthDto emailAuthDto) throws Exception {
        memberService.checkEmail(emailAuthDto);
        //System.out.println("여기");
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/check/{memberId}")
    public ResponseEntity<String> idCheck(@PathVariable String memberId) throws Exception {
        Long result = memberService.checkMember(memberId);
        if(result==0L) {
            return new ResponseEntity<String>(FAIL, HttpStatus.OK);

        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/sns/{memberKey}")
    public ResponseEntity<String> snsCheck(@PathVariable String memberKey) throws Exception {
        Long result = memberService.checkSnsMember(memberKey);
        if(result==0L) {
            return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @PostMapping("/signup/sns")
    public ResponseEntity<String> registerSnsMember(@RequestBody MemberDto memberDto) {
        MemberEntity result = memberService.insertSnsMember(memberDto);
        if(result.getMemberKey()==memberDto.getMemberKey()) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> signin(@RequestBody MemberDto memberDto, HttpServletResponse res) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {
            MemberEntity memberEntity = memberService.signin(memberDto);
            if(memberEntity != null) {
                // memberId가 있을 때
                if (memberEntity.getMemberId().equals(memberDto.getMemberId()) && passwordEncoder.matches(memberDto.getMemberPwd(), memberEntity.getMemberPwd())) {
                    // 이메일 인증을 받았을 때
                    if (memberEntity.getEmailAuth()) {
                        // jwt 토큰 발급
                        String token = securityService.createToken(memberDto);
                        // header에 jwt-auth-token이라고 설정해주어 interceptor에서 jwt-auth-token을 찾게해줌
                        res.setHeader("jwt-auth-token", token);
//            resultMap.put("signinId", memberDto.getMemberId());
//                        resultMap.put("signinIdx", memberEntity.getMemberIdx());
//                        resultMap.put("jwt-auth-token", token);
                        resultMap.put("status", true);
                        status = HttpStatus.ACCEPTED;
                        return new ResponseEntity<Map<String, Object>>(resultMap, status);
                    }
                    resultMap.put("email", memberDto.getMemberId());
                    resultMap.put("email_confirm", "emailconfim");
                    status = HttpStatus.OK;
                    return new ResponseEntity<Map<String, Object>>(resultMap, status);
                }
            }
        } catch (RuntimeException e){
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 로그인 안됨
        resultMap.put("notfound", FAIL);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

// token을 이용하여 사용자 정보 조회
//    @PostMapping("/info")
//    public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req, @RequestBody MemberDto memberDto){
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status = null;
//        try {
//            String info = memberService.getServerInfo();
////            resultMap.putAll(securityService.get(req.getHeader("jwt-auth-token")));
//            String token = securityService.createToken(memberDto);
//            resultMap.putAll(securityService.get(token));
//            resultMap.put("status", true);
//            resultMap.put("info", info);
//            resultMap.put("request_body", memberDto.getMemberIdx());
//            status = HttpStatus.ACCEPTED;
//        }catch (RuntimeException e){
//            resultMap.put("message", e.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<Map<String, Object>>(resultMap, status);
//    }
@RequestMapping(value = "/login/oauth_kakao")
public String oauthKakao(
        @RequestParam(value = "code", required = false) String code
        , Model model) throws Exception {

    System.out.println("#########" + code);
    String access_Token = getAccessToken(code);


    System.out.println("####access_Token#### : " + access_Token);
    System.out.println("1");

    String result = getUserInfo(access_Token);
    //System.out.println("###access_Token#### : " + access_Token);
    //System.out.println("###userInfo#### : " + userInfo.get("email"));
    //System.out.println("###nickname#### : " + userInfo.get("nickname"));

    //JSONObject kakaoInfo =  new JSONObject(userInfo);
    model.addAttribute("kakaoInfo", access_Token);
    System.out.println("result"+result);
    Long idx=memberService.checkSnsMember(result);
    System.out.println(idx);
    if(idx==0L){
        MemberDto member=new MemberDto(result, "kakao");
        memberService.insertSnsMember(member);
        idx=memberService.checkSnsMember(result);
    }
    System.out.println(idx);
    return idx.toString(); //본인 원하는 경로 설정
}

    //토큰발급
    public String getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //  URL연결은 입출력에 사용 될 수 있고, POST 혹은 PUT 요청을 하려면 setDoOutput을 true로 설정해야함.
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //	POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=e35ccc21d2cf1759f526eef14ea4b921");  //본인이 발급받은 key
            sb.append("&redirect_uri=http://localhost:8080/api/members/login/oauth_kakao");     // 본인이 설정해 놓은 경로
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();


            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return access_Token;
    }

    //유저정보조회
    public String getUserInfo (String access_Token) {

        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        String result="";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
//            System.out.println("response body : " + result);
//
//            JsonParser parser = new JsonParser();
//            JsonElement element = parser.parse(result);
//
//            String properties = element.getAsJsonObject().get("id").getAsJsonObject().toString();
//            System.out.println(properties);
//            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
//
//            //String nickname = properties.getAsJsonObject().get("nickname").getAsString();
//            String email = kakao_account.getAsJsonObject().get("email").getAsString();
//
//            userInfo.put("accessToken", access_Token);
//            //userInfo.put("nickname", nickname);
//            userInfo.put("email", email);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }
}
