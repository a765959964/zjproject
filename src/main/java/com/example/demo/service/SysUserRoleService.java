package com.example.demo.service;

import com.example.demo.model.SysUserRole;
import com.example.demo.core.universal.Service;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
* @Description: SysUserRoleService接口
* @author zf
* @date 2018/10/31 13:52
*/
public interface SysUserRoleService extends Service<SysUserRole> {


    List<SysUserRole> getAll(Map map);

    List<String> getRolesByUserId(String userId);

}