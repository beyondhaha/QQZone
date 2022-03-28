package com.axx.qqzone.service;

import com.axx.qqzone.pojo.Reply;

import java.util.List;

public interface ReplyService {
    List<Reply> getReplyListByTopicId(Integer id);
}
