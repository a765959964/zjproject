package com.example.demo.service.impl;

import com.example.demo.dao.SysRoleResourceMapper;
import com.example.demo.model.SysRoleResource;
import com.example.demo.service.SysRoleResourceService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @Description: SysRoleResourceService接口实现类
* @author zf
* @date 2018/09/30 09:44
*/
@Service
public class SysRoleResourceServiceImpl extends AbstractService<SysRoleResource> implements SysRoleResourceService {

    @Resource
    private SysRoleResourceMapper sysRoleResourceMapper;

}