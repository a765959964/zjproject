package com.example.demo.controller.santint;

import com.example.demo.core.aop.AnnotationLog;
import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.ret.RetResult;
import com.example.demo.model.Myorder;
import com.example.demo.service.MyorderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santint.core.util.ZJ_DateUtils;
import com.santint.core.web.query.QueryFilter;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
* @Description: MyorderController类
* @author zf
* @date 2019/12/18 16:34
*/
@RestController
@RequestMapping("/sys/myorder")
public class MyorderController {

    @Resource
    private MyorderService myorderService;

    @AnnotationLog("进入外卖统计页面")
//    @RequiresPermissions("sys:myorder:myorder")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/myorder/myorderList");
        return mv;
    }



    @AnnotationLog("进入外卖菜品份数")
//    @RequiresPermissions("sys:myorder:myorder")
    @GetMapping("/myorderListCount")
    public ModelAndView myorderListCount(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/myorder/myorderListCount");
        return mv;
    }


    /**
    * lay ui 分页
    * @param page 当前页
    * @param limit 每页条数
    * @return
    */
    @RequiresPermissions("sys:myorder:myorder")
    @GetMapping("/getAll")
    public LayuiResult<Myorder> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List<Myorder> list = myorderService.getAll(map);
        PageInfo<Myorder> pageInfo = new PageInfo<Myorder>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


    /**
     * 查询外卖订单 详情
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getOrderList")
    public LayuiResult getOrderList(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
                                       @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        if(filter.getFilters().get("kitchenId")!= null){
            String kitchenId =  filter.getFilters().get("kitchenId").toString();
            map.put("kitchenId",kitchenId);
        }else{
            map.put("kitchenId",1);
        }

        if(filter.getFilters().get("nickname")!= null ){
            String nickname =  filter.getFilters().get("nickname").toString();
            map.put("nickname",nickname);
        }
        if(filter.getFilters().get("type")!= null){
            String type =  filter.getFilters().get("type").toString();
            map.put("type",type);
        }else{
            map.put("type",4);
        }

        if(filter.getFilters().get("state") != null){
            String state = filter.getFilters().get("state").toString();
            map.put("state",state);
        }else{
            map.put("state","3,5,7");
        }
        if(filter.getFilters().get("ordertime") != null){
           String ordertime =  filter.getFilters().get("ordertime").toString();
           map.put("ordertime",ordertime);
        }else{
           map.put("ordertime", ZJ_DateUtils.getNowDate());
        }


        List list = myorderService.getOrderList(map);
        PageInfo pageInfo = new PageInfo(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }



    /**
     * 查询外卖订单 所点菜品数量
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getOrderListCount")
    public LayuiResult getOrderListCount(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
                                    @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);

//        if(filter.getFilters().get("ordertime") != null){
//            String ordertime =  filter.getFilters().get("ordertime").toString();
//            map.put("ordertime",ordertime);
//        }


        if(filter.getFilters().get("beginDate") != null){
            String beginDate =  filter.getFilters().get("beginDate").toString();
            map.put("beginDate",beginDate);
        }else{
            map.put("beginDate",ZJ_DateUtils.format(new Date(),"yyyy-MM-dd"));
        }

        if(filter.getFilters().get("endDate") != null){
            String endDate =  filter.getFilters().get("endDate").toString();
            map.put("endDate",endDate);
        }else{
            map.put("endDate",ZJ_DateUtils.format(new Date(),"yyyy-MM-dd"));
        }

        if(filter.getFilters().get("type") != null){
            String type =  filter.getFilters().get("type").toString();
            map.put("type",type);
        }

        List list = myorderService.getOrderListCount(map);
        PageInfo pageInfo = new PageInfo(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


    /**
     * 添加页面
     **/
    @RequiresPermissions("sys:myorder:add")
    @RequestMapping(value = "/myorderAdd",method = RequestMethod.GET)
    public ModelAndView myorderAdd() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/myorder/myorderAdd");
        return mv;
    }

    @AnnotationLog("添加操作")
    @RequiresPermissions("sys:myorder:add")
    @PostMapping("/insert")
    public RetResult<Integer> insert(Myorder myorder) throws Exception{
    // myorder.setId(ApplicationUtils.getUUID());
    	Integer state = myorderService.insert(myorder);
        return RetResponse.makeOKRsp(state);
    }


    /**
    * 批量删除
    * @param ids [1,2,3]
    * @return
    */
    @AnnotationLog("进行批量删除")
    @RequiresPermissions("sys:myorder:batchRemove")
    @PostMapping("/batchRemove")
    public RetResult<Integer> batchRemove(String ids) throws Exception{
        Integer state = myorderService.deleteByIds(ids);
        return RetResponse.makeOKRsp(state);
    }

    @AnnotationLog("进行删除操作")
    @RequiresPermissions("sys:myorder:remove")
    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = myorderService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @RequiresPermissions("sys:myorder:edit")
    @GetMapping("/getById")
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        Myorder myorder =myorderService.selectById(id);
        mv.setViewName("views/system/myorder/myorderEdit");
        mv.addObject("myorder",myorder);
        return mv;
    }

    @AnnotationLog("更新操作")
    @RequiresPermissions("sys:myorder:edit")
    @PostMapping("/update")
    public RetResult<Integer> update(Myorder myorder) throws Exception {
        Integer state = myorderService.update(myorder);
        return RetResponse.makeOKRsp(state);
    }

    @GetMapping("/selectById")
    public RetResult<Myorder> selectById(@RequestParam String id) throws Exception {
        Myorder myorder = myorderService.selectById(id);
        return RetResponse.makeOKRsp(myorder);
    }



   /**
	* @Description: 分页查询
	* @Reutrn RetResult<PageInfo<Myorder>>
	*/
    @GetMapping("/list")
    public RetResult<List<Myorder>> list() throws Exception {
        List<Myorder> list = myorderService.selectAll();
        return RetResponse.makeOKRsp(list);
    }





}