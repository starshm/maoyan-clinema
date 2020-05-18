package com.stylefeng.guns.rest.modular.controller.order;

import com.stylefeng.guns.rest.modular.controller.order.vo.FilmOrderVo;
import com.stylefeng.guns.rest.modular.controller.order.vo.ReqeustOrderVO;
import com.stylefeng.guns.rest.modular.service.order.IFilmOrder;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/5/9
 */
@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping("/sys/filmOrder")
public class FilmOrderController {

    @Autowired
    IFilmOrder filmOrder;

    @PostMapping("/getAllFilmOrders")
    public ResponseVO findAllFilmOrder(@RequestBody ReqeustOrderVO reqeustOrderVO){

        List<FilmOrderVo> list =  filmOrder.findAllFilmOrder(reqeustOrderVO);

        Integer count = filmOrder.selectCount();

        return ResponseVO.success(count,list);
    }

    @PostMapping("/deleteFilmOrder")
    public ResponseVO deleteFilmOrderByUUID(@RequestParam("uuid") String uuid){
        System.out.println(uuid);
        Integer count = filmOrder.deleteFilmOrderById(uuid);

        return ResponseVO.success("删除成功");

    }
}
