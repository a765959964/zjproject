package com.example.demo.controller.system;

import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.model.SysUserRole;
import com.example.demo.service.SysUserRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santint.core.web.query.QueryFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
* @Description: SysUserRoleController类
* @author zf
* @date 2018/10/31 13:52
*/
@RestController
@RequestMapping("/sysUserRole")
public class SysUserRoleController {

    @Resource
    private SysUserRoleService sysUserRoleService;

    @PostMapping("/insert")
    public RetResult<Integer> insert(SysUserRole sysUserRole) throws Exception{
    // sysUserRole.setId(ApplicationUtils.getUUID());
    	Integer state = sysUserRoleService.insert(sysUserRole);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = sysUserRoleService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public RetResult<Integer> update(SysUserRole sysUserRole) throws Exception {
        Integer state = sysUserRoleService.update(sysUserRole);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public RetResult<SysUserRole> selectById(@RequestParam String id) throws Exception {
        SysUserRole sysUserRole = sysUserRoleService.selectById(id);
        return RetResponse.makeOKRsp(sysUserRole);
    }


    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        SysUserRole sysUserRole =sysUserRoleService.selectById(id);
        mv.setViewName("views/sysUserRole/sysUserRoleEdit");
        mv.addObject("sysUserRole",sysUserRole);
        return mv;
    }

   /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn RetResult<PageInfo<SysUserRole>>
	*/
    @PostMapping("/list")
    public RetResult<PageInfo<SysUserRole>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<SysUserRole> list = sysUserRoleService.selectAll();
        PageInfo<SysUserRole> pageInfo = new PageInfo<SysUserRole>(list);
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
    public LayuiResult<SysUserRole> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List<SysUserRole> list = sysUserRoleService.getAll(map);
        PageInfo<SysUserRole> pageInfo = new PageInfo<SysUserRole>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


}