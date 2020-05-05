package com.stylefeng.guns.rest.modular.controller.user;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.common.persistence.dao.UserMapper;
import com.stylefeng.guns.rest.common.persistence.model.UserT;
import com.stylefeng.guns.rest.modular.controller.user.vo.UserTPageVo;
import com.stylefeng.guns.rest.modular.service.user.IUserTService;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/4/30
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;


    // 管理员相关

    /**************************************************************************************************/
    @RequestMapping("/info")
    public ResponseVO getInfo() {
        System.out.println("获取用户信息成功");
        return ResponseVO.success("获取用户信息成功", "222");
    }


    // 消费者用户相关
    /**************************************************************************************************/
    @Autowired
    private IUserTService userTService;

    @PostMapping("/findAllCUser")
    public ResponseVO findAllCUser(@RequestBody  UserTPageVo pageVo) {

        String username = pageVo.getUsername();
        Integer  size = pageVo.getPageSize();
        Integer current = pageVo.getNowPage();
        Page<UserT> page =  userTService.selectAllCUser(username, size, current);

        int nowPage = page.getCurrent();
        int total = (int) page.getTotal();
        List<UserT> list = page.getRecords();

        return ResponseVO.success(nowPage,total,list);
    }


    @GetMapping("/deleteCUser")
    public ResponseVO deleteCUser(@RequestParam("uuid") Integer uuid){

        int count = userTService.deleteUserById(uuid);
        if(count == 0){
            return ResponseVO.serviceFail("删除失败");
        }

        return ResponseVO.success("删除成功");
    }

}
