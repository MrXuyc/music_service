package com.sunshine.music.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String resourcePath;
    /**
     * 时长
     */
    private Integer duration;

    private Integer singerId;

    private String singerName;
    /**
     * 专辑id
     */
    private Integer albumId;
    /**
     * 专辑名称
     */
    private String albumName;
    /**
     * 图片地址以;号分割
     */
    private String images;

    private Integer recordCompanyId;

    private String recordCompany;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @JSONField(serialize = false)
    private int status;

}
