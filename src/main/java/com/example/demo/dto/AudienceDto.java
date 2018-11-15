package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AudienceDto {

    private Long id;
    private String nickname;
    private Date createTime;

}
