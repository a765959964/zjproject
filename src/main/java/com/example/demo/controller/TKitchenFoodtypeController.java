package com.example.demo.controller;

import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.model.TKitchenFoodtype;
import com.example.demo.service.TKitchenFoodtypeService;
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
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
* @Description: TKitchenFoodtypeController类
* @author zf
* @date 2019/03/14 16:57
*/
@RestController
@RequestMapping("/sys/foodtype")
public class TKitchenFoodtypeController {

    @Resource
    private TKitchenFoodtypeService tKitchenFoodtypeService;

    @RequiresPermissions("sys:foodtype:foodtype")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/foodtype/foodtypeList");
        return mv;
    }

    @PostMapping("/insert")
    public RetResult<Integer> insert(TKitchenFoodtype tKitchenFoodtype) throws Exception{
    // tKitchenFoodtype.setId(ApplicationUtils.getUUID());
        tKitchenFoodtype.setFoodSort(1);
    	Integer state = tKitchenFoodtypeService.insert(tKitchenFoodtype);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = tKitchenFoodtypeService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public RetResult<Integer> update(TKitchenFoodtype tKitchenFoodtype) throws Exception {
        Integer state = tKitchenFoodtypeService.update(tKitchenFoodtype);
        return RetResponse.makeOKRsp(state);
    }

    @GetMapping("/selectById")
    public RetResult<TKitchenFoodtype> selectById(@RequestParam String id) throws Exception {
        TKitchenFoodtype tKitchenFoodtype = tKitchenFoodtypeService.selectById(id);
        return RetResponse.makeOKRsp(tKitchenFoodtype);
    }

    @GetMapping("/getByKitId")
    public RetResult<List<TKitchenFoodtype>> getByKitId(@RequestParam Long id,@RequestParam String foodName) throws Exception {

        TKitchenFoodtype tf = new TKitchenFoodtype();
        tf.setKitchenId(id);
        tf.setFoodName(foodName);
        List<TKitchenFoodtype> list = tKitchenFoodtypeService.select(tf);
        return RetResponse.makeOKRsp(list);
    }

    @GetMapping("/getTest")
    public RetResult<List<TKitchenFoodtype>> getTest() throws Exception {

        Condition c = new Condition(TKitchenFoodtype.class);
        c.createCriteria().andCondition("kitchen_id = 1");
        List<TKitchenFoodtype> list = tKitchenFoodtypeService.selectByCondition(c);
        return RetResponse.makeOKRsp(list);
    }



    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        TKitchenFoodtype tKitchenFoodtype =tKitchenFoodtypeService.selectById(id);
        mv.setViewName("views/tKitchenFoodtype/tKitchenFoodtypeEdit");
        mv.addObject("tKitchenFoodtype",tKitchenFoodtype);
        return mv;
    }

   /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn RetResult<PageInfo<TKitchenFoodtype>>
	*/
    @PostMapping("/list")
    public RetResult<PageInfo<TKitchenFoodtype>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<TKitchenFoodtype> list = tKitchenFoodtypeService.selectAll();
        PageInfo<TKitchenFoodtype> pageInfo = new PageInfo<TKitchenFoodtype>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }


   /**
    * lay ui 分页
    * @param page 当前页
    * @param limit 每页条数
    * @return
    */
    @RequiresPermissions("sys:foodtype:foodtype")
    @RequestMapping("/getAll")
    @ResponseBody
    public LayuiResult<TKitchenFoodtype> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);

        if(filter.getFilters().get("kitchenId")!=null){
            String kitchenId = filter.getFilters().get("kitchenId").toString();
            map.put("kitchenId",kitchenId);
        }

        if(filter.getFilters().get("status")!=null){
            String status = filter.getFilters().get("status").toString();
            map.put("status",status);
        }

        List<TKitchenFoodtype> list = tKitchenFoodtypeService.getAll(map);
        PageInfo<TKitchenFoodtype> pageInfo = new PageInfo<TKitchenFoodtype>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


}