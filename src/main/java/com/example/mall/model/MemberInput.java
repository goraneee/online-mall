package com.example.mall.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberInput {

    private String userId;
    private String userName;
    private String phone;
    private String password;

    private String rePassword;
    private String userStatus;

    private String zipcode; // 우편 번호
    private String addr; //
    private String addrDetail; //
}
