package com.stylefeng.guns.rest.modular.service.order.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.common.persistence.dao.Order2020TMapper;
import com.stylefeng.guns.rest.modular.controller.order.vo.FilmOrderVo;
import com.stylefeng.guns.rest.modular.controller.order.vo.ReqeustOrderVO;
import com.stylefeng.guns.rest.modular.service.order.IFilmOrder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/9
 */
@Service
public class FilmOrderImpl implements IFilmOrder {

    @Autowired
    Order2020TMapper order2020TMapper;

    @Override
    public List<FilmOrderVo> findAllFilmOrder(ReqeustOrderVO reqeustOrderVO) {
        if(StringUtils.isEmpty(reqeustOrderVO.getOrderId())){
            reqeustOrderVO.setOrderId(null);
        }

        reqeustOrderVO.setNowPage((reqeustOrderVO.getNowPage()-1)*reqeustOrderVO.getNowPage());

        List<FilmOrderVo> orders = order2020TMapper.selectAllOrder(reqeustOrderVO);
        return orders;
    }

    @Override
    public Integer selectCount() {

        return order2020TMapper.selectCount(new EntityWrapper<>());
    }

    @Override
    public Integer deleteFilmOrderById(String uuid) {
        return order2020TMapper.deleteById(uuid);
    }
}
