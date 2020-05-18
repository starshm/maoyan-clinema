package com.stylefeng.guns.rest.modular.controller.showHall.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * @author minghai
 * @description
 * @date 2020/5/16
 */
@Data
public class ShowHallVo {
    private Integer uuid;
    private String theaterName;
    private String showName;
    private String beginTime;
    private String endTime;
    private Integer minPrice;
    private Integer maxPrice;
}
