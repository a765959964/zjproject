package com.example.demo.service;

import com.example.demo.model.SysRoleMenu;
import com.example.demo.core.universal.Service;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
* @Description: SysRoleMenuService接口
* @author zf
* @date 2018/12/04 10:38
*/
public interface SysRoleMenuService extends Service<SysRoleMenu> {


    List<SysRoleMenu> getAll(Map map);

    List<String>  findByRoleId(String roleId);

    void deleteByRoleId(String roleId);

 }