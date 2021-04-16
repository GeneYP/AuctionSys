package com.web.auction.service;

import com.web.auction.pojo.Auction;

import java.util.List;

public interface AuctionService {

    public List<Auction> queryAuctions();

    public void addAuction(Auction auction);
    
    public void deleteAuction(int auctionid);
}
