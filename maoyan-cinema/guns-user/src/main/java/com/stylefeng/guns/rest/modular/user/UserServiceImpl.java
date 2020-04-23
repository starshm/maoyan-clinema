package com.stylefeng.guns.rest.modular.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.api.user.UserAPI;
import com.stylefeng.guns.api.user.vo.UserInfoModel;
import com.stylefeng.guns.api.user.vo.UserModel;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.common.persistence.dao.UserTMapper;
import com.stylefeng.guns.rest.common.persistence.model.UserT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Service(interfaceClass = UserAPI.class,loadbalance = "roundrobin" )
public class UserServiceImpl implements UserAPI{

    @Autowired
    private UserTMapper UserTMapper;

    @Override
    public boolean register(UserModel userModel) {
        // 将注册信息实体转换为数据实体[user_t]
        UserT UserT = new UserT();
        UserT.setUserName(userModel.getUsername());
        UserT.setEmail(userModel.getEmail());
        UserT.setAddress(userModel.getAddress());
        UserT.setUserPhone(userModel.getPhone());
        // 创建时间和修改时间 -> current_timestamp

        // 数据加密 【MD5混淆加密 + 盐值 -> Shiro加密】
        String md5Password = MD5Util.encrypt(userModel.getPassword());
        UserT.setUserPwd(md5Password); // 注意

        UserT.setUserSex(0);// 防止获取用户信息时报错，就唯独取性别时报空指针，很迷。。。。

        // 将数据实体存入数据库
        Integer insert = UserTMapper.insert(UserT);
        if(insert>0){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public int login(String username, String password) {
        // 根据登陆账号获取数据库信息
        UserT userT = new UserT();
        userT.setUserName(username);

        UserT result = UserTMapper.selectOne(userT);

        // 获取到的结果，然后与加密以后的密码做匹配
        if(result!=null && result.getUuid()>0){
            String md5Password = MD5Util.encrypt(password);
            if(result.getUserPwd().equals(md5Password)){
                return result.getUuid();
            }
        }
        return 0;
    }

    @Override
    public boolean checkUsername(String username) {
        EntityWrapper<UserT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("user_name",username);
        Integer result = UserTMapper.selectCount(entityWrapper);
        if(result!=null && result>0){
            return false;
        }else{
            return true;
        }
    }

    private UserInfoModel do2UserInfo(UserT userT){
        UserInfoModel userInfoModel = new UserInfoModel();

        userInfoModel.setUuid(userT.getUuid());
        userInfoModel.setHeadAddress(userT.getHeadUrl());
        userInfoModel.setPhone(userT.getUserPhone());
        userInfoModel.setUpdateTime(userT.getUpdateTime().getTime());
        userInfoModel.setEmail(userT.getEmail());
        userInfoModel.setUsername(userT.getUserName());
        userInfoModel.setNickname(userT.getNickName());
        userInfoModel.setLifeState(""+userT.getLifeState());
        userInfoModel.setBirthday(userT.getBirthday());
        userInfoModel.setAddress(userT.getAddress());
        userInfoModel.setBeginTime(userT.getBeginTime().getTime());
        userInfoModel.setBiography(userT.getBiography());
        userInfoModel.setSex(userT.getUserSex());

        return userInfoModel;
    }

    @Override
    public UserInfoModel getUserInfo(int uuid) {
        // 根据主键查询用户信息 [UserT]
        System.out.println(uuid+" ----------- ");
        UserT userT = UserTMapper.selectById(uuid);
        System.out.println(uuid+" ----------- "+userT);
        // 将UserT转换UserInfoModel
        UserInfoModel userInfoModel = do2UserInfo(userT);
        // 返回UserInfoModel
        return userInfoModel;
    }

    @Override
    public UserInfoModel updateUserInfo(UserInfoModel userInfoModel) {
        // 将传入的参数转换为DO 【UserT】
        UserT UserT = new UserT();
        UserT.setUuid(userInfoModel.getUuid());
        UserT.setNickName(userInfoModel.getNickname());
        UserT.setLifeState(Integer.parseInt(userInfoModel.getLifeState()));
        UserT.setBirthday(userInfoModel.getBirthday());
        UserT.setBiography(userInfoModel.getBiography());
        UserT.setBeginTime(null);
        UserT.setHeadUrl(userInfoModel.getHeadAddress());
        UserT.setEmail(userInfoModel.getEmail());
        UserT.setAddress(userInfoModel.getAddress());
        UserT.setUserPhone(userInfoModel.getPhone());
        UserT.setUserSex(userInfoModel.getSex());
        UserT.setUpdateTime(null);

        // DO存入数据库
        Integer integer = UserTMapper.updateById(UserT);
        if(integer>0){
            // 将数据从数据库中读取出来
            UserInfoModel userInfo = getUserInfo(UserT.getUuid());
            // 将结果返回给前端
            return userInfo;
        }else{
            return null;
        }
    }


}
