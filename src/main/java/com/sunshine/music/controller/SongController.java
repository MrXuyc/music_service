package com.sunshine.music.controller;

import com.sunshine.music.entity.Song;
import com.sunshine.music.service.SongService;
import com.sunshine.music.util.Result;
import com.sunshine.music.util.ResultUtil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SongController {

    private SongService songService;

    @GetMapping("/song/query/{id}")
    public Result findSong(@PathVariable(value = "id") Integer id){
        Song song = songService.findSongById(id);
        if(song!=null){
            return ResultUtil.success(song);
        }
        return ResultUtil.error("未找到该id对应的音乐");
    }
}
