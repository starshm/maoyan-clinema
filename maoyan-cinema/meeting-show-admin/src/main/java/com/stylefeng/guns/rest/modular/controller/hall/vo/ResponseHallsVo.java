package com.stylefeng.guns.rest.modular.controller.hall.vo;

import lombok.Data;

/**
 * @author minghai
 * @description
 * @date 2020/5/7
 */
@Data
public class ResponseHallsVo {
    /**
     * 主键编号
     */
    private Integer uuid;
    /**
     * 影院编号
     */
    private String cinemaName;
    /**
     * 电影编号
     */
    private String  filmName;
    /**
     * 开始时间
     */
    private String beginTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 放映厅类型
     */
    private String hallType;
    /**
     * 放映厅名称
     */
    private String hallName;
    /**
     * 票价
     */
    private Integer price;
}
