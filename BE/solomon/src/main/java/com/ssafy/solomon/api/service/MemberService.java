package com.ssafy.solomon.api.service;

import com.ssafy.solomon.api.dto.EmailAuthDto;
import com.ssafy.solomon.api.dto.MemberDto;
import com.ssafy.solomon.db.entity.EmailAuthEntity;
import com.ssafy.solomon.db.entity.MemberEntity;
import com.ssafy.solomon.db.repository.EmailAuthRepository;
import com.ssafy.solomon.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final EmailAuthRepository emailAuthRepository;
    private final EmailSendService emailSendService;
    private final PasswordEncoder passwordEncoder;

    // member sign up
     public MemberEntity insertMember(MemberDto memberDto) throws MessagingException {
         EmailAuthEntity email = emailAuthRepository.save(
                 EmailAuthEntity.builder()
                         .email(memberDto.getMemberId())
                         .authToken(UUID.randomUUID().toString())
                         .expired(false)
                         .build()
         );
        MemberEntity member = memberDto.toEntity();
        memberRepository.save(member);

         StringBuffer emailcontent = new StringBuffer();
         emailcontent.append("<!DOCTYPE html>");
         emailcontent.append("<html>");
         emailcontent.append("<head>");
         emailcontent.append("</head>");
         emailcontent.append("<body>");
         emailcontent.append(
                 " <div" 																																																	+
                         "	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 600px; height: 600px; border-top: 4px solid #62b0ec; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"		+
                         "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">"																															+
                         "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\"><b>SOLOMONJEOB</b></span><br />"																													+
                         "		<span style=\"color: #62b0ec\">회원가입 인증</span> 메일입니다."																																				+
                         "	</h1>\n"																																																+
                         "	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"																													+
                         member.getNickName()																																																+
                         "		님 안녕하세요 :D<br />"																																													+
                         "		솔로몬접에 가입해 주셔서 진심으로 감사드립니다.<br />"																																						+
                         "		아래 <b style=\"color: #62b0ec\">'메일 인증'</b> 버튼을 클릭하여 회원가입을 완료해 주세요.<br />"																													+
                         "		감사합니다."																																															+
                         "	</p>"																																																	+
                         "	<a style=\"color: #FFF; text-decoration: none; text-align: center;\""																																	+
                         "	href=\"https://i6c207.p.ssafy.io/api/members/email/auth?email=" + email.getEmail() + "&authToken=" + email.getAuthToken() + "\" target=\"_blank\">"														+
                         "		<p"																																																	+
                         "			style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: #62b0ec; line-height: 45px; vertical-align: middle; font-size: 16px;\">"							+
                         "			메일 인증</p>"																																														+
                         "	</a>"																																																	+
                         "	<div style=\"border-top: 4px solid #62b0ec; padding: 5px;\"></div>"																																		+
                         " </div>"
         );
         emailcontent.append("</body>");
         emailcontent.append("</html>");

        emailSendService.sendEmail(email.getEmail(), emailcontent.toString());
        return member;
    }

    // confirm email
    public void checkEmail(EmailAuthDto emailAuthDto) throws SQLException {
         EmailAuthEntity email = emailAuthRepository.findEmailAuthByEmail(emailAuthDto.getEmail(), emailAuthDto.getAuthToken(), LocalDateTime.now())
                 .orElseThrow(() -> new SQLException());
         MemberEntity member = memberRepository.findByMemberId(emailAuthDto.getEmail());
         email.useToken();
         //System.out.println("값 확인1 : " + email.getExpired());
         member.emailVerified();
         //System.out.println("값 확인2 : " + member.getEmailAuth());
    }

    // check ID(email)
    public Long checkMember(String memberId) throws SQLException {
         MemberEntity memberEntity = memberRepository.findByMemberId(memberId);
         Long idx = 0L;
         if(memberEntity!=null)
             idx = memberEntity.getMemberIdx();

         return idx;
    }

    // check SNS ID(token)
    public Long checkSnsMember(String memberKey) throws SQLException {
         MemberEntity memberEntity = memberRepository.findByMemberKey(memberKey);
         Long idx = 0L;
         if(memberEntity!=null)
             idx = memberEntity.getMemberIdx();

         return idx;
    }

    public MemberEntity insertSnsMember(MemberDto memberDto) {
        MemberEntity member = memberDto.toSnsEntity();
        memberRepository.save(member);
        return member;
    }

    public MemberEntity signin(MemberDto memberDto) throws Exception {
        MemberEntity memberEntity = memberRepository.findByMemberId(memberDto.getMemberId());

        return memberEntity;

//         // 해당 이메일의 유저가 존재하지 않습니다.
//         if(memberEntity == null){
//             return false;
//         }
//         // 비밀번호가 일치하지 않습니다.
//         if(!passwordEncoder.matches(pwd, memberEntity.getMemberPwd())){
//             return false;
//         }
//         return true;

        // memberPwd가 암호화 되어있음..
//        if(memberDto.getMemberId().equals(memberEntity.getMemberId()) || memberDto.getMemberPwd().equals(memberEntity.getMemberPwd())){
//            return memberDto;
//        }else{
//            throw new RuntimeException("그런 사람은 없습니다.");
//        }

    }

    public String getServerInfo() throws Exception {
        return "정보전달할것";
    }
}
