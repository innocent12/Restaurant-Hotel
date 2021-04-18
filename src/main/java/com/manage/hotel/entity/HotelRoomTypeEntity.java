package com.manage.hotel.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Blob;
import java.io.Serializable;
/**
 * <p>
 * 房型表
 * </p>
 *
 * @author auto-show
 * @since 2021-03-30
 */
@TableName("hotel_room_type")
public class HotelRoomTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 房价
     */
    private String price;

    /**
     * 类型(1.大床;2.单人;)
     */
    private String type;

    /**
     * 图片
     */
    private byte[] picture;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "HotelRoomTypeEntity{" +
        ", id=" + id +
        ", price=" + price +
        ", type=" + type +
        ", picture=" + picture +
        ", description=" + description +
        "}";
    }
}
