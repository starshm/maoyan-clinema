package com.stylefeng.guns.rest.modular.show;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.api.show.ShowServiceAPI;
import com.stylefeng.guns.api.show.vo.ShowVO;
import com.stylefeng.guns.rest.common.persistence.dao.ShowTMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/4/28
 */
@Component
@Service(interfaceClass = ShowServiceAPI.class)
public class DefaultShowServiceImpl implements  ShowServiceAPI{

    @Autowired
    ShowTMapper showTMapper;

    @Override
    public String getList() {
        return "我是list";
    }

    @Override
    public List<ShowVO> getAllShowIndex(boolean isLimit, int nowPage, int pageSize) {
        if(isLimit){

        }else{

        }
        return null;
    }

}
