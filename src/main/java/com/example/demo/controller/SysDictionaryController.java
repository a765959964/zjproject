package com.example.demo.controller;

import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.model.SysDictionary;
import com.example.demo.service.SysDictionaryService;
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
* @Description: SysDictionaryController类
* @author zf
* @date 2018/12/12 10:02
*/
@RestController
@RequestMapping("/sys/dict")
public class SysDictionaryController {

    @Resource
    private SysDictionaryService sysDictionaryService;

    @RequiresPermissions("sys:dict:dict")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/dict/dictList");
        return mv;
    }


    @RequestMapping(value = "/dictAdd",method = RequestMethod.GET)
    public ModelAndView dictAdd(Model model,String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        if(id != null ){
           SysDictionary sysDictionary =  sysDictionaryService.selectById(id);
           mv.addObject("sysDictionary",sysDictionary);
           mv.setViewName("views/system/dict/dictAddChildren");
        }else{
           mv.setViewName("views/system/dict/dictAdd");
        }
        return mv;
    }

    @RequiresPermissions("sys:dict:batchRemove")
    @PostMapping("/batchRemove")
    public RetResult<Integer> batchRemove(String ids) throws Exception{
        // sysDictionary.setId(ApplicationUtils.getUUID());
        Integer state = sysDictionaryService.deleteByIds(ids);
        return RetResponse.makeOKRsp(state);
    }



    @RequiresPermissions("sys:dict:add")
    @PostMapping("/insert")
    public RetResult<Integer> insert(SysDictionary sysDictionary) throws Exception{
    // sysDictionary.setId(ApplicationUtils.getUUID());
    	Integer state = sysDictionaryService.insert(sysDictionary);
        return RetResponse.makeOKRsp(state);
    }

    @RequiresPermissions("sys:dict:remove")
    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = sysDictionaryService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @RequiresPermissions("sys:dict:edit")
    @PostMapping("/update")
    public RetResult<Integer> update(SysDictionary sysDictionary) throws Exception {
        Integer state = sysDictionaryService.update(sysDictionary);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public RetResult<SysDictionary> selectById(@RequestParam String id) throws Exception {
        SysDictionary sysDictionary = sysDictionaryService.selectById(id);
        return RetResponse.makeOKRsp(sysDictionary);
    }


    @RequiresPermissions("sys:dict:dict")
    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        SysDictionary sysDictionary =sysDictionaryService.selectById(id);
        mv.setViewName("views/system/dict/dictEdit");
        mv.addObject("dict",sysDictionary);
        return mv;
    }

   /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn RetResult<PageInfo<SysDictionary>>
	*/
    @PostMapping("/list")
    public RetResult<PageInfo<SysDictionary>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<SysDictionary> list = sysDictionaryService.selectAll();
        PageInfo<SysDictionary> pageInfo = new PageInfo<SysDictionary>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }


   /**
    * lay ui 分页
    * @param page 当前页
    * @param limit 每页条数
    * @return
    */
    @RequiresPermissions("sys:dict:dict")
    @RequestMapping("/getAll")
    @ResponseBody
    public LayuiResult<SysDictionary> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List<SysDictionary> list = sysDictionaryService.getAll(map);
        PageInfo<SysDictionary> pageInfo = new PageInfo<SysDictionary>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


}