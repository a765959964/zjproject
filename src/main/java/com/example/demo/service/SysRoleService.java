package com.example.demo.service;

import com.example.demo.model.SysRole;
import com.example.demo.core.universal.Service;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
* @Description: SysRoleService接口
* @author zf
* @date 2018/10/09 09:37
*/
public interface SysRoleService extends Service<SysRole> {
    List getRoleMenu();
}