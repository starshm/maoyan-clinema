package com.stylefeng.guns.rest.modular.controller.show.vo;

import com.stylefeng.guns.rest.common.persistence.model.ShowT;
import com.stylefeng.guns.rest.common.persistence.model.ShowTypeDictT;
import lombok.Data;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/10
 */
@Data
public class ResponseShowVO {
    List<ShowTypeDictT> showTypes;
    List<ShowT> shows;
}
