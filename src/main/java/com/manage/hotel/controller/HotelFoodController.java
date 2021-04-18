package com.manage.hotel.controller;

import com.manage.hotel.service.HotelFoodService;
import com.manage.hotel.entity.HotelFoodEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 特色菜 前端控制器
 * </p>
 *
 * @author auto-show
 * @since 2021-03-27
 */
@RequestMapping("/hotelFood")
@Controller
public class HotelFoodController {
    private final String PREFIX = "/hotel/hotelFood/";

    @Autowired
    private HotelFoodService hotelFoodService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "hotelFood";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/hotelFood_add")
    public String hotelFoodAdd() {
        return PREFIX + "hotelFood_add";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/hotelFood_update/{hotelFoodId}")
    public String hotelFoodUpdate(@PathVariable Integer hotelFoodId, Model model) {
        HotelFoodEntity hotelFood = hotelFoodService.getById(hotelFoodId);
        model.addAttribute("item",hotelFood);
        return PREFIX + "hotelFood_edit";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return hotelFoodService.list(null);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HttpServletRequest request) {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        HotelFoodEntity hotelFoodEntity = new HotelFoodEntity();
        if(file != null){
//            System.out.println(file.getOriginalFilename());
            try {
                byte[] pic = file.getBytes();
                hotelFoodEntity.setPicture(pic);
            }catch (Exception e){
                System.out.println("空文件");
                e.printStackTrace();
            }
        }
        hotelFoodEntity.setFoodName(params.getParameter("foodName"));
        hotelFoodEntity.setDescription(params.getParameter("description"));
        hotelFoodEntity.setPrice(params.getParameter("price"));
        hotelFoodService.save(hotelFoodEntity);
        return "{}";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer hotelFoodId) {
        hotelFoodService.removeById(hotelFoodId);
        return "{}";
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(HttpServletRequest request) {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        String update_pic = request.getParameter("pic_status");
        HotelFoodEntity hotelFood = new HotelFoodEntity();
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        if(file != null&&update_pic.equals("true")){
            try {
                byte[] blob = file.getBytes();
                hotelFood.setPicture(blob);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            System.out.println("空文件");
        }
        hotelFood.setFoodName(params.getParameter("foodName"));
        hotelFood.setId(Integer.parseInt(params.getParameter("id")));
        hotelFood.setDescription(params.getParameter("description"));
        hotelFood.setPrice(params.getParameter("price"));
        hotelFoodService.updateById(hotelFood);
        return "{}";
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{hotelFoodId}")
    @ResponseBody
    public Object detail(@PathVariable("hotelFoodId") Integer hotelFoodId) {
        return hotelFoodService.getById(hotelFoodId);
    }
}
