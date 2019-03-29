package com.example.demo.service.impl;

import com.example.demo.dao.TKitchenFoodtypeMapper;
import com.example.demo.model.TKitchenFoodtype;
import com.example.demo.service.TKitchenFoodtypeService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: TKitchenFoodtypeService接口实现类
* @author zf
* @date 2019/03/14 16:57
*/
@Service
public class TKitchenFoodtypeServiceImpl extends AbstractService<TKitchenFoodtype> implements TKitchenFoodtypeService {

    @Resource
    private TKitchenFoodtypeMapper tKitchenFoodtypeMapper;

    @Override
    public List<TKitchenFoodtype> getAll(Map map){
        return tKitchenFoodtypeMapper.getAll(map);
    }

    @Override
    public TKitchenFoodtype getByKitId(String kitchenId) {
        return tKitchenFoodtypeMapper.getByKitId(kitchenId);
    }

}