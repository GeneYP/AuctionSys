package com.web.auction.pojo;

public class AuctionCustom extends Auction{
//商品竞拍类继承商品类
    private String auctionPrice;
    private String userName;

    public String getAuctionPrice() {
        return auctionPrice;
    }

    public void setAuctionPrice(String auctionPrice) {
        this.auctionPrice = auctionPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
