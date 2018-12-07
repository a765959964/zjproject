package com.example.demo.controller;

import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.utils.ApplicationUtils;
import com.example.demo.model.Demo3;
import com.example.demo.service.Demo3Service;
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
* @Description: Demo3Controller类
* @author zf
* @date 2018/12/07 16:18
*/
@RestController
@RequestMapping("/demo3")
public class Demo3Controller {

    @Resource
    private Demo3Service demo3Service;

    @PostMapping("/insert")
    public RetResult<Integer> insert(Demo3 demo3) throws Exception{
    // demo3.setId(ApplicationUtils.getUUID());
    	Integer state = demo3Service.insert(demo3);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = demo3Service.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    public RetResult<Integer> update(Demo3 demo3) throws Exception {
        Integer state = demo3Service.update(demo3);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    public RetResult<Demo3> selectById(@RequestParam String id) throws Exception {
        Demo3 demo3 = demo3Service.selectById(id);
        return RetResponse.makeOKRsp(demo3);
    }


    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        Demo3 demo3 =demo3Service.selectById(id);
        mv.setViewName("views/demo3/demo3Edit");
        mv.addObject("demo3",demo3);
        return mv;
    }

   /**
	* @Description: 分页查询
	* @param page 页码
	* @param size 每页条数
	* @Reutrn RetResult<PageInfo<Demo3>>
	*/
    @PostMapping("/list")
    public RetResult<PageInfo<Demo3>> list(@RequestParam(defaultValue = "0") Integer page,
					@RequestParam(defaultValue = "0") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Demo3> list = demo3Service.selectAll();
        PageInfo<Demo3> pageInfo = new PageInfo<Demo3>(list);
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
    public LayuiResult<Demo3> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List<Demo3> list = demo3Service.getAll(map);
        PageInfo<Demo3> pageInfo = new PageInfo<Demo3>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


}