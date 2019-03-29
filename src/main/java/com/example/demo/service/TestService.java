package com.example.demo.service;

import com.example.demo.model.Test;
import com.example.demo.core.universal.Service;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
* @Description: TestService接口
* @author zf
* @date 2019/03/15 15:21
*/
public interface TestService extends Service<Test> {


    List<Test> getAll(Map map);
}