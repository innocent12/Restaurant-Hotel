package com.manage.hotel.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Blob;
import java.io.Serializable;
/**
 * <p>
 * 特色菜
 * </p>
 *
 * @author auto-show
 * @since 2021-03-30
 */
@TableName("hotel_food")
public class HotelFoodEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 特色菜名
     */
    private String foodName;

    /**
     * 图片
     */
    private byte[] picture;

    /**
     * 价格
     */
    private String price;

    /**
     * 描述
     */
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "HotelFoodEntity{" +
        ", id=" + id +
        ", foodName=" + foodName +
        ", picture=" + picture +
        ", price=" + price +
        ", description=" + description +
        "}";
    }
}
