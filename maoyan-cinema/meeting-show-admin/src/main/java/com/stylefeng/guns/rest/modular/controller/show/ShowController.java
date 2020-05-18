package com.stylefeng.guns.rest.modular.controller.show;

import com.stylefeng.guns.rest.common.persistence.model.ShowT;
import com.stylefeng.guns.rest.common.persistence.model.ShowTypeDictT;
import com.stylefeng.guns.rest.modular.controller.show.vo.RequestShowVo;
import com.stylefeng.guns.rest.modular.controller.show.vo.ResponseShowVO;
import com.stylefeng.guns.rest.modular.service.show.IShowService;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/9
 */
@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/sys/show")
public class ShowController {

    public static  final String IMGPRE = "http://img.minghai.store";

    @Autowired
    private IShowService showService;

    @PostMapping("/findAllShows")
    public ResponseVO findAllShows(@RequestBody RequestShowVo requestShowVo ){

        Integer count = showService.selectShowsCount(requestShowVo.getShowName(),requestShowVo.getShowType());

        List<ShowT> shows = showService.selectAllSHows(requestShowVo,count);


        return ResponseVO.success(IMGPRE,count,shows);
    }

    @GetMapping("/findAllShowTypes")
    public ResponseVO findAllShowTypes(){

        List<ShowTypeDictT> showTypes = showService.selectAllShowTypes();
        return ResponseVO.success(showTypes);
    }

    @PostMapping("/addShow")
    public ResponseVO addShow(@RequestBody ShowT showT){
        boolean  flag = showService.save(showT);
        if(flag){
            return ResponseVO.success("添加成功");
        }else{
            return ResponseVO.serviceFail("添加失败");
        }
    }

    @GetMapping("/deleteShow")
    public ResponseVO deleteShow(@RequestParam("uuid") Integer uuid){
        boolean flag = showService.deleteShow(uuid);
        if(flag){
            return ResponseVO.success("删除成功");
        }else{
            return ResponseVO.serviceFail("删除失败");
        }
    }
}
