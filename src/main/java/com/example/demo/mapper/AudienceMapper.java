package com.example.demo.mapper;

import com.example.demo.model.Audience;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AudienceMapper {

    @Insert("INSERT INTO audience (nickname) VALUES ( #{nickname} )")
    int insert(Audience audience);

    @Select("SELECT id, nickname, create_time FROM audience")
    List<Audience> getAll();

}
