package com.web.auction.service.impl;

import com.web.auction.mapper.AuctionMapper;
import com.web.auction.pojo.Auction;
import com.web.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionMapper auctionMapper;

    @Override
    public List<Auction> queryAuctions() {
        List<Auction> list = auctionMapper.selectByExample(null);
        return list;
    }

    @Override
    public void addAuction(Auction auction) {
        auctionMapper.insert(auction);
    }
}
