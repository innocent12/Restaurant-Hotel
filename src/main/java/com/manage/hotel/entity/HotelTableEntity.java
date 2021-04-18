package com.manage.hotel.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
/**
 * <p>
 * 餐桌表
 * </p>
 *
 * @author auto-show
 * @since 2021-03-27
 */
@TableName("hotel_table")
public class HotelTableEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用餐人数
     */
    private Integer peopleNum;

    /**
     * 餐桌状态
     */
    private String status;

    /**
     * 餐桌名
     */
    private String tabName;

    /**
     * 餐桌价格
     */
    private String tabPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabPrice() {
        return tabPrice;
    }

    public void setTabPrice(String tabPrice) {
        this.tabPrice = tabPrice;
    }

    @Override
    public String toString() {
        return "HotelTableEntity{" +
        ", id=" + id +
        ", peopleNum=" + peopleNum +
        ", status=" + status +
        ", tabName=" + tabName +
        ", tabPrice=" + tabPrice +
        "}";
    }
}
