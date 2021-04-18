package com.manage.hotel.controller;


import com.manage.hotel.service.HotelRoomService;
import com.manage.hotel.entity.HotelRoomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 客房表 前端控制器
 * </p>
 *
 * @author auto-show
 * @since 2021-03-27
 */
@RequestMapping("/hotelRoom")
@Controller
public class HotelRoomController {

    private final String PREFIX = "/hotel/hotelRoom/";

    @Autowired
    private HotelRoomService hotelRoomService;

    /**
     * 跳转到酒店客房首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "hotelRoom";
    }

    /**
     * 跳转到添加酒店客房
     */
    @RequestMapping("/hotelRoom_add")
    public String hotelRoomAdd() {
        return PREFIX + "hotelRoom_add";
    }

    /**
     * 跳转到修改酒店客房
     */
    @RequestMapping("/hotelRoom_update/{hotelRoomId}")
    public String hotelRoomUpdate(@PathVariable Integer hotelRoomId, Model model) {
        HotelRoomEntity hotelRoom = hotelRoomService.getById(hotelRoomId);
        model.addAttribute("item",hotelRoom);
        return PREFIX + "hotelRoom_edit";
    }

    /**
     * 获取酒店客房列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return hotelRoomService.list(null);
    }

    /**
     * 新增酒店客房
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HotelRoomEntity hotelRoom) {
        hotelRoomService.save(hotelRoom);
        return "{}";
    }

    /**
     * 删除酒店客房
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer hotelRoomId) {
        hotelRoomService.removeById(hotelRoomId);
        return "{}";
    }

    /**
     * 修改酒店客房
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(HotelRoomEntity hotelRoom) {
        hotelRoomService.updateById(hotelRoom);
        return "{}";
    }

    /**
     * 酒店客房详情
     */
    @RequestMapping(value = "/detail/{hotelRoomId}")
    @ResponseBody
    public Object detail(@PathVariable("hotelRoomId") Integer hotelRoomId) {
        return hotelRoomService.getById(hotelRoomId);
    }

}
