package com.stylefeng.guns.rest.modular.controller.film;

import com.stylefeng.guns.rest.common.persistence.model.*;
import com.stylefeng.guns.rest.modular.controller.film.vo.*;
import com.stylefeng.guns.rest.modular.service.film.IFilmService;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author minghai
 * @description
 * @date 2020/5/5
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/sys/film")
public class FilmController {

    private static final String IMGPRE = "http://img.minghai.store";

    @Autowired
    private IFilmService filmService;

    @GetMapping("/findAllCatSourceYear")
    public ResponseVO findAllCatSourceYear(){

        List<YearDictT> yearDictTS = filmService.findAllYear();

        List<SourceDictT> sourceDictTS = filmService.findAllSource();

        List<CatDictT> catDictTS = filmService.findAllCat();

        CatSourceYearVo catSourceYearVo = new CatSourceYearVo();
        catSourceYearVo.setFilmCats(catDictTS);
        catSourceYearVo.setFilmSources(sourceDictTS);
        catSourceYearVo.setFilmDates(yearDictTS);
        return ResponseVO.success(catSourceYearVo);
    }

    @GetMapping("/findAllActors")
    public ResponseVO findAllActors(){

        List<ActorT> actorTS = filmService.findAllActors();

        return ResponseVO.success(actorTS);
    }

    @RequestMapping("/findAllFilms")
    public ResponseVO findAllFilms(@RequestBody RequestVo vo){

        String filmName = vo.getFilmName();
        Integer filmCat = vo.getFilmCat();
        Integer filmSource = vo.getFilmSource();
        Integer filmDate = vo.getFilmDate();
        Integer nowPage = vo.getNowPage();
        Integer pageSize = vo.getPageSize();
        Integer filmStatu = vo.getFilmStatu();

        List<FilmVO> films = filmService.findAllFilms(filmName,filmCat,filmSource,filmDate,nowPage,pageSize,filmStatu);

        int count = filmService.selectCount(filmName,filmCat,filmSource,filmDate,filmStatu);

        return ResponseVO.success(IMGPRE,count,films);
    }

    @PostMapping("/addFilms")
    public ResponseVO addFilms(@RequestBody RequestVOAddFilm requestVOAddFilm){
        System.out.println(requestVOAddFilm);

        FilmT filmT = getFilmtFromRequestVo(requestVOAddFilm);
        Integer uuid = filmService.save(filmT);

        FilmInfoT filmInfoT = getFilmInfoTFromRequestVo(requestVOAddFilm,uuid);
        Integer count = filmService.saveFilmInfo(filmInfoT);

        if(uuid != null && count ==1){

            String[] actors = requestVOAddFilm.getActIds().split("#");
            String[] roleNames = requestVOAddFilm.getRoleNames().split("#");
            List<FilmActorT> list = new ArrayList<>();
            for(int i = 0; i < actors.length;  i++){
                FilmActorT filmActorT = new FilmActorT();
                filmActorT.setFilmId(uuid);
                filmActorT.setActorId(Integer.parseInt(actors[i]));
                filmActorT.setRoleName(roleNames[i]);
                list.add(filmActorT);
            }
            boolean flag = filmService.saveFilmActor(list);
            if(flag){
                return ResponseVO.success("添加成功");
            }
        }
        return ResponseVO.serviceFail("添加失败");
    }

    @GetMapping("/deleteFilm")
    public ResponseVO deleteFilm(@RequestParam("uuid") Integer uuid){
        Integer count = filmService.deleteByid(uuid);
        if(count == 1){
            return ResponseVO.success("删除成功");
        }else{
            return ResponseVO.serviceFail("删除失败");
        }
    }

    private FilmInfoT getFilmInfoTFromRequestVo(RequestVOAddFilm requestVOAddFilm,Integer filmId) {
        FilmInfoT filmInfoT = new FilmInfoT();
        filmInfoT.setFilmId(filmId+"");
        filmInfoT.setFilmEnName(requestVOAddFilm.getFilmEnName());
        filmInfoT.setFilmScore(requestVOAddFilm.getFilmScore());
        filmInfoT.setFilmScoreNum(requestVOAddFilm.getFilmScorers());
        filmInfoT.setFilmLength(requestVOAddFilm.getFilmLength());
        filmInfoT.setBiography(requestVOAddFilm.getBiography());
        filmInfoT.setFilmImgs(requestVOAddFilm.getFilmImgs());
        filmInfoT.setDirectorId(requestVOAddFilm.getDirectorId());

        return filmInfoT;
    }

    private FilmT getFilmtFromRequestVo(RequestVOAddFilm requestVOAddFilm) {
        FilmT filmT = new FilmT();
        filmT.setFilmName((requestVOAddFilm.getFilmName()));
        filmT.setFilmType(requestVOAddFilm.getFilmTypeId());
        filmT.setImgAddress(requestVOAddFilm.getMainImgAddress());
        filmT.setFilmScore(requestVOAddFilm.getFilmScore());
        filmT.setFilmPresalenum(requestVOAddFilm.getPreSaleNum());
        filmT.setFilmBoxOffice(requestVOAddFilm.getBoxOffice());
        filmT.setFilmSource(requestVOAddFilm.getFilmSourceId());
        filmT.setFilmCats(requestVOAddFilm.getFilmCatIds());
        filmT.setFilmArea(1);// 影片区域
        filmT.setFilmDate(requestVOAddFilm.getDateId());
        filmT.setFilmTime(requestVOAddFilm.getFilmTime());
        filmT.setFilmStatus(requestVOAddFilm.getFilmStatus());
        return filmT;
    }
}
