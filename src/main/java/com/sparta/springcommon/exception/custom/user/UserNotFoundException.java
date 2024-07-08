package com.sparta.springcommon.exception.custom.user;


import com.sparta.springcommon.common.ResponseCodeEnum;

public class UserNotFoundException extends UserException{
    public UserNotFoundException(){ super(ResponseCodeEnum.USER_NOT_FOUND);}
}
