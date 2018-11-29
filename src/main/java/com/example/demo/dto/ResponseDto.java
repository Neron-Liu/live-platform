package com.example.demo.dto;

import com.example.demo.common.ResponseCodeEnum;
import lombok.Data;

@Data
public class ResponseDto<T> {

    private int code;
    private String mesg;
    private T data;

    public static <T> ResponseDto<T> success(T data) {
        ResponseDto<T> response = new ResponseDto<T>();
        response.setData(data);
        return response;
    }

    public static ResponseDto<?> failure(ResponseCodeEnum codeEnum) {
        ResponseDto<?> response = new ResponseDto<>();
        response.setCode(codeEnum.code());
        response.setMesg(codeEnum.mesg());
        return response;
    }

}
