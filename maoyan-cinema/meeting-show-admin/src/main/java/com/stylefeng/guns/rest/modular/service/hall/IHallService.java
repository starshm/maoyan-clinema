package com.stylefeng.guns.rest.modular.service.hall;

import com.stylefeng.guns.rest.common.persistence.model.HallDictT;
import com.stylefeng.guns.rest.modular.controller.hall.vo.RequestAddHallVo;
import com.stylefeng.guns.rest.modular.controller.hall.vo.ResponseCinemaFilmHall;
import com.stylefeng.guns.rest.modular.controller.hall.vo.ResponseHallsVo;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/7
 */
public interface IHallService {
    public List<ResponseHallsVo> findAllHalls(String hallNams, Integer nowPage, Integer pageSize);
    public Integer selectCount(String hallNams);


    ResponseCinemaFilmHall findAllCinemaFilmHall();

    boolean save(RequestAddHallVo requestAddHallVo);

    boolean deleteHall(Integer fieldId);

    boolean deleteHallFilmInfo(Integer filmId);
}
