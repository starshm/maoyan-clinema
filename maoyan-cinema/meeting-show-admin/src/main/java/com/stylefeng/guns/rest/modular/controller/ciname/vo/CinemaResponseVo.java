package com.stylefeng.guns.rest.modular.controller.ciname.vo;

import com.stylefeng.guns.rest.common.persistence.model.AreaDictT;
import com.stylefeng.guns.rest.common.persistence.model.BrandDictT;
import com.stylefeng.guns.rest.common.persistence.model.CinemaT;
import lombok.Data;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/4
 */
@Data
public class CinemaResponseVo {
    List<BrandDictT> allBrands;
    List<AreaDictT> allAreas;
    List<CinemaT> allCinemas;
}
