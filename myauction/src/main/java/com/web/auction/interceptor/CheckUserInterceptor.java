package com.web.auction.interceptor;

import com.web.auction.pojo.Auctionuser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckUserInterceptor implements HandlerInterceptor {
    //在执行Handler之前拦截
    //true:进入handler false:不能进入
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        System.out.println("Handle的Request：" + request);
        System.out.println("Handle的Session：" + session);
        Auctionuser user = (Auctionuser) session.getAttribute("user");
        System.out.println("Handle拿到了" + user);
        if (user != null){
            System.out.println("登陆成功！\n" + user);
            return true;
        } else {
            //重定向到login
            System.out.println("拦截了一个请求");
            response.sendRedirect("/login");
            return false;
        }

    }

    //执行Handler之后，在return之前调用
    //应用场景：在需要修改Handler的M A V时可调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //执行Handler之后，在return之后调用
    //在调用完Handler时，做异常的日志的记录
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
