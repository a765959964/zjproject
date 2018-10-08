package com.example.demo.service.impl;

import com.example.demo.dao.SysUserRoleMapper;
import com.example.demo.model.SysUserRole;
import com.example.demo.service.SysUserRoleService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @Description: SysUserRoleService接口实现类
* @author zf
* @date 2018/09/30 09:43
*/
@Service
public class SysUserRoleServiceImpl extends AbstractService<SysUserRole> implements SysUserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

}