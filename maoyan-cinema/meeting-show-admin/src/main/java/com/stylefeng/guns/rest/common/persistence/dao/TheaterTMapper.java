package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.TheaterT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.modular.controller.theater.vo.ResponseTheaterVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 剧院信息表 Mapper 接口
 * </p>
 *
 * @author minghai
 * @since 2020-05-11
 */
public interface TheaterTMapper extends BaseMapper<TheaterT> {

    List<ResponseTheaterVO> selectAllTheater(@Param("theaterName") String theaterName, @Param("areaId") Integer areaId, @Param("nowPage") Integer nowPage, @Param("pageSize") Integer pageSize);
}
