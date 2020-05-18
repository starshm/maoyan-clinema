package com.stylefeng.guns.rest.modular.service.theater;

import com.stylefeng.guns.rest.common.persistence.model.AreaDictT;
import com.stylefeng.guns.rest.common.persistence.model.TheaterT;
import com.stylefeng.guns.rest.modular.controller.theater.vo.RequestTeacherVO;
import com.stylefeng.guns.rest.modular.controller.theater.vo.ResponseTheaterVO;

import java.util.List;

public interface ITheaterService {
    List<AreaDictT> selectAllArea();

    Integer selectCount(String theaterName, Integer areaId);

    List<ResponseTheaterVO> selectAllTeacher(RequestTeacherVO requestTeacherVO);

    boolean deleteTheater(Integer uuid);

    void save(TheaterT theaterT);

    List<TheaterT> selectAllTheaters();
}
