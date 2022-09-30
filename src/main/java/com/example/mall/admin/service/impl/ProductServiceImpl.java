package com.example.mall.admin.service.impl;

import com.example.mall.admin.repository.ProductRepository;
import com.example.mall.admin.service.ProductService;
import com.example.mall.entity.Product;
import com.example.mall.admin.model.ProductDto;
import com.example.mall.admin.model.ProductInput;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    // 등록
    @Override
    public boolean add(ProductInput parameter) {

        Product product = Product.builder()
            .productName(parameter.getProductName())
            .productImg(parameter.getProductImg())
            .productQuantity(parameter.getProductQuantity())
            .price(parameter.getPrice())
            .category(parameter.getCategory())
            .stockedDt(parameter.getStockedDt())
            .build();

        productRepository.save(product);
        return true;
    }

    // 수정
    @Override
    public boolean update(ProductInput parameter) {  // id로 찾아서 다른 값들을 바꾼다.

        Optional<Product> OptionalProduct = productRepository.findById(parameter.getId());

        if (OptionalProduct.isPresent()){
            Product product = OptionalProduct.get();
            product.setProductImg(parameter.getProductImg());
            product.setProductName(parameter.getProductName());
            product.setProductQuantity(parameter.getProductQuantity());
            product.setCategory(parameter.getCategory());
            product.setPrice(parameter.getPrice());
            product.setStockedDt(parameter.getStockedDt());
        }
        return true;
    }
    // 삭제
    @Override
    public boolean delete(long id) {
        Optional<Product> OptionalProduct = productRepository.findById(id);
        productRepository.delete(OptionalProduct.get());
        return true;
    }


// 어떤 필드 "sortValue"에 대해 내림차순 리스트를 정렬할 때 이용하는 메서드
//    private Sort getSortBySortValueDesc(){
//        return Sort.by(Sort.Direction.DESC, "sortValue");   // 내림차순?
//    }

    @Override
    public List<ProductDto> list() {
        return ProductDto.of(productRepository.findAll());
    }
}