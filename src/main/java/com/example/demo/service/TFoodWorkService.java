package com.example.demo.service;

import com.example.demo.core.universal.Service;
import com.example.demo.model.TFoodWork;

import java.util.List;
import java.util.Map;

/**
* @Description: TFoodWorkService接口
* @author zf
* @date 2020/01/15 09:43
*/
public interface TFoodWorkService extends Service<TFoodWork> {


    List<TFoodWork> getAll(Map map);


    /**
     * 查询工作餐信息
     * @param params
     * @return
     */
    List getFoodWorkList(Map params);


    /**
     * 查询门店菜品信息
     * @param params
     * @return
     */
    List getKitFoodList(Map params);


    /**
     * 获取门店自助餐list
     * @param params
     * @return
     */
    List getKitZzList(Map params);

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