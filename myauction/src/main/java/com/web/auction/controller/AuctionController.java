package com.web.auction.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.auction.pojo.Auction;
import com.web.auction.pojo.Auctionrecord;
import com.web.auction.pojo.Auctionuser;
import com.web.auction.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
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

    @GetMapping("/deleteAuction")
    public String deleteAuction(int auctionid){
        //删除(
        auctionService.removeAuction(auctionid);
        return "redirect:/queryAuctions";
    }

    @GetMapping("/toUpdate")
    public ModelAndView toUpdate(int auctionid){    //先查询再更新
        Auction auction = auctionService.findAuctionById(auctionid);
        ModelAndView mv = new ModelAndView();
        mv.addObject("auction", auction);  //数据放入视图
        mv.setViewName("updateAuction");
        return mv;
    }

    @PostMapping("/submitUpdateAuction")
    public String submitUpdateAuction(Auction auction, MultipartFile pic){
        //1. 处理文件，保存到文件夹中  D://ProTempFile
        try{
            String path = "D:/proTempFile";
            if (pic.getSize() > 0) {
                File oldFile = new File(path, auction.getAuctionpic());  //先把旧的文件地址给检查咯，有的话给删咯
                if(oldFile.exists()){
                    oldFile.delete();
                }
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
        auctionService.updateAuction(auction);
        return "redirect:/queryAuctions";
    }

    @GetMapping("/toDetail")
    public ModelAndView toDetail(int auctionid){
        Auction auction = auctionService.findAuctionAndRecordById(auctionid);
        ModelAndView mv = new ModelAndView();
        mv.addObject("auctionDetail", auction);
        mv.setViewName("auctionDetail");
        return mv;
    }

    @PostMapping("/saveAuctionRecord")   //DispatcherServlet(异常会抛给这个前段控制器)-->异常处理器要集中处理应用的异常
    public String saveAuctionRecord(Auctionrecord record, HttpSession session){
        record.setAuctiontime(new Date());
        Auctionuser user = (Auctionuser) session.getAttribute("user");
        record.setUserid(user.getUserid());
        return "redirect:/toDetail?auctionid=" + record.getAuctionid();
    }
}
