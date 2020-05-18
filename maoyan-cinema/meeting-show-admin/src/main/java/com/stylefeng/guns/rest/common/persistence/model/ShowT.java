package com.stylefeng.guns.rest.common.persistence.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 演出主表
 * </p>
 *
 * @author minghai
 * @since 2020-05-09
 */
@TableName("show_t")
public class ShowT extends Model<ShowT> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    @TableId(value = "UUID", type = IdType.AUTO)
    private Integer uuid;
    /**
     * 影片名称
     */
    @TableField("show_name")
    private String showName;
    /**
     * 演出类型： 见 show_type表
     */
    @TableField("show_type")
    private Integer showType;
    /**
     * 演出主图地址
     */
    @TableField("img_address")
    private String imgAddress;
    /**
     * 演出mini图地址
     */
    @TableField("miniImg_address")
    private String miniimgAddress;
    /**
     * 演出时间
     */
    @TableField("show_time")
    private Date showTime;
    /**
     * 演出票务状态,1-在售中，2-即将开售，3-预售
     */
    @TableField("show_status")
    private Integer showStatus;
    /**
     * 演出详情
     */
    private String biography;


    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Integer getShowType() {
        return showType;
    }

    public void setShowType(Integer showType) {
        this.showType = showType;
    }

    public String getImgAddress() {
        return imgAddress;
    }

    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }

    public String getMiniimgAddress() {
        return miniimgAddress;
    }

    public void setMiniimgAddress(String miniimgAddress) {
        this.miniimgAddress = miniimgAddress;
    }

    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

    public Integer getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Override
    protected Serializable pkVal() {
        return this.uuid;
    }

    @Override
    public String toString() {
        return "ShowT{" +
        "uuid=" + uuid +
        ", showName=" + showName +
        ", showType=" + showType +
        ", imgAddress=" + imgAddress +
        ", miniimgAddress=" + miniimgAddress +
        ", showTime=" + showTime +
        ", showStatus=" + showStatus +
        ", biography=" + biography +
        "}";
    }
}
