package com.stylefeng.guns.rest.modular.vo;
import com.stylefeng.guns.rest.common.persistence.model.UserT;
import lombok.Data;

import java.util.List;

/**
 * @author minghai
 * @description
 * @date 2020/4/30
 */
@Data
public class ResponseVO<M> {
    // 返回状态【0-成功，1-业务失败，999-表示系统异常】
    private int status;
    // 返回信息
    private String msg;
    // 返回数据实体;
    private M data;
    // 图片前缀
    private String imgPre;

    private Integer total;
    private Integer nowPage;

    private ResponseVO(){}


    public static<M> ResponseVO success(String imgPre,int total,M m){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(m);
        responseVO.setImgPre(imgPre);
        responseVO.setTotal(total);

        return responseVO;
    }
    public static<M> ResponseVO success(String imgPre,M m){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(m);
        responseVO.setImgPre(imgPre);

        return responseVO;
    }

    public static<M> ResponseVO success(M m){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(m);

        return responseVO;
    }

    public static<M> ResponseVO success(String msg){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setMsg(msg);

        return responseVO;
    }
    // 业务异常
    public static<M> ResponseVO serviceFail(String msg){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(1);
        responseVO.setMsg(msg);

        return responseVO;
    }
    // 系统异常
    public static<M> ResponseVO appFail(String msg){
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatus(999);
        responseVO.setMsg(msg);

        return responseVO;
    }

    public static ResponseVO success(int nowPage, int total, List<UserT> list) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setTotal(total);
        responseVO.setNowPage(nowPage);
        responseVO.setData(list);
        return responseVO;
    }
    public static ResponseVO success(int total, List<UserT> list) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setTotal(total);
        responseVO.setData(list);
        return responseVO;
    }
}
