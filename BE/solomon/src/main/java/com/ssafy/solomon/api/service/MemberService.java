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
     public MemberEntity insertMember(MemberDto memberDto) {
         EmailAuthEntity email = emailAuthRepository.save(
                 EmailAuthEntity.builder()
                         .email(memberDto.getMemberId())
                         .authToken(UUID.randomUUID().toString())
                         .expired(false)
                         .build()
         );
        MemberEntity member = memberDto.toEntity();
        memberRepository.save(member);

        emailSendService.sendEmail(email.getEmail(), email.getAuthToken());
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
