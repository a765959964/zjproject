package com.example.demo.controller.system;

import com.example.demo.core.aop.AnnotationLog;
import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.model.SystemLog;
import com.example.demo.service.SystemLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santint.core.web.query.QueryFilter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
* @Description: SystemLogController类
* @author zf
* @date 2019/06/11 11:05
*/
@RestController
@RequestMapping("/sys/systemLog")
public class SystemLogController {

    @Resource
    private SystemLogService systemLogService;

    @RequiresPermissions("sys:log:log")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/systemLog/systemLogList");
        return mv;
    }

    /**
     * 添加页面
     **/
    @RequiresPermissions("sys:log:add")
    @RequestMapping(value = "/systemLogAdd",method = RequestMethod.GET)
    public ModelAndView systemLogAdd() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/systemLog/systemLogAdd");
        return mv;
    }

    @AnnotationLog("添加操作")
    @RequiresPermissions("sys:log:add")
    @PostMapping("/insert")
    public RetResult<Integer> insert(SystemLog systemLog) throws Exception{
    // systemLog.setId(ApplicationUtils.getUUID());
    	Integer state = systemLogService.insert(systemLog);
        return RetResponse.makeOKRsp(state);
    }


    /**
    * 批量删除
    * @param ids [1,2,3]
    * @return
    */
    @AnnotationLog("进行批量删除")
    @RequiresPermissions("sys:log:batchRemove")
    @PostMapping("/batchRemove")
    public RetResult<Integer> batchRemove(String ids) throws Exception{
        Integer state = systemLogService.deleteByIds(ids);
        return RetResponse.makeOKRsp(state);
    }

    @AnnotationLog("进行删除操作")
    @RequiresPermissions("sys:log:remove")
    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = systemLogService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @AnnotationLog("更新操作")
    @RequiresPermissions("sys:log:edit")
    @PostMapping("/update")
    public RetResult<Integer> update(SystemLog systemLog) throws Exception {
        Integer state = systemLogService.update(systemLog);
        return RetResponse.makeOKRsp(state);
    }

    @GetMapping("/selectById")
    public RetResult<SystemLog> selectById(@RequestParam String id) throws Exception {
        SystemLog systemLog = systemLogService.selectById(id);
        return RetResponse.makeOKRsp(systemLog);
    }

    @RequiresPermissions("sys:log:edit")
    @GetMapping("/getById")
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        SystemLog systemLog =systemLogService.selectById(id);
        mv.setViewName("views/system/systemLog/systemLogEdit");
        mv.addObject("systemLog",systemLog);
        return mv;
    }

   /**
	* @Description: 分页查询
	* @Reutrn RetResult<PageInfo<SystemLog>>
	*/
    @GetMapping("/list")
    public RetResult<List<SystemLog>> list() throws Exception {
        List<SystemLog> list = systemLogService.selectAll();
        return RetResponse.makeOKRsp(list);
    }


   /**
    * lay ui 分页
    * @param page 当前页
    * @param limit 每页条数
    * @return
    */
    @RequiresPermissions("sys:log:log")
    @GetMapping("/getAll")
    public LayuiResult<SystemLog> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List<SystemLog> list = systemLogService.getAll(map);
        PageInfo<SystemLog> pageInfo = new PageInfo<SystemLog>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


}