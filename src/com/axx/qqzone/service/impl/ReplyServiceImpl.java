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

    @Override
    public void addReply(Reply reply) {
        replyDAO.addReply(reply);
    }

    @Override
    public void delReply(Integer id) {
        //1、根据id获取到reply
        Reply reply = replyDAO.getReply(id);
        if (reply != null) {
            //2、如果reply有关联的hostReply,则先删除hostReply
            HostReply hostReply = hostReplyService.getHostReplyByReplyId(reply.getId());
            if (hostReply != null) {
                hostReplyService.delHostReply(hostReply.getId());
            }
        }
        //3、删除reply
        replyDAO.delReply(id);
    }

    @Override
    public void delReplyList(Topic topic) {
        List<Reply> replyList = replyDAO.getReplyList(topic);
        if (replyList != null) {
            for (Reply reply : replyList) {
                delReply(reply.getId());
            }
        }
    }
}
