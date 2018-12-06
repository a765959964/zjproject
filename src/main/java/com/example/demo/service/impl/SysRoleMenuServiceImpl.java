package com.example.demo.service.impl;

import com.example.demo.dao.SysRoleMenuMapper;
import com.example.demo.model.SysRoleMenu;
import com.example.demo.service.SysRoleMenuService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: SysRoleMenuService接口实现类
* @author zf
* @date 2018/12/04 10:38
*/
@Service
public class SysRoleMenuServiceImpl extends AbstractService<SysRoleMenu> implements SysRoleMenuService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysRoleMenu> getAll(Map map){
        return sysRoleMenuMapper.getAll(map);
    }

    @Override
    public List<String> findByRoleId(String roleId) {
        return sysRoleMenuMapper.findByRoleId(roleId);
    }

    @Override
    public void deleteByRoleId(String roleId) {
        sysRoleMenuMapper.deleteByRoleId(roleId);
    }


}