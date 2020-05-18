package com.stylefeng.guns.rest.modular.service.show.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.rest.common.persistence.dao.ShowTMapper;
import com.stylefeng.guns.rest.common.persistence.dao.ShowTypeDictTMapper;
import com.stylefeng.guns.rest.common.persistence.model.ShowT;
import com.stylefeng.guns.rest.common.persistence.model.ShowTypeDictT;
import com.stylefeng.guns.rest.modular.controller.show.vo.RequestShowVo;
import com.stylefeng.guns.rest.modular.service.show.IShowService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/10
 */
@Service
public class ShowServiceImpl implements IShowService {

    @Autowired
    ShowTypeDictTMapper showTypeDictTMapper;

    @Autowired
    ShowTMapper showTMapper;

    @Override
    public List<ShowTypeDictT> selectAllShowTypes() {
        return showTypeDictTMapper.selectList(new EntityWrapper<>());
    }

    @Override
    public Integer selectShowsCount(String showName, Integer showType) {
        EntityWrapper<ShowT> entityWrapper = new EntityWrapper<>();
        if(!StringUtils.isEmpty(showName)){
            entityWrapper.like("show_name",showName);
        }
        if(showType != null){
            entityWrapper.eq("show_type",showType);
        }

        return showTMapper.selectCount(entityWrapper);
    }

    @Override
    public List<ShowT> selectAllSHows(RequestShowVo requestShowVo, Integer count) {
        String showName = requestShowVo.getShowName();
        Integer showType = requestShowVo.getShowType();

        Integer nowPage = requestShowVo.getNowPage();
        Integer pageSize = requestShowVo.getPageSize();

        EntityWrapper<ShowT> entityWrapper = new EntityWrapper<>();
        if(!StringUtils.isEmpty(showName)){
            entityWrapper.like("show_name",showName);
        }
        if(showType != null){
            entityWrapper.eq("show_type",showType);
        }

        Page<ShowT> page = new Page<>();
        page.setTotal(count);
        page.setCurrent(nowPage);
        page.setSize(pageSize);

        return showTMapper.selectPage(page,entityWrapper);
    }

    @Override
    public boolean save(ShowT showT) {
        showTMapper.insert(showT);
        return showT.getUuid() == null ? false : true;
    }

    @Override
    public boolean deleteShow(Integer uuid) {
        Integer count = showTMapper.deleteById(uuid);
        System.out.println(count);
        return count == 1 ? true : false;
    }

    @Override
    public List<ShowT> selectAllShows() {
        return showTMapper.selectList(new EntityWrapper<>());
    }
}
