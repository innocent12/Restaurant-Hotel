package com.manage.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.manage.hotel.entity.ResultWrapper;
import com.manage.hotel.service.HotelAdminService;
import com.manage.hotel.entity.HotelAdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.manage.hotel.tools.ResponseData;
import com.manage.hotel.tools.KaptchaUtil;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author auto-show
 * @since 2021-03-27
 */
@RequestMapping("/hotelAdmin")
@Controller
public class HotelAdminController {
    private final String PREFIX = "/hotel/hotelAdmin/";

    @Autowired
    private ResponseData responseData;

    @Autowired
    private HotelAdminService hotelAdminService;

    @Autowired
    private ResultWrapper resultWrapper;

    /**
     * 跳转到login界面
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView(){
        return PREFIX + "login";
    }

    /**
     * 管理员登录接口
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object hotelAdminLogin(HttpServletRequest request, Model model){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(!KaptchaUtil.checkKaptchaCode(request)){
            resultWrapper.setMessage("验证码错误");
            resultWrapper.setStatusCode(201);
            return resultWrapper;
        }
        QueryWrapper<HotelAdminEntity> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("name", username);
        adminQueryWrapper.eq("password", password);
        HotelAdminEntity hotelAdminEntity = hotelAdminService.getOne(adminQueryWrapper);
        if (hotelAdminEntity == null){
            resultWrapper.setMessage("账号或密码错误");
            resultWrapper.setStatusCode(202);
            return resultWrapper;
        }else{
//            保存用户信息到session
            request.getSession().setAttribute("admin", hotelAdminEntity);
            resultWrapper.setMessage("登录成功");
            resultWrapper.setStatusCode(200);
            return resultWrapper;
        }
    }

    /**
     * 管理员端首页
     * @param model
     * @return
     */
    @RequestMapping(value = "/manage")
    public String hotelManagement(Model model){
        List list = Arrays.asList("管理员","留言板","特色菜","订单","客房","房型","餐桌","用户");
        model.addAttribute("titles", list);
        return PREFIX + "manage";
    }

    /**
     * 跳转到管理员首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "hotelAdmin";
    }

    /**
     * 跳转到添加管理员
     */
    @RequestMapping("/hotelAdmin_add")
    public String hotelAdminAdd() {
        return PREFIX + "hotelAdmin_add";
    }

    /**
     * 跳转到修改管理员
     */
    @RequestMapping("/hotelAdmin_update/{hotelAdminId}")
    public String hotelAdminUpdate(@PathVariable Integer hotelAdminId, Model model) {
        HotelAdminEntity hotelAdmin = hotelAdminService.getById(hotelAdminId);
        model.addAttribute("item",hotelAdmin);
        return PREFIX + "hotelAdmin_edit";
    }

    /**
     * 获取管理员列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return hotelAdminService.list(null);
    }

    /**
     * 新增管理员
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HotelAdminEntity hotelAdmin) {
        hotelAdminService.save(hotelAdmin);
        return responseData.SuccessResponseData(null);
    }

    /**
     * 删除管理员
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer hotelAdminId) {
        hotelAdminService.removeById(hotelAdminId);
        return "{}";
    }

    /**
     * 修改管理员
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(HotelAdminEntity hotelAdmin) {
        hotelAdminService.updateById(hotelAdmin);
        return "{}";
    }

    /**
     * 管理员详情
     */
    @RequestMapping(value = "/detail/{hotelAdminId}")
    @ResponseBody
    public Object detail(@PathVariable("hotelAdminId") Integer hotelAdminId) {
        return hotelAdminService.getById(hotelAdminId);
    }
}
