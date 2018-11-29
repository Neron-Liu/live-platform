package com.example.demo.common.exception;

import com.example.demo.common.ResponseCodeEnum;

/**
 * @author neron.liu
 */
public class PlatformException extends RuntimeException {

    private ResponseCodeEnum errorCode;

    public PlatformException(ResponseCodeEnum errorCode) {
        this.errorCode = errorCode;
    }

    public ResponseCodeEnum getErrorCode() {
        return errorCode;
    }


}
