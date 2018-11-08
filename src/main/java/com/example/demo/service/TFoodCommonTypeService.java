package com.example.demo.service;

import com.example.demo.dto.TreeDto;
import com.example.demo.model.TFoodCommonType;
import com.example.demo.core.universal.Service;
import com.santint.core.web.query.QueryFilter;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
* @Description: TFoodCommonTypeService接口
* @author zf
* @date 2018/11/02 13:45
*/
public interface TFoodCommonTypeService extends Service<TFoodCommonType> {


    List<TFoodCommonType> getAll(Map map);

    List<TFoodCommonType>  getFoodFiledLevel(Map map);

    List<TreeDto> getTreeDto(Map map);

    List findByCode(Map map);

    Integer findByCount(Map map);


    void createJsonAndFile();
}