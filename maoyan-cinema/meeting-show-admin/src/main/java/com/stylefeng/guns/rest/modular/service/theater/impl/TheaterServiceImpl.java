package com.stylefeng.guns.rest.modular.service.theater.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.rest.common.persistence.dao.AreaDictTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.TheaterTMapper;
import com.stylefeng.guns.rest.common.persistence.model.AreaDictT;
import com.stylefeng.guns.rest.common.persistence.model.TheaterT;
import com.stylefeng.guns.rest.modular.controller.theater.vo.RequestTeacherVO;
import com.stylefeng.guns.rest.modular.controller.theater.vo.ResponseTheaterVO;
import com.stylefeng.guns.rest.modular.service.theater.ITheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/11
 */
@Service
public class TheaterServiceImpl implements ITheaterService {
    @Autowired
    AreaDictTMapper areaDictTMapper;

    @Autowired
    TheaterTMapper theaterTMapper;

    @Override
    public List<AreaDictT> selectAllArea() {
        return areaDictTMapper.selectList(new EntityWrapper<>());
    }

    @Override
    public Integer selectCount(String theaterName, Integer areaId) {
        EntityWrapper<TheaterT> entityWrapper = new EntityWrapper<>();
        if(StringUtils.isEmpty(theaterName)){
            entityWrapper.like("theater_name",theaterName);
        }
        if(areaId != null){
            entityWrapper.eq("area_id",areaId);
        }
        return theaterTMapper.selectCount(entityWrapper);
    }

    @Override
    public List<ResponseTheaterVO> selectAllTeacher(RequestTeacherVO requestTeacherVO) {
        String theaterName = requestTeacherVO.getTheaterName();
        if(StringUtils.isEmpty(theaterName)){
            theaterName = null;
        }
        Integer areaId = requestTeacherVO.getAreaId();
        Integer nowPage = requestTeacherVO.getNowPage();
        Integer pageSize = requestTeacherVO.getPageSize();
        nowPage = (nowPage -1) * pageSize;
        List<ResponseTheaterVO> result = theaterTMapper.selectAllTheater(theaterName,areaId,nowPage,pageSize);
        return result;
    }

    @Override
    public boolean deleteTheater(Integer uuid) {
        Integer count = theaterTMapper.deleteById(uuid);
        if(count > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void save(TheaterT theaterT) {
        theaterTMapper.insert(theaterT);
    }

    @Override
    public List<TheaterT> selectAllTheaters() {
        return theaterTMapper.selectList(new EntityWrapper<>());
    }
}
