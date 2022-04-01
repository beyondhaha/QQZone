package com.axx.qqzone.service;

import com.axx.qqzone.pojo.Reply;
import com.axx.qqzone.pojo.Topic;

import java.util.List;

public interface ReplyService {
    List<Reply> getReplyListByTopicId(Integer id);

    void addReply(Reply reply);

    void delReply(Integer id);

    void delReplyList(Topic topic);
}
