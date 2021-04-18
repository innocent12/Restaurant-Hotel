package com.manage.hotel.controller;

import com.manage.hotel.entity.HotelRoomTypeEntity;
import com.manage.hotel.service.HotelRoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 房型表 前端控制器
 * </p>
 *
 * @author auto-show
 * @since 2021-03-27
 */
@RequestMapping("/hotelRoomType")
@Controller
public class HotelRoomTypeController {

    private final String PREFIX = "/hotel/hotelRoomType/";
    private final String IMG_PATH = "room_type/";

    @Autowired
    private HotelRoomTypeService hotelRoomTypeService;

    /**
     * 跳转到酒店房型首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "hotelRoomType";
    }

    /**
     * 跳转到添加酒店房型
     */
    @RequestMapping("/hotelRoomType_add")
    public String hotelRoomTypeAdd() {
        return PREFIX + "hotelRoomType_add";
    }

    /**
     * 跳转到修改酒店房型
     */
    @RequestMapping("/hotelRoomType_update/{hotelRoomTypeId}")
    public String hotelRoomTypeUpdate(@PathVariable Integer hotelRoomTypeId, Model model) {
        HotelRoomTypeEntity hotelRoomType = hotelRoomTypeService.getById(hotelRoomTypeId);
        model.addAttribute("item",hotelRoomType);
        return PREFIX + "hotelRoomType_edit";
    }

    /**
     * 获取酒店房型列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return hotelRoomTypeService.list(null);
    }

    /**
     * 新增酒店房型
     * 需对前台form-data进行解析
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HttpServletRequest request) {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        HotelRoomTypeEntity hotelRoomTypeEntity = new HotelRoomTypeEntity();
        if(file != null){
            System.out.println(file.getOriginalFilename());
            try {
                byte[] pic = file.getBytes();
                hotelRoomTypeEntity.setPicture(pic);
            }catch (Exception e){
                System.out.println("空文件");
                e.printStackTrace();
            }
        }
        hotelRoomTypeEntity.setType(params.getParameter("type"));
        hotelRoomTypeEntity.setDescription(params.getParameter("description"));
        hotelRoomTypeEntity.setPrice(params.getParameter("price"));
        hotelRoomTypeService.save(hotelRoomTypeEntity);
        return "{}";
    }

    /**
     * 删除酒店房型
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer hotelRoomTypeId) {
        hotelRoomTypeService.removeById(hotelRoomTypeId);
        return "{}";
    }

    /**
     * 修改酒店房型
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(HttpServletRequest request) {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        String update_pic = request.getParameter("pic_status");
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");
        HotelRoomTypeEntity hotelRoomTypeEntity = new HotelRoomTypeEntity();
        if(file != null&&update_pic.equals("true")){
            try {
                byte[] blob = file.getBytes();
                hotelRoomTypeEntity.setPicture(blob);
            }catch (Exception e){
                e.printStackTrace();
            }
        }else {
            System.out.println("空文件");
        }
        hotelRoomTypeEntity.setType(params.getParameter("type"));
        hotelRoomTypeEntity.setId(Integer.parseInt(params.getParameter("id")));
        hotelRoomTypeEntity.setDescription(params.getParameter("description"));
        hotelRoomTypeEntity.setPrice(params.getParameter("price"));
        hotelRoomTypeService.updateById(hotelRoomTypeEntity);
        return "{}";
    }

    /**
     * 酒店房型详情
     */
    @RequestMapping(value = "/detail/{hotelRoomTypeId}")
    @ResponseBody
    public Object detail(@PathVariable("hotelRoomTypeId") Integer hotelRoomTypeId) {
        return hotelRoomTypeService.getById(hotelRoomTypeId);
    }
}
