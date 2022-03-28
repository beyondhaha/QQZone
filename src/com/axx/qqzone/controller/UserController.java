package com.axx.qqzone.controller;

import com.axx.qqzone.pojo.Topic;
import com.axx.qqzone.pojo.UserBasic;
import com.axx.qqzone.service.TopicService;
import com.axx.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserController {
    private UserBasicService userBasicService = null;
    private TopicService topicService = null;

    public String login(String loginId, String pwd, HttpSession session) {
        //1、登录验证
        UserBasic userBasic = userBasicService.login(loginId, pwd);
        if (userBasic != null) {
            List<UserBasic> friendList = userBasicService.getFriendList(userBasic);
            List<Topic> topicList = topicService.getTopicList(userBasic);

            userBasic.setFriendList(friendList);
            userBasic.setTopicList(topicList);

            session.setAttribute("userBasic", userBasic);
            session.setAttribute("friend",userBasic);
            return "index";
        } else {
            return "login";
        }
    }

    public String friend(Integer id,HttpSession session){
        //根据ID获取到当前朋友
        UserBasic curFriend = userBasicService.getUserBasicById(id);
        //获取当前朋友的日志列表
        List<Topic> topicList = topicService.getTopicList(curFriend);
        curFriend.setTopicList(topicList);

        session.setAttribute("friend",curFriend);

        return "index";
    }
}
