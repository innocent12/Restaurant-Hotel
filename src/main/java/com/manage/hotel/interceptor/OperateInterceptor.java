package com.manage.hotel.interceptor;

import com.manage.hotel.entity.HotelUserEntity;
import com.manage.hotel.entity.HotelAdminEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;

@Component
public class OperateInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle)
        throws Exception{
        HotelAdminEntity admin =(HotelAdminEntity) request.getSession().getAttribute("admin");
        HotelUserEntity user =(HotelUserEntity) request.getSession().getAttribute("user");
        if (user == null&&admin == null){
            response.sendRedirect("/index");
            return false;
        }else if (user != null){
            return true;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
        throws Exception{
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception{
    }

}
