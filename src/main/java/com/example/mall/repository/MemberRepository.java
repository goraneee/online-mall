package com.example.mall.repository;
import com.example.mall.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> { // 멤버클래스와 pk의 타입

    Optional<Member> findByUserId(String userId);

    Optional<Member> findByEmailAuthKey(String emailAuthKey);
}
