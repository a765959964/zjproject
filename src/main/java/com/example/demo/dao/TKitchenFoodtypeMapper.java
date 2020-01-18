package com.example.demo.dao;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.TKitchenFoodtype;

import java.util.HashMap;
import java.util.List;

public interface TKitchenFoodtypeMapper extends Mapper<TKitchenFoodtype> {


    /**
     * 根据门店id 获得门店信息
     * @param kitchenId
     * @return
     */
    TKitchenFoodtype  getByKitId(String kitchenId);

    /**
     * 获取门店菜品信息
     * @param map
     * @return
     */
    List getFoodTypeList(HashMap map);

    /**
     * 获取分类信息
     * @return
     */
    List getTypeList();
}