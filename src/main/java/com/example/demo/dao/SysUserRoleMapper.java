package com.example.demo.dao;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper extends Mapper<SysUserRole> {


    /**
     * 根据用户id获取 角色值
     * @param usereId
     * @return
     */
    List<String> getRolesByUserId(String usereId);
}