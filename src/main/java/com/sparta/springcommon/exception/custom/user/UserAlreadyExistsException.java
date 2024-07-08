package com.sparta.springcommon.exception.custom.user;


import com.sparta.springcommon.common.ResponseCodeEnum;

public class UserAlreadyExistsException extends UserException {
    public UserAlreadyExistsException() {
        super(ResponseCodeEnum.USER_ALREADY_EXISTS);
    }
}