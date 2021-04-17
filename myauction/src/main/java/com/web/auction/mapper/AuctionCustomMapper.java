package com.web.auction.mapper;

import com.web.auction.pojo.Auction;
import com.web.auction.pojo.AuctionCustom;

import java.util.List;

public interface AuctionCustomMapper {
    public Auction findAuctionAndRecordById(int auctionid);

    public List<AuctionCustom> findAuctionEnd();

    public List<Auction> findAuctionNow();
}
