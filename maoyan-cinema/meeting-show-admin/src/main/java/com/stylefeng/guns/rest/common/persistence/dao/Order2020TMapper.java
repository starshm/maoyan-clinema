package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.Order2020T;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.modular.controller.order.vo.FilmOrderVo;
import com.stylefeng.guns.rest.modular.controller.order.vo.ReqeustOrderVO;

import java.util.List;

/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 * @author minghai
 * @since 2020-05-09
 */
public interface Order2020TMapper extends BaseMapper<Order2020T> {

    List<FilmOrderVo> selectAllOrder(ReqeustOrderVO reqeustOrderVO);
}
