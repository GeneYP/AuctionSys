package com.web.auction.service.impl;

import com.web.auction.mapper.AuctionMapper;
import com.web.auction.mapper.AuctionuserMapper;
import com.web.auction.pojo.Auctionuser;
import com.web.auction.pojo.AuctionuserExample;
import com.web.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuctionuserMapper auctionuserMapper;

    @Override
    public Auctionuser login(String username, String password) {
        //select * from auctionuser Where username=? and password=?
        AuctionuserExample example = new AuctionuserExample();
        AuctionuserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andUserpasswordEqualTo(password);
        List<Auctionuser> list = auctionuserMapper.selectByExample(example);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void register(Auctionuser user) {
        user.setUserisadmin(0);   //默认普通用户注册
        auctionuserMapper.insert(user);
    }
}
