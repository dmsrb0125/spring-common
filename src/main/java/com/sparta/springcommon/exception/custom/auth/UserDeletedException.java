package com.sparta.springcommon.exception.custom.auth;


import com.sparta.springcommon.common.ResponseCodeEnum;

public class UserDeletedException extends UserAuthenticationException {
    public UserDeletedException() {
        super(ResponseCodeEnum.USER_DELETED);
    }
}
