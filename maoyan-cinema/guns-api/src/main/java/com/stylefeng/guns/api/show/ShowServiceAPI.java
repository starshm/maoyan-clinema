package com.stylefeng.guns.api.show;

import com.stylefeng.guns.api.show.vo.ShowVO;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/4/28
 */
public interface ShowServiceAPI {

    public String getList();

    public List<ShowVO> getAllShowIndex(boolean isLimit,int nowPage,int pageSize);


}
