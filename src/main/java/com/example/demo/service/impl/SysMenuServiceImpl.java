package com.example.demo.service.impl;

import com.example.demo.dao.SysMenuMapper;
import com.example.demo.model.SysMenu;
import com.example.demo.service.SysMenuService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: SysMenuService接口实现类
* @author zf
* @date 2018/10/30 14:12
*/
@Service
public class SysMenuServiceImpl extends AbstractService<SysMenu> implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> getAll(Map map){
        return sysMenuMapper.getAll(map);
    }

    @Override
    public List<SysMenu> getTreeTable() {
        return sysMenuMapper.getTreeTable();
    }

}