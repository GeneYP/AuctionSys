package com.web.auction.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.web.auction.pojo.Auctionuser;
import com.web.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        System.out.println("正在登陆...");
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(String username, String password, String valideCode, HttpSession session, Model model){
        //1. 判断验证码
        String vrifyCode = (String) session.getAttribute("vrifyCode");
        if(!vrifyCode.equals(valideCode)){
            model.addAttribute("errorMsg", "验证码错误");
            return "login";
        }
        Auctionuser loginUser = userService.login(username, password);
        System.out.println("LoginUser：" + loginUser + username + password);
        if(loginUser != null){
            //保存
            session.setAttribute("user", loginUser);
            System.out.println("登录"+session);
            return "redirect:/queryAuctions";
        }else{
            model.addAttribute("errorMsg", "账号或密码错误");
            return "login";
        }
    }

    @GetMapping("/doLogout")
    public String doLogout(HttpSession session){
        session.invalidate();
        return "login";
    }

    //BindingResult表示错误消息存放的容器，必须紧跟pojo对象之后
    @PostMapping("/doRegister")
    public String doRegister(@ModelAttribute("registerModel") @Validated Auctionuser user, BindingResult bindingResult,
                             Model model){
        //1. 判断校验是否通过
        if (bindingResult.hasErrors()){
            //提取错误消息，返回Register
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                //获取名字返回
                model.addAttribute(error.getField(), error.getDefaultMessage());
            }
            return "register";
        }
        //2. 保存到数据库
        userService.register(user);
        return "login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("registerModel") Auctionuser user){
        return "register";
    }

    @Autowired
    private DefaultKaptcha captchaProducer;
    /**
     * 获取验证码
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws Exception {
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = captchaProducer.createText();
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = captchaProducer.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
