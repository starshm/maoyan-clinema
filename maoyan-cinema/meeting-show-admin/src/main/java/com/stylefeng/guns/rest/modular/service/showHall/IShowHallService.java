package com.stylefeng.guns.rest.modular.service.showHall;

import com.stylefeng.guns.rest.common.persistence.model.ShowFieldT;
import com.stylefeng.guns.rest.modular.controller.showHall.vo.ShowHallVo;

import java.util.List;

public interface IShowHallService {

    Integer selectTotal(String theaterName);
    List<ShowHallVo> findAllShowHall(String theaterName, Integer nowPage, Integer pageSize);


    Integer save(ShowFieldT showFieldT);

    Integer update(ShowFieldT showFieldT);

    Integer deleteById(Integer uuid);
}
