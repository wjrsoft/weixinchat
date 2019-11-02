package com.wonder.core.dao;

import org.springframework.stereotype.Repository;

import com.wonder.core.schema.WxUser;
@Repository("wxUserDAO")
public interface WxUserDAO {
    WxUser selectByOpenid(String openid);
    void insertByWxUser(WxUser WxUser);
}