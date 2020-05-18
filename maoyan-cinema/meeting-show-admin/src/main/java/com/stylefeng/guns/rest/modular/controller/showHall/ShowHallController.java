package com.stylefeng.guns.rest.modular.controller.showHall;

import com.stylefeng.guns.rest.common.persistence.model.ShowFieldT;
import com.stylefeng.guns.rest.common.persistence.model.ShowT;
import com.stylefeng.guns.rest.common.persistence.model.TheaterT;
import com.stylefeng.guns.rest.modular.controller.showHall.vo.ShowHallVo;
import com.stylefeng.guns.rest.modular.controller.showHall.vo.TheaterShowVO;
import com.stylefeng.guns.rest.modular.service.show.IShowService;
import com.stylefeng.guns.rest.modular.service.showHall.IShowHallService;
import com.stylefeng.guns.rest.modular.service.theater.ITheaterService;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/16
 */
@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/sys/showHall")
public class ShowHallController {

    @Autowired
    IShowHallService showHallService;

    @Autowired
    IShowService showService;

    @Autowired
    ITheaterService theaterService;

    @GetMapping("/getAllShowHall")
    public ResponseVO getAllShowHall(
            @RequestParam("theaterName") String theaterName,
            @RequestParam("nowPage") Integer nowPage,
            @RequestParam("pageSize") Integer pageSize
    ){
        Integer total = showHallService.selectTotal(theaterName);
        List<ShowHallVo> allShowHall = showHallService.findAllShowHall(theaterName, nowPage, pageSize);

        return ResponseVO.success(total,allShowHall);
    }


    @GetMapping("/findAllShowTheaters")
    public ResponseVO findAllShowTheaters(){
        List<ShowT> shows = showService.selectAllShows();
        List<TheaterT> theaters = theaterService.selectAllTheaters();
        TheaterShowVO theaterShowVO = new TheaterShowVO();
        theaterShowVO.setShows(shows);
        theaterShowVO.setTheaters(theaters);
        return ResponseVO.success(theaterShowVO);
    }

    @PostMapping("/saveOrUpdate")
    public ResponseVO saveOrUpdate(@RequestBody ShowFieldT showFieldT){
        if(showFieldT.getUuid() != null ){
            Integer count = showHallService.update(showFieldT);
            return ResponseVO.success("更新成功");
        }else{
            Integer count = showHallService.save(showFieldT);
            return ResponseVO.success("添加成功");
        }
    }

    @GetMapping("/deleteShowHallById")
    public ResponseVO deleteShowHallById(@RequestParam("uuid") Integer uuid){
        Integer count = showHallService.deleteById(uuid);
        if(count == 1){
            return ResponseVO.success("删除成功");
        }
        return ResponseVO.serviceFail("删除失败");
    }

}
