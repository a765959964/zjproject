package com.example.demo.controller.santint;

import com.example.demo.core.aop.AnnotationLog;
import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.ret.RetResult;
import com.example.demo.model.TPrefabricateOrder;
import com.example.demo.service.TPrefabricateOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santint.core.web.query.QueryFilter;
import com.santint.core.web.util.RequestUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
* @Description: TPrefabricateOrderController类
* @author zf
* @date 2019/11/06 10:20
*/
@RestController
@RequestMapping("/sys/tPrefabricateOrder")
public class TPrefabricateOrderController {

    @Resource
    private TPrefabricateOrderService tPrefabricateOrderService;

    @AnnotationLog("进入菜品预售管理页面")
//    @RequiresPermissions("sys:tPrefabricateOrder:tPrefabricateOrder")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/tPrefabricateOrder/tPrefabricateOrderList");
        return mv;
    }


    @AnnotationLog("进入预制单自动生成页面")
    @RequestMapping(value="execute", method = RequestMethod.GET)
    public void listExecuteView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        tPrefabricateOrderService.execute("1","2020-01-02","3");

    }




    /**
    * lay ui 分页
    * @param page 当前页
    * @param limit 每页条数
    * @return
    */
//    @RequiresPermissions("sys:tPrefabricateOrder:tPrefabricateOrder")
    @GetMapping("/getAll")
    public LayuiResult<TPrefabricateOrder> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);

        if(filter.getFilters().get("kitchenId")!=null){
            String kitchenId =  filter.getFilters().get("kitchenId").toString();
            map.put("kitchenId",kitchenId);
        }

        if(filter.getFilters().get("type")!=null){
            String type =  filter.getFilters().get("type").toString();
            map.put("type",type);
        }



        List<TPrefabricateOrder> list = tPrefabricateOrderService.getAll(map);
        PageInfo<TPrefabricateOrder> pageInfo = new PageInfo<TPrefabricateOrder>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


    /**
     * 查询预售单列表信息
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getPrefabricateList")
    public LayuiResult getPrefabricateList(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
                                                  @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);

        if(filter.getFilters().get("kitchenId")!=null){
            String kitchenId =  filter.getFilters().get("kitchenId").toString();
            map.put("kitchenId",kitchenId);
        }

        if(filter.getFilters().get("type")!=null){
            String type =  filter.getFilters().get("type").toString();
            map.put("type",type);
        }
        if(filter.getFilters().get("foodtypeId")!=null){
            String foodtypeId =  filter.getFilters().get("foodtypeId").toString();
            map.put("foodtypeId",foodtypeId);
        }

        if(filter.getFilters().get("foodType")!=null){
            String foodType =  filter.getFilters().get("foodType").toString();

            if(foodType.equals("1")){
                foodType = "1";
                map.put("foodType",foodType);
            }else if(foodType.equals("2")){
                map.put("foodType","2");
            }else if(foodType.equals("3")){
                map.put("foodType","3");
            }
        }

        if(filter.getFilters().get("foodName")!=null){
            String foodName =  filter.getFilters().get("foodName").toString();
            map.put("foodName",foodName);
        }

        if(filter.getFilters().get("prefabricateTime")!=null){
            String prefabricateTime =  filter.getFilters().get("prefabricateTime").toString();
            map.put("prefabricateTime",prefabricateTime);
        }


        List list = tPrefabricateOrderService.getPrefabricateList(map);
        PageInfo<List> pageInfo = new PageInfo<List>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


    /**
     * 添加页面
     **/
//    @RequiresPermissions("sys:tPrefabricateOrder:add")
    @RequestMapping(value = "/tPrefabricateOrderAdd",method = RequestMethod.GET)
    public ModelAndView tPrefabricateOrderAdd(HttpServletRequest req) throws Exception {

        String id  = RequestUtil.getStringAry(req, "ids");
        System.out.println("*****"+id);
        String[] ids = RequestUtil.getStringAryByStr(req,"ids");
        System.out.println("值是："+ids.length);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/tPrefabricateOrder/tPrefabricateOrderAdd");
        return mv.addObject("ids",id);
    }

    /**
     * 批量修改
     * @param tPrefabricateOrder
     * @return
     * @throws Exception
     */
    @AnnotationLog("进行预售管理批量修改")
    @PostMapping("/batchUpdate")
    public RetResult<Integer> batchUpdate(HttpServletRequest request,TPrefabricateOrder tPrefabricateOrder) throws Exception{
        // 获取id
        String [] ids =  RequestUtil.getStringAryByStr(request,"ids");
        Integer state = 0;
        for(int i = 0; i<ids.length; i++){

            tPrefabricateOrder.setFoodid(Integer.parseInt(ids[i]));
             state = tPrefabricateOrderService.batchUpdate(tPrefabricateOrder);
        }

        return RetResponse.makeOKRsp(state);
    }




//    @AnnotationLog("添加操作")
//    @RequiresPermissions("sys:tPrefabricateOrder:add")
    @PostMapping("/insert")
    public RetResult<Integer> insert(TPrefabricateOrder tPrefabricateOrder) throws Exception{
    	Integer state = tPrefabricateOrderService.insert(tPrefabricateOrder);
        return RetResponse.makeOKRsp(state);
    }




    /**
    * 批量删除
    * @param ids [1,2,3]
    * @return
    */
    @AnnotationLog("进行预售管理批量删除")
//    @RequiresPermissions("sys:tPrefabricateOrder:batchRemove")
    @PostMapping("/batchRemove")
    public RetResult<Integer> batchRemove(String ids) throws Exception{
        Integer state = tPrefabricateOrderService.deleteByIds(ids);
        return RetResponse.makeOKRsp(state);
    }

    @AnnotationLog("进行删除操作")
    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = tPrefabricateOrderService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @GetMapping("/getById")
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        TPrefabricateOrder tPrefabricateOrder =tPrefabricateOrderService.selectById(id);
        mv.setViewName("views/system/tPrefabricateOrder/tPrefabricateOrderEdit");

        mv.addObject("tPrefabricateOrder",tPrefabricateOrder).addObject("prefabricateTime",tPrefabricateOrder.getPrefabricateTime().substring(0,10));
        return mv;
    }

    @AnnotationLog("更新操作")
//    @RequiresPermissions("sys:tPrefabricateOrder:edit")
    @PostMapping("/update")
    public RetResult<Integer> update(TPrefabricateOrder tPrefabricateOrder) throws Exception {
        Integer state = tPrefabricateOrderService.update(tPrefabricateOrder);
        return RetResponse.makeOKRsp(state);
    }

    @GetMapping("/selectById")
    public RetResult<TPrefabricateOrder> selectById(@RequestParam String id) throws Exception {
        TPrefabricateOrder tPrefabricateOrder = tPrefabricateOrderService.selectById(id);
        return RetResponse.makeOKRsp(tPrefabricateOrder);
    }



   /**
	* @Description: 分页查询
	* @Reutrn RetResult<PageInfo<TPrefabricateOrder>>
	*/
    @GetMapping("/list")
    public RetResult<List<TPrefabricateOrder>> list() throws Exception {
        List<TPrefabricateOrder> list = tPrefabricateOrderService.selectAll();
        return RetResponse.makeOKRsp(list);
    }





}