package com.youlintech.autogenerate.controller;

import com.youlintech.autogenerate.service.EverydayBibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author oyhz
 */
@RestController
@RequestMapping("/auto/Generate")
public class EverydayBibleAutoController {
    @Autowired
    private EverydayBibleService everydayBibleService;

    /**
     * 自动生成每日圣经的对象json格式字符串
     * @return
     */
    @PostMapping("/everydayBible")
    public String aiAutoGenerate(@RequestBody(required = false) String todayJoin) {
        return everydayBibleService.autoGenerateEverydayBible(todayJoin);
    }
}
