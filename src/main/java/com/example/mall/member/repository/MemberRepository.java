package com.example.mall.member.repository;
import com.example.mall.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> { // 멤버클래스와 pk의 타입


//    Optional<Member> findById(String userId);

    Optional<Member> findByEmailAuthKey(String emailAuthKey);

    Optional<Member> findByUserId(String userId);




}
