package com.example.demo.controller;

import com.example.demo.common.ResponseCodeEnum;
import com.example.demo.dto.ResponseDto;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import redis.clients.jedis.exceptions.JedisException;

import java.sql.SQLException;

@ControllerAdvice
public class DefaultExceptionHandler {

    final ResponseDto unknownSystemError = ResponseDto.failure(ResponseCodeEnum.UNKNOWN_SYSTEM_ERR);

    final ResponseDto cacheAccessError = ResponseDto.failure(ResponseCodeEnum.CACHE_ACCESS_ERR);

    final ResponseDto dbAccessError = ResponseDto.failure(ResponseCodeEnum.DB_ACCESS_ERR);

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({RuntimeException.class})
    @ResponseBody
    public ResponseDto unknownSystemError() {
        return unknownSystemError;
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({JedisException.class, RedisSystemException.class})
    @ResponseBody
    public ResponseDto cacheAccessError() {
        return cacheAccessError;
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    @ResponseBody
    public ResponseDto dbAccessError() {
        return dbAccessError;
    }

}
