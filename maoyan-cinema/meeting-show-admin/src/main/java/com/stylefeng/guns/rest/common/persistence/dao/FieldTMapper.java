package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.FieldT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.modular.controller.hall.vo.ResponseHallsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author minghai
 * @since 2020-05-07
 */
public interface FieldTMapper extends BaseMapper<FieldT> {

    List<ResponseHallsVo> selectHallPage(@Param("hallName") String hallNams, @Param("nowPage") Integer nowPage, @Param("pageSize") Integer pageSize);
}
