package com.manage.hotel.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
/**
 * <p>
 * 订单表
 * </p>
 *
 * @author auto-show
 * @since 2021-03-27
 */
@TableName("hotel_order")
public class HotelOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名字
     */
    private String name;

    /**
     * 状态(1.空闲;2.已预约;3(客房).已入住;4.已完成;0.已取消)
     */
    private String status;

    /**
     * 身份证号
     */
    @TableField("cardId")
    private String cardid;

    /**
     * 餐桌号
     */
    private Integer tabId;

    /**
     * 客房号
     */
    private Integer roomId;

    /**
     * 订单类型(1.访客订单;2.餐桌订单)
     */
    private String orderType;

    /**
     * 到店时间
     */
    private LocalDate subscribeTime;


    /**
     * 离店时间
     */
    private LocalDate departTime;

    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;

    /**
     * 订单名
     */
    private String orderName;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public Integer getTabId() {
        return tabId;
    }

    public void setTabId(Integer tabId) {
        this.tabId = tabId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }


    public LocalDate getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(LocalDate subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public LocalDate getDepartTime() {
        return departTime;
    }

    public void setDepartTime(LocalDate departTime) {
        this.departTime = departTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "HotelOrderEntity{" +
        ", id=" + id +
        ", name=" + name +
        ", status=" + status +
        ", cardid=" + cardid +
        ", tabId=" + tabId +
        ", roomId=" + roomId +
        ", orderType=" + orderType +
        ", subscribeTime=" + subscribeTime +
        ", departTime=" + departTime +
        ", createTime=" + createTime +
        "}";
    }

    /**
     * 计算订单日期
     * @return
     */
    public Integer calcDays(){
        return (int) (departTime.toEpochDay() - subscribeTime.toEpochDay());
    }
}
