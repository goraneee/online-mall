package com.example.mall.member.service;

import com.example.mall.admin.model.ProductDto;
import com.example.mall.admin.model.ProductInput;
import com.example.mall.admin.model.ProductParam;
import com.example.mall.entity.Cart;
import com.example.mall.member.model.CartDto;
import com.example.mall.member.model.CartInput;
import com.example.mall.member.model.MemberInput;
import com.example.mall.member.model.MemberParam;
import java.util.List;

public interface CartService {



    /**
     * 장바구니에 신규 추가
     */
    boolean add(MemberInput memberParam, ProductParam productParam);

    /**
     * 장바구니 안에 있는 상품 개수 변경
     */
    boolean update(ProductInput parameter);


    /**
     * 장바구니에서 삭제
     */
    boolean delete(MemberInput memberParam, ProductParam productParam); // 기본키

    /**
     * 장바구니 리스트
     */
    List<ProductDto> list(long id);    // 개인 마다 장바구니



}
