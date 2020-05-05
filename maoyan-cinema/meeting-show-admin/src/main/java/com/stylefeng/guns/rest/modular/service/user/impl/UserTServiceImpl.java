package com.stylefeng.guns.rest.modular.service.user.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.persistence.dao.UserMapper;
import com.stylefeng.guns.rest.common.persistence.dao.UserTMapper;
import com.stylefeng.guns.rest.common.persistence.model.User;
import com.stylefeng.guns.rest.common.persistence.model.UserT;
import com.stylefeng.guns.rest.modular.service.user.IUserTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/4/30
 */
@Service
public class UserTServiceImpl implements IUserTService {

    /**
     这里一定区分，userT 为消费者
                  user  为管理员
     */

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTMapper userTMapper;
    @Override
    public long login(String username, String password) {
        // 根据登陆账号获取数据库信息
        User user = new User();
        user.setUserName(username);

        User result = userMapper.selectOne(user);

        // 获取到的结果，然后与加密以后的密码做匹配
        if(result!=null && result.getId()>0){
            String md5Password = MD5Util.encrypt(password);
            if(result.getPassword().equals(md5Password)){
                return result.getId();
            }
        }
        return 0;
    }

    @Override
    public Page<UserT> selectAllCUser(String username, int size, int current) {
        EntityWrapper<UserT> wrapper = new EntityWrapper<>();
        if(username != null && username.length() != 0){
            wrapper.like("user_name",username);
        }
        Page<UserT> page = new Page<>();
        Integer total = userTMapper.selectCount(wrapper);
        page.setTotal(total);
        page.setSize(size);
        page.setCurrent(current);

        List<UserT> userTList = userTMapper.selectPage(page, wrapper);
        page.setRecords(userTList);

        return page;
    }

    @Override
    public int deleteUserById(Integer uuid) {

        return userTMapper.deleteById(uuid);
    }
}
