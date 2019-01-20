package com.sunshine.music.service;

import com.sunshine.music.dao.SongMenuCustomRepository;
import com.sunshine.music.dao.SongMenuRepository;
import com.sunshine.music.entity.SongMenu;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service
public class SongMenuService {
    @Resource
    private SongMenuRepository songMenuRepository;

    @Resource
    private SongMenuCustomRepository songMenuCustomRepository;


    public List<SongMenu> findSongMenusByUserId(Integer userId) {
        List<SongMenu> songMenus = songMenuCustomRepository.findByUserId(userId);
        return songMenus;
    }
}
