package com.example.demo.dao;

import com.example.demo.core.universal.Mapper;
import com.example.demo.core.utils.TreeList;
import com.example.demo.dto.TreeListDto;
import com.example.demo.model.SysMenu;

import java.util.List;

public interface SysMenuMapper extends Mapper<SysMenu> {

    List<SysMenu> getAll();

    List<SysMenu> getTreeTable();

    List<SysMenu> getListByUserId(String userId);


    List<String> getMenusByUserId(String userId);


    List<TreeListDto> getTreeList();

    List<TreeListDto> getByRoleIdTreeList(String roleId);
}