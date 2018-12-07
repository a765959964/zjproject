package com.example.demo.service;

import com.example.demo.model.Demo3;
import com.example.demo.core.universal.Service;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
* @Description: Demo3Service接口
* @author zf
* @date 2018/12/07 16:18
*/
public interface Demo3Service extends Service<Demo3> {


    List<Demo3> getAll(Map map);
}