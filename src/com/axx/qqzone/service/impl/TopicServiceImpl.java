package com.axx.qqzone.service.impl;

import com.axx.qqzone.dao.TopicDAO;
import com.axx.qqzone.pojo.Reply;
import com.axx.qqzone.pojo.Topic;
import com.axx.qqzone.pojo.UserBasic;
import com.axx.qqzone.service.ReplyService;
import com.axx.qqzone.service.TopicService;
import com.axx.qqzone.service.UserBasicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    private TopicDAO topicDAO = null;
    private ReplyService replyService = null;
    private UserBasicService userBasicService = null;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    @Override
    //通过id获取指定的topic信息，包含这个topic关联的作者信息
    public Topic getTopic(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        UserBasic author = topic.getAuthor();
        author = userBasicService.getUserBasicById(author.getId());
        topic.setAuthor(author);
        return topic;
    }

    //删除topic
    @Override
    public void delTopic(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        if (topic != null) {
            replyService.delReplyList(topic);

            topicDAO.delTopic(topic);
        }
    }

    @Override
    public Topic getTopicById(Integer id) {
        Topic topic = getTopic(id);
        List<Reply> replyList = replyService.getReplyListByTopicId(topic.getId());
        topic.setReplyList(replyList);
        return topic;
    }
}
