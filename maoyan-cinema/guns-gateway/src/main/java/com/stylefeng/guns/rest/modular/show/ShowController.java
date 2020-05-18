package com.stylefeng.guns.rest.modular.show;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.show.ShowServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author minghai
 * @description
 * @date 2020/4/28
 */
@RestController
@RequestMapping("/show")
public class ShowController {

    @Reference(
            interfaceClass = ShowServiceAPI.class,
            check = false
    )
    private ShowServiceAPI showServiceAPI;

    @RequestMapping("/getAllShows")
    public String getAllShows(){

        return null;
    }
}
