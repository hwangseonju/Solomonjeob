package com.ssafy.solomon.api.service;

import com.ssafy.solomon.api.dto.EmailAuthDto;
import com.ssafy.solomon.api.dto.MemberDto;
import com.ssafy.solomon.db.entity.EmailAuthEntity;
import com.ssafy.solomon.db.entity.MemberEntity;
import com.ssafy.solomon.db.repository.EmailAuthCustomRepository;
import com.ssafy.solomon.db.repository.EmailAuthCustomRepositoryImpl;
import com.ssafy.solomon.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final EmailAuthCustomRepository emailAuthCustomRepository;
    private final EmailAuthCustomRepositoryImpl emailAuthCustomRepositoryImpl;
    private final EmailSendService emailSendService;
    private final PasswordEncoder passwordEncoder;

     public MemberEntity insertMember(MemberDto memberDto) {
         EmailAuthEntity email = emailAuthCustomRepository.save(
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

    public void checkEmail(EmailAuthDto emailAuthDto) throws Exception {
         EmailAuthEntity email = emailAuthCustomRepositoryImpl.findValidAuthByEmail(emailAuthDto.getEmail(), emailAuthDto.getAuthToken(), LocalDateTime.now())
                 .orElseThrow(() -> new Exception());
         MemberEntity member = memberRepository.findByMemberId(emailAuthDto.getEmail());
         email.useToken();
         member.emailVerified();
    }

    public Long checkMember(String memberId) {
         Long memberEntity = memberRepository.findByMemberId(memberId).getMemberIdx();
         Long idx = 0L;
//         if(memberEntity!=null)
//             idx = memberEntity.getMemberIdx();

         return idx;
    }

    public Long checkSnsMember(String memberKey, String memberSns){
         MemberEntity memberEntity = memberRepository.findByMemberKeyAndMemberSns(memberKey, memberSns);
         Long idx = 0L;
//         if(memberEntity!=null)
//             idx = memberEntity.getMemberIdx();

         return idx;
    }
}
