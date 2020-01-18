package com.example.demo.controller.santint;

import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.ret.RetResult;
import com.example.demo.model.TFoodtable;
import com.example.demo.service.TFoodtableService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santint.core.web.query.QueryFilter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
* @Description: TFoodtableController类
* @author zf
* @date 2019/03/14 15:32
*/
@RestController
@RequestMapping("/sys/foodtable")
public class TFoodtableController {

    @Resource
    private TFoodtableService tFoodtableService;

    @RequiresPermissions("sys:foodtable:foodtable")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/foodtable/foodtableList");
        return mv;
    }




    @PostMapping("/insert")
    public RetResult<Integer> insert(TFoodtable tFoodtable) throws Exception{
    // tFoodtable.setId(ApplicationUtils.getUUID());
    	Integer state = tFoodtableService.insert(tFoodtable);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = tFoodtableService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public RetResult<Integer> update(TFoodtable tFoodtable) throws Exception {
        Integer state = tFoodtableService.update(tFoodtable);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public RetResult<TFoodtable> selectById(@RequestParam String id) throws Exception {
        TFoodtable tFoodtable = tFoodtableService.selectById(id);
        return RetResponse.makeOKRsp(tFoodtable);
    }


    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        TFoodtable foodtable =tFoodtableService.getById(id);
        mv.setViewName("views/system/foodtable/foodtableEdit");
        mv.addObject("foodtable",foodtable).addObject("id",id);
        return mv;
    }

   /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn RetResult<PageInfo<TFoodtable>>
	*/
    @PostMapping("/list")
    public RetResult<PageInfo<TFoodtable>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<TFoodtable> list = tFoodtableService.selectAll();
        PageInfo<TFoodtable> pageInfo = new PageInfo<TFoodtable>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }


   /**
    * lay ui 分页
    * @param page 当前页
    * @param limit 每页条数
    * @return
    */
   @RequiresPermissions("sys:foodtable:foodtable")
    @RequestMapping("/getAll")
    @ResponseBody
    public LayuiResult<TFoodtable> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);

       if(filter.getFilters().get("id")!=null){
           String id = filter.getFilters().get("id").toString();
           map.put("id",id);
       }

        if(filter.getFilters().get("foodname")!=null){
            String foodName = filter.getFilters().get("foodname").toString();
            map.put("foodname",foodName);
        }

        map.put("orderField","create_time");
        map.put("sort","desc");
        List<TFoodtable> list = tFoodtableService.getAll(map);
        PageInfo<TFoodtable> pageInfo = new PageInfo<TFoodtable>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


}