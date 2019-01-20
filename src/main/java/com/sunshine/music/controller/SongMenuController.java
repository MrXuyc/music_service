package com.sunshine.music.controller;

import com.sunshine.music.entity.SongMenu;
import com.sunshine.music.service.SongMenuService;
import com.sunshine.music.util.Result;
import com.sunshine.music.util.ResultUtil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SongMenuController {

    @Resource
    private SongMenuService songMenuService;

    @GetMapping("/songMenu/userId/{id}")
    public Result findSongMenus(@PathVariable(value = "id") Integer userId){
        try {
            List<SongMenu> songMenus = songMenuService.findSongMenusByUserId(userId);
            return ResultUtil.success(songMenus);
        }catch (Exception e){
            return ResultUtil.error("查询异常");
        }
    }
}
