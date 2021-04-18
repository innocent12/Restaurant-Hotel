package com.manage.hotel.controller;

import com.manage.hotel.service.HotelTableService;
import com.manage.hotel.entity.HotelTableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 餐桌表 前端控制器
 * </p>
 *
 * @author auto-show
 * @since 2021-03-27
 */
@RequestMapping("/hotelTable")
@Controller
public class HotelTableController {
    private final String PREFIX = "/hotel/hotelTable/";

    @Autowired
    private HotelTableService hotelTableService;

    /**
     * 跳转到酒店餐桌首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "hotelTable";
    }

    /**
     * 跳转到添加酒店餐桌
     */
    @RequestMapping("/hotelTable_add")
    public String hotelTableAdd() {
        return PREFIX + "hotelTable_add";
    }

    /**
     * 跳转到修改酒店餐桌
     */
    @RequestMapping("/hotelTable_update/{hotelTableId}")
    public String hotelTableUpdate(@PathVariable Integer hotelTableId, Model model) {
        HotelTableEntity hotelTable = hotelTableService.getById(hotelTableId);
        model.addAttribute("item",hotelTable);
        return PREFIX + "hotelTable_edit";
    }

    /**
     * 获取酒店餐桌列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return hotelTableService.list(null);
    }

    /**
     * 新增酒店餐桌
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HotelTableEntity hotelTable) {
        hotelTableService.save(hotelTable);
        return "{}";
    }

    /**
     * 删除酒店餐桌
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer hotelTableId) {
        hotelTableService.removeById(hotelTableId);
        return "{}";
    }

    /**
     * 修改酒店餐桌
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(HotelTableEntity hotelTable) {
        hotelTableService.updateById(hotelTable);
        return "{}";
    }

    /**
     * 酒店餐桌详情
     */
    @RequestMapping(value = "/detail/{hotelTableId}")
    @ResponseBody
    public Object detail(@PathVariable("hotelTableId") Integer hotelTableId) {
        return hotelTableService.getById(hotelTableId);
    }
}
