package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.ShowFieldT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.modular.controller.showHall.vo.ShowHallVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 演出场次表 Mapper 接口
 * </p>
 *
 * @author minghai
 * @since 2020-05-16
 */
public interface ShowFieldTMapper extends BaseMapper<ShowFieldT> {

    Integer selectTotal(@Param("theaterName") String theaterName);

    List<ShowHallVo> findAllShowHall(@Param("theaterName") String theaterName, @Param("nowPage") Integer nowPage, @Param("pageSize") Integer pageSize);
}
