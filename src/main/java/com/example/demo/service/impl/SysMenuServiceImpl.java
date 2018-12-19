package com.example.demo.service.impl;

import antlr.StringUtils;
import com.example.demo.core.universal.AbstractService;
import com.example.demo.core.utils.TreeList;
import com.example.demo.dao.SysMenuMapper;
import com.example.demo.dto.TreeListDto;
import com.example.demo.model.SysMenu;
import com.example.demo.service.SysMenuService;
import com.santint.core.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    public List<SysMenu> getAll() {
        return sysMenuMapper.getAll();
    }

    @Override
    public List<SysMenu> getAll(Map map){
        return sysMenuMapper.getAll(map);
    }

    @Override
    public List<SysMenu> getTreeTable() {
        return sysMenuMapper.getTreeTable();
    }




    @Override
    public List<SysMenu> getListByUserId(String userId) {
        List<SysMenu> sysMenuList =   sysMenuMapper.getListByUserId(userId);
        List<SysMenu> list = getList(sysMenuList);
        return list;
    }




    private List<SysMenu> getList(List<SysMenu> sysMenuList){

        List<SysMenu> list = new ArrayList();

        for( SysMenu sysMenu : sysMenuList){
            //找到根目录
            if(sysMenu.getPid()==0){
                list.add(sysMenu);
            }
            //找子目录
            for(SysMenu sm :sysMenuList){
                if(sm.getPid()==sysMenu.getId()){
                    if(sysMenu.getChildren()==null){
                        sysMenu.setChildren(new ArrayList<>());
                    }
                    sysMenu.getChildren().add(sm);
                }
            }
        }
        return list;
    }




    @Override
    public List<String> getMenusByUserId(String userId) {
        return sysMenuMapper.getMenusByUserId(userId);
    }

    @Override
    public List<TreeListDto> getTreeList() {
        return sysMenuMapper.getTreeList();
    }

    @Override
    public List<TreeListDto> getByRoleIdTreeList(String roleId) {
        return sysMenuMapper.getByRoleIdTreeList(roleId);
    }


    @Override
    public Set<String> listPerms(String userId) {
        List<String> perms = sysMenuMapper.listUserPerms(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms){
            if(StringUtil.isNotEmpty(perm)){
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}