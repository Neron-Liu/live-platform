package com.example.demo.controller;

import com.example.demo.dto.ResponseDto;

public abstract class BaseController {

    @FunctionalInterface
    interface ControllerFunction<R> {

        /**
         *
         * @return
         */
        R apply();

    }

    protected <R> ResponseDto<R> handle(ControllerFunction<R> function) {
        R result = function.apply();
        return ResponseDto.success(result);
    }

}
