package com.manage.hotel.config;

import com.manage.hotel.interceptor.OperateInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    String[] addPathPatterns = {
            "/hotelUser/**","/hotelAdmin/**","/hotelComment/**","/hotelFood/**",
            "/hotelOrder/**","/hotelRoom/**","/hotelRoomType/**","/hotelTable/**"
    };

    String[] excludePathPatterns = {
            "/hotelAdmin/login","/index","/hotelUser/login_view","/index/kaptcha",
            "/hotelUser/register_view","/hotelUser/valid_user","/hotelUser/add"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //添加对用户未登录的拦截器，并添加排除项
        registry.addInterceptor(new OperateInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }
}
