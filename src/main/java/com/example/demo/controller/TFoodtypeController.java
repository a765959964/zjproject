package com.example.demo.controller;

import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.model.TFoodtype;
import com.example.demo.service.TFoodtypeService;
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
* @Description: TFoodtypeController类
* @author zf
* @date 2019/03/19 11:42
*/
@RestController
@RequestMapping("/sys/tFoodtype")
public class TFoodtypeController {

    @Resource
    private TFoodtypeService tFoodtypeService;

    @RequiresPermissions("sys:tFoodtype:tFoodtype")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/tFoodtype/tFoodtypeList");
        return mv;
    }

    /**
     * 添加页面
     **/
    @RequestMapping(value = "/tFoodtypeAdd",method = RequestMethod.GET)
    public ModelAndView tFoodtypeAdd() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/tFoodtype/tFoodtypeAdd");
        return mv;
    }



    @PostMapping("/insert")
    public RetResult<Integer> insert(TFoodtype tFoodtype) throws Exception{
    // tFoodtype.setId(ApplicationUtils.getUUID());
    	Integer state = tFoodtypeService.insert(tFoodtype);
        return RetResponse.makeOKRsp(state);
    }


    /**
    * 批量删除
    * @param ids [1,2,3]
    * @return
    */
    @RequiresPermissions("sys:tFoodtype:batchRemove")
    @PostMapping("/batchRemove")
    public RetResult<Integer> batchRemove(String ids) throws Exception{
        Integer state = tFoodtypeService.deleteByIds(ids);
        return RetResponse.makeOKRsp(state);
    }

    @RequiresPermissions("sys:tFoodtype:remove")
    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = tFoodtypeService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public RetResult<Integer> update(TFoodtype tFoodtype) throws Exception {
        Integer state = tFoodtypeService.update(tFoodtype);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public RetResult<TFoodtype> selectById(@RequestParam String id) throws Exception {
        TFoodtype tFoodtype = tFoodtypeService.selectById(id);
        return RetResponse.makeOKRsp(tFoodtype);
    }


    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        TFoodtype tFoodtype =tFoodtypeService.selectById(id);
        mv.setViewName("views/system/tFoodtype/tFoodtypeEdit");
        mv.addObject("tFoodtype",tFoodtype);
        return mv;
    }

   /**
	* @Description: 分页查询
	* @Reutrn RetResult<PageInfo<TFoodtype>>
	*/
    @GetMapping("/list")
    public RetResult<List<TFoodtype>> list() throws Exception {
        List<TFoodtype> list = tFoodtypeService.selectAll();
        return RetResponse.makeOKRsp(list);
    }


   /**
    * lay ui 分页
    * @param page 当前页
    * @param limit 每页条数
    * @return
    */
    @RequestMapping("/getAll")
    @ResponseBody
    public LayuiResult<TFoodtype> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List<TFoodtype> list = tFoodtypeService.getAll(map);
        PageInfo<TFoodtype> pageInfo = new PageInfo<TFoodtype>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


}