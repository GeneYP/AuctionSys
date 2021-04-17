package com.web.auction;


import com.web.auction.mapper.AuctionCustomMapper;
import com.web.auction.mapper.AuctionMapper;
import com.web.auction.pojo.Auction;
import com.web.auction.pojo.Auctionrecord;
import com.web.auction.service.AuctionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestAuction {

    @Autowired
    private AuctionMapper auctionMapper;   //DAO接口
    @Autowired
    private AuctionService auctionService;

    @Test
    public void testQuery(){
        List<Auction> list = auctionMapper.selectByExample(null);
        for (Auction auction : list) {
            System.out.println(auction);
        }
    }

    @Test
    public void testQuery2(){
        Auction auction = auctionService.findAuctionAndRecordById(15);
        System.out.println(auction);
        List<Auctionrecord> list = auction.getAuctionrecordList();
        for (Auctionrecord auctionrecord : list) {
            System.out.println(auctionrecord.getAuctiontime()+","+auctionrecord.getAuctionprice()+","+auctionrecord.getUser().getUsername());
        }
    }
}
