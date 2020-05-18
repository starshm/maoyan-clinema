package com.stylefeng.guns.rest.modular.show.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.stylefeng.guns.api.show.vo.ShowVO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/18
 */
@Data
public class ShowIndexVO {
    List<ShowVO> shows;

}
