package com.manage.hotel.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.manage.hotel.entity.ResultWrapper;
import com.manage.hotel.service.HotelUserService;
import com.manage.hotel.entity.HotelUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.manage.hotel.tools.KaptchaUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author auto-show
 * @since 2021-03-27
 */
@RequestMapping("/hotelUser")
@Controller
public class HotelUserController {
    private final String PREFIX = "/hotel/hotelUser/";

    @Autowired
    private HotelUserService hotelUserService;

    @Autowired
    private ResultWrapper resultWrapper;

    /**
     * 跳转到酒店用户首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "hotelUser";
    }

    /**
     * 用户注册页面
     * @return
     */
    @RequestMapping("/register_view")
    public String register(){
        return PREFIX + "register";
    }

    /**
     * 用户登录页面
     * @return
     */
    @RequestMapping("/login_view")
    public String login(){ return PREFIX + "login";}

    /**
     * 清空session用户信息
     * 用户登出
     * @return
     */
    @RequestMapping("/logOut")
    @ResponseBody
    public Object logOut(HttpServletRequest request){
        request.getSession().invalidate();
        return "{}";
    }

    /**
     * 用户登录入口,成功后保存数据到session
     * @param model ,request
     * @return
     */
    @RequestMapping("/valid_user")
    @ResponseBody
    public Object validUser(Model model, HttpServletRequest request){
        HotelUserEntity hotelUserEntity = new HotelUserEntity();
        hotelUserEntity.setName(request.getParameter("name"));
        hotelUserEntity.setPassword(request.getParameter("password"));
        if (!KaptchaUtil.checkKaptchaCode(request)){
            resultWrapper.setMessage("验证码有误");
            return resultWrapper;
        }
        QueryWrapper<HotelUserEntity> wrapper = new QueryWrapper<>();
        wrapper.setEntity(hotelUserEntity);
        hotelUserEntity = hotelUserService.getOne(wrapper);
        if (hotelUserEntity.getId() == null){
            resultWrapper.setMessage("账号或密码错误");
            return resultWrapper;
        }
        model.addAttribute("user", hotelUserEntity);
        request.getSession().setAttribute("user",hotelUserEntity);
        return hotelUserEntity;
    }

    /**
     * 跳转到添加酒店用户
     */
    @RequestMapping("/hotelUser_add")
    public String hotelUserAdd() {
        return PREFIX + "hotelUser_add";
    }

    /**
     * 跳转到修改酒店用户
     */
    @RequestMapping("/hotelUser_update/{hotelUserId}")
    public String hotelUserUpdate(@PathVariable Integer hotelUserId, Model model) {
        HotelUserEntity hotelUser = hotelUserService.getById(hotelUserId);
        model.addAttribute("item",hotelUser);
        return PREFIX + "hotelUser_edit";
    }

    /**
     * 获取酒店用户列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return hotelUserService.list(null);
    }

    /**
     * 新增酒店用户
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HotelUserEntity hotelUser) {
        QueryWrapper<HotelUserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("name", hotelUser.getName());
        if (hotelUserService.getOne(wrapper) == null){
            hotelUserService.save(hotelUser);
            resultWrapper.setMessage("success");
        }else {
            resultWrapper.setMessage("exist");
        }
        return resultWrapper;
    }

    /**
     * 删除酒店用户
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer hotelUserId) {
        hotelUserService.removeById(hotelUserId);
        return "{}";
    }

    /**
     * 修改酒店用户
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(HotelUserEntity hotelUser) {
        hotelUserService.updateById(hotelUser);
        return "{}";
    }

    /**
     * 酒店用户详情
     */
    @RequestMapping(value = "/detail/{hotelUserId}")
    @ResponseBody
    public Object detail(@PathVariable("hotelUserId") Integer hotelUserId) {
        return hotelUserService.getById(hotelUserId);
    }
}
