package com.example.demo.dao;

import com.example.demo.core.universal.Mapper;
import com.example.demo.dto.TreeDto;
import com.example.demo.model.TFoodCommonType;

import java.util.List;
import java.util.Map;

public interface TFoodCommonTypeMapper extends Mapper<TFoodCommonType> {



     List<TFoodCommonType> getFoodFiledLevel(Map map);


    List<TreeDto>  getTreeDto(Map map);


}