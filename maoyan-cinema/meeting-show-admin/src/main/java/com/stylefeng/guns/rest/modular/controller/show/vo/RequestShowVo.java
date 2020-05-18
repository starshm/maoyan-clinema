package com.stylefeng.guns.rest.modular.controller.show.vo;

import lombok.Data;

/**
 * @author minghai
 * @description
 * @date 2020/5/10
 *         showName: "",
 *         nowPage: 1,
 *         pageSize: 5,
 *         showType: '',
 */
@Data
public class RequestShowVo {
    private String showName;
    private Integer nowPage;
    private Integer pageSize;
    private Integer showType;
}
