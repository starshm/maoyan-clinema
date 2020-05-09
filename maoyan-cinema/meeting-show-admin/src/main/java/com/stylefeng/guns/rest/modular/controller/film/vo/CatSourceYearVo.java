package com.stylefeng.guns.rest.modular.controller.film.vo;

import com.stylefeng.guns.rest.common.persistence.model.CatDictT;
import com.stylefeng.guns.rest.common.persistence.model.SourceDictT;
import com.stylefeng.guns.rest.common.persistence.model.YearDictT;
import lombok.Data;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/5
 */
@Data
public class CatSourceYearVo {
    List<YearDictT> filmDates;

    List<SourceDictT> filmSources;

    List<CatDictT> filmCats;

}
