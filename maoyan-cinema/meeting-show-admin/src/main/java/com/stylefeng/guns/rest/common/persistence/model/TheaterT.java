package com.stylefeng.guns.rest.common.persistence.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 剧院信息表
 * </p>
 *
 * @author minghai
 * @since 2020-05-11
 */
@TableName("theater_t")
public class TheaterT extends Model<TheaterT> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    @TableId(value = "UUID", type = IdType.AUTO)
    private Integer uuid;
    /**
     * 剧院名称
     */
    @TableField("theater_name")
    private String theaterName;
    /**
     * 剧院电话
     */
    @TableField("theater_phone")
    private String theaterPhone;
    /**
     * 地域编号
     */
    @TableField("area_id")
    private Integer areaId;
    /**
     * 剧院图片地址
     */
    @TableField("img_address")
    private String imgAddress;
    /**
     * 剧院地址
     */
    @TableField("theater_address")
    private String theaterAddress;


    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getTheaterPhone() {
        return theaterPhone;
    }

    public void setTheaterPhone(String theaterPhone) {
        this.theaterPhone = theaterPhone;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public String getTheaterAddress() {
        return theaterAddress;
    }

    public void setTheaterAddress(String theaterAddress) {
        this.theaterAddress = theaterAddress;
    }

    @Override
    protected Serializable pkVal() {
        return this.uuid;
    }

    @Override
    public String toString() {
        return "TheaterT{" +
        "uuid=" + uuid +
        ", theaterName=" + theaterName +
        ", theaterPhone=" + theaterPhone +
        ", areaId=" + areaId +
        ", imgAddress=" + imgAddress +
        ", theaterAddress=" + theaterAddress +
        "}";
    }
}
