package com.example.demo.service.impl;

import com.example.demo.dao.SysResourceMapper;
import com.example.demo.model.SysResource;
import com.example.demo.service.SysResourceService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @Description: SysResourceService接口实现类
* @author zf
* @date 2018/09/30 09:42
*/
@Service
public class SysResourceServiceImpl extends AbstractService<SysResource> implements SysResourceService {

    @Resource
    private SysResourceMapper sysResourceMapper;

}