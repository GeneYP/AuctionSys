package com.web.auction.controller;

import com.web.auction.utils.AuctionPriceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理
 */
@ControllerAdvice    //特殊的Controller
public class MyException {

    @ExceptionHandler(AuctionPriceException.class)      //处理某个异常的方法
    public ModelAndView handleException(Exception ex){
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMsg", ex.getMessage());
        mv.setViewName("error");
        return mv;
    }
}
