package com.stylefeng.guns.rest.modular.service.order;

import com.stylefeng.guns.rest.modular.controller.order.vo.FilmOrderVo;
import com.stylefeng.guns.rest.modular.controller.order.vo.ReqeustOrderVO;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/9
 */
public interface IFilmOrder {
    List<FilmOrderVo> findAllFilmOrder(ReqeustOrderVO reqeustOrderVO);

    Integer selectCount();

    Integer deleteFilmOrderById(String uuid);
}
