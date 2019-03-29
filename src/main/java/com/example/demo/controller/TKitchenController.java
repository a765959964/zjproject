package com.example.demo.controller;

import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.model.TKitchen;
import com.example.demo.service.TKitchenService;
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
* @Description: TKitchenController类
* @author zf
* @date 2019/03/15 16:38
*/
@RestController
@RequestMapping("/sys/tKitchen")
public class TKitchenController {

    @Resource
    private TKitchenService tKitchenService;

    @RequiresPermissions("sys:tKitchen:tKitchen")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/tKitchen/tKitchenList");
        return mv;
    }

    /**
     * 添加页面
     **/
    @RequestMapping(value = "/tKitchenAdd",method = RequestMethod.GET)
    public ModelAndView tKitchenAdd() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/tKitchen/tKitchenAdd");
        return mv;
    }



    @PostMapping("/insert")
    public RetResult<Integer> insert(TKitchen tKitchen) throws Exception{
    // tKitchen.setId(ApplicationUtils.getUUID());
    	Integer state = tKitchenService.insert(tKitchen);
        return RetResponse.makeOKRsp(state);
    }


    /**
    * 批量删除
    * @param ids [1,2,3]
    * @return
    */
    @RequiresPermissions("sys:tKitchen:batchRemove")
    @PostMapping("/batchRemove")
    public RetResult<Integer> batchRemove(String ids) throws Exception{
        Integer state = tKitchenService.deleteByIds(ids);
        return RetResponse.makeOKRsp(state);
    }

    @RequiresPermissions("sys:tKitchen:remove")
    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = tKitchenService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public RetResult<Integer> update(TKitchen tKitchen) throws Exception {
        Integer state = tKitchenService.update(tKitchen);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public RetResult<TKitchen> selectById(@RequestParam String id) throws Exception {
        TKitchen tKitchen = tKitchenService.selectById(id);
        return RetResponse.makeOKRsp(tKitchen);
    }


    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        TKitchen tKitchen =tKitchenService.selectById(id);
        mv.setViewName("views/system/tKitchen/tKitchenEdit");
        mv.addObject("tKitchen",tKitchen);
        return mv;
    }

   /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn RetResult<PageInfo<TKitchen>>
	*/
    @GetMapping("/list")
    public RetResult<List<TKitchen>> list() throws Exception {
        List<TKitchen> list = tKitchenService.selectAll();
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
    public LayuiResult<TKitchen> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List<TKitchen> list = tKitchenService.getAll(map);
        PageInfo<TKitchen> pageInfo = new PageInfo<TKitchen>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


}