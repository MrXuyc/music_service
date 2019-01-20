package com.sunshine.music.dao;

import com.sunshine.music.entity.Song;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song,Integer> {
}
