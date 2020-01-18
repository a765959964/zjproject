package com.example.demo.service.impl;

import com.example.demo.core.universal.AbstractService;
import com.example.demo.dao.MyorderMapper;
import com.example.demo.model.Myorder;
import com.example.demo.service.MyorderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: MyorderService接口实现类
* @author zf
* @date 2019/12/18 16:34
*/
@Service
public class MyorderServiceImpl extends AbstractService<Myorder> implements MyorderService {

    @Resource
    private MyorderMapper myorderMapper;

    @Override
    public List<Myorder> getAll(Map map){
        return myorderMapper.getAll(map);
    }

    @Override
    public List getOrderList(Map params) {
        return myorderMapper.getOrderList(params);
    }

    @Override
    public List getOrderListCount(Map params) {
        return myorderMapper.getOrderListCount(params);
    }

    @Override
    public Integer getByOrderTypeCount(Map params) {
        return myorderMapper.getByOrderTypeCount(params);
    }

}