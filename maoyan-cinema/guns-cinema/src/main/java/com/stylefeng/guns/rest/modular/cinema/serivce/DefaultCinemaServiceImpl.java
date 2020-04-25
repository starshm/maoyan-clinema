package com.stylefeng.guns.rest.modular.cinema.serivce;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.cinema.CinemaServiceAPI;
import com.stylefeng.guns.api.cinema.vo.*;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Service(interfaceClass = CinemaServiceAPI.class,executes = 10)
public class DefaultCinemaServiceImpl implements CinemaServiceAPI {

    @Autowired
    private CinemaTMapper CinemaTMapper;
    @Autowired
    private AreaDictTMapper AreaDictTMapper;
    @Autowired
    private BrandDictTMapper BrandDictTMapper;
    @Autowired
    private HallDictTMapper HallDictTMapper;
    @Autowired
    private HallFilmInfoTMapper HallFilmInfoTMapper;
    @Autowired
    private FieldTMapper FieldTMapper;


    //1、根据CinemaQueryVO，查询影院列表
    @Override
    public Page<CinemaVO> getCinemas(CinemaQueryVO cinemaQueryVO){
        // 业务实体集合
        List<CinemaVO> cinemas = new ArrayList<>();

        Page<CinemaT> page = new Page<>(cinemaQueryVO.getNowPage(),cinemaQueryVO.getPageSize());
        // 判断是否传入查询条件 -> brandId,distId,hallType 是否==99
        EntityWrapper<CinemaT> entityWrapper = new EntityWrapper<>();
        if(cinemaQueryVO.getBrandId() != 99){
            entityWrapper.eq("brand_id",cinemaQueryVO.getBrandId());
        }
        if(cinemaQueryVO.getDistrictId() != 99){
            entityWrapper.eq("area_id",cinemaQueryVO.getDistrictId());
        }
        if(cinemaQueryVO.getHallType() != 99){  // %#3#%
            entityWrapper.like("hall_ids","%#+"+cinemaQueryVO.getHallType()+"+#%");
        }

        // 将数据实体转换为业务实体
        List<CinemaT> CinemaTS = CinemaTMapper.selectPage(page, entityWrapper);
        for(CinemaT CinemaT : CinemaTS){
            CinemaVO cinemaVO = new CinemaVO();

            cinemaVO.setUuid(CinemaT.getUuid()+"");
            cinemaVO.setMinimumPrice(CinemaT.getMinimumPrice()+"");
            cinemaVO.setCinemaName(CinemaT.getCinemaName());
            cinemaVO.setAddress(CinemaT.getCinemaAddress());

            cinemas.add(cinemaVO);
        }

        // 根据条件，判断影院列表总数
        long counts = CinemaTMapper.selectCount(entityWrapper);

        // 组织返回值对象
        Page<CinemaVO> result = new Page<>();
        result.setRecords(cinemas);
        result.setSize(cinemaQueryVO.getPageSize());
        result.setTotal(counts);

        return result;
    }
    //2、根据条件获取品牌列表[除了就99以外，其他的数字为isActive]
    @Override
    public List<BrandVO> getBrands(int brandId){
        boolean flag = false;
        List<BrandVO> brandVOS = new ArrayList<>();
        // 判断brandId是否存在
        BrandDictT BrandDictT = BrandDictTMapper.selectById(brandId);
        // 判断brandId 是否等于 99
        if(brandId == 99 || BrandDictT==null || BrandDictT.getUuid() == null){
            flag = true;
        }
        // 查询所有列表
        List<BrandDictT> BrandDictTS = BrandDictTMapper.selectList(null);
        // 判断flag如果为true，则将99置为isActive
        for(BrandDictT brand : BrandDictTS){
            BrandVO brandVO = new BrandVO();
            brandVO.setBrandName(brand.getShowName());
            brandVO.setBrandId(brand.getUuid()+"");
            // 如果flag为true，则需要99，如为false，则匹配上的内容为active
            if(flag){
                if(brand.getUuid() == 99){
                    brandVO.setActive(true);
                }
            }else{
                if(brand.getUuid() == brandId){
                    brandVO.setActive(true);
                }
            }

            brandVOS.add(brandVO);
        }

        return brandVOS;
    }
    //3、获取行政区域列表
    @Override
    public List<AreaVO> getAreas(int areaId){
        boolean flag = false;
        List<AreaVO> areaVOS = new ArrayList<>();
        // 判断brandId是否存在
        AreaDictT AreaDictT = AreaDictTMapper.selectById(areaId);
        // 判断brandId 是否等于 99
        if(areaId == 99 || AreaDictT==null || AreaDictT.getUuid() == null){
            flag = true;
        }
        // 查询所有列表
        List<AreaDictT> AreaDictTS = AreaDictTMapper.selectList(null);
        // 判断flag如果为true，则将99置为isActive
        for(AreaDictT area : AreaDictTS){
            AreaVO areaVO = new AreaVO();
            areaVO.setAreaName(area.getShowName());
            areaVO.setAreaId(area.getUuid()+"");
            // 如果flag为true，则需要99，如为false，则匹配上的内容为active
            if(flag){
                if(area.getUuid() == 99){
                    areaVO.setActive(true);
                }
            }else{
                if(area.getUuid() == areaId){
                    areaVO.setActive(true);
                }
            }

            areaVOS.add(areaVO);
        }

        return areaVOS;
    }
    //4、获取影厅类型列表
    @Override
    public List<HallTypeVO> getHallTypes(int hallType){
        boolean flag = false;
        List<HallTypeVO> hallTypeVOS = new ArrayList<>();
        // 判断brandId是否存在
        HallDictT HallDictT = HallDictTMapper.selectById(hallType);
        // 判断brandId 是否等于 99
        if(hallType == 99 || HallDictT==null || HallDictT.getUuid() == null){
            flag = true;
        }
        // 查询所有列表
        List<HallDictT> HallDictTS = HallDictTMapper.selectList(null);
        // 判断flag如果为true，则将99置为isActive
        for(HallDictT hall : HallDictTS){
            HallTypeVO hallTypeVO = new HallTypeVO();
            hallTypeVO.setHalltypeName(hall.getShowName());
            hallTypeVO.setHalltypeId(hall.getUuid()+"");
            // 如果flag为true，则需要99，如为false，则匹配上的内容为active
            if(flag){
                if(hall.getUuid() == 99){
                    hallTypeVO.setActive(true);
                }
            }else{
                if(hall.getUuid() == hallType){
                    hallTypeVO.setActive(true);
                }
            }

            hallTypeVOS.add(hallTypeVO);
        }

        return hallTypeVOS;
    }
    //5、根据影院编号，获取影院信息
    @Override
    public CinemaInfoVO getCinemaInfoById(int cinemaId){

        // 数据实体
        CinemaT CinemaT = CinemaTMapper.selectById(cinemaId);
        // 将数据实体转换成业务实体
        CinemaInfoVO cinemaInfoVO = new CinemaInfoVO();
        cinemaInfoVO.setCinemaAdress(CinemaT.getCinemaAddress());
        cinemaInfoVO.setImgUrl(CinemaT.getImgAddress());
        cinemaInfoVO.setCinemaPhone(CinemaT.getCinemaPhone());
        cinemaInfoVO.setCinemaName(CinemaT.getCinemaName());
        cinemaInfoVO.setCinemaId(CinemaT.getUuid()+"");

        return cinemaInfoVO;
    }

    //6、获取所有电影的信息和对应的放映场次信息，根据影院编号
    @Override
    public List<FilmInfoVO> getFilmInfoByCinemaId(int cinemaId){

        List<FilmInfoVO> filmInfos = FieldTMapper.getFilmInfos(cinemaId);

        return filmInfos;
    }
    //7、根据放映场次ID获取放映信息
    @Override
    public HallInfoVO getFilmFieldInfo(int fieldId){

        HallInfoVO hallInfoVO = FieldTMapper.getHallInfo(fieldId);

        return hallInfoVO;
    }
    //8、根据放映场次查询播放的电影编号，然后根据电影编号获取对应的电影信息
    @Override
    public FilmInfoVO getFilmInfoByFieldId(int fieldId){

        FilmInfoVO filmInfoVO = FieldTMapper.getFilmInfoById(fieldId);

        return filmInfoVO;
    }

    @Override
    public OrderQueryVO getOrderNeeds(int fieldId) {

        OrderQueryVO orderQueryVO = new OrderQueryVO();

        FieldT FieldT = FieldTMapper.selectById(fieldId);

        orderQueryVO.setCinemaId(FieldT.getCinemaId()+"");
        orderQueryVO.setFilmPrice(FieldT.getPrice()+"");

        return orderQueryVO;
    }

}
