package com.example.demo.service.impl;

import com.example.demo.dao.db1.SysRoleMapper;
import com.example.demo.model.SysRole;
import com.example.demo.service.SysRoleService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
}