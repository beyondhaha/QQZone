package com.axx.qqzone.dao.impl;

import com.axx.myssm.basedao.BaseDAO;
import com.axx.qqzone.dao.ReplyDAO;
import com.axx.qqzone.pojo.Reply;
import com.axx.qqzone.pojo.Topic;

import java.util.List;

public class ReplyDAOImpl extends BaseDAO<Reply> implements ReplyDAO {
    @Override
    public List<Reply> getReplyList(Topic topic) {
        return super.executeQuery("select * from t_reply where topic = ?", topic.getId());
    }

    @Override
    public void addReply(Reply reply) {
        super.executeUpdate("insert into t_reply values (0,?,?,?,?)", reply.getContent(), reply.getReplyDate(), reply.getAuthor().getId(), reply.getTopic().getId());
    }

    @Override
    public void delReply(Integer id) {
        super.executeUpdate("delete from t_reply where id = ?", id);
    }

    @Override
    public Reply getReply(Integer id) {
        Reply reply = super.load("select * from t_reply where id = ?", id);
        return reply;
    }
}
