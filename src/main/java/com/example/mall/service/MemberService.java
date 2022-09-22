package com.example.mall.service;

import com.example.mall.entity.Member;
import com.example.mall.model.MemberInput;
import java.util.List;
import org.springframework.stereotype.Service;

public interface MemberService {


    boolean register(MemberInput parameter);

    /**
     * uuid에 해당하는 계정을 활성화한다.
     *
     * */
    boolean emailAuth(String uuid);


    /**
     * 입력한 이메일로 비밀번호 초기화 정보를 전송
     */
//    boolean sendResetPassword(ResetPasswordInput parameter);


    /**
     * 입력받은 uuid에 대해서 password로 초기화한다
     * */
    boolean resetPassword(String userId, String password);


    /**
     * 입력받은 uuid 유효성 검사
     */
    boolean checkResetPassword(String uuid);

    /**
     * 회원 목록을 가져온다. - 관리자에서만 사용 가능
     */
//    List<MemberDto> list(MemberParam memberParam);

    /**
     * 회원 상세 정보
     */
//    MemberDto detail(String userId);


    /**
     * 회원 상태 변경
     */
    boolean updateStatus(String userId, String userStatus);


    /**
     * 회원 비밀번호 초기화
     */
    boolean updatePassword(String userId, String userStatus);

    /**
     * 회원 정보 페이지 비밀번호 변경
     */
//    ServiceResult updateMemberPassword(MemberInput parameter);

//    ServiceResult updateMember(MemberInput parameter);

    /**
     * 회원 탈퇴시키는 로직
     */
//    ServiceResult withdraw(String userId, String password);


}
