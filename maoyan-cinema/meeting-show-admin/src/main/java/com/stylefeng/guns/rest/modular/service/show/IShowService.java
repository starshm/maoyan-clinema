package com.stylefeng.guns.rest.modular.service.show;

import com.stylefeng.guns.rest.common.persistence.model.ShowT;
import com.stylefeng.guns.rest.common.persistence.model.ShowTypeDictT;
import com.stylefeng.guns.rest.modular.controller.show.vo.RequestShowVo;

import java.util.List;

public interface IShowService {
    List<ShowTypeDictT> selectAllShowTypes();

    Integer selectShowsCount(String showName, Integer showType);

    List<ShowT> selectAllSHows(RequestShowVo requestShowVo,Integer count);

    boolean save(ShowT showT);

    boolean deleteShow(Integer uuid);

    List<ShowT> selectAllShows();
}
