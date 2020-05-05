package com.stylefeng.guns.rest.modular.controller.ciname.vo;

import lombok.Data;

/**
 * @author minghai
 * @description
 * @date 2020/5/4
 */
@Data
public class CinemaRequestVo {
    private String cinemaName;
    private Integer brand;
    private Integer area;
    private Integer nowPage;
    private Integer pageSize;
}
