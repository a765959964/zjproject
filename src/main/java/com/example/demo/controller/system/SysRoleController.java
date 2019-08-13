package com.example.demo.controller.system;

import com.example.demo.core.aop.AnnotationLog;
import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.dto.TreeListDto;
import com.example.demo.model.SysRole;
import com.example.demo.model.SysRoleMenu;
import com.example.demo.service.SysMenuService;
import com.example.demo.service.SysRoleMenuService;
import com.example.demo.service.SysRoleService;
import com.example.demo.service.SysUserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santint.core.util.StringUtil;
import com.santint.core.web.query.QueryFilter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description: SysRoleController类
* @author zf
* @date 2018/10/09 09:37
*/
@RestController
@RequestMapping("/sys/role/")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;


    @Autowired
    private SysMenuService sysMenuService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @RequiresPermissions("sys:role:role")
    @AnnotationLog("访问角色管理页面")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/role/roleList");
        return mv;
    }

    @AnnotationLog("添加角色")
    @RequiresPermissions("sys:role:add")
    @RequestMapping(value = "/roleAdd",method = RequestMethod.GET)
    public ModelAndView roleAdd(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/role/roleAdd");
        return mv;
    }

    @AnnotationLog("保存角色")
    @RequiresPermissions("sys:role:add")
    @PostMapping("/insert")
    @ResponseBody
    public RetResult<Integer> insert(SysRole sysRole,String roleMenu) throws Exception{
        //sysRole.setId(ApplicationUtils.getUUID());

        Integer state = sysRoleService.insert(sysRole);
        SysRole sr = sysRoleService.selectOne(sysRole);
//        System.out.println("获得id值:"+sr.getId());
        System.out.println(roleMenu=="" +"==="+roleMenu.equals(""));
        if(!roleMenu.equals("")) {
            String[] s = roleMenu.split(",");
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(sr.getId());
            for (String s1 : s) {
                sysRoleMenu.setMenuId(Integer.parseInt(s1));
                sysRoleMenuService.insert(sysRoleMenu);
            }
        }
        return RetResponse.makeOKRsp(state);
    }

    @AnnotationLog("删除角色")
    @RequiresPermissions("sys:role:remove")
    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = sysRoleService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @AnnotationLog("更新角色")
    @RequiresPermissions("sys:role:edit")
    @PostMapping("/update")
    public RetResult<Integer> update(SysRole sysRole,String roleMenu) throws Exception {
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        sysRoleMenuService.deleteByRoleId(sysRole.getId()+"");
        Integer state = sysRoleService.update(sysRole);
        sysRoleMenu.setRoleId(sysRole.getId());
        if(roleMenu!="") {
            String[] menuIds = roleMenu.split(",");
            for(int i=0;i<menuIds.length;i++){
                sysRoleMenu.setMenuId(Integer.parseInt(menuIds[i]));
                sysRoleMenuService.insert(sysRoleMenu);
            }
        }
        return RetResponse.makeOKRsp(state);
    }

    @GetMapping("/selectById")
    public RetResult<SysRole> selectById(@RequestParam String id) throws Exception {
        SysRole sysRole = sysRoleService.selectById(id);
        return RetResponse.makeOKRsp(sysRole);
    }

    @RequiresPermissions("sys:role:role")
    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        SysRole sysRole =sysRoleService.selectById(id);
        mv.setViewName("views/system/role/roleEdit");
        mv.addObject("sysRole",sysRole).addObject("roleId",id);
        return mv;
    }

    @RequiresPermissions("sys:role:role")
    @RequestMapping(value = "/findByRoleId",method = RequestMethod.GET)
    @ResponseBody
    public Map findByRoleId(String roleId){
        Map map = new HashMap();
        Map<String,List> listMap = new HashMap<>();
        List checkedList = sysRoleMenuService.findByRoleId(roleId);
        List<TreeListDto> treeList =sysMenuService.getTreeList();
        listMap.put("list",treeList);
        listMap.put("checkedAlias",checkedList);
        map.put("code",0);
        map.put("msg","获取成功");
        map.put("data",listMap);
        return map;
    }


   /**
    * lay ui 分页
    * @param page 当前页
    * @param limit 每页条数
    * @return
    */
    @RequiresPermissions("sys:role:role")
    @RequestMapping("/getAll")
    @ResponseBody
    public LayuiResult<SysRole> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);

        if(filter.getFilters().get("id")!=null){
            String id = filter.getFilters().get("id").toString();
            map.put("id",id);
        }

        if(filter.getFilters().get("roleName")!=null){
            String roleName = filter.getFilters().get("roleName").toString();
            map.put("roleName",roleName);
        }

        List<SysRole> list = sysRoleService.getAll(map);
        PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }

    @RequiresPermissions("sys:role:role")
    @RequestMapping("/getRoleMenu")
    @ResponseBody
    public List getRoleMenu(){
      return  sysRoleService.getRoleMenu();
    }

    @RequiresPermissions("sys:role:role")
    @RequestMapping("/getRoleList")
    @ResponseBody
    public Map  getRoleList(String userId){
        Map map = new HashMap();
        List<Map> mapList = new ArrayList<>();
        Map<String,String> params = null;
        List<SysRole> roleList = sysRoleService.getAll();

        map.put("code",0);
        map.put("msg","success");
        if(StringUtil.isNotEmpty(userId)){
            List<String> roleIds = sysUserRoleService.getRoleIdsByUserId(userId);
                for (SysRole  role: roleList){  //先循环所有角色
                    for (String roleId : roleIds){      //循环用户对应的角色
                        params = new HashMap<>();
                        params.put("value",role.getId()+"");
                        params.put("name",role.getRoleName());
                        if(roleId.equals(role.getId()+"")){
                            params.put("selected" ,"selected");
                        }else{
                            params.put("selected" ,"");
                        }
                        params.put("disabled","");
                        mapList.add(params);
                    }
                }
            map.put("data",mapList);
        }else{
            for (SysRole  role: roleList){
                params = new HashMap<>();
                params.put("value",role.getId()+"");
                params.put("name",role.getRoleName());
                mapList.add(params);
            }
            map.put("data",mapList);
        }
        return map;
    }
}