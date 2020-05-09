package com.stylefeng.guns.rest.modular.controller.hall.vo;

import lombok.Data;

/**
 * @author minghai
 * @description
 * @date 2020/5/8
 *  addForm: {
 *         cinemaId: '',
 *         filmId: '',
 *         hallTypeId: '',
 *         beginTime: '',
 *         endTime: '',
 *         filmPrice: '',
 *         hallName: ''
 *       },
 */
@Data
public class RequestAddHallVo {
    private String hallName;
    private String beginTime;
    private String endTime;
    private Integer cinemaId;
    private Integer filmId;
    private Integer hallTypeId;
    private Integer filmPrice;


}
