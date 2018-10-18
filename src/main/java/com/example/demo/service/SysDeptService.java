package com.example.demo.service;

import com.example.demo.model.SysDept;
import com.example.demo.core.universal.Service;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
* @Description: SysDeptService接口
* @author zf
* @date 2018/10/11 09:36
*/
public interface SysDeptService extends Service<SysDept> {


    List<SysDept> getAll(Map map);

    List<SysDept> getTreeData();

    SysDept getByPid(Integer pid);
}