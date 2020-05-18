package com.stylefeng.guns.rest.modular.controller.theater.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @author minghai
 * @description
 * @date 2020/5/12
 */

@Data
public class ResponseTheaterVO {

    private Integer uuid;
    private String theaterName;
    private String theaterPhone;
    private String area;
    private String imgAddress;
    private String theaterAddress;
}
