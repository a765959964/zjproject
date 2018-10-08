package com.example.demo.service.impl;

import com.example.demo.dao.SysUserMapper;
import com.example.demo.model.Person;
import com.example.demo.model.SysUser;
import com.example.demo.service.SysUserService;
import com.example.demo.core.universal.AbstractService;
import com.santint.core.web.query.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: SysUserService接口实现类
* @author zf
* @date 2018/10/08 09:36
*/
@Service
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;


    @Override
    public List<SysUser> getAll(Map map) {
        return sysUserMapper.getAll(map);
    }
}