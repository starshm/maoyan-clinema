package com.stylefeng.guns.rest.modular.controller.film.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author minghai
 * @description
 * @date 2020/5/5
 */
@Data
public class ResponseFilmsVO {
    private String filmName;
    private String filmType;
    private String imgAddress;
    private String filmScore;
    private Integer filmPresalenum; // 预售数量
    private Integer filmBoxOffice;// 票房
    private String filmSource;
    private String filmCat;
    private String filmArea;
    private String filmDate;
    private Date filmTime; // 上映时间
    private Integer filmStatus; // 影片状态
    private String filmEnName;
    private Integer filmScoreNum;
    private Integer filmLength;
    private String biography;
    private String directorName;
    private String filmImgs[];




}
