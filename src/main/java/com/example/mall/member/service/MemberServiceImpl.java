package com.example.mall.member.service;

import com.example.mall.admin.repository.ProductRepository;
import com.example.mall.admin.service.AdminProductService;
import com.example.mall.component.MailComponents;
import com.example.mall.entity.Cart;
import com.example.mall.entity.Coupon;
import com.example.mall.entity.CouponType;
import com.example.mall.entity.enumType.UserGrade;
import com.example.mall.member.entity.Member;
import com.example.mall.member.entity.MemberCode;
import com.example.mall.member.exception.MemberNotEmailAuthException;
import com.example.mall.member.exception.MemberStopUserException;
import com.example.mall.member.model.MemberDto;
import com.example.mall.member.model.MemberInput;
import com.example.mall.member.repository.MemberRepository;
import com.example.mall.member.repository.OrderDetailRepository;
import com.example.mall.member.repository.OrderRepository;
import com.example.mall.repository.CartRepository;
import com.example.mall.repository.CouponRepository;
import com.example.mall.repository.LoginHistoryRepository;
import com.example.mall.repository.model.ResetPasswordInput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ProductRepository productRepository;
    private final AdminProductService adminProductService;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CouponRepository couponRepository;

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;


    private final MailComponents mailComponents;

    //  private final PasswordEncoder passwordEncoder;
    private final LoginHistoryRepository loginHistoryRepository;


    //(1) 회원 가입
    @Override
    public boolean register(MemberInput parameter) {

        // 아이디 중복
        Optional<Member> optionalMember = memberRepository.findByUserId(parameter.getUserId());
        if (optionalMember.isPresent()) { // apa
            return false;
        }
        // 비밀번호 틀린 경우
        if (!parameter.getPassword().equals(parameter.getRePassword())) {
            return false;
        }

        String encPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());
        //passwordEncoder.encode(parameter.getPassword());      -->  순환 참조 났던 부분

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
        memberRepository.save(member);

        System.out.println("http://localhost:8080/member/email-auth?id=" + uuid);

        // 메일
        String email = parameter.getUserId();
        String subject = "Online mall 가입을 축하드립니다.";
        String text = "<p>Online mall 가입을 축하드립니다.</p>" +
            "<p>아래 링크를 클릭하셔서 가입을 완료하세요.</p>" +
            "<div><a target='_blank' href='http://localhost:8080/member/email-auth?id=" +
            uuid +
            "'>가입 완료</a></div>";
        mailComponents.sendMail(email, subject, text);

/* order 테스트
        // 주문 테스트 - 멤버, 상품 만들기

        Product product1 = productRepository.findById(1L).get();
        Product product2 = productRepository.findById(2L).get();

        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail1 =  OrderDetail.builder()
            .product(product1)
            .quantity(13)
            .recipientName("우영우")
            .recipientPhone("0109993333")
            .recipientAddr("서울시")
            .recipientAddrDetail("마포구")
            .recipientZipcode("2090-333")
            .build();

        OrderDetail orderDetail2 =  OrderDetail.builder()
            .product(product2)
            .quantity(50)
            .recipientName("김고은")
            .recipientPhone("0101029382")
            .recipientAddr("경기도")
            .recipientAddrDetail("안양시")
            .recipientZipcode("209-333")
            .build();

        orderDetailList.add(orderDetail1);
        orderDetailList.add(orderDetail2);

        Order order  = Order.builder()
            .member(member)
            .orderDate(LocalDateTime.now())
            .orderDetailList(orderDetailList)
            .build();

        orderDetailRepository.save(orderDetail1);
        orderDetailRepository.save(orderDetail2);
        orderRepository.save(order);
        memberRepository.save(member);
*/

        return true;
    }

    // (2) 로그인
    @Override
    public boolean authenticate(MemberInput parameter) {

        /*
        // user의 비번은 인코딩된 상태이다.member의 비번은 암호화되지 않은 상태
        Member user = this.memberRepository.findByUserId(parameter.getUserName())   //이 부분을 수정
            .orElseThrow(() -> new RuntimeException("존재하지 않는 ID 입니다."));

        // 비번 확인
        if(!passwordEncoder.matches(parameter.getPassword(), user.getPassword())){
            throw new RuntimeException("비밀 번호가 일치하지 않습니다.");
        }
*/

        return true;
    }

    // (3) 이메일 인증
    @Override
    public boolean emailAuth(String uuid) { // 이메일 인증

        Optional<Member> optionalMember = memberRepository.findByEmailAuthKey(
            uuid);    // 있으면 Optional<Member> 가 리턴된다.

        if (!optionalMember.isPresent()) {
            return false;
        }
        Member member = optionalMember.get();

        // 이미 활성화됐기 때문에 또 활성화할 필요없다.
        if (member.isEmailAuthYn()) {
            return false;
        }

        // 장바구니 추가
        Cart cart = new Cart();
        LocalDateTime now = LocalDateTime.now();

        // 쿠폰 추가
        Coupon coupon = Coupon.builder()
            .couponType(CouponType.COUPON_C)
            .couponRegDt(now)
            .couponExpirationDt(now.plusMonths(3))
//            .member(member)  => TransientPropertyValueException 발생
            .build();
        List<Coupon> couponList = new ArrayList<>();
        couponList.add(coupon);

        // member에만 set()하고 반대편은 저장 x
        member.setCart(cart);
//        member.setCouponList(couponList);
        member.setUserStatus(MemberCode.MEMBER_STATUS_ING);
        member.setEmailAuthYn(true);
        member.setEmailAuthDt(LocalDateTime.now());
        member.setCumulativePurchases(0L);
        member.setUserGrade(UserGrade.NEWBIE);
        // couponRepository.save(coupon);
        cartRepository.save(cart);
        memberRepository.save(member);  // 연관관계의 주인만 저장해도 된다. CASCADE 설정해서 cart, coupon에도 저장한다.
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
        String subject = "[Online mall] 비밀번호 초기화 메일입니다.";
        String text = "<p>Online mall 비밀번호 초기화 메일입니다.</p>" +
            "<p>비밀번호를 초기화해주세요.</p>" +
            "<div><a target='_blank'   href='http://localhost:8080/member/reset/password?id=" +
            uuid +
            "'> 비밀번호 초기화 링크 </a></div>";

        mailComponents.sendMail(email, subject, text);
        return true;

    }


    // 시큐리티가 사용자 인증하는 부분 - 권한을 부여한다
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   //

        Optional<Member> optionalMember = memberRepository.findByUserId(username);
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();

        // 추가
        if (Member.MEMBER_STATUS_REQ.equals(member.getUserStatus())) {
            throw new MemberNotEmailAuthException("이메일 활성화 이후에 로그인 해주세요");
        }
        if (Member.MEMBER_STATUS_STOP.equals(member.getUserStatus())) {
            throw new MemberStopUserException("정지된 회원입니다.");
        }
        if (!member.isEmailAuthYn()) {
            throw new MemberNotEmailAuthException("이메일 활성화 이후에 로그인 해주세요");
        }

        // 권한 설정
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));    // member

        if (member.isAdminYn()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));       // ?
            // 관리자 / 회원 여부를 시큐리티에 담는다
        }
        return new User(member.getUserId(), member.getPassword(), grantedAuthorities);  // role
    }


    // 회원 상세 목록 가져오기?
    @Override
    public List<MemberDto> list(MemberInput memberParam) {
        return MemberDto.of(memberRepository.findAll());
    }

    // 비밀번호 찾기는 보류
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
