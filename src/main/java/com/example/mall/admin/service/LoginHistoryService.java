package com.example.mall.admin.service;

import com.example.mall.entity.LoginHistory;
import java.util.List;

// 안 쓸수도 있음
public interface LoginHistoryService {


    /**
     * 로그인 히스토리 추가
     */
    boolean addHistory(String userId);


    /**
     * 사용자에 대한 히스토리 목록을 가져온다
     * @param userId - 아마도 히스토리테이블에서??
     * @return
     */
    List<LoginHistory> list(String userId);



}
