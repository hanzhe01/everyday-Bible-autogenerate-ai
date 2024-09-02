package com.youlintech.autogenerate.service.impl;

import com.youlintech.autogenerate.service.EverydayBibleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


/**
 * @author ：oyhz
 */
@Service
@Slf4j
public class EverydayBibleServiceImpl implements EverydayBibleService {

    private final OpenAiChatModel chatModel;
    @Autowired
    public EverydayBibleServiceImpl(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }
    @Override
    public String autoGenerateEverydayBible(String todayListStr) {
        /**
         * 获取今日日期
         */
        LocalDate currentDate = LocalDate.now();
        log.info("生成日期：{}", todayListStr);
        if (todayListStr == null) {
            todayListStr = currentDate.toString();
        }
        String prompt = "帮我生成"+todayListStr+"的每日圣经和历史上的今天内容，指定使用New International Version (NIV)," +
                "并且返回json格式的List集合的数据，内容为：" +
                "verse:String格式的经文诗节标题" +
                "verseContent:String格式的经文诗节内容" +
                "bibleDate:日期类型yyyy-MM-dd的圣经日期,为生成的日期也就是今日"+
                "historyTodayEvents:String类型，生成基督教的历史事件内容，只需要生成一条" +
                "根据你所知的返回，只需要返回list的json字符串，其他多余说明不需要返回，生成英文";
        String result = chatModel.call(new Prompt(
                prompt.toString(),
                OpenAiChatOptions.builder()
//                        .withModel("gpt-4o-mini")
                        .withModel("gpt-4o")
                        .withTemperature(0.4F)
                        .build()
        )).getResult().getOutput().getContent();
        log.info("每日圣经json数据：{}", result);
        return result;
    }
}
