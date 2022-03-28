package com.axx.qqzone.controller;

import com.axx.qqzone.pojo.Topic;
import com.axx.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;

public class TopicController {
    private TopicService topicService = null;

    public String topicDetail(Integer id, HttpSession session) {
        Topic topic = topicService.getTopicById(id);

        session.setAttribute("topic", topic);
        return "frames/detail";
    }
}
