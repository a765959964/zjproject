package com.example.demo.controller;

import com.example.demo.service.TFoodCommonTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
* @Description: JSON 文件生成类
* @author zf
* @date 2018/11/02 13:45
*/
@Controller
@RequestMapping("/food")
public class TFoodCommonTypeController {

    @Resource
    private TFoodCommonTypeService tFoodCommonTypeService;

    /**
     * 生成0.json 菜系文件夹
     * @param req
     * @return
     */
    @RequestMapping("/createJsonAndFile")
    @ResponseBody
    public void createJsonAndFile(HttpServletRequest req) {
        tFoodCommonTypeService.createJsonAndFile();
    }




}