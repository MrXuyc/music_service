package com.sunshine.music.dao;

import com.sunshine.music.entity.SongMenu;
import com.sunshine.music.entity.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongMenuCustomRepository extends Repository<User,Integer> {
    @Query(value = "select sm from SongMenu sm where userId =:userId")
    List<SongMenu> findByUserId(@Param(value = "userId")Integer userId);
}
