package com.manage.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.manage.hotel.entity.HotelCommentEntity;
import com.manage.hotel.entity.HotelFoodEntity;
import com.manage.hotel.entity.HotelRoomTypeEntity;
import com.manage.hotel.entity.HotelTableEntity;
import com.manage.hotel.service.HotelCommentService;
import com.manage.hotel.service.HotelFoodService;
import com.manage.hotel.service.HotelRoomTypeService;
import com.manage.hotel.service.HotelTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.Constants;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

@RequestMapping("/index")
@Controller
public class NormalController {

    @Autowired
    private Producer producer;

    @Autowired
    private HotelRoomTypeService hotelRoomTypeService;

    @Autowired
    private HotelFoodService hotelFoodService;

    @Autowired
    private HotelTableService hotelTableService;

    @Autowired
    private HotelCommentService hotelCommentService;

    @RequestMapping("/statistic")
    public String statistic(){
        return "statistic";
    }

    @RequestMapping("")
    public String index(Model model){
//      获取所有的房型list,房型相同的会自动去重
        QueryWrapper<HotelRoomTypeEntity> wrapper = new QueryWrapper<>();
        wrapper.select("distinct type,id,price,picture,description");
        List<HotelRoomTypeEntity> list = hotelRoomTypeService.list(wrapper);
        ArrayList<String> roomTypeList = new ArrayList<>();
        QueryWrapper<HotelTableEntity> wrapper1 = new QueryWrapper<>();
        wrapper1.select("distinct tab_name,tab_price,status");
        wrapper1.eq("status","空闲");
        List<HotelTableEntity> tabList = hotelTableService.list(wrapper1);
        model.addAttribute("AllRoomType", list);
        model.addAttribute("tabs", tabList);
//      清空list、wrapper
        QueryWrapper<HotelFoodEntity> foodWrapper = new QueryWrapper<>();
        foodWrapper.select("distinct food_name,id,picture,price,description");
        List<HotelFoodEntity> foodList = hotelFoodService.list(foodWrapper);
        model.addAttribute("foodList", foodList);
//        获取comment list
        List<HotelCommentEntity> commentList = hotelCommentService.list();
        model.addAttribute("comments", commentList);
        return "index";
    }

    /**
     * 背景页面
     * @return
     */
    @RequestMapping("/blackboard")
    public String blackboard(){
        return "blackboard";
    }

    @RequestMapping("/error")
    public String error(){ return "404"; }

    /**
     * 生成验证码
     */
    @RequestMapping("/kaptcha")
    public void index(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        response.setDateHeader("Expires", 0);

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = producer.createText();

        // store the text in the session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        // create the image with the text
        BufferedImage bi = producer.createImage(capText);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // write the data out
        try {
            ImageIO.write(bi, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            try {
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
