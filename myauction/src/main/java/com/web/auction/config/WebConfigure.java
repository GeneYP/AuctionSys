package com.web.auction.config;

import com.sun.javaws.IconUtil;
import com.web.auction.interceptor.CheckUserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration  //SpringMVC的配置类
public class WebConfigure implements WebMvcConfigurer {

    //addResourseHandler("") 虚拟的访问路径   Http请求访问照片
    //addResourceLocations("") 文件的物理路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**").addResourceLocations("file:D:/proTempFile/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> paths = new ArrayList<>();
        //不被拦截的内容↓
        paths.add("/css/**");
        paths.add("/js/**");
        paths.add("/images/**");
        paths.add("/defaultKaptcha");
        paths.add("/login");
        paths.add("/doLogin");
        paths.add("/register");
        paths.add("/doRegister");
        System.out.println("不被拦截的内容：" + paths);

        registry.addInterceptor(new CheckUserInterceptor()).addPathPatterns("/**").excludePathPatterns(paths);
    }
}
