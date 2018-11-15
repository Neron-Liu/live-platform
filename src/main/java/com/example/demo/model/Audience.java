package com.example.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class Audience {

    private Long id;
    private String nickname;
    private Date createTime;

}
