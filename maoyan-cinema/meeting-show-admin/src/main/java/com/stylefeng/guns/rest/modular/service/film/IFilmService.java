package com.stylefeng.guns.rest.modular.service.film;

import com.stylefeng.guns.rest.common.persistence.model.*;
import com.stylefeng.guns.rest.modular.controller.film.vo.FilmVO;

import java.util.List;

public interface IFilmService {
    public List<YearDictT> findAllYear();

    public List<SourceDictT> findAllSource();

    List<CatDictT> findAllCat();


    List<FilmVO> findAllFilms(String filmName, Integer filmCat, Integer filmSource, Integer filmDate, Integer nowPage, Integer pageSize, Integer filmStatu);


    int selectCount(String filmName, Integer filmCat, Integer filmSource, Integer filmDate, Integer filmStatu);

    List<ActorT> findAllActors();

    Integer save(FilmT filmT);

    Integer saveFilmInfo(FilmInfoT filmInfoT);

    boolean saveFilmActor(List<FilmActorT> list);

    Integer deleteByid(Integer uuid);
}
