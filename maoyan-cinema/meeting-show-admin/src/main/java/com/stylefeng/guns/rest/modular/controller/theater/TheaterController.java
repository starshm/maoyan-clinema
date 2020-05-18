package com.stylefeng.guns.rest.modular.controller.theater;

import com.stylefeng.guns.rest.common.persistence.model.AreaDictT;
import com.stylefeng.guns.rest.common.persistence.model.TheaterT;
import com.stylefeng.guns.rest.modular.controller.theater.vo.RequestTeacherVO;
import com.stylefeng.guns.rest.modular.controller.theater.vo.ResponseTheaterVO;
import com.stylefeng.guns.rest.modular.service.theater.ITheaterService;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/11
 */
@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/sys/theater")
public class TheaterController {

    public static  final String IMGPRE = "http://img.minghai.store";

    @Autowired
    ITheaterService theaterService;

    @GetMapping("/getAllArea")
    public ResponseVO getAllArea(){
        List<AreaDictT> list = theaterService.selectAllArea();
        return ResponseVO.success(list);
    }

    @PostMapping("/getAllTeacher")
    public ResponseVO getAllTeacher(@RequestBody RequestTeacherVO requestTeacherVO){

        Integer total = theaterService.selectCount(requestTeacherVO.getTheaterName(),requestTeacherVO.getAreaId());

        List<ResponseTheaterVO> theaterTS = theaterService.selectAllTeacher(requestTeacherVO);

        return ResponseVO.success(IMGPRE,total,theaterTS);
    }

    @GetMapping("/deleteTheater")
    public ResponseVO deleteTheater(@RequestParam("uuid") Integer uuid){
        boolean flag = theaterService.deleteTheater(uuid);
        if(flag){
            return ResponseVO.success("删除成功");
        }else{
            return ResponseVO.serviceFail("删除失败");
        }
    }

    @PostMapping("/addTheater")
    public ResponseVO addTheater(@RequestBody TheaterT theaterT){

        theaterService.save(theaterT);
        if(theaterT.getUuid() != null){
            return ResponseVO.success("添加成功");
        }else{
            return ResponseVO.serviceFail("添加失败");
        }
    }
}
