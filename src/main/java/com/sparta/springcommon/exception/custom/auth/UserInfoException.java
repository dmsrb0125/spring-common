package com.sparta.springcommon.exception.custom.auth;


import com.sparta.springcommon.common.ResponseCodeEnum;

public class UserInfoException extends UserAuthenticationException {
    public UserInfoException() {
        super(ResponseCodeEnum.INVALID_USER_INFO);
    }
}
