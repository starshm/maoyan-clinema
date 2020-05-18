package com.stylefeng.guns.rest.modular.controller.order.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author minghai
 * @description
 * @date 2020/5/9
 *           uuid: '2dd17d8e8c6a4bb48c6396eb7e030bac',
 *           userName: "王小虎",
 *           orderTime: "2016-05-03 11:53:42",
 *           filmName: "我不是药神",
 *           filmPrice: 63.2,
 *           orderPrice: 126.4,
 *           orderStatus: 0,
 *           cinemaName: '大地影院',
 */
@Data
public class FilmOrderVo {
    private String uuid;
    private String userName;
    private Date orderTime;
    private String filmName;
    private String cinemaName;
    private Double filmPrice;
    private Double orderPrice;
    private Integer orderStatus;

}
