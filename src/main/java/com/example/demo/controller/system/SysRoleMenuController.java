package com.example.demo.controller.system;

import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.model.SysRoleMenu;
import com.example.demo.service.SysRoleMenuService;
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
* @Description: SysRoleMenuController类
* @author zf
* @date 2018/12/04 10:38
*/
@RestController
@RequestMapping("/sysRoleMenu")
public class SysRoleMenuController {

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @PostMapping("/insert")
    public RetResult<Integer> insert(SysRoleMenu sysRoleMenu) throws Exception{
    // sysRoleMenu.setId(ApplicationUtils.getUUID());
    	Integer state = sysRoleMenuService.insert(sysRoleMenu);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = sysRoleMenuService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public RetResult<Integer> update(SysRoleMenu sysRoleMenu) throws Exception {
        Integer state = sysRoleMenuService.update(sysRoleMenu);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public RetResult<SysRoleMenu> selectById(@RequestParam String id) throws Exception {
        SysRoleMenu sysRoleMenu = sysRoleMenuService.selectById(id);
        return RetResponse.makeOKRsp(sysRoleMenu);
    }


    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        SysRoleMenu sysRoleMenu =sysRoleMenuService.selectById(id);
        mv.setViewName("views/sysRoleMenu/sysRoleMenuEdit");
        mv.addObject("sysRoleMenu",sysRoleMenu);
        return mv;
    }

   /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn RetResult<PageInfo<SysRoleMenu>>
	*/
    @PostMapping("/list")
    public RetResult<PageInfo<SysRoleMenu>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<SysRoleMenu> list = sysRoleMenuService.selectAll();
        PageInfo<SysRoleMenu> pageInfo = new PageInfo<SysRoleMenu>(list);
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
    public LayuiResult<SysRoleMenu> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List<SysRoleMenu> list = sysRoleMenuService.getAll(map);
        PageInfo<SysRoleMenu> pageInfo = new PageInfo<SysRoleMenu>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


}