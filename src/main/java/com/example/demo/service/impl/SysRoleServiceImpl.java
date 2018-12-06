package com.example.demo.service.impl;

import com.example.demo.core.universal.AbstractService;
import com.example.demo.dao.db1.SysRoleMapper;
import com.example.demo.model.SysRole;
import com.example.demo.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: SysRoleService接口实现类
* @author zf
* @date 2018/05/25 23:01
*/
@Service
public class SysRoleServiceImpl extends AbstractService<SysRole> implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List getRoleMenu() {
        return sysRoleMapper.getRoleMenu();
    }

    @Override
    public List getRoleList() {
        return sysRoleMapper.getRoleList();
    }

    @Override
    public List getRoleListByUserId(String userId) {
        return sysRoleMapper.getRoleListByUserId(userId);
    }

    @Override
    public List<SysRole> getAll() {
        return sysRoleMapper.getAll();
    }
}