package com.web.auction.service;

import com.web.auction.pojo.Auction;
import com.web.auction.pojo.Auctionrecord;

import java.util.List;

public interface AuctionService {

    public List<Auction> queryAuctions();

    public void addAuction(Auction auction);
    
//    public void deleteAuction(int auctionid);

    public void removeAuction(int auctionid);

    public Auction findAuctionById(int auctionid);

    public void updateAuction(Auction auction);

    public Auction findAuctionAndRecordById(int auctionid);

    public void addAuctionrecord(Auctionrecord record) throws Exception;
}
