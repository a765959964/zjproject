package com.example.demo.dao.db1;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.SysRole;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper extends Mapper<SysRole> {

    List<SysRole> getAll();

    List getRoleMenu();

    List findByRoleId(String roleId);

    List getRoleList();

    List getRoleListByUserId(String userId);
}