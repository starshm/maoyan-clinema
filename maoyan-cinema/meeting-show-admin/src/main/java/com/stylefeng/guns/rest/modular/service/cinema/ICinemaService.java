package com.stylefeng.guns.rest.modular.service.cinema;


import com.stylefeng.guns.rest.common.persistence.model.AreaDictT;
import com.stylefeng.guns.rest.common.persistence.model.BrandDictT;
import com.stylefeng.guns.rest.common.persistence.model.CinemaT;
import com.stylefeng.guns.rest.common.persistence.model.HallDictT;

import java.util.List;

public interface ICinemaService {

    public List<BrandDictT>  findAllBrands();

    public List<AreaDictT> findAllAreas();

    public List<CinemaT> findAllCinemas(String cinemaName, Integer brand, Integer area, Integer nowPage, Integer pageSize);

    public Integer selectCount();

    public List<HallDictT> findAllHalls();

    public int save(CinemaT cinemaT);

    public Integer deleteCinemaById(Integer uuid);

    public int saveOrUpdate(CinemaT cinemaT);

    Integer selectCount(String cinemaName, Integer brand, Integer area);
}
