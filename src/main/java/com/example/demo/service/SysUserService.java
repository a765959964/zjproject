package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.model.SysUser;
import com.example.demo.core.universal.Service;
import com.santint.core.web.query.QueryFilter;

import java.util.List;
import java.util.Map;

/**
* @Description: SysUserService接口
* @author zf
* @date 2018/10/08 09:36
*/
public interface SysUserService extends Service<SysUser> {

    List<SysUser> getAll(Map map);

}