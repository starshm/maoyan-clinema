package com.stylefeng.guns.rest.modular.service.user;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.common.persistence.model.UserT;

public interface IUserTService {
    long login(String username, String password);

    // 返回所有的消费者用户
    Page<UserT> selectAllCUser(String username,int size,int current);

    int deleteUserById(Integer uuid);
}
