package com.example.demo.service;

import com.example.demo.core.universal.Service;
import com.example.demo.model.Myorder;

import java.util.List;
import java.util.Map;

/**
* @Description: MyorderService接口
* @author zf
* @date 2019/12/18 16:34
*/
public interface MyorderService extends Service<Myorder> {


    List<Myorder> getAll(Map map);



    /**
     * 查询订单详情
     * @param params
     * @return
     */
    List getOrderList(Map params);

    /**
     * 查询所点菜品订单 数量
     * @param params
     * @return
     */
    List getOrderListCount(Map params);

    /**
     * 查询订单总数
     * @param params
     * @return
     */
    Integer getByOrderTypeCount(Map params);
}