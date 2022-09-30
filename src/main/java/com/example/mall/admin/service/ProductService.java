package com.example.mall.admin.service;

import com.example.mall.admin.model.ProductDto;
import com.example.mall.admin.model.ProductInput;
import java.util.List;

public interface ProductService {

    /**
     * 상품 신규 추가
     */
    boolean add(ProductInput product);   // 인풋으로 바꿔야할 수도 있다.

    /**
     * 상품 수정
     * 상품명,
     */
    boolean update(ProductInput parameter);


    /**
     * 상품 삭제
     */
    boolean delete(long id); // 기본키

    /**
     * 상품 리스트
     */
    List<ProductDto> list();

}
