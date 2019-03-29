package com.example.demo.service.impl;

import com.example.demo.dao.TFoodtypeMapper;
import com.example.demo.model.TFoodtype;
import com.example.demo.service.TFoodtypeService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: TFoodtypeService接口实现类
* @author zf
* @date 2019/03/19 11:42
*/
@Service
public class TFoodtypeServiceImpl extends AbstractService<TFoodtype> implements TFoodtypeService {

    @Resource
    private TFoodtypeMapper tFoodtypeMapper;

    @Override
    public List<TFoodtype> getAll(Map map){
        return tFoodtypeMapper.getAll(map);
    }

}