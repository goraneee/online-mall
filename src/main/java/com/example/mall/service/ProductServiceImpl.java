package com.example.mall.service;

import com.example.mall.component.MailComponents;
import com.example.mall.entity.Member;
import com.example.mall.model.MemberInput;
import com.example.mall.repository.MemberRepository;
import com.example.mall.repository.ProductRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;




}