package com.stylefeng.guns.rest.modular.controller.film.vo;

import lombok.Data;

/**
 * @author minghai
 * @description
 * @date 2020/5/5
 * filmName: "",
 *         filmCat: "",
 *         filmArea: "",
 *         filmDate: "",
 *         nowPage: 1,
 *         pageSize: 5,
 */
@Data
public class RequestVo {
    private String filmName;
    private Integer filmCat;
    private Integer filmSource;
    private Integer filmDate;
    private Integer nowPage;
    private Integer pageSize;
    private Integer filmStatu;
}
