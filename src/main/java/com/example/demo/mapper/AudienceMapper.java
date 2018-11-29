package com.example.demo.mapper;

import com.example.demo.model.Audience;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author neron.liu
 */
@Mapper
public interface AudienceMapper {

    /**
     *
     * @param audience
     * @return
     */
    @Insert("INSERT IGNORE INTO audience (nickname, create_time) VALUES ( #{nickname}, #{createTime} )")
    @Options(useGeneratedKeys = true)
    int insert(Audience audience);

    /**
     *
     * @param nickname
     * @return
     */
    @Select("SELECT id, nickname, create_time FROM audience WHERE nickname = #{nickname}")
    Audience getByNickname(String nickname);

    /**
     *
     * @return
     */
    @Select("SELECT id, nickname, create_time FROM audience")
    List<Audience> getAll();

    /**
     *
     * @param id
     * @return
     */
    @Delete("DELETE FROM audience WHERE ID = #{id}")
    int delete(@Param("id") Long id);

}
