package com.example.demo.service;

import com.example.demo.core.universal.Service;
import com.example.demo.model.TFoodtable;

import java.util.List;
import java.util.Map;

/**
* @Description: TFoodtableService接口
* @author zf
* @date 2019/03/14 15:32
*/
public interface TFoodtableService extends Service<TFoodtable> {


    List<TFoodtable> getAll(Map map);

    TFoodtable getById(String id);
}