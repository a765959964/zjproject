package com.example.demo.controller;

import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.ret.RetResult;
import com.example.demo.model.Person;
import com.example.demo.model.SysUser;
import com.example.demo.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santint.core.web.query.QueryFilter;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
* @Description: SysUserController类
* @author zf
* @date 2018/10/08 09:36
*/
@RestController
@RequestMapping("/sysuser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @PostMapping("/insert")
    public RetResult<Integer> insert(SysUser sysUser) throws Exception{
    // sysUser.setId(ApplicationUtils.getUUID());
    	Integer state = sysUserService.insert(sysUser);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = sysUserService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public RetResult<Integer> update(SysUser sysUser) throws Exception {
        Integer state = sysUserService.update(sysUser);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public RetResult<SysUser> selectById(@RequestParam String id) throws Exception {
        SysUser sysUser = sysUserService.selectById(id);
        return RetResponse.makeOKRsp(sysUser);
    }

    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        SysUser sysUser = sysUserService.selectById(id);
        mv.setViewName("views/user/userEdit");
        mv.addObject("sysUser",sysUser);
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
    @RequestMapping("/getAll")
    @ResponseBody
    public LayuiResult<SysUser> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
                                      @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List<SysUser> list =sysUserService.getAll(map);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


}