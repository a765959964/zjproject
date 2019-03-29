package com.example.demo.service;

import com.example.demo.model.TFoodtype;
import com.example.demo.core.universal.Service;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
* @Description: TFoodtypeService接口
* @author zf
* @date 2019/03/19 11:42
*/
public interface TFoodtypeService extends Service<TFoodtype> {


    List<TFoodtype> getAll(Map map);
}