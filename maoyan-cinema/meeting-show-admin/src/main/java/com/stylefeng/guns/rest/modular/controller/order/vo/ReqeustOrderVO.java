package com.stylefeng.guns.rest.modular.controller.order.vo;

import lombok.Data;

/**
 * @author minghai
 * @description
 * @date 2020/5/9
 *
 *         orderId: "",
 *         nowPage: 1,
 *         pageSize: 10
 */
@Data
public class ReqeustOrderVO {
    private String orderId;
    private Integer nowPage;
    private Integer pageSize;
}
