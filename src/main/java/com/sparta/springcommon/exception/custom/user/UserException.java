package com.sparta.springcommon.exception.custom.user;

import com.sparta.springcommon.common.ResponseCodeEnum;
import lombok.Getter;

@Getter
public class UserException extends RuntimeException {
    private final ResponseCodeEnum responseCode;

    public UserException(ResponseCodeEnum responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }
}
