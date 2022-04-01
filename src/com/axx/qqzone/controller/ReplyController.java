package com.axx.qqzone.controller;

import com.axx.qqzone.pojo.Reply;
import com.axx.qqzone.pojo.Topic;
import com.axx.qqzone.pojo.UserBasic;
import com.axx.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.util.Date;

public class ReplyController {
    private ReplyService replyService = null;

    public String addReply(String content, Integer topicId, HttpSession session) {
        UserBasic author = (UserBasic) session.getAttribute("userBasic");
        Reply reply = new Reply(content, new Date(), author, new Topic(topicId));
        replyService.addReply(reply);
        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
    }

    public String delReply(Integer replyId, Integer topicId) {
        replyService.delReply(replyId);

        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
    }
}
