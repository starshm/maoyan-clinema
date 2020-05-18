package com.stylefeng.guns.rest.modular.film.serivce;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.film.FilmServiceApi;
import com.stylefeng.guns.api.film.vo.*;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Service(interfaceClass = FilmServiceApi.class)
public class DefaultFilmServiceImpl implements FilmServiceApi {

    @Autowired
    private BannerTMapper bannerTMapper;

    @Autowired
    private FilmTMapper filmTMapper;

    @Autowired
    private CatDictTMapper CatDictTMapper;

    @Autowired
    private YearDictTMapper YearDictTMapper;

    @Autowired
    private SourceDictTMapper sourceDictTMapper;

    @Autowired
    private FilmInfoTMapper FilmInfoTMapper;

    @Autowired
    private ActorTMapper ActorTMapper;

    @Override
    public List<BannerVO> getBanners() {
        List<BannerVO> result = new ArrayList<>();
        List<BannerT> Banners = bannerTMapper.selectList(null);

        for(BannerT BannerT : Banners){
            BannerVO bannerVO = new BannerVO();
            bannerVO.setBannerId(BannerT.getUuid()+"");
            bannerVO.setBannerUrl(BannerT.getBannerUrl());
            bannerVO.setBannerAddress(BannerT.getBannerAddress());
            result.add(bannerVO);
        }

        return result;
    }

    private List<FilmInfo> getFilmInfos(List<FilmT> Films){
        List<FilmInfo> filmInfos = new ArrayList<>();
        for(FilmT FilmT : Films){
            FilmInfo filmInfo = new FilmInfo();
            filmInfo.setScore(FilmT.getFilmScore());
            filmInfo.setImgAddress(FilmT.getImgAddress());
            filmInfo.setFilmType(FilmT.getFilmType());
            filmInfo.setFilmScore(FilmT.getFilmScore());
            filmInfo.setFilmName(FilmT.getFilmName());
            filmInfo.setFilmId(FilmT.getUuid()+"");
            filmInfo.setExpectNum(FilmT.getFilmPresalenum());
            filmInfo.setBoxNum(FilmT.getFilmBoxOffice());
            filmInfo.setShowTime(DateUtil.getDay(FilmT.getFilmTime()));

            // 将转换的对象放入结果集
            filmInfos.add(filmInfo);
        }

        return filmInfos;
    }

    @Override
    public FilmVO getHotFilms(boolean isLimit,int nums,int nowPage,int sortId,int sourceId,int yearId,int catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();

        // 热映影片的限制条件
        EntityWrapper<FilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","1");
        // 判断是否是首页需要的内容
        if(isLimit){
            // 如果是，则限制条数、限制内容为热映影片
            Page<FilmT> page = new Page<>(1,nums);
            List<FilmT> films = filmTMapper.selectPage(page, entityWrapper);
            // 组织filmInfos
            filmInfos = getFilmInfos(films);
            filmVO.setFilmInfo(filmInfos);

            // filmNum 为正在热映的全部影片
            Integer count = filmTMapper.selectCount(entityWrapper);
            filmVO.setFilmNum(count);
        }else{
            // 如果不是，则是列表页，同样需要限制内容为热映影片
            Page<FilmT> page = null;
            // 根据sortId的不同，来组织不同的Page对象
            // 1-按热门搜索，2-按时间搜索，3-按评价搜索
            switch (sortId){
                case 1 :
                    page = new Page<>(nowPage,nums,"film_box_office");
                    break;
                case 2 :
                    page = new Page<>(nowPage,nums,"film_time");
                    break;
                case 3 :
                    page = new Page<>(nowPage,nums,"film_score");
                    break;
                default:
                    page = new Page<>(nowPage,nums,"film_box_office");
                    break;
            }

            // 如果sourceId,yearId,catId 不为99 ,则表示要按照对应的编号进行查询
            if(sourceId != 99){
                entityWrapper.eq("film_source",sourceId);
            }
            if(yearId != 99){
                entityWrapper.eq("film_date",yearId);
            }
            if(catId != 99){
                // #2#4#22#
                String catStr = "%#"+catId+"#%";
                entityWrapper.like("film_cats",catStr);
            }

            List<FilmT> Films = filmTMapper.selectPage(page, entityWrapper);
            // 组织filmInfos
            filmInfos = getFilmInfos(Films);
            filmVO.setFilmNum(Films.size());

            // 需要总页数 totalCounts/nums -> 0 + 1 = 1
            // 每页10条，我现在有6条 -> 1
            int totalCounts = filmTMapper.selectCount(entityWrapper);
            int totalPages = (totalCounts/nums)+1;

            filmVO.setFilmInfo(filmInfos);
            filmVO.setTotalPage(totalPages);
            filmVO.setNowPage(nowPage);
        }

        return filmVO;
    }

    @Override
    public FilmVO getSoonFilms(boolean isLimit,int nums,int nowPage,int sortId,int sourceId,int yearId,int catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();

        // 即将上映影片的限制条件
        EntityWrapper<FilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","2");
        // 判断是否是首页需要的内容
        if(isLimit){
            // 如果是，则限制条数、限制内容为热映影片
            Page<FilmT> page = new Page<>(1,nums);
            List<FilmT> Films = filmTMapper.selectPage(page, entityWrapper);
            // 组织filmInfos
            filmInfos = getFilmInfos(Films);
            filmVO.setFilmInfo(filmInfos);

            Integer count = filmTMapper.selectCount(entityWrapper);
            filmVO.setFilmNum(count);
        }else{
            // 如果不是，则是列表页，同样需要限制内容为即将上映影片
            Page<FilmT> page = null;
            // 根据sortId的不同，来组织不同的Page对象
            // 1-按热门搜索，2-按时间搜索，3-按评价搜索
            switch (sortId){
                case 1 :
                    page = new Page<>(nowPage,nums,"film_preSaleNum");
                    break;
                case 2 :
                    page = new Page<>(nowPage,nums,"film_time");
                    break;
                case 3 :
                    page = new Page<>(nowPage,nums,"film_preSaleNum");
                    break;
                default:
                    page = new Page<>(nowPage,nums,"film_preSaleNum");
                    break;
            }

            // 如果sourceId,yearId,catId 不为99 ,则表示要按照对应的编号进行查询
            if(sourceId != 99){
                entityWrapper.eq("film_source",sourceId);
            }
            if(yearId != 99){
                entityWrapper.eq("film_date",yearId);
            }
            if(catId != 99){
                // #2#4#22#
                String catStr = "%#"+catId+"#%";
                entityWrapper.like("film_cats",catStr);
            }

            List<FilmT> Films = filmTMapper.selectPage(page, entityWrapper);
            // 组织filmInfos
            filmInfos = getFilmInfos(Films);
            filmVO.setFilmNum(Films.size());

            // 需要总页数 totalCounts/nums -> 0 + 1 = 1
            // 每页10条，我现在有6条 -> 1
            int totalCounts = filmTMapper.selectCount(entityWrapper);
            int totalPages = (totalCounts/nums)+1;

            filmVO.setFilmInfo(filmInfos);
            filmVO.setTotalPage(totalPages);
            filmVO.setNowPage(nowPage);
        }

        return filmVO;
    }

    @Override
    public FilmVO getClassicFilms(int nums, int nowPage, int sortId, int sourceId, int yearId, int catId) {
        FilmVO filmVO = new FilmVO();
        List<FilmInfo> filmInfos = new ArrayList<>();

        // 即将上映影片的限制条件
        EntityWrapper<FilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","3");

        // 如果不是，则是列表页，同样需要限制内容为即将上映影片
        Page<FilmT> page = null;
        // 根据sortId的不同，来组织不同的Page对象
        // 1-按热门搜索，2-按时间搜索，3-按评价搜索
        switch (sortId){
            case 1 :
                page = new Page<>(nowPage,nums,"film_box_office");
                break;
            case 2 :
                page = new Page<>(nowPage,nums,"film_time");
                break;
            case 3 :
                page = new Page<>(nowPage,nums,"film_score");
                break;
            default:
                page = new Page<>(nowPage,nums,"film_box_office");
                break;
        }

        // 如果sourceId,yearId,catId 不为99 ,则表示要按照对应的编号进行查询
        if(sourceId != 99){
            entityWrapper.eq("film_source",sourceId);
        }
        if(yearId != 99){
            entityWrapper.eq("film_date",yearId);
        }
        if(catId != 99){
            // #2#4#22#
            String catStr = "%#"+catId+"#%";
            entityWrapper.like("film_cats",catStr);
        }

        List<FilmT> Films = filmTMapper.selectPage(page, entityWrapper);
        // 组织filmInfos
        filmInfos = getFilmInfos(Films);
        filmVO.setFilmNum(Films.size());

        // 需要总页数 totalCounts/nums -> 0 + 1 = 1
        // 每页10条，我现在有6条 -> 1
        int totalCounts = filmTMapper.selectCount(entityWrapper);
        int totalPages = (totalCounts/nums)+1;

        filmVO.setFilmInfo(filmInfos);
        filmVO.setTotalPage(totalPages);
        filmVO.setNowPage(nowPage);

        return filmVO;
    }


    @Override
    public List<FilmInfo> getBoxRanking() {
        // 条件 -> 正在上映的，票房前10名
        EntityWrapper<FilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","1");

        Page<FilmT> page = new Page<>(1,10,"film_box_office",false);

        List<FilmT> Films = filmTMapper.selectPage(page,entityWrapper);

        List<FilmInfo> filmInfos = getFilmInfos(Films);

        return filmInfos;
    }

    @Override
    public List<FilmInfo> getExpectRanking() {
        // 条件 -> 即将上映的，预售前10名
        EntityWrapper<FilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","2");

        Page<FilmT> page = new Page<>(1,10,"film_preSaleNum",false);

        List<FilmT> Films = filmTMapper.selectPage(page,entityWrapper);

        List<FilmInfo> filmInfos = getFilmInfos(Films);

        return filmInfos;

    }

    @Override
    public List<FilmInfo> getTop() {
        // 条件 -> 正在上映的，评分前10名
        EntityWrapper<FilmT> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("film_status","1");

        Page<FilmT> page = new Page<>(1,10,"film_score",false);

        List<FilmT> Films = filmTMapper.selectPage(page,entityWrapper);

        List<FilmInfo> filmInfos = getFilmInfos(Films);

        return filmInfos;
    }

    @Override
    public List<CatVO> getCats() {
        List<CatVO> cats = new ArrayList<>();
        // 查询实体对象 - CatDictT
        List<CatDictT> Cats = CatDictTMapper.selectList(null);
        // 将实体对象转换为业务对象 - CatVO
        for(CatDictT CatDictT : Cats){
            CatVO catVO = new CatVO();
            catVO.setCatId(CatDictT.getUuid()+"");
            catVO.setCatName(CatDictT.getShowName());

            cats.add(catVO);
        }

        return cats;
    }

    @Override
    public List<SourceVO> getSources() {
        List<SourceVO> sources = new ArrayList<>();
        System.out.println("**********************************************************");
        List<SourceDictT> SourceDicts = sourceDictTMapper.selectList(null);
        System.out.println("**********************************************************");
        for(SourceDictT SourceDictT : SourceDicts){
            SourceVO sourceVO = new SourceVO();

            sourceVO.setSourceId(SourceDictT.getUuid()+"");
            sourceVO.setSourceName(SourceDictT.getShowName());

            sources.add(sourceVO);
        }
        return sources;
    }

    @Override
    public List<YearVO> getYears() {
        List<YearVO> years = new ArrayList<>();
        // 查询实体对象 - CatDictT
        List<YearDictT> Years = YearDictTMapper.selectList(null);
        // 将实体对象转换为业务对象 - CatVO
        for(YearDictT YearDictT : Years){
            YearVO yearVO = new YearVO();
            yearVO.setYearId(YearDictT.getUuid()+"");
            yearVO.setYearName(YearDictT.getShowName());

            years.add(yearVO);
        }
        return years;
    }

    @Override
    public FilmDetailVO getFilmDetail(int searchType, String searchParam) {
        FilmDetailVO filmDetailVO = null;
        // searchType 1-按名称  2-按ID的查找
        if(searchType == 1){
            filmDetailVO = filmTMapper.getFilmDetailByName("%"+searchParam+"%");
        }else{
            filmDetailVO = filmTMapper.getFilmDetailById(searchParam);
        }

        return filmDetailVO;
    }

    private FilmInfoT getFilmInfo(String filmId){

        FilmInfoT filmInfoT = new FilmInfoT();
        filmInfoT.setFilmId(filmId);

        filmInfoT = FilmInfoTMapper.selectOne(filmInfoT);

        return filmInfoT;
    }

    @Override
    public FilmDescVO getFilmDesc(String filmId) {

        FilmInfoT FilmInfoT = getFilmInfo(filmId);

        FilmDescVO filmDescVO = new FilmDescVO();
        filmDescVO.setBiography(FilmInfoT.getBiography());
        filmDescVO.setFilmId(filmId);

        return filmDescVO;
    }

    @Override
    public ImgVO getImgs(String filmId) {

        FilmInfoT FilmInfoT = getFilmInfo(filmId);
        // 图片地址是五个以逗号为分隔的链接URL
        String filmImgStr = FilmInfoT.getFilmImgs();
        String[] filmImgs = filmImgStr.split(",");

        ImgVO imgVO = new ImgVO();
        imgVO.setMainImg(filmImgs[0]);
        imgVO.setImg01(filmImgs[1]);
        imgVO.setImg02(filmImgs[2]);
        imgVO.setImg03(filmImgs[3]);
        imgVO.setImg04(filmImgs[4]);

        return imgVO;
    }

    @Override
    public ActorVO getDectInfo(String filmId) {

        FilmInfoT FilmInfoT = getFilmInfo(filmId);

        // 获取导演编号
        Integer directId = FilmInfoT.getDirectorId();

        ActorT ActorT = ActorTMapper.selectById(directId);

        ActorVO actorVO = new ActorVO();
        actorVO.setImgAddress(ActorT.getActorImg());
        actorVO.setDirectorName(ActorT.getActorName());

        return actorVO;
    }

    @Override
    public List<ActorVO> getActors(String filmId) {

        List<ActorVO> actors = ActorTMapper.getActors(filmId);

        return actors;
    }


}
