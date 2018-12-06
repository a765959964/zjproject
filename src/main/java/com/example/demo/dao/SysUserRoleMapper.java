package com.example.demo.dao;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.SysUserRole;

import java.util.List;
import java.util.Map;

public interface SysUserRoleMapper extends Mapper<SysUserRole> {



    List<SysUserRole> getByUserIdAndRoleId(Map params);

    /**
     * 根据用户id获取 角色值
     * @param usereId
     * @return
     */
    List<String> getRolesByUserId(String usereId);

    /**
     * 根据用户id 获取 角色ids
     * @param userId
     * @return
     */
    List<String> getRoleIdsByUserId(String userId);

    /**
     * 删除用户id 或者角色id
     * @param map
     */
    void deleteByUserIdOrRoleId(Map map);
}