package com.example.demo.controller;

import cn.hutool.core.date.DateUtil;
import com.example.demo.core.aop.AnnotationLog;
import com.example.demo.core.configurer.DateConverter;
import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.ret.RetResult;
import com.example.demo.model.TFoodWork;
import com.example.demo.service.TFoodWorkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santint.core.util.ZJ_DateUtils;
import com.santint.core.web.query.QueryFilter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description: TFoodWorkController类
* @author zf
* @date 2020/01/15 09:43
*/
@RestController
@RequestMapping("/sys/tFoodWork")
public class TFoodWorkController {

    @Resource
    private TFoodWorkService tFoodWorkService;


    /**
     * 处理时间
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        GenericConversionService genericConversionService = (GenericConversionService) binder.getConversionService();
        if (genericConversionService != null) {
            genericConversionService.addConverter(new DateConverter());
        }
    }


    @AnnotationLog("进入工作餐页面")
//    @RequiresPermissions("sys:tFoodWork:tFoodWork")
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/tFoodWork/tFoodWorkList");
        return mv;
    }


    @AnnotationLog("进入自助餐页面")
//    @RequiresPermissions("sys:tFoodWork:tFoodWork")
    @RequestMapping(value = "/zzfood",method = RequestMethod.GET)
    public ModelAndView zzlistView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/tFoodWork/zzfood");
        return mv;
    }


    /**
    * lay ui 分页
    * @param page 当前页
    * @param limit 每页条数
    * @return
    */
//    @RequiresPermissions("sys:tFoodWork:tFoodWork")
    @GetMapping("/getAll")
    public LayuiResult<TFoodWork> getAll(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
                                          @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List<TFoodWork> list = tFoodWorkService.getAll(map);
        PageInfo<TFoodWork> pageInfo = new PageInfo<TFoodWork>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }

    @GetMapping("/getFoodWorkList")
    public LayuiResult getFoodWorkList (HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
                                        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        List list = tFoodWorkService.getFoodWorkList(map);
        PageInfo pageInfo = new PageInfo<>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


    @GetMapping("/getKitZzList")
    public LayuiResult getKitZzList (HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
                                        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);
        map.put("kitchenId",1);
        map.put("type",2);
        List list = tFoodWorkService.getKitZzList(map);
        PageInfo pageInfo = new PageInfo<>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }



    /**
     * 门店菜品
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getKitFoodList")
    public LayuiResult getKitFoodList (HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
                                        @RequestParam(defaultValue = "0") Integer limit){
        HashMap map = new HashMap();
        PageHelper.startPage(page, limit);
        QueryFilter filter = new QueryFilter(request);

        if(filter.getFilters().get("foodname") !=null){
            String foodname = filter.getFilters().get("foodname").toString();
            map.put("foodname",foodname);
        }

        map.put("kitchenId",1);
        List list = tFoodWorkService.getKitFoodList(map);
        PageInfo pageInfo = new PageInfo<>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


    /**
     * 添加页面
     **/
//    @RequiresPermissions("sys:tFoodWork:add")
    @RequestMapping(value = "/tFoodWorkAdd",method = RequestMethod.GET)
    public ModelAndView tFoodWorkAdd() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/tFoodWork/tFoodWorkAdd");
        return mv;
    }


    @RequestMapping(value = "/zzfoodAdd",method = RequestMethod.GET)
    public ModelAndView zzfoodAdd(HttpServletRequest req) throws Exception {
        String foodid =  req.getParameter("foodid");
        String foodname = req.getParameter("foodname");
        ModelAndView mv = new ModelAndView();
        mv.addObject("foodid",foodid).addObject("foodname",foodname);
        mv.setViewName("views/system/tFoodWork/zzfoodAdd");
        return mv;
    }

    @AnnotationLog("添加操作")
//    @RequiresPermissions("sys:tFoodWork:add")
    @PostMapping("/insert")
    public RetResult<Integer> insert(TFoodWork tFoodWork) throws Exception{
    // tFoodWork.setId(ApplicationUtils.getUUID());
    	Integer state = tFoodWorkService.insert(tFoodWork);
        return RetResponse.makeOKRsp(state);
    }


    /**
    * 批量删除
    * @param ids [1,2,3]
    * @return
    */
    @AnnotationLog("进行批量删除")
//    @RequiresPermissions("sys:tFoodWork:batchRemove")
    @PostMapping("/batchRemove")
    public RetResult<Integer> batchRemove(String ids) throws Exception{
        Integer state = tFoodWorkService.deleteByIds(ids);
        return RetResponse.makeOKRsp(state);
    }

    @AnnotationLog("进行删除操作")
//    @RequiresPermissions("sys:tFoodWork:remove")
    @PostMapping("/deleteById")
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = tFoodWorkService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }


    @AnnotationLog("进行工作餐删除操作")
//    @RequiresPermissions("sys:tFoodWork:remove")
    @PostMapping("/delByWorkDate")
    public RetResult<Integer> delByWorkDate(HttpServletRequest req) throws Exception {
        int num = 0;
        String kitchenId = req.getParameter("kitchenId");
        String type = req.getParameter("type");
        String workDate = req.getParameter("workDate");
        Map params = new HashMap();
        params.put("kitchenId",kitchenId);
        params.put("type",type);
        params.put("workDate",workDate);
        num = tFoodWorkService.delByWorkDate(params);
        return RetResponse.makeOKRsp(num);
    }


//    @RequiresPermissions("sys:tFoodWork:edit")
    @GetMapping("/getById")
    public ModelAndView getById(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        TFoodWork tFoodWork =tFoodWorkService.selectById(id);
        mv.setViewName("views/system/tFoodWork/tFoodWorkEdit");
        mv.addObject("tFoodWork",tFoodWork).addObject("workDate", ZJ_DateUtils.format(tFoodWork.getWorkDate(),"yyyy-MM-dd"));
        return mv;
    }


    @GetMapping("/getByZzId")
    public ModelAndView getByZzId(String id, Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        TFoodWork tFoodWork =tFoodWorkService.selectById(id);
        mv.setViewName("views/system/tFoodWork/tFoodZzEdit");
        mv.addObject("tFoodWork",tFoodWork).addObject("workDate", ZJ_DateUtils.format(tFoodWork.getWorkDate(),"yyyy-MM-dd"));
        return mv;
    }



    @AnnotationLog("更新操作")
//    @RequiresPermissions("sys:tFoodWork:edit")
    @PostMapping("/update")
    public RetResult<Integer> update(TFoodWork tFoodWork) throws Exception {
        Integer state = tFoodWorkService.update(tFoodWork);
        return RetResponse.makeOKRsp(state);
    }



    @PostMapping("/foodwork/addZzOrder")
    public RetResult<Integer> addWorkOrder(TFoodWork tFoodWork,HttpServletRequest req){
        int num=0;
        String ary = req.getParameter("ary");
        String[] arys = ary.split(",");
        String kitchenId = req.getParameter("kitchenId");
        String foodid = req.getParameter("foodid");
        for(int i = 0; i<arys.length;i++ ){
            //添加订单详情
            TFoodWork t  = new TFoodWork();
            String weekDate = weekDay(arys[i]);
            Map params = new HashMap();
            params.put("kitchenId",kitchenId);
            params.put("type",2);
            params.put("foodid",foodid);
            params.put("workDate",weekDate);
            Integer nums = tFoodWorkService.getFoodWorkCount(params);
            if(nums == 0) {      //不存在 ，新增
                t.setWorkDate(DateUtil.parse(weekDate));
                t.setType(2);
                t.setKitchenId(Integer.parseInt(kitchenId));
                t.setFoodWorkId(Integer.parseInt(foodid));
                num = tFoodWorkService.insert(t);
                num++;
            }
        }

        return RetResponse.makeOKRsp(num);
    }


    private  String weekDay(String week){
        LocalDate today = LocalDate.now();
        String str = null;
        switch (week) {
            case "1":
                str = today.with(DayOfWeek.MONDAY).toString();
                break;
            case "2":
                str = today.with(DayOfWeek.TUESDAY).toString();
                break;
            case "3":
                str = today.with(DayOfWeek.WEDNESDAY).toString();
                break;
            case "4":
                str = today.with(DayOfWeek.THURSDAY).toString();
                break;
            case "5":
                str = today.with(DayOfWeek.FRIDAY).toString();
                break;
            case "6":
                str = today.with(DayOfWeek.SATURDAY).toString();
                break;
            case "7":
                str = today.with(DayOfWeek.SUNDAY).toString();
                break;
        }
        return  str;
    }



    @GetMapping("/selectById")
    public RetResult<TFoodWork> selectById(@RequestParam String id) throws Exception {
        TFoodWork tFoodWork = tFoodWorkService.selectById(id);
        return RetResponse.makeOKRsp(tFoodWork);
    }



   /**
	* @Description: 分页查询
	* @Reutrn RetResult<PageInfo<TFoodWork>>
	*/
    @GetMapping("/list")
    public RetResult<List<TFoodWork>> list() throws Exception {
        List<TFoodWork> list = tFoodWorkService.selectAll();
        return RetResponse.makeOKRsp(list);
    }





}