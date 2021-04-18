package com.manage.hotel.controller;

import com.manage.hotel.entity.HotelCommentEntity;
import com.manage.hotel.entity.HotelUserEntity;
import com.manage.hotel.entity.ResultWrapper;
import com.manage.hotel.service.HotelCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * <p>
 * 留言表 前端控制器
 * </p>
 *
 * @author auto-show
 * @since 2021-03-27
 */
@RequestMapping("/hotelComment")
@Controller
public class HotelCommentController {
    private final String PREFIX = "/hotel/hotelComment/";

    @Autowired
    private HotelCommentService hotelCommentService;

    @Autowired
    private ResultWrapper resultWrapper;

    /**
     * 跳转到留言首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "hotelComment";
    }

    /**
     * 跳转到添加留言
     */
    @RequestMapping("/hotelComment_add")
    public String hotelCommentAdd() {
        return PREFIX + "hotelComment_add";
    }

    /**
     * 跳转到修改留言
     */
    @RequestMapping("/hotelComment_update/{hotelCommentId}")
    public String hotelCommentUpdate(@PathVariable Integer hotelCommentId, Model model) {
        HotelCommentEntity hotelComment = hotelCommentService.getById(hotelCommentId);
        model.addAttribute("item",hotelComment);
        return PREFIX + "hotelComment_edit";
    }

    /**
     * 发布留言
     * @param request
     * @return
     */
    @RequestMapping("/addComment")
    @ResponseBody
    public Object hotelAddComment(HttpServletRequest request){
        String comment = request.getParameter("content");
        HotelUserEntity hotelUserEntity = (HotelUserEntity) request.getSession().getAttribute("user");
        HotelCommentEntity hotelCommentEntity = new HotelCommentEntity();
        if (hotelUserEntity != null){
            hotelCommentEntity.setName(hotelUserEntity.getName());
        }
        hotelCommentEntity.setCreateTime(LocalDateTime.now());
        hotelCommentEntity.setContent(comment);
        hotelCommentService.save(hotelCommentEntity);
        resultWrapper.setMessage("留言成功");
        resultWrapper.setStatusCode(200);
        return resultWrapper;
    }
    
    /**
     * 获取留言列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return hotelCommentService.list(null);
    }

    /**
     * 新增留言
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(HotelCommentEntity hotelComment) {
        hotelCommentService.save(hotelComment);
        return "{}";
    }

    /**
     * 删除留言
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer hotelCommentId) {
        hotelCommentService.removeById(hotelCommentId);
        return "{}";
    }

    /**
     * 修改留言
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(HotelCommentEntity hotelComment) {
        hotelCommentService.updateById(hotelComment);
        return "{}";
    }

    /**
     * 留言详情
     */
    @RequestMapping(value = "/detail/{hotelCommentId}")
    @ResponseBody
    public Object detail(@PathVariable("hotelCommentId") Integer hotelCommentId) {
        return hotelCommentService.getById(hotelCommentId);
    }
}
