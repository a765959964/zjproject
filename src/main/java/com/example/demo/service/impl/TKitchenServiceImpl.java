package com.example.demo.service.impl;

import com.example.demo.dao.TKitchenMapper;
import com.example.demo.model.TKitchen;
import com.example.demo.service.TKitchenService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: TKitchenService接口实现类
* @author zf
* @date 2019/03/15 16:38
*/
@Service
public class TKitchenServiceImpl extends AbstractService<TKitchen> implements TKitchenService {

    @Resource
    private TKitchenMapper tKitchenMapper;

    @Override
    public List<TKitchen> getAll(Map map){
        return tKitchenMapper.getAll(map);
    }

}