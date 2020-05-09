package com.stylefeng.guns.rest.modular.controller.ciname;

import com.stylefeng.guns.rest.common.persistence.model.AreaDictT;
import com.stylefeng.guns.rest.common.persistence.model.BrandDictT;
import com.stylefeng.guns.rest.common.persistence.model.CinemaT;
import com.stylefeng.guns.rest.common.persistence.model.HallDictT;
import com.stylefeng.guns.rest.modular.controller.ciname.vo.CinemaRequestVo;
import com.stylefeng.guns.rest.modular.controller.ciname.vo.CinemaResponseVo;
import com.stylefeng.guns.rest.modular.controller.ciname.vo.CinemaVO;
import com.stylefeng.guns.rest.modular.service.cinema.ICinemaService;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Objects;

/**
 * @author minghai
 * @description
 * @date 2020/5/2
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/sys/ciname")
public class CinemaController {

    private static final String IMGPRE = "http://img.minghai.store";

    @Autowired
    private ICinemaService cinemaService;


    @PostMapping("/findAllCinemas")
    public ResponseVO findAllCinemas(@RequestBody  CinemaRequestVo cinemaRequestVo){


        List<BrandDictT> allBrands = cinemaService.findAllBrands();
        List<AreaDictT> allAreas = cinemaService.findAllAreas();
        String cinemaName = cinemaRequestVo.getCinemaName();
        Integer area = cinemaRequestVo.getArea();
        Integer brand = cinemaRequestVo.getBrand();
        Integer nowPage = cinemaRequestVo.getNowPage();
        Integer pageSize = cinemaRequestVo.getPageSize();

        List<CinemaT> allCinemas = cinemaService.findAllCinemas(cinemaName, brand, area, nowPage, pageSize);

        CinemaResponseVo cinemaResponseVo = new CinemaResponseVo();
        cinemaResponseVo.setAllAreas(allAreas);
        cinemaResponseVo.setAllBrands(allBrands);
        cinemaResponseVo.setAllCinemas(allCinemas);

        // 总页数
        Integer total = cinemaService.selectCount(cinemaName, brand, area);

        return ResponseVO.success(IMGPRE,total,cinemaResponseVo);

    }

    @GetMapping("/findAllHalls")
    public ResponseVO findAllHalls(){
        List<HallDictT> halls = cinemaService.findAllHalls();
        return ResponseVO.success(halls);
    }

    @PostMapping("/addCinema")
    public ResponseVO addCinema(@RequestBody CinemaT cinemaT){
        if(Objects.isNull(cinemaT) || cinemaT.getCinemaName() == null){
            return ResponseVO.serviceFail("添加失败");
        }

//        int count = cinemaService.save(cinemaT);
        int count = cinemaService.saveOrUpdate(cinemaT);
        if(count == 1){
            return ResponseVO.success(count);
        }else{
            return ResponseVO.serviceFail("添加失败");
        }
    }

    @GetMapping("/deleteCinemaById")
    public ResponseVO deleteCinemaById(@RequestParam("uuid") Integer uuid){
        Integer count =  cinemaService.deleteCinemaById(uuid);
        if(count == 1){
            return ResponseVO.success("删除成功");
        }else{
            return ResponseVO.serviceFail("删除失败");
        }

    }



}
