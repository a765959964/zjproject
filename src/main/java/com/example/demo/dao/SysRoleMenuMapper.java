package com.example.demo.dao;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuMapper extends Mapper<SysRoleMenu> {
    List<String> findByRoleId(String roleId);

    void deleteByRoleId(String roleId);

}