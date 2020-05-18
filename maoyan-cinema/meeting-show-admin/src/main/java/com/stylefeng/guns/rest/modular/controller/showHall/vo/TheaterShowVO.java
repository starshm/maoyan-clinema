package com.stylefeng.guns.rest.modular.controller.showHall.vo;

import com.stylefeng.guns.rest.common.persistence.model.ShowT;
import com.stylefeng.guns.rest.common.persistence.model.TheaterT;
import lombok.Data;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/17
 */
@Data
public class TheaterShowVO {
    List<ShowT> shows;
    List<TheaterT> theaters;
}
