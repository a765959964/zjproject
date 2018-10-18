package com.example.demo.dao;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.SysDept;

import java.util.List;

public interface SysDeptMapper extends Mapper<SysDept> {

     List<SysDept> getTreeData();

    SysDept getByPid(Integer pid);

}