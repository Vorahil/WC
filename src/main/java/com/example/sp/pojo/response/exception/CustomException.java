package com.example.sp.pojo.response.exception;

import lombok.Getter;
import com.example.sp.pojo.response.ReturnCode;

@Getter
public class CustomException extends RuntimeException {
    private final int code;
    public CustomException(ReturnCode returnCode) {
        super(returnCode.getMessage());
        this.code = returnCode.getCode();
    }

}