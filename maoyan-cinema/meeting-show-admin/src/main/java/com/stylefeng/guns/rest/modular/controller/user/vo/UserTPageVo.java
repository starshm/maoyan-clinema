package com.stylefeng.guns.rest.modular.controller.user.vo;

import lombok.Data;

/**
 * @author minghai
 * @description
 * @date 2020/5/1
 */
@Data
public class UserTPageVo {
    private String username;
    private Integer pageSize;
    private Integer nowPage;
}
