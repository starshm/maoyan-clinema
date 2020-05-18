package com.stylefeng.guns.rest.modular.service.hall.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import com.stylefeng.guns.rest.modular.controller.hall.vo.RequestAddHallVo;
import com.stylefeng.guns.rest.modular.controller.hall.vo.ResponseCinemaFilmHall;
import com.stylefeng.guns.rest.modular.controller.hall.vo.ResponseHallsVo;
import com.stylefeng.guns.rest.modular.service.hall.IHallService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/7
 */
@Service
public class HallServiceImpl implements IHallService {

    @Autowired
    FieldTMapper fieldTMapper;

    @Autowired
    CinemaTMapper cinemaTMapper;

    @Autowired
    FilmTMapper filmTMapper;

    @Autowired
    HallDictTMapper hallDictTMapper;

    @Autowired
    FilmInfoTMapper filmInfoTMapper;

    @Autowired
    CatDictTMapper catDictTMapper;


    @Autowired
    HallFilmInfoTMapper hallFilmInfoTMapper;

    @Override
    public List<ResponseHallsVo> findAllHalls(String hallNams, Integer nowPage, Integer pageSize) {
        if(StringUtils.isEmpty(hallNams)){
            hallNams = null;
        }
        nowPage = (nowPage -1) * pageSize;
        List<ResponseHallsVo> responseHallsVo = fieldTMapper.selectHallPage(hallNams,nowPage,pageSize);
        return responseHallsVo;


    }


    @Override
    public Integer selectCount(String hallNams) {
        EntityWrapper<FieldT> entityWrapper = new EntityWrapper<>();
        if(!StringUtils.isEmpty(hallNams)){
            entityWrapper.like("hall_name",hallNams);
        }

        return fieldTMapper.selectCount(entityWrapper);
    }

    @Override
    public ResponseCinemaFilmHall findAllCinemaFilmHall() {
        List<CinemaT> cinemaTS = cinemaTMapper.selectList(new EntityWrapper<>());

        List<FilmT> filmTS = filmTMapper.selectList(new EntityWrapper<FilmT>().eq("film_status","1"));
        List<HallDictT> hallDictTS = hallDictTMapper.selectList(new EntityWrapper<>());
        ResponseCinemaFilmHall responseCinemaFilmHall = new ResponseCinemaFilmHall();
        responseCinemaFilmHall.setCinemaTS(cinemaTS);
        responseCinemaFilmHall.setFilmTS(filmTS);
        responseCinemaFilmHall.setHallDictTS(hallDictTS);

        return responseCinemaFilmHall;
    }

    @Override
    @Transactional
    public boolean save(RequestAddHallVo requestAddHallVo) {
        boolean flag = true;
        FieldT fieldT = new FieldT();
        fieldT.setCinemaId(requestAddHallVo.getCinemaId());
        fieldT.setFilmId(requestAddHallVo.getFilmId());
        fieldT.setHallId(requestAddHallVo.getHallTypeId());
        fieldT.setBeginTime(requestAddHallVo.getBeginTime());
        fieldT.setEndTime(requestAddHallVo.getEndTime());
        fieldT.setPrice(requestAddHallVo.getFilmPrice());
        fieldT.setHallName(requestAddHallVo.getHallName());
        Integer count = fieldTMapper.insert(fieldT);
        Integer fieldId = fieldT.getUuid();
        if(fieldId != null){
            FilmT filmT = filmTMapper.selectById(requestAddHallVo.getFilmId());
            FilmInfoT filmInfoT = filmInfoTMapper.selectById(requestAddHallVo.getFilmId());

            HallFilmInfoT hallFilmInfoT = new HallFilmInfoT();
            hallFilmInfoT.setFilmId(requestAddHallVo.getFilmId());
            hallFilmInfoT.setFilmName(filmT.getFilmName());
            hallFilmInfoT.setFilmLength(filmInfoT.getFilmLength()+"");
            hallFilmInfoT.setFilmLanguage("国语");
            hallFilmInfoT.setImgAddress(filmT.getImgAddress());
            // 分类转换
            String catsId = filmT.getFilmCats();
            String catNames = getCatsNamesFromCatIds(catsId);
            hallFilmInfoT.setFilmCats(catNames);
            // 演员转换
            String[] actors = filmTMapper.selectFilmActorsById(filmT.getUuid());
            String actorName = actors[0];
            for (int i = 1; i < actors.length; i++) {
                actorName+="," + actors[i];
            }
            hallFilmInfoT.setActors(actorName);

            Integer insert = hallFilmInfoTMapper.insert(hallFilmInfoT);
            System.out.println(insert);
            if(hallFilmInfoT.getUuid() != null){
                return true;
            }

        }

        return false;
    }

    private String getCatsNamesFromCatIds(String catsId) {
        catsId = catsId.substring(1);
        String[] split = catsId.split("#");
        CatDictT catDictT = catDictTMapper.selectById(Integer.parseInt(split[0]));
        String catNames = catDictT.getShowName();
        for (int i = 1; i < split.length; i++){
             catDictT = catDictTMapper.selectById(Integer.parseInt(split[i]));
            catNames += ","+catDictT.getShowName();
        }
        return catNames;
    }

}
