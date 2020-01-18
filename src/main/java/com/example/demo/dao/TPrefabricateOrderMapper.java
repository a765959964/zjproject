package com.example.demo.dao;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.TPrefabricateOrder;

import java.util.List;
import java.util.Map;

public interface TPrefabricateOrderMapper extends Mapper<TPrefabricateOrder> {

    /**
     * 根据条件查询当前预制单是否存在菜品信息
     * @param params
     * @return
     */
   Integer  getMap(Map params);

    /**
     * 更新预制单信息
     * @param tPrefabricateOrder
     * @return
     */
   Integer  updatePrefabricate(TPrefabricateOrder tPrefabricateOrder);

    /**
     * 批量修改预制单信息
     * @param tPrefabricateOrder
     * @return
     */
   Integer batchUpdate(TPrefabricateOrder tPrefabricateOrder);


    /**
     * 查询预售列表详情
     * @param params
     * @return
     */
    List getPrefabricateList(Map params);

    /**
     * 查看单个预制信息
     * @param params
     * @return
     */
    TPrefabricateOrder getPrefabricate(Map params);


    /**
     * 更新预制单信息
     * @param params
     */
    Integer updatePrefabricate(Map params);


    /**
     * 查看剩余份数
     * @param params
     * @return
     */
    List getRemainNum(Map params);

}