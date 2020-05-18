package com.stylefeng.guns.rest.modular.service.film.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import com.stylefeng.guns.rest.modular.controller.film.vo.FilmVO;
import com.stylefeng.guns.rest.modular.controller.film.vo.ResponseFilmsVO;
import com.stylefeng.guns.rest.modular.service.film.IFilmService;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/5
 */
@Service
public class FilmServiceImpl implements IFilmService {

    @Autowired
    private YearDictTMapper yearDictTMapper;

    @Autowired
    private SourceDictTMapper sourceDictTMapper;

    @Autowired
    private CatDictTMapper catDictTMapper;


    @Autowired
    private FilmTMapper filmTMapper;

    @Autowired
    private ActorTMapper actorTMapper;

    @Autowired
    private FilmInfoTMapper filmInfoTMapper;


    @Autowired
    private FilmActorTMapper filmActorTMapper;
    @Override
    public List<YearDictT> findAllYear() {
        return yearDictTMapper.selectList(new EntityWrapper<YearDictT>()
                .orderDesc(Arrays.asList("uuid"))
        );
    }

    @Override
    public List<SourceDictT> findAllSource() {
        return sourceDictTMapper.selectList(new EntityWrapper<SourceDictT>()
                .orderDesc(Arrays.asList("uuid"))
        );
    }

    @Override
    public List<CatDictT> findAllCat() {
        return catDictTMapper.selectList(new EntityWrapper<CatDictT>()
                .orderDesc(Arrays.asList("uuid"))
        );
    }

    @Override
    public List<FilmVO> findAllFilms(String filmName, Integer filmCat, Integer filmSource, Integer filmDate, Integer nowPage, Integer pageSize, Integer filmStatu) {
        EntityWrapper<FilmT> entityWrapper = new EntityWrapper<>();
        if(!StringUtils.isEmpty(filmName)){
            entityWrapper.like("film_name",filmName);
        }
        if(filmCat != null  && filmCat != 99){
            entityWrapper.like("film_cats","#"+filmCat+"#");
        }
        if(filmSource != null && filmSource != 99){
            entityWrapper.eq("film_source",filmSource);
        }
        if(filmDate != null && filmSource != 99){
            entityWrapper.eq("film_date",filmDate);
        }

        if(filmStatu != 0){
            entityWrapper.eq("film_status",filmStatu);
        }

        Integer count = filmTMapper.selectCount(entityWrapper);

        Page<FilmT> page = new Page<>();
        page.setTotal(count);
        page.setSize(pageSize);
        page.setCurrent(nowPage);

        List<FilmT> filmTS = filmTMapper.selectPage(page, entityWrapper);


        List<FilmVO> filmVOS = filmTListToFilmVoList(filmTS);

        return filmVOS;
    }


    @Override
    public int selectCount(String filmName, Integer filmCat, Integer filmSource, Integer filmDate, Integer filmStatu) {
        EntityWrapper<FilmT> entityWrapper = new EntityWrapper<>();
        if(!StringUtils.isEmpty(filmName)){
            entityWrapper.like("film_name",filmName);
        }
        if(filmCat != null  && filmCat != 99){
            entityWrapper.like("film_cats","#"+filmCat+"#");
        }
        if(filmSource != null && filmSource != 99){
            entityWrapper.eq("film_source",filmSource);
        }
        if(filmDate != null && filmSource != 99){
            entityWrapper.eq("film_date",filmDate);
        }

        if(filmStatu != 0){
            entityWrapper.eq("film_status",filmStatu);
        }

        Integer count = filmTMapper.selectCount(entityWrapper);
        return count;
    }

    @Override
    public List<ActorT> findAllActors() {
        return actorTMapper.selectList(new EntityWrapper<ActorT>());
    }

    @Override
    public Integer save(FilmT filmT) {
        Integer count = filmTMapper.insert(filmT);

        return filmT.getUuid();
    }

    @Override
    public Integer saveFilmInfo(FilmInfoT filmInfoT) {
        Integer count = filmInfoTMapper.insert(filmInfoT);
        return count;
    }

    @Override
    public boolean saveFilmActor(List<FilmActorT> list) {
        filmActorTMapper.insertFilmActors(list);
        return true;
    }

    @Override
    public Integer deleteByid(Integer uuid) {
        return filmTMapper.deleteById(uuid);
    }


    private List<FilmVO> filmTListToFilmVoList(List<FilmT> filmTS){
        List<FilmVO> filmVOS = new ArrayList<>();
        for (FilmT filmT : filmTS) {
            FilmVO filmVO = new FilmVO();
            filmVO.setUuid(filmT.getUuid());
            filmVO.setImgAddress(filmT.getImgAddress());
            filmVO.setFilmType(getFilmType(filmT.getFilmType()));
            filmVO.setFilmTime(filmT.getFilmTime());
            filmVO.setFilmStatus(filmT.getFilmStatus());
            filmVO.setFilmScore(filmT.getFilmScore());
            filmVO.setFilmPresalenum(filmT.getFilmPresalenum());
            filmVO.setFilmName(filmT.getFilmName());
            filmVO.setFilmBoxOffice(filmT.getFilmBoxOffice());

            filmVO.setFilmSource(getSources(filmT.getFilmSource()));
            filmVO.setFilmDate(getFilmDate(filmT.getFilmDate()));
            filmVO.setFilmCats(getFilmCats(filmT.getFilmCats()));

            filmVOS.add(filmVO);

        }

        return filmVOS;
    }

    private String[] getFilmCats(String filmCats) {
        filmCats = filmCats.substring(1);
        String[] split = filmCats.split("#");
        String[] catsNew = new String[split.length];
        for (int i = 0; i < split.length; i++) {
            CatDictT catDictT = catDictTMapper.selectById(Integer.parseInt(split[i]));
            catsNew[i] = catDictT.getShowName();
        }

        return catsNew;
    }

    private String getFilmDate(Integer filmDate) {
        YearDictT yearDictT = yearDictTMapper.selectById(filmDate);
        return yearDictT.getShowName();

    }

    private String getSources(Integer filmScore) {
        SourceDictT sourceDictT = sourceDictTMapper.selectById(filmScore);
        return sourceDictT.getShowName();
    }

    private String getFilmType(int count){
       switch (count){
           case 0:
               return "2D";
           case 1:
               return "3D";
           case 2:
               return "3DIMAX";
           case 3:
               return "4D";
           default:
               return "无";
       }
    }

}
