package com.example.demo.controller;

import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.ret.ServiceException;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.core.utils.TreeList;
import com.example.demo.dto.TreeListDto;
import com.example.demo.model.SysMenu;
import com.example.demo.model.SysUser;
import com.example.demo.model.UserInfo;
import com.example.demo.service.SysMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santint.core.util.JSonUtils;
import com.santint.core.web.query.QueryFilter;
import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
* @Description: SysMenuController类
* @author zf
* @date 2018/10/30 10:48
*/
@RestController
@RequestMapping("/sysmenu")
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;



    @RequestMapping(value = "/listView",method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/menu/menuList");
        return mv;
    }

    @PostMapping("/insert")
    public RetResult<Integer> insert(SysMenu sysMenu) throws Exception{
    // sysMenu.setId(ApplicationUtils.getUUID());
    	Integer state = sysMenuService.insert(sysMenu);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = sysMenuService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public RetResult<Integer> update(SysMenu sysMenu) throws Exception {
        Integer state = sysMenuService.update(sysMenu);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public RetResult<SysMenu> selectById(@RequestParam String id) throws Exception {
        SysMenu sysMenu = sysMenuService.selectById(id);
        return RetResponse.makeOKRsp(sysMenu);
    }


    @RequestMapping(value = "/getParentId",method = RequestMethod.GET)
    public ModelAndView getParentId(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        SysMenu sysMenu =sysMenuService.selectById(id);
        mv.setViewName("views/system/menu/menuAdd");
        mv.addObject("sysMenu",sysMenu);
        return mv;
    }







    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        SysMenu sysMenu =sysMenuService.selectById(id);
        mv.setViewName("views/sysMenu/sysMenuEdit");
        mv.addObject("sysMenu",sysMenu);
        return mv;
    }

    @RequestMapping(value = "/getByIdEdit",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getByIdEdit(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        SysMenu sysMenu =sysMenuService.selectById(id);
        mv.addObject("sysMenu",sysMenu);
        if(sysMenu.getPid()==0){
            mv.addObject("pname","根目录");
        }else{
            SysMenu sm =sysMenuService.selectById(sysMenu.getPid().toString());
            mv.addObject("pname",sm.getName());
        }
        mv.setViewName("views/system/menu/menuEdit");
        return mv;
    }


   /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn RetResult<PageInfo<SysMenu>>
	*/
    @PostMapping("/list")
    public RetResult<PageInfo<SysMenu>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<SysMenu> list = sysMenuService.selectAll();
        PageInfo<SysMenu> pageInfo = new PageInfo<SysMenu>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }


   /**
    * lay ui 分页
    * @param page 当前页
    * @param limit 每页条数
    * @return
    */
    @RequestMapping("/getAll")
    @ResponseBody
    public LayuiResult<SysMenu> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List<SysMenu> list = sysMenuService.getAll(map);
        PageInfo<SysMenu> pageInfo = new PageInfo<SysMenu>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }



    @RequestMapping("/getTreeTable")
    @ResponseBody
    public LayuiResult<SysMenu> getTreeTable(HttpServletRequest request){
        HashMap map = new HashMap();
     /*   Subject subject = SecurityUtils.getSubject();
        SysUser userInfo = (SysUser) subject.getPrincipal();
        if(userInfo == null){
            throw  new ServiceException("没有登录");
        }

        System.out.println(userInfo.getId());*/
        List<SysMenu> list = sysMenuService.getTreeTable();
        PageInfo<SysMenu> pageInfo = new PageInfo<SysMenu>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


    @RequestMapping("/getTreeList")
    @ResponseBody
    public  Map getTreeList(){
        Map params = new HashMap();
        List<TreeListDto> list = sysMenuService.getTreeList();
        Map<String,List> mapList = new HashMap<>();
        mapList.put("list",list);
        params.put("code",0);
        params.put("msg","成功");
        params.put("data",mapList);
        return params;
    }







}