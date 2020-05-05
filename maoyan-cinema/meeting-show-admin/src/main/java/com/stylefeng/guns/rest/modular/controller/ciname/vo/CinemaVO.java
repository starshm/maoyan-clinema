package com.stylefeng.guns.rest.modular.controller.ciname.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @author minghai
 * @description
 * @date 2020/5/4
 */
@Data
public class CinemaVO {
    private Integer uuid;
    private String cinemaName;
    private String cinemaPhone;
    private String brandName;
    private String  areaName;
    private String[] hallNames;
    private String imgAddress;
    private String cinemaAddress;
    private Integer minimumPrice;
}
