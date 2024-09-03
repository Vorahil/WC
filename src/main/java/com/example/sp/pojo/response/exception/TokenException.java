package com.example.sp.pojo.response.exception;

import com.example.sp.pojo.response.ReturnCode;

public class TokenException extends RuntimeException{
    private final int code;
    public TokenException(ReturnCode code) {
        super(code.getMessage());
        this.code = code.getCode();
    }
    public TokenException(ReturnCode returnCode, String message) {
        super(message);
        this.code = returnCode.getCode();
    }
}
