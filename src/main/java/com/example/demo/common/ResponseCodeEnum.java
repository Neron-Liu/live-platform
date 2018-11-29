package com.example.demo.common;

import com.example.demo.common.exception.PlatformException;

/**
 *
 */
public enum ResponseCodeEnum {
    /**
     *
     */
    SUCCESS(0, "success"),
    UNKNOWN_SYSTEM_ERR(101, "unknown system err"),
    CACHE_ACCESS_ERR(101, "cache access err"),
    DB_ACCESS_ERR(102, "database access err"),

    AUDIENCE_NOT_FOUND_ERR(211, "audience not found err"),
    ;

    ResponseCodeEnum(int code, String mesg) {
        this.code = code;
        this.mesg = mesg;
    }

    public PlatformException asException() {
        return new PlatformException(this);
    }

    private int code;
    private String mesg;

    public int code() {
        return code;
    }

    public String mesg() {
        return mesg;
    }
}
