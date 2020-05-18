package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.HallFilmInfoT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影厅电影信息表 Mapper 接口
 * </p>
 *
 * @author minghai
 * @since 2020-05-07
 */
public interface HallFilmInfoTMapper extends BaseMapper<HallFilmInfoT> {

    HallFilmInfoT selectHallInfoByFilmId(@Param("filmId") Integer filmId);

    Integer deleteByFilmId(Integer filmId);
}
