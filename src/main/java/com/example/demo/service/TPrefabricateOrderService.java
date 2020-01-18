package com.example.demo.service;

import com.example.demo.core.universal.Service;
import com.example.demo.model.TPrefabricateOrder;

import java.util.List;
import java.util.Map;

/**
* @Description: TPrefabricateOrderService接口
* @author zf
* @date 2019/11/06 10:20
*/
public interface TPrefabricateOrderService extends Service<TPrefabricateOrder> {


    List<TPrefabricateOrder> getAll(Map map);

    Integer  getMap(Map params);

    Integer  updatePrefabricate(TPrefabricateOrder tPrefabricateOrder);

    List getPrefabricateList(Map params);

    /**
     * 批量修改预制单信息
     * @param tPrefabricateOrder
     * @return
     */
    Integer batchUpdate(TPrefabricateOrder tPrefabricateOrder);


    /**
     * 查看剩余份数
     * @param params
     * @return
     */
    List getRemainNum(Map params);

    /**
     * 查看单个预制信息
     * @param params
     * @return
     */
    TPrefabricateOrder getPrefabricate(Map params);

    /**
     * 自动生成预制单信息
     * @param kitchenId
     * @param date
     * @param pmtype
     * @return
     */
    Map execute(String kitchenId,String date,String pmtype);

}