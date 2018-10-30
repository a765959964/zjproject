package com.example.demo.dao;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.SysMenu;

import java.util.List;

public interface SysMenuMapper extends Mapper<SysMenu> {


    List<SysMenu> getTreeTable();
}