package com.manage.hotel.tools;


import com.google.code.kaptcha.Constants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class KaptchaUtil {

    public static boolean checkKaptchaCode(HttpServletRequest request){
        String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String actual = request.getParameter("kaptcha");
        if (actual == null || !code.equals(actual)){
            return false;
        }
        return true;
    }
}
