package com.axx.qqzone.service.impl;

import com.axx.qqzone.dao.ReplyDAO;
import com.axx.qqzone.pojo.HostReply;
import com.axx.qqzone.pojo.Reply;
import com.axx.qqzone.pojo.Topic;
import com.axx.qqzone.pojo.UserBasic;
import com.axx.qqzone.service.HostReplyService;
import com.axx.qqzone.service.ReplyService;
import com.axx.qqzone.service.UserBasicService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
    private ReplyDAO replyDAO = null;
    private HostReplyService hostReplyService = null;
    private UserBasicService userBasicService = null;

    @Override
    public List<Reply> getReplyListByTopicId(Integer id) {
        List<Reply> replyList = replyDAO.getReplyList(new Topic(id));
        for (int i = 0; i < replyList.size(); i++) {
            Reply reply = replyList.get(i);
            //1、将关联的作者设置进去
            Integer authorId = reply.getAuthor().getId();
            UserBasic author = userBasicService.getUserBasicById(authorId);
            reply.setAuthor(author);

            //2、将关联的主人回复设置进去
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            reply.setHostReply(hostReply);
        }
        return replyList;
    }
}
