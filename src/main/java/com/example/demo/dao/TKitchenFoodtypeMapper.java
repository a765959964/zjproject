package com.example.demo.dao;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.TKitchenFoodtype;

public interface TKitchenFoodtypeMapper extends Mapper<TKitchenFoodtype> {


    /**
     * 根据门店id 获得门店信息
     * @param kitchenId
     * @return
     */
    TKitchenFoodtype  getByKitId(String kitchenId);
}