package com.example.mall.admin.service.impl;

import com.example.mall.admin.model.ProductDto;
import com.example.mall.admin.model.ProductInput;
import com.example.mall.admin.repository.ProductRepository;
import com.example.mall.admin.service.ProductService;
import com.example.mall.entity.Product;
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

        Product product = Product.builder().productName(parameter.getProductName())
            .productImg(parameter.getProductImg()).stockQuantity(parameter.getStockQuantity())
            .price(parameter.getPrice()).category(parameter.getCategory())
            .stockedDt(parameter.getStockedDt())
            .soldOutYn(false)
            .cumulativeSales(0)
            .build();

        // 재고 수량이 0일 때는 입고 예정일이 필요하다


        productRepository.save(product);
        return true;
    }

    // 수정
    @Override
    public boolean update(ProductInput parameter) {  // id로 찾아서 다른 값들을 바꾼다.

        Optional<Product> OptionalProduct = productRepository.findById(parameter.getId());

        if (OptionalProduct.isPresent()) {
            Product product = OptionalProduct.get();
            product.setProductImg(parameter.getProductImg());
            product.setProductName(parameter.getProductName());
            product.setStockQuantity(parameter.getStockQuantity());
            product.setCategory(parameter.getCategory());
            product.setPrice(parameter.getPrice());
            product.setStockedDt(parameter.getStockedDt());
            productRepository.save(product);
            return true;
          }

        return false;
    }

    // 삭제
    @Override
    public boolean delete(long id) {
        Optional<Product> OptionalProduct = productRepository.findById(id);
        productRepository.delete(OptionalProduct.get());
        return true;
    }


    // 모든 상품에 대해 보여준다.
    @Override
    public List<ProductDto> list() {
        return ProductDto.of(productRepository.findAll());
    }
}