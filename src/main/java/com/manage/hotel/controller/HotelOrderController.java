package com.manage.hotel.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.manage.hotel.entity.*;
import com.manage.hotel.service.HotelOrderService;
import com.manage.hotel.service.HotelRoomTypeService;
import com.manage.hotel.service.HotelTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author auto-show
 * @since 2021-03-27
 */
@RequestMapping("/hotelOrder")
@Controller
public class HotelOrderController {

    private final String PREFIX = "/hotel/hotelOrder/";

    @Autowired
    private HotelOrderService hotelOrderService;

    @Autowired
    private HotelRoomTypeService hotelRoomTypeService;

    @Autowired
    private HotelTableService hotelTableService;

    @Autowired
    private ResultWrapper resultWrapper;

    /**
     * 跳转到酒店订单首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "hotelOrder";
    }

    /**
     * 跳转到添加酒店订单
     */
    @RequestMapping("/hotelOrder_add")
    public String hotelOrderAdd() {
        return PREFIX + "hotelOrder_add";
    }

    /**
     * 跳转到修改酒店订单
     */
    @RequestMapping("/hotelOrder_update/{hotelOrderId}")
    public String hotelOrderUpdate(@PathVariable Integer hotelOrderId, Model model) {
        HotelOrderEntity hotelOrder = hotelOrderService.getById(hotelOrderId);
        model.addAttribute("item",hotelOrder);
        return PREFIX + "hotelOrder_edit";
    }

    /**
     * 获取酒店订单列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return hotelOrderService.list(null);
    }

    /**
     * 新增酒店订单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HotelOrderEntity hotelOrder) {
        hotelOrderService.save(hotelOrder);
        return "{}";
    }

    /**
     * 跳转到个人订单界面
     * @return
     */
    @RequestMapping("/userOrder")
    public String userOrder(){
        return PREFIX + "userOrder";
    }

    /**
     * 订单中心
     * @param request
     * @return
     */
    @RequestMapping(value = "/searchOrder")
    @ResponseBody
    public Object searchOrder(HttpServletRequest request, Model model){
        QueryWrapper<HotelOrderEntity> wrapper = new QueryWrapper<>();
        HotelUserEntity user = (HotelUserEntity) request.getSession().getAttribute("user");
        wrapper.eq("name", user.getName());
        wrapper.notIn("status", "已删除");
        List<HotelOrderEntity> hotelOrderEntities = hotelOrderService.list(wrapper);
        return hotelOrderEntities;
    }

    /**
     * 订单收入统计
     * @param type
     * @param month
     * @return
     */
    @RequestMapping(value = "/income")
    @ResponseBody
    public Object income(String type, String month){
        QueryWrapper<HotelOrderEntity> wrapper = new QueryWrapper<>();
        String m = month.replace(".", "-");
        m = m + "-01 00:00:00";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-yyyy-dd HH:mm:ss");
        LocalDateTime t = LocalDateTime.parse(m, dateTimeFormatter);
        wrapper.between("create_time", t, t.plusMonths(1));
        wrapper.eq("status", "已完成");
        resultWrapper.clear();
        if (type.equals("tab")){
            wrapper.eq("order_type", "订餐");
            List<HotelOrderEntity> list = hotelOrderService.list(wrapper);
            JSONObject json = new JSONObject();
            for(HotelOrderEntity orderEntity: list){
                QueryWrapper<HotelTableEntity> wrapper1 = new QueryWrapper<>();
                wrapper1.eq("tab_name", orderEntity.getOrderName());
                HotelTableEntity te = hotelTableService.list(wrapper1).get(0);
                Double total = Double.parseDouble(te.getTabPrice());
                String type_str = orderEntity.getOrderName();
//                存在该餐桌,累加价格,否则，新增餐桌-价格、映射关系
                if(json.containsKey(type_str)){
                    json.put(type_str, json.getDouble(type_str) + total);
                }else {
                    json.put(type_str, total);
                }
            }
            resultWrapper.setMessage(type);
            resultWrapper.setObject(json);
            return resultWrapper;
        }else if (type.equals("room")){
            wrapper.eq("order_type","订房");
            List<HotelOrderEntity> list = hotelOrderService.list(wrapper);
            JSONObject json = new JSONObject();
            for(HotelOrderEntity orderEntity: list){
                QueryWrapper<HotelRoomTypeEntity> wrapper1 = new QueryWrapper<>();
                wrapper1.eq("type",orderEntity.getOrderName());
                HotelRoomTypeEntity room_type = hotelRoomTypeService.list(wrapper1).get(0);
                Double total = Double.parseDouble(room_type.getPrice())*orderEntity.calcDays();
                String type_str = room_type.getType();
//                存在该房型,累加价格,否则，新增房型-价格,映射关系
                if(json.containsKey(type_str)){
                    json.put(type_str, json.getDouble(type_str) + total);
                }else {
                    json.put(type_str, total);
                }
            }
            resultWrapper.setObject(json);
            resultWrapper.setMessage(type);
            return resultWrapper;
        }else {
            resultWrapper.setMessage("参数错误");
            return resultWrapper;
        }
    }

    /**
     * 用户端新增订单
     * @param request
     * @return
     */
    @RequestMapping(value = "/placeOrder")
    @ResponseBody
    public Object newOrder(HttpServletRequest request){
        HotelOrderEntity orderEntity = new HotelOrderEntity();
        String type = request.getParameter("orderType");
        if (type.equals("订房")){
            String roomType = request.getParameter("roomType").split("-")[0];
            QueryWrapper<HotelRoomTypeEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("type", roomType);
            Integer room_id = hotelRoomTypeService.list(wrapper).get(0).getId();
            orderEntity.setOrderType(type);
            orderEntity.setOrderName(roomType);
            orderEntity.setRoomId(room_id);
        }else if (type.equals("订餐")){
            String tabType = request.getParameter("tabName").split("-")[0];
            QueryWrapper<HotelTableEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("tab_name", tabType);
            Integer tab_id = hotelTableService.list(wrapper).get(0).getId();
            orderEntity.setOrderType(type);
            orderEntity.setOrderName(tabType);
            orderEntity.setTabId(tab_id);
        }
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String subTime = request.getParameter("subscribetime").replace(".", "-");
        String depTime = request.getParameter("departtime").replace(".", "-");
        orderEntity.setCreateTime(LocalDateTime.now());
        orderEntity.setSubscribeTime(LocalDate.parse(subTime, timeFormatter));
        orderEntity.setDepartTime(LocalDate.parse(depTime, timeFormatter));
        orderEntity.setCardid(request.getParameter("cardId"));
        orderEntity.setName(request.getParameter("name"));
        orderEntity.setStatus("已预约");
        hotelOrderService.save(orderEntity);
        resultWrapper.setMessage("订单预订成功");
        resultWrapper.setStatusCode(200);
        return resultWrapper;
    }

    /**
     * 删除酒店订单
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer hotelOrderId) {
        hotelOrderService.removeById(hotelOrderId);
        return "{}";
    }

    /**
     * 修改酒店订单
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(HotelOrderEntity hotelOrder) {
        System.out.println(hotelOrder.getId());
        hotelOrderService.updateById(hotelOrder);
        return "{}";
    }

    /**
     * 酒店订单详情
     */
    @RequestMapping(value = "/detail/{hotelOrderId}")
    @ResponseBody
    public Object detail(@PathVariable("hotelOrderId") Integer hotelOrderId) {
        return hotelOrderService.getById(hotelOrderId);
    }

}
