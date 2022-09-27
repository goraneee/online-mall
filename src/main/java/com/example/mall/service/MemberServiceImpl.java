package com.example.mall.service;

import com.example.mall.component.MailComponents;
import com.example.mall.configuration.UserAuthenticationSuccessHandler;
import com.example.mall.entity.Cart;
import com.example.mall.entity.Coupon;
import com.example.mall.entity.CouponType;
import com.example.mall.entity.LoginHistory;
import com.example.mall.entity.Member;
import com.example.mall.entity.MemberCode;
import com.example.mall.model.MemberInput;
import com.example.mall.model.ResetPasswordInput;
import com.example.mall.repository.CartRepository;
import com.example.mall.repository.CouponRepository;
import com.example.mall.repository.LoginHistoryRepository;
import com.example.mall.repository.MemberRepository;
import com.example.mall.util.RequestUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
//    , UserDetails {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CouponRepository couponRepository;


    private final MailComponents mailComponents;
    private final PasswordEncoder passwordEncoder;

    private final LoginHistoryRepository loginHistoryRepository;


    // (2) 로그인
    @Override
    public boolean authenticate(MemberInput parameter){
        // user의 비번은 인코딩된 상태이다.member의 비번은 암호화되지 않은 상태
        Member user = this.memberRepository.findByUserId(parameter.getUserId())
            .orElseThrow(() -> new RuntimeException("존재하지 않는 ID 입니다."));

        // 비번 확인
        if(!this.passwordEncoder.matches(parameter.getPassword(), user.getPassword())){
            throw new RuntimeException("비밀 번호가 일치하지 않습니다.");

        }
        return true;
    }


    //(1) 회원 가입
    @Override
    public boolean register(MemberInput parameter) {

        // 아이디 중복
        Optional<Member> optionalMember  = memberRepository.findByUserId(parameter.getUserId());
        if(optionalMember.isPresent()){ // apa
            return false;
        }
        // 비밀번호 틀린 경우
        if(!parameter.getPassword().equals(parameter.getRePassword())){
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
//            .emailAuthYn(false)
            .emailAuthKey(uuid)
            .userStatus(Member.MEMBER_STATUS_REQ)
            .point(0L)
            .addr(parameter.getAddr())
            .addrDetail(parameter.getAddrDetail())
            .zipcode(parameter.getZipcode())
            .build();

        // 장바구니 추가
//        Cart cart = new Cart();
//        LocalDateTime now =  LocalDateTime.now();
//
//        // 쿠폰 추가
//        Coupon coupon = Coupon.builder()
//            .couponType(CouponType.COUPON_A)
//            .couponRegDt(now)
//            .couponExpirationDt(now.plusMonths(3))
////            .member(member)  => TransientPropertyValueException 발생
//            .build();
//
//        // member에만 set()하고 반대편은 저장 x
//        member.setCart(cart);
//        member.setCoupon(coupon);
//
//        couponRepository.save(coupon);
//        cartRepository.save(cart);



        memberRepository.save(member);

        // 메일 보내기
        String email = parameter.getUserId();
        String subject = "Online mall 가입을 축하드립니다.";
        String text =   "<p>Online mall 가입을 축하드립니다.</p>" +
                        "<p>아래 링크를 클릭하셔서 가입을 완료하세요.</p>" +
                        "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id=" +
                        uuid +
                        "'>가입 완료</a></div>";
        mailComponents.sendMail(email, subject, text);

        return true;
    }


    // (3) 이메일 인증
    @Override
    public boolean emailAuth(String uuid) { // 이메일 인증

        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(uuid);    // 있으면 Optional<Member> 가 리턴된다.

        if(!optionalMember.isPresent()){
            return false;
        }
        Member member = optionalMember.get();

        // 이미 활성화됐기 때문에 또 활성화할 필요없다.
        if(member.isEmailAuthYn()){
            return false;
        }

        // 장바구니 추가
        Cart cart = new Cart();
        LocalDateTime now =  LocalDateTime.now();

        // 쿠폰 추가
        Coupon coupon = Coupon.builder()
            .couponType(CouponType.COUPON_C)
            .couponRegDt(now)
            .couponExpirationDt(now.plusMonths(3))
//            .member(member)  => TransientPropertyValueException 발생
            .build();

        // member에만 set()하고 반대편은 저장 x
        member.setCart(cart);
        member.setCoupon(coupon);
        member.setUserStatus(MemberCode.MEMBER_STATUS_ING);
        member.setEmailAuthYn(true);
        member.setEmailAuthDt(LocalDateTime.now());

//        couponRepository.save(coupon);
//        cartRepository.save(cart);
        memberRepository.save(member);  // 연관관계의 주인만 저장해도 된다.
        return true;
    }


    @Override
    public boolean sendResetPassword(ResetPasswordInput parameter) {


        Optional<Member> optionalMember = memberRepository.findByUserId(parameter.getUserId());

        // 정보가 없으면

        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();
        String uuid = UUID.randomUUID().toString();

        member.setResetPasswordKey(uuid);
        member.setResetPasswordLimitDt(LocalDateTime.now().plusDays(1));    // 하루 안에 초기화 해야한다.
        memberRepository.save(member);

        // 있으면 메일을 보낸다.
        String email = parameter.getUserId();
        String subject = "[fastlms] 비밀번호 초기화 메일입니다.";
        String text = "<p>fastlms 비밀번호 초기화 메일입니다.</p>" +
            "<p>비밀번호를 초기화해주세요.</p>" +
            "<div><a target='_blank'   href='http://localhost:8080/member/reset/password?id=" +
            uuid +
            "'> 비밀번호 초기화 링크 </a></div>";

        mailComponents.sendMail(email, subject, text);
        return true;

    }

    @Override
    public boolean resetPassword(String userId, String password) {
        return false;
    }

    @Override
    public boolean updatePassword(String userId, String userStatus) {
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



}
