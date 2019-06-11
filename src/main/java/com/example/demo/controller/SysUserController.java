package com.example.demo.controller;

import com.example.demo.core.aop.AnnotationLog;
import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.ServiceException;
import com.example.demo.model.*;
import com.example.demo.service.SysDeptService;
import com.example.demo.service.SysRoleService;
import com.example.demo.service.SysUserRoleService;
import com.example.demo.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santint.core.web.query.QueryFilter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description: SysUserController类
* @author zf
* @date 2018/10/08 09:36
*/
@Controller
@RequestMapping("/sys/user/")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysDeptService sysDeptService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    @Resource
    private SysRoleService sysRoleService;

    @AnnotationLog("用户登录")
    @GetMapping("/login")
    @ResponseBody
    public RetResult<SysUser> login(String username, String password) {
        Subject currentUser = SecurityUtils.getSubject();
        //登录
        try {
            currentUser.login(new UsernamePasswordToken(username, password));
        }catch (IncorrectCredentialsException i){
            throw new ServiceException("密码输入错误");
        }catch (UnknownAccountException e){
            throw new ServiceException("没有找到此账户"+username);
        }
        //从session取出用户信息
        SysUser user = (SysUser) currentUser.getPrincipal();
        return RetResponse.makeOKRsp(user);
    }

    @RequiresPermissions("sys:user:user")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/user/userList");
        return mv;
    }


    @RequestMapping(value = "/listViews",method = RequestMethod.GET)
    public String listViews() throws Exception {
       /* ModelAndView mv = new ModelAndView();
        mv.setViewName("");*/
        return "views/user/userLists";
    }

    @AnnotationLog("添加用户")
    @RequiresPermissions("sys:user:add")
    @RequestMapping(value = "/userAdd",method = RequestMethod.GET)
    public ModelAndView userAdd(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/user/userAdd");
        return mv;
    }

    @AnnotationLog("保存用户")
    @RequiresPermissions("sys:user:add")
    @PostMapping("/insert")
    @ResponseBody
    public RetResult<Integer> insert(SysUser sysUser,String roleIds) throws Exception{

        Integer state = sysUserService.insert(sysUser);
        SysUser sysUser1 = sysUserService.selectOne(sysUser);
        if(roleIds != null){
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(sysUser1.getId());
            String [] roles = roleIds.split(",");
            for(int i=0;i<roles.length;i++){
                sysUserRole.setRoleId(Integer.parseInt(roles[i]));
                sysUserRoleService.insert(sysUserRole);
            }
        }
        return RetResponse.makeOKRsp(state);
    }

    @AnnotationLog("删除用户")
    @RequiresPermissions("sys:user:remove")
    @PostMapping("/deleteById")
    @ResponseBody
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = sysUserService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @AnnotationLog("更新用户")
    @RequiresPermissions("sys:user:edit")
    @PostMapping("/update")
    @ResponseBody
    public RetResult<Integer> update(SysUser sysUser,String roleIds) throws Exception {
        String[] ids = roleIds.split(",");
        Map params = new HashMap();
        params.put("userId",sysUser.getId());
        if(!ids.equals("")){
            for (String roleId: ids) {
                params.put("roleId",roleId);
                //roleId 是否存在，存在的话，删除，不存在的话，添加
                List<SysUserRole> sysUserRoleList  =  sysUserRoleService.getByUserIdAndRoleId(params);
                if(sysUserRoleList.size() > 0){
                    sysUserRoleService.deleteByUserIdOrRoleId(params);
                }else{
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setUserId(sysUser.getId());
                    sysUserRole.setRoleId(Integer.parseInt(roleId));
                    sysUserRoleService.insert(sysUserRole);
                }
            }
        }
        Integer state = sysUserService.update(sysUser);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public RetResult<SysUser> selectById(@RequestParam String id) throws Exception {
        SysUser sysUser = sysUserService.selectById(id);
        return RetResponse.makeOKRsp(sysUser);
    }
    @RequiresPermissions("sys:user:user")
    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        SysUser sysUser = sysUserService.selectById(id);

        Map params = new HashMap<>();
        StringBuilder sb  = new StringBuilder();
        sb.append("<select  name=\"roleIds\"\n" +
                "                   xm-select-show-count=\"2\" xm-select=\"select1\">");
        List<SysRole> roleList = sysRoleService.getAll();
        List<String> roleIds = sysUserRoleService.getRoleIdsByUserId(id);
        for (SysRole  role: roleList){
            sb.append("<option value='"+role.getId()+"' ");
            if(roleIds.size() > 0){
                for (String roleId : roleIds){
                    if(roleId.equals(role.getId()+"")){
                        sb.append(" selected=\"selected\"");
                    }
                }
            }
            sb.append("> "+role.getRoleName()+"</option>");
        }
        sb.append("</select>");
        if(sysUser.getDeptId() != null){
            SysDept sysdept =  sysDeptService.selectById(sysUser.getDeptId().toString());
            mv.addObject("deptName",sysdept.getName());
        }else{
            mv.addObject("deptName","");
        }
        mv.setViewName("views/system/user/userEdit");
        mv.addObject("sysUser",sysUser).addObject("sb",sb);
        return mv;
    }


    /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn RetResult<PageInfo<SysUser>>
	*/
    @PostMapping("/list")
    public RetResult<PageInfo<SysUser>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<SysUser> list = sysUserService.selectAll();
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }

    /**
     * lay ui 分页
     * @param page 当前页
     * @param limit 每页条数
     * @return
     */
    @RequiresPermissions("sys:user:user")
    @RequestMapping("/getAll")
    @ResponseBody
    public LayuiResult<SysUser> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
                                      @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        map.put("deptId","0");

        if(filter.getFilters().get("deptId")!=null){
            String deptId = filter.getFilters().get("deptId").toString();
            map.put("deptId",deptId);
        }
        if(filter.getFilters().get("name")!=null){
            String name =  filter.getFilters().get("name").toString();
            map.put("name",name);
        }else if(filter.getFilters().get("address")!= null){
            String address = filter.getFilters().get("address").toString();
            map.put("address",address);
        }else if(filter.getFilters().get("deptId")!=null){
            String deptId = filter.getFilters().get("deptId").toString();
            map.put("deptId",deptId);
        }

        List<SysUser> list =sysUserService.getAll(map);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


}