package com.sunshine.music.service;

import com.sunshine.music.dao.SongCustomRepository;
import com.sunshine.music.dao.SongRepository;
import com.sunshine.music.entity.Song;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SongService {
    @Resource
    private SongRepository songRepository;

    @Resource
    private SongCustomRepository songCustomRepository;

    public Song findSongById(Integer id) {
        Song song = songRepository.findOne(id);
        return song;
    }
}
