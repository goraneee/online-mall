package com.example.mall.member.exception;

public class MemberNotEmailAuthException extends RuntimeException{
    public MemberNotEmailAuthException(String error) {
        super(error);
    }
}
