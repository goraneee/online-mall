package com.example.mall.repository;

import com.example.mall.entity.LoginHistory;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {
//    Optional<List<LoginHistory>> findLoginHistoriesBy();
    @Query(nativeQuery = true,
        value = " select * from login_history " +
            " where user_id = :userId " +
            " order by id desc " +
            " ; "
    )
    List<LoginHistory> findByIdLimit(@Param("userId") String userId);   // 매개변수

    Optional<LoginHistory> findByUserId(String userId);

}
