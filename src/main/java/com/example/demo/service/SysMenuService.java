package com.example.demo.service;

import com.example.demo.core.utils.TreeList;
import com.example.demo.dto.SysMenuDto;
import com.example.demo.dto.TreeListDto;
import com.example.demo.model.SysMenu;
import com.example.demo.core.universal.Service;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* @Description: SysMenuService接口
* @author zf
* @date 2018/10/30 14:12
*/
public interface SysMenuService extends Service<SysMenu> {

    List<SysMenu> getAll();

    List<SysMenu> getAll(Map map);

    List<SysMenu> getTreeTable();

    List<SysMenu> getListByUserId(String userId);

    List<String> getMenusByUserId(String userId);

    List<TreeListDto> getTreeList();

    List<TreeListDto> getByRoleIdTreeList(String roleId);

    Set<String> listPerms(String userId);

}