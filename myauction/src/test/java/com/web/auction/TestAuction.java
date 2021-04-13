package com.web.auction;


import com.web.auction.mapper.AuctionMapper;
import com.web.auction.pojo.Auction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestAuction {

    @Autowired
    private AuctionMapper auctionMapper;   //DAO接口

    @Test
    public void testQuery(){
        List<Auction> list = auctionMapper.selectByExample(null);
        for (Auction auction : list) {
            System.out.println(auction);
        }
    }

}
