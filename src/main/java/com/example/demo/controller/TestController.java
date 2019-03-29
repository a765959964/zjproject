package com.example.demo.controller;

import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.model.Test;
import com.example.demo.service.TestService;
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
* @Description: TestController类
* @author zf
* @date 2019/03/15 15:21
*/
@RestController
@RequestMapping("/sys/test")
public class TestController {

    @Resource
    private TestService testService;

    @RequiresPermissions("sys:test:test")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/test/testList");
        return mv;
    }

    /**
     * 添加页面
     **/
    @RequestMapping(value = "/testAdd",method = RequestMethod.GET)
    public ModelAndView testAdd() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/test/testAdd");
        return mv;
    }



    @PostMapping("/insert")
    public RetResult<Integer> insert(Test test) throws Exception{
    // test.setId(ApplicationUtils.getUUID());
    	Integer state = testService.insert(test);
        return RetResponse.makeOKRsp(state);
    }


    /**
    * 批量删除
    * @param ids [1,2,3]
    * @return
    */
    @RequiresPermissions("sys:test:batchRemove")
    @PostMapping("/batchRemove")
    public RetResult<Integer> batchRemove(String ids) throws Exception{
        Integer state = testService.deleteByIds(ids);
        return RetResponse.makeOKRsp(state);
    }

    @RequiresPermissions("sys:test:remove")
    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = testService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public RetResult<Integer> update(Test test) throws Exception {
        Integer state = testService.update(test);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public RetResult<Test> selectById(@RequestParam String id) throws Exception {
        Test test = testService.selectById(id);
        return RetResponse.makeOKRsp(test);
    }


    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        Test test =testService.selectById(id);
        mv.setViewName("views/system/test/testEdit");
        mv.addObject("test",test);
        return mv;
    }

   /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn RetResult<PageInfo<Test>>
	*/
    @PostMapping("/list")
    public RetResult<PageInfo<Test>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Test> list = testService.selectAll();
        PageInfo<Test> pageInfo = new PageInfo<Test>(list);
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
    public LayuiResult<Test> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List<Test> list = testService.getAll(map);
        PageInfo<Test> pageInfo = new PageInfo<Test>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


}