package com.stylefeng.guns.api.show.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author minghai
 * @description
 * @date 2020/5/18
 */
@Data
public class ShowVO {
    private Integer uuid;
    private String showName;
    private String showType;
    private String imgAddress;
    private String miniimgAddress;
    private Date showTime;
    private Integer showStatus;
    private String biography;
}
