package com.stylefeng.guns.rest.modular.service.showHall.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.common.persistence.dao.ShowFieldTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.ShowTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.TheaterTMapper;
import com.stylefeng.guns.rest.common.persistence.model.ShowFieldT;
import com.stylefeng.guns.rest.common.persistence.model.ShowT;
import com.stylefeng.guns.rest.modular.controller.showHall.vo.ShowHallVo;
import com.stylefeng.guns.rest.modular.service.showHall.IShowHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.Element;
import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/16
 */
@Service
public class ShowHallServiceImpl implements IShowHallService {

    @Autowired
    ShowFieldTMapper showFieldTMapper;

    @Autowired
    ShowTMapper showTMapper;

    @Autowired
    TheaterTMapper theaterTMapper;


    @Override
    public Integer selectTotal(String theaterName) {
        Integer total = showFieldTMapper.selectTotal(theaterName);
        return total;
    }

    @Override
    public List<ShowHallVo> findAllShowHall(String theaterName, Integer nowPage, Integer pageSize) {
        nowPage = (nowPage - 1) * pageSize;
        List<ShowHallVo> list = showFieldTMapper.findAllShowHall(theaterName,nowPage,pageSize);
        return list;
    }

    @Override
    public Integer save(ShowFieldT showFieldT) {
        Integer insert = showFieldTMapper.insert(showFieldT);
        return insert;
    }

    @Override
    public Integer update(ShowFieldT showFieldT) {
        return showFieldTMapper.updateAllColumnById(showFieldT);
    }

    @Override
    public Integer deleteById(Integer uuid) {
        return showFieldTMapper.deleteById(uuid);
    }

}
