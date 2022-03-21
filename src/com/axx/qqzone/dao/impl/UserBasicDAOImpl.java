package com.axx.qqzone.dao.impl;

import com.axx.myssm.basedao.BaseDAO;
import com.axx.qqzone.dao.UserBasicDAO;
import com.axx.qqzone.pojo.UserBasic;

import java.util.List;

public class UserBasicDAOImpl extends BaseDAO<UserBasic> implements UserBasicDAO {
    @Override
    public UserBasic getUserBasic(String loginId, String pwd) {
        return super.load("select * from t_user_basic where loginId = ? and pwd = ?", loginId, pwd);
    }

    @Override
    public List<UserBasic> getFriendList(UserBasic userBasic) {
        String sql = "select * from t_user_basic where id in (select fid from t_friend where uid = ?)";
        return super.executeQuery(sql, userBasic.getId());
    }
}
