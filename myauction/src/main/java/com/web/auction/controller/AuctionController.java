package com.web.auction.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.auction.pojo.Auction;
import com.web.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

@Controller
public class AuctionController {
    @Autowired
    private AuctionService auctionService;

    public static final int PAGE_SIZE = 10;

    @GetMapping("/queryAuctions")   //分页查询   //preHandle
    public ModelAndView queryAuctions(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum){
        //1. 分页拦截  重构sql语句  注：要先拦截再查询
        PageHelper.startPage(pageNum, PAGE_SIZE);
        List<Auction> list= auctionService.queryAuctions();
        //2. 构建分页bean
        PageInfo page = new PageInfo(list);
        ModelAndView mv = new ModelAndView();
        mv.addObject("auctionList", list);
        mv.addObject("page", page);
        mv.setViewName("index");
        //postHandle
        return mv;
        //afterCompletion
    }

    @PostMapping("/publishAuctions")
    public String publicAuctions(Auction auction, MultipartFile pic){
        //1. 处理文件，保存到文件夹中  D://ProTempFile
        try{
            if (pic.getSize() > 0) {
                String path = "D:/proTempFile";
                File targetFile = new File(path, pic.getOriginalFilename());
                pic.transferTo(targetFile);
                //设置图片的名字、类型
                auction.setAuctionpic(pic.getOriginalFilename());
                auction.setAuctionpictype(pic.getContentType());
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        //2. 保存文本数据到数据库中。
        auctionService.addAuction(auction);
        return "redirect:/queryAuctions";
    }

    @GetMapping("/toAdd")
    public String toAdd(){
        return "addAuction";
    }

    @DeleteMapping("/deleteAuction/{auctionid}")
    public String deleteAuction(@PathVariable int auctionid){
        //1. 查找

        //2. 保存删除操作
        auctionService.deleteAuction(auctionid);
        return "redirect:/queryAuctions";
    }
}
