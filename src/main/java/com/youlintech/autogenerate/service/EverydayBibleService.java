package com.youlintech.autogenerate.service;


import java.time.LocalDate;

public interface EverydayBibleService {
    /**
     * 自动生成每日圣经素材
     * @param today 生成的发布日期
     * @return
     */
    public String autoGenerateEverydayBible(String today);
}
