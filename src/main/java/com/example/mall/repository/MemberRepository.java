package com.example.mall.repository;
import com.example.mall.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

//    Optional<Member> findByEmailAuthKey(String emailAuthKey);
    Optional<Member> findByUserIdAndUserName(String userId, String userName);
    Optional<Member> findByResetPasswordKey(String resetPasswordKey);
    Optional<Member> findByUserId(String userId);
}
