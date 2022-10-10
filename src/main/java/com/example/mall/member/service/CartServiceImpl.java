package com.example.mall.member.service;

import com.example.mall.admin.model.ProductDto;
import com.example.mall.admin.model.ProductInput;
import com.example.mall.admin.model.ProductParam;
import com.example.mall.admin.repository.ProductRepository;
import com.example.mall.entity.Cart;
import com.example.mall.entity.Product;
import com.example.mall.member.entity.Member;
import com.example.mall.member.model.CartDto;
import com.example.mall.member.model.CartInput;
import com.example.mall.member.model.MemberInput;
import com.example.mall.member.model.MemberParam;
import com.example.mall.member.repository.MemberRepository;
import com.example.mall.repository.CartRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl  implements CartService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;


    // 멤버의 cart에
    @Override
    public boolean add(MemberInput memberInput, ProductParam productParam) {

        Long memberNo = memberInput.getId();
        Member member = memberRepository.findById(memberNo).get();
        Cart cart = cartRepository.findById(member.getCart().getId()).get();
        memberRepository.save(member);
        cartRepository.save(cart);
        return true;
    }

    @Override
    public boolean delete(MemberInput memberInput, ProductParam productParam) {

        Long memberNo = memberInput.getId();
        Member member = memberRepository.findById(memberNo).get();
        Cart cart = cartRepository.findById(member.getCart().getId()).get();
        memberRepository.save(member);
        cartRepository.save(cart);
        return true;
    }

    @Override
    public List<ProductDto> list(long id) {    // 카트 번호를 넣으면
        Cart cart = cartRepository.findById(id).get();
        return null;
    }

    @Override
    public boolean update(ProductInput parameter) {
        return false;
    }
}
