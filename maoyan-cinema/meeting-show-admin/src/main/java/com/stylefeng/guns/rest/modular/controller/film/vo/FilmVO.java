package com.stylefeng.guns.rest.modular.controller.film.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;

/**
 * @author minghai
 * @description
 * @date 2020/5/5
 */

@Data
public class FilmVO {
    private Integer uuid;
    private String filmName;
    /**
     * 片源类型: 0-2D,1-3D,2-3DIMAX,4-无
     */
    private String filmType;
    private String imgAddress;
    private String filmScore;
    private Integer filmPresalenum;
    private Integer filmBoxOffice;
    private String filmSource;
    private String filmCats[];
    private String filmArea;
    private String filmDate;
    private Date filmTime;
    private Integer filmStatus;
    private String biography;

}
