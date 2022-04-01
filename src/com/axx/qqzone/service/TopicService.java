package com.axx.qqzone.service;

import com.axx.qqzone.pojo.Topic;
import com.axx.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicService {
    //查询特定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic);

    //根据id获取特定topic
    Topic getTopicById(Integer id);

    //通过id获取指定的topic信息，包含这个topic关联的作者信息
    Topic getTopic(Integer id);

    //通过id删除topic
    void delTopic(Integer id);
}
