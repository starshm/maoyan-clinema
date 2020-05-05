package com.stylefeng.guns.rest.modular.controller.film;

import com.stylefeng.guns.rest.modular.controller.film.vo.RequestVo;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author minghai
 * @description
 * @date 2020/5/5
 */
@RestController
@CrossOrigin(value = "*", maxAge = 3600)
@RequestMapping("/film")
public class FilmController {

    @RequestMapping("/findAllFilms")
    public ResponseVO findAllFilms(@RequestBody RequestVo vo){

        System.out.println(vo);

        return null;
    }
}
