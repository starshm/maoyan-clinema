package com.stylefeng.guns.rest.modular.controller.comm;

import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.rest.modular.vo.ResponseVO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.security.provider.MD5;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author minghai
 * @description
 * @date 2020/5/4
 */
@RestController
@RequestMapping("common")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommController {

    @RequestMapping("/uploadImg")
    public ResponseVO uploadImg(
            @RequestParam("file")MultipartFile file,
            @RequestParam("uploadType")String uploadType){
        if(file.isEmpty()){
            return ResponseVO.serviceFail("上传失败");
        }

        String fileName = file.getOriginalFilename();
        String type = fileName.substring(fileName.lastIndexOf("."));
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmmSS");
        fileName =  format.format(date);

        fileName += type;


        System.out.println(fileName);
        String filePath = "E:\\Environment\\product\\cinema\\";
        File myfile = new File(filePath + fileName);
        System.out.println(myfile.getAbsoluteFile());

        try {
            file.transferTo(myfile);
            System.out.println("上传成功");
            return ResponseVO.success("上传成功","/"+uploadType+"/"+fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("上传失败");
        }

        return ResponseVO.serviceFail("上传失败");
    }
}
