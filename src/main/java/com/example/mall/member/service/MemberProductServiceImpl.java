package com.example.mall.member.service;

import com.example.mall.admin.model.ProductDto;
import com.example.mall.admin.model.ProductParam;
import com.example.mall.admin.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberProductServiceImpl implements MemberProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> list() {
        return ProductDto.of(productRepository.findAll());
    }

    @Override
    public List<ProductDto> list(ProductParam parameter) {
        return ProductDto.of(productRepository.findAll());
    }

}
