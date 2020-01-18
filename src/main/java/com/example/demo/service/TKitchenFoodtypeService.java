package com.example.demo.service;

import com.example.demo.core.universal.Service;
import com.example.demo.model.TKitchenFoodtype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description: TKitchenFoodtypeService接口
* @author zf
* @date 2019/03/14 16:57
*/
public interface TKitchenFoodtypeService extends Service<TKitchenFoodtype> {


    List<TKitchenFoodtype> getAll(Map map);

    TKitchenFoodtype getByKitId(String kitchenId);

    List getFoodTypeList(HashMap map);


    /**
     * 获取分类信息
     * @return
     */
    List getTypeList();
}