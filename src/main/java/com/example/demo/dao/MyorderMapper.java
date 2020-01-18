package com.example.demo.dao;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.Myorder;

import java.util.List;
import java.util.Map;

public interface MyorderMapper extends Mapper<Myorder> {


    /**
     * 查询订单详情
     * @param params
     * @return
     */
    List getOrderList(Map params);

    /**
     *  订单
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