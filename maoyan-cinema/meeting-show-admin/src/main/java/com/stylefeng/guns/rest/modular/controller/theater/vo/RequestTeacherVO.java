package com.stylefeng.guns.rest.modular.controller.theater.vo;

import lombok.Data;

/**
 * @author minghai
 * @description
 * @date 2020/5/11
 *  requestVo: {
 *         theaterName: "",
 *         areaId: "",
 *         nowPage: 1,
 *         pageSize: 5
 *       }
 */
@Data
public class RequestTeacherVO {
    private String theaterName;
    private Integer areaId;
    private Integer nowPage;
    private Integer pageSize;

}
