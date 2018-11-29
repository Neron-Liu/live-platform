package com.example.demo.service;

import com.example.demo.common.ResponseCodeEnum;
import com.example.demo.common.exception.PlatformException;
import com.example.demo.dto.AudienceDto;
import com.example.demo.mapper.AudienceMapper;
import com.example.demo.model.Audience;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

/**
 * @author neron.liu
 */
@Slf4j
@Service
@Transactional(readOnly = true, rollbackFor = DataAccessException.class)
public class AudienceService {

    @Autowired
    private AudienceMapper audienceMapper;
    @Autowired
    private RedisTemplate<String, Audience> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Audience> audienceValueOps;
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Audience> audienceHashOps;

    @Transactional(rollbackFor = DataAccessException.class)
    public AudienceDto join(AudienceDto audienceDto) {
        Preconditions.checkNotNull(audienceDto);

        Audience audience = new Audience();
        audience.setNickname(audienceDto.getNickname());
        audience.setCreateTime(new Date());

        audienceValueOps.set(audienceDto.getNickname(), audience);
        audienceHashOps.put("online_audiences", audienceDto.getNickname(), audience);

        int rowNum = audienceMapper.insert(audience);
        if (rowNum > 0) {
            audienceDto.setId(audience.getId());
            audienceDto.setCreateTime(audience.getCreateTime());
        } else {
            Optional<Audience> audienceOpt = ofNullable(audienceMapper.getByNickname(audienceDto.getNickname()));
            audienceOpt.ifPresentOrElse(
                    audienceFromDb -> {
                        audienceDto.setId(audienceFromDb.getId());
                        audienceDto.setNickname(audienceFromDb.getNickname());
                        audienceDto.setCreateTime(audienceFromDb.getCreateTime());
                    }, ResponseCodeEnum.AUDIENCE_NOT_FOUND_ERR::asException);
        }

        return audienceDto;
    }

    @Transactional
    public boolean quit(Long audienceId) {
        Preconditions.checkNotNull(audienceId);

        int rows = audienceMapper.delete(audienceId);

        return rows == 1;
    }

    /**
     * @return
     */
    public List<String> getAll() {
        return Lists.newArrayList(audienceHashOps.keys("online_audiences"));
    }

}
