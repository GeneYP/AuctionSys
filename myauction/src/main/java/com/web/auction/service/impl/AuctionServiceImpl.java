package com.web.auction.service.impl;

import com.web.auction.mapper.AuctionCustomMapper;
import com.web.auction.mapper.AuctionMapper;
import com.web.auction.mapper.AuctionrecordMapper;
import com.web.auction.pojo.Auction;
import com.web.auction.pojo.Auctionrecord;
import com.web.auction.pojo.AuctionrecordExample;
import com.web.auction.service.AuctionService;
import com.web.auction.utils.AuctionPriceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.util.Date;
import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionMapper auctionMapper;
    @Autowired
    private AuctionrecordMapper auctionrecordMapper;
    @Autowired
    private AuctionCustomMapper auctionCustomMapper;

    @Override
    public List<Auction> queryAuctions() {
        List<Auction> list = auctionMapper.selectByExample(null);
        return list;
    }

    @Override
    public void addAuction(Auction auction) {
        auctionMapper.insert(auction);
    }


//    @Override
//    public void deleteAuction(int auctionid) {
//        System.out.println("即将删除" + auctionid);
//        auctionMapper.deleteByPrimaryKey(auctionid);
//    }

    @Transactional
    @Override
    public void removeAuction(int auctionid) {
        AuctionrecordExample example = new AuctionrecordExample();
        AuctionrecordExample.Criteria criteria = example.createCriteria();
        criteria.andAuctionidEqualTo(auctionid);
        auctionrecordMapper.deleteByExample(example);
        auctionMapper.deleteByPrimaryKey(auctionid);
    }

    @Override
    public Auction findAuctionById(int auctionid) {
        return auctionMapper.selectByPrimaryKey(auctionid);
    }

    @Override
    public void updateAuction(Auction auction) {
        System.out.println("即将修改：" + auction);
        auctionMapper.updateByPrimaryKey(auction);
    }

    @Override
    public Auction findAuctionAndRecordById(int auctionid) {
        return auctionCustomMapper.findAuctionAndRecordById(auctionid);
    }

    /**
     * 判断当前时间是否过期
     * 如果首次竞价必须高于起拍价
     * 如果后序竞价必须高于最高价
     * @param record
     * @throws Exception
     */
    @Override
    public void addAuctionrecord(Auctionrecord record) throws Exception {
        //判断业务规则
        Auction auction = auctionCustomMapper.findAuctionAndRecordById(record.getAuctionid());
        if(auction.getAuctionendtime().after(new Date())==false){
            throw new AuctionPriceException("商品竞拍已结束!");
        } else {
            //判断是不是首次竞价
            if(auction.getAuctionrecordList().size()>0){  //是首次
                Auctionrecord maxRecord = auction.getAuctionrecordList().get(0);
                if(record.getAuctionprice()<=maxRecord.getAuctionprice()){
                    throw new AuctionPriceException("必须高于最高价！");
                }
            } else {    // 不是首次
                if(record.getAuctionprice()<=auction.getAuctionstartprice()){
                    throw new AuctionPriceException("必须高于起拍价！");
                }
            }
        }
        auctionrecordMapper.insert(record);
    }
}
