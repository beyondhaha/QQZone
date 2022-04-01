package com.axx.qqzone.dao.impl;

import com.axx.myssm.basedao.BaseDAO;
import com.axx.qqzone.dao.HostReplyDAO;
import com.axx.qqzone.pojo.HostReply;

public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return super.load("select * from t_host_reply where reply = ?", replyId);
    }

    @Override
    public void delHostReply(Integer id) {
        super.executeUpdate("delete from t_host_reply where id = ?", id);
    }
}
