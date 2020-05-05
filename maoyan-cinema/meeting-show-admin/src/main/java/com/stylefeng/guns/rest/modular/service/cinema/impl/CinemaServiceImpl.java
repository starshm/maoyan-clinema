package com.stylefeng.guns.rest.modular.service.cinema.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.common.persistence.dao.AreaDictTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.BrandDictTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.CinemaTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.HallDictTMapper;
import com.stylefeng.guns.rest.common.persistence.model.AreaDictT;
import com.stylefeng.guns.rest.common.persistence.model.BrandDictT;
import com.stylefeng.guns.rest.common.persistence.model.CinemaT;
import com.stylefeng.guns.rest.common.persistence.model.HallDictT;
import com.stylefeng.guns.rest.modular.service.cinema.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/2
 */
@Service
public class CinemaServiceImpl implements ICinemaService {

    @Autowired
    private BrandDictTMapper brandDictTMapper;

    @Autowired
    private AreaDictTMapper areaDictTMapper;

    @Autowired
    private CinemaTMapper cinemaTMapper;


    @Autowired
    private HallDictTMapper hallDictTMapper;

    @Override
    public List<BrandDictT> findAllBrands() {

        return brandDictTMapper.selectList(new EntityWrapper<BrandDictT>());
    }

    @Override
    public List<AreaDictT> findAllAreas() {

        return areaDictTMapper.selectList(new EntityWrapper<AreaDictT>());
    }

    @Override
    public List<CinemaT> findAllCinemas(String cinemaName, Integer brand, Integer area, Integer nowPage, Integer pageSize) {
        EntityWrapper<CinemaT> entityWrapper = new EntityWrapper<>();
        if(!StringUtils.isEmpty(cinemaName)){
            entityWrapper.like("cinema_name",cinemaName);
        }
        if(brand != null && brand != 0 && brand != 99){
            entityWrapper.eq("brand_id",brand);
        }
        if(area!= null && area != 0 && area != 99){
            entityWrapper.eq("area_id",area);
        }
        Page<CinemaT> page = new Page<>();
        Integer count = cinemaTMapper.selectCount(new EntityWrapper<>());
        page.setTotal(count);
        page.setCurrent(nowPage);
        page.setSize(pageSize);
        List<CinemaT> cinemaTS = cinemaTMapper.selectPage(page, entityWrapper);
        return cinemaTS;
    }

    @Override
    public Integer selectCount() {
        return cinemaTMapper.selectCount(new EntityWrapper<>());
    }

    @Override
    public List<HallDictT> findAllHalls() {
        return hallDictTMapper.selectList(new EntityWrapper<>());
    }

    @Override
    public int save(CinemaT cinemaT) {
        Integer count = cinemaTMapper.insert(cinemaT);
        return count;
    }

    @Override
    public Integer deleteCinemaById(Integer uuid) {

        return cinemaTMapper.deleteById(uuid);
    }

    @Override
    public int saveOrUpdate(CinemaT cinemaT) {
        int count = 0;
        if(cinemaT.getUuid() != null){
             count = cinemaTMapper.updateById(cinemaT);
             return count;
        }else{
            count = cinemaTMapper.insert(cinemaT);
            return count;
        }
    }

}
