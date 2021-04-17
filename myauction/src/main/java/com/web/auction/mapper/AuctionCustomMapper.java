package com.web.auction.mapper;

import com.web.auction.pojo.Auction;

public interface AuctionCustomMapper {
    public Auction findAuctionAndRecordById(int auctionid);
}
