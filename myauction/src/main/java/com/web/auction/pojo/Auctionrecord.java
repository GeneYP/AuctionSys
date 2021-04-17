package com.web.auction.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Auctionrecord implements Serializable {
    private Integer id;

    private Integer userid;

    private Integer auctionid;

    private Date auctiontime;

    private double auctionprice;

    private Auctionuser user;  //一方关联 一对多

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Integer auctionid) {
        this.auctionid = auctionid;
    }

    public Date getAuctiontime() {
        return auctiontime;
    }

    public void setAuctiontime(Date auctiontime) {
        this.auctiontime = auctiontime;
    }

    public double getAuctionprice() {
        return auctionprice;
    }

    public void setAuctionprice(double auctionprice) {
        this.auctionprice = auctionprice;
    }

    public Auctionuser getUser() {
        return user;
    }

    public void setUser(Auctionuser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userid=").append(userid);
        sb.append(", auctionid=").append(auctionid);
        sb.append(", auctiontime=").append(auctiontime);
        sb.append(", auctionprice=").append(auctionprice);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}