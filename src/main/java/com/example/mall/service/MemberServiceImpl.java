package com.example.mall.service;

import com.example.mall.entity.Member;
import com.example.mall.model.MemberInput;
import com.example.mall.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public boolean register(MemberInput parameter) {
        // 같은 아이디가 존재하는 경우
        Optional<Member> optionalMember  = memberRepository.findByUserId(parameter.getUserId());
        if(optionalMember.isPresent()){ // apa
            return false;
        }

        String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());
        String uuid = UUID.randomUUID().toString();

        Member member = Member.builder()

            .userId(parameter.getUserId())
            .userName(parameter.getUserName())
            .phone(parameter.getPhone())
            .password(encPassword)
            .regDt(LocalDateTime.now())
            .emailAuthYn(false)
            .emailAuthKey(uuid)
            .userStatus(Member.MEMBER_STATUS_REQ)
            .build();
        memberRepository.save(member);

        // 메일 보내기
//        String email = parameter.getUserId();
//        String subject = "fastlms 사이트 가입을 축하드립니다.";
//        String text = "<p>fastlms 사이트 가입을 축하드립니다.</p>" +
//            "<p>아래 링크를 클릭하셔서 가입을 완료하세요.</p>" +
//            "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id=" +
//            uuid +
//            "'>가입 완료</a></div>";
//
//        mailComponents.sendMail(email, subject, text);
        return true;
    }

/*
    @Override
    public MemberDto detail(String userId) {

        Optional<Member> optionalMember = memberRepository.findById(userId);
        if(!optionalMember.isPresent()){
            return null;
        }

        // optional 멤버 존재할 때만
        Member member = optionalMember.get();
        return MemberDto.of(member);
    }

 */

    @Override
    public boolean emailAuth(String uuid) {
        return false;
    }

    @Override
    public boolean resetPassword(String userId, String password) {
        return false;
    }

    @Override
    public boolean checkResetPassword(String uuid) {
        return false;
    }

    @Override
    public boolean updateStatus(String userId, String userStatus) {
        return false;
    }

    @Override
    public boolean updatePassword(String userId, String userStatus) {
        return false;
    }
}
