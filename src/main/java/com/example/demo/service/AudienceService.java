package com.example.demo.service;

import com.example.demo.dto.AudienceDto;
import com.example.demo.mapper.AudienceMapper;
import com.example.demo.model.Audience;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
@Transactional(readOnly = true)
public class AudienceService {

    @Autowired
    private AudienceMapper audienceMapper;

    @Transactional
    public void join(AudienceDto audienceDto) {
        Audience audience = new Audience();
        audience.setNickname(audienceDto.getNickname());

        audienceMapper.insert(audience);
    }

    @Transactional
    public void quit(Long audienceId) {

    }

    public List<AudienceDto> getAll() {
        List<Audience> audiences = audienceMapper.getAll();

        return ofNullable(audiences).orElse(Collections.emptyList())
                .stream()
                .map(audience -> {
                    AudienceDto dto = new AudienceDto();
                    dto.setId(audience.getId());
                    dto.setNickname(audience.getNickname());
                    dto.setCreateTime(audience.getCreateTime());

                    return dto;
                })
                .collect(Collectors.toList());
    }

}
