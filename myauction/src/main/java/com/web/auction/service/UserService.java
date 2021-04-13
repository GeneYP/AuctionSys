package com.web.auction.service;

import com.web.auction.pojo.Auctionuser;

public interface UserService {

    /**
     * 登录方法
     * @param username  账号
     * @param password  密码
     * @return          用户对象，非空判断是否存在
     */
    public Auctionuser login(String username, String password);

    public void register(Auctionuser user);
}
