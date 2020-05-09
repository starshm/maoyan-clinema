package com.stylefeng.guns.rest.modular.controller.film.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author minghai
 * @description
 * @date 2020/5/7
 *  addForm: {
 *         filmStatus: "",
 *         filmName: "",
 *         filmEnName: "",
 *         mainImgAddress: "",
 *         filmScore: "",
 *         filmScorers: "",
 *         preSaleNum: "",
 *         boxOffice: "",
 *         filmSourceId: "",
 *         filmCatIds: [],
 *         areaId: "",
 *         dateId: "",
 *         filmTime: "",
 *         directorId: "",
 *         actIds: "",
 *         filmLength: "",
 *         biography: "",
 *         filmImgs: "",
 *         filmTypeId: "",
 *         roleNames: ""
 *       },
 */
@Data
public class RequestVOAddFilm {
    private String filmName;
    private String filmEnName;
    private Integer filmStatus;
    private String filmScore;
    private Integer filmScorers; // 以万为单位
    private Integer preSaleNum;
    private Integer boxOffice;
    private String filmCatIds;
    private Integer filmSourceId;
    private Integer areaId; // 地区 默认，即影片的地区
    private Integer dateId;
    private Date filmTime;
    private Integer directorId;
    private String actIds;
    private String roleNames;
    private Integer filmLength;
    private String biography;
    private Integer filmTypeId;

    private String mainImgAddress;
    private String filmImgs;

}
