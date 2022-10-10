package com.example.mall.member.service;

import com.example.mall.admin.model.ProductDto;
import com.example.mall.admin.model.ProductParam;
import java.util.List;


// Member에 대한 상품 서비스니까 상품 list만 있으면 된다.
public interface MemberProductService {

    /**
     * 상품 리스트
     */
    List<ProductDto> list();

    /**
     * 상품 (검색) 리스트
     */
    List<ProductDto> list(ProductParam parameter);

}
