package com.example.demo.service.impl;

import com.example.demo.dao.TFoodtableMapper;
import com.example.demo.model.TFoodtable;
import com.example.demo.service.TFoodtableService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: TFoodtableService接口实现类
* @author zf
* @date 2019/03/14 15:32
*/
@Service
public class TFoodtableServiceImpl extends AbstractService<TFoodtable> implements TFoodtableService {

    @Resource
    private TFoodtableMapper tFoodtableMapper;

    @Override
    public List<TFoodtable> getAll(Map map){
        return tFoodtableMapper.getAll(map);
    }

}