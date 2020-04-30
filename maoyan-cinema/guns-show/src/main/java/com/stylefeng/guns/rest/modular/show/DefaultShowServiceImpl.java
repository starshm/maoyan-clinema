package com.stylefeng.guns.rest.modular.show;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.api.show.ShowServiceAPI;
import org.springframework.stereotype.Controller;

/**
 * @author minghai
 * @description
 * @date 2020/4/28
 */
@Controller
@Service(interfaceClass = ShowServiceAPI.class)
public class DefaultShowServiceImpl implements  ShowServiceAPI{


    @Override
    public String getList() {
        return "我是list";
    }
}
