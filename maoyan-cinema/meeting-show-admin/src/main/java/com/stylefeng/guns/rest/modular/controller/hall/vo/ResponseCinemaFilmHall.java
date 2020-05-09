package com.stylefeng.guns.rest.modular.controller.hall.vo;

import com.stylefeng.guns.rest.common.persistence.model.CinemaT;
import com.stylefeng.guns.rest.common.persistence.model.FilmT;
import com.stylefeng.guns.rest.common.persistence.model.HallDictT;
import lombok.Data;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/8
 */
@Data
public class ResponseCinemaFilmHall {
    private List<HallDictT> hallDictTS;
    private List<FilmT>  filmTS;
    private List<CinemaT> cinemaTS;
}
