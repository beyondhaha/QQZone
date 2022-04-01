package com.axx.qqzone.controller;

import com.axx.qqzone.pojo.Topic;
import com.axx.qqzone.pojo.UserBasic;
import com.axx.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class TopicController {
    private TopicService topicService = null;

    public String topicDetail(Integer id, HttpSession session) {
        Topic topic = topicService.getTopicById(id);

        session.setAttribute("topic", topic);
        return "frames/detail";
    }

    public String delTopic(Integer topicId) {
        topicService.delTopic(topicId);
        return "redirect:topic.do?operate=getTopicList";
    }

    public String getTopicList(HttpSession session) {
        //1、从session中获取当前用户的信息
        UserBasic userBasic = (UserBasic) session.getAttribute("userBasic");
        //2、再次查询当前用户关联的所有的日志
        List<Topic> topicList = topicService.getTopicList(userBasic);
        //3、设置一下关联的日志列表
        userBasic.setTopicList(topicList);
        //4、覆盖session中friend的信息
        session.setAttribute("friend", userBasic);
        return "frames/main";
    }
}
