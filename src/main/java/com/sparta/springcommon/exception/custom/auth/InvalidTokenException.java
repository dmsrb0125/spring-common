package com.sparta.springcommon.exception.custom.auth;


import com.sparta.springcommon.common.ResponseCodeEnum;

public class InvalidTokenException extends UserAuthenticationException {
    public InvalidTokenException() {
        super(ResponseCodeEnum.INVALID_TOKENS);
    }
}
