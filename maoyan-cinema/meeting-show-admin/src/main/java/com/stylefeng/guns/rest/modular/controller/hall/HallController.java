package com.stylefeng.guns.rest.modular.controller.hall;

import com.stylefeng.guns.rest.common.persistence.model.HallDictT;
import com.stylefeng.guns.rest.modular.controller.hall.vo.RequestAddHallVo;
import com.stylefeng.guns.rest.modular.controller.hall.vo.ResponseCinemaFilmHall;
import com.stylefeng.guns.rest.modular.controller.hall.vo.ResponseHallsVo;
import com.stylefeng.guns.rest.modular.service.hall.IHallService;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/7
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/sys/hall")
public class HallController {

    @Autowired
    IHallService hallService;

    @GetMapping("/findAllHalls")
    public ResponseVO findAllHalls(
            @RequestParam(required = false,name = "hallName") String hallName,
            @RequestParam(required = true,name = "nowPage") Integer nowPage,
            @RequestParam(required = true,name = "pageSize") Integer pageSize
    ){
        System.out.println(hallName + "  " + nowPage + "  " + pageSize);

        Integer count = hallService.selectCount(hallName);
        List<ResponseHallsVo> allHalls = hallService.findAllHalls(hallName, nowPage, pageSize);

        return ResponseVO.success(count,allHalls);
    }

    @GetMapping("/getAllCinemaFilmHall")
    public ResponseVO findAllCinemaFilmHall(){
        ResponseCinemaFilmHall result = hallService.findAllCinemaFilmHall();
        return ResponseVO.success(result);
    }

    @PostMapping("/addOneHall")
    public ResponseVO addOneHall(@RequestBody RequestAddHallVo requestAddHallVo){

        System.out.println(requestAddHallVo);

        boolean flag = hallService.save(requestAddHallVo);
        if(flag){
            return ResponseVO.success("添加成功");
        }
        return ResponseVO.serviceFail("添加失败");
    }

}



