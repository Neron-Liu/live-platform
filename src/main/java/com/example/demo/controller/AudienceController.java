package com.example.demo.controller;

import com.example.demo.dto.AudienceDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.UpdateAudienceDto;
import com.example.demo.service.AudienceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AudienceController {

    @Autowired
    private AudienceService audienceService;

    @GetMapping("/audiences")
    @ResponseBody
    public ResponseDto<List<AudienceDto>> getOnlineAudiences(
            @RequestParam(name = "online_only", defaultValue = "false") boolean onlineOnly) {

        return ResponseDto.success(audienceService.getAll());
    }

    @GetMapping("/audience/{audienceId}")
    @ResponseBody
    public ResponseDto<AudienceDto> getAudienceById(
            @PathVariable(name = "audienceId") int id) {
        return null;
    }

    @PostMapping("/audience/{audienceId}")
    public ResponseDto<AudienceDto> updateAudienceById(@RequestBody UpdateAudienceDto updateAudienceDto) {
        return null;
    }

}
