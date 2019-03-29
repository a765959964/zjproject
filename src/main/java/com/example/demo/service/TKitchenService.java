package com.example.demo.service;

import com.example.demo.model.TKitchen;
import com.example.demo.core.universal.Service;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
* @Description: TKitchenService接口
* @author zf
* @date 2019/03/15 16:38
*/
public interface TKitchenService extends Service<TKitchen> {


    List<TKitchen> getAll(Map map);
}