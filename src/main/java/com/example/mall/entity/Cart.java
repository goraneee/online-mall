package com.example.mall.entity;

import com.example.mall.member.entity.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 중간 테이블
// member <--다대일--> cart <--일대다--> product

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "CART_ID")
    private Long id;

    @ManyToOne
    @JoinColumn
    private Member member;

    @ManyToOne
    @JoinColumn
    private Product product;
    private Integer quantity;
}
