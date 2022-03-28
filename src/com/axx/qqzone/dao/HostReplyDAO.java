package com.axx.qqzone.dao;

import com.axx.qqzone.pojo.HostReply;

public interface HostReplyDAO {
    //通过回复的ID获取特定主人回复
    HostReply getHostReplyByReplyId(Integer replyId);

}
