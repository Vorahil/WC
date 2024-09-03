package com.example.sp.pojo.response.exception;


import com.example.sp.pojo.response.ReturnCode;
import lombok.Getter;

@Getter
public class AuthorityException extends RuntimeException {
    private final int code;
    public AuthorityException(ReturnCode returnCode) {
        super(returnCode.getMessage());
        this.code = returnCode.getCode();
    }

}