package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.rest.common.persistence.model.FilmActorT;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 影片与演员映射表 Mapper 接口
 * </p>
 *
 * @author minghai
 * @since 2020-05-07
 */
public interface FilmActorTMapper extends BaseMapper<FilmActorT> {

    void insertFilmActors(List<FilmActorT> list);
}
