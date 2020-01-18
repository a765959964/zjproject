package com.example.demo.dao;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.TFoodWork;

import java.util.List;
import java.util.Map;

public interface TFoodWorkMapper extends Mapper<TFoodWork> {


    /**
     * 查询工作餐信息
     * @param params
     * @return
     */
    List getFoodWorkList(Map params);

    /**
     * 获取门店自助餐list
     * @param params
     * @return
     */
    List getKitZzList(Map params);

    /**
     * 查询门店菜品信息
     * @param params
     * @return
     */
    List getKitFoodList(Map params);

    /**
     * 根据日期删除自助餐信息
     * @param params
     * @return
     */
    Integer delByWorkDate(Map params);


    /**
     * 查询自助餐信息是否存在
     * @param params
     * @return
     */
    Integer getFoodWorkCount(Map params);
}