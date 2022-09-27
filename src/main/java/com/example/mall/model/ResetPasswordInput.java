package com.example.mall.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResetPasswordInput {

    private String userId;
    private String userName;
    private String id;  // url의 파라미터를 의미한다
    private String password;

}
