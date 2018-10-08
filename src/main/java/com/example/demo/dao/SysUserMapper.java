package com.example.demo.dao;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.Person;
import com.example.demo.model.SysUser;
import com.santint.core.web.query.QueryFilter;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser> {


    List<SysUser> getAll(Map map);
}