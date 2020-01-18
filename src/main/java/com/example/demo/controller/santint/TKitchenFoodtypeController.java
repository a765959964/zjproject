package com.example.demo.controller.santint;

import com.example.demo.core.ret.LayuiResult;
import com.example.demo.core.ret.RetResponse;
import com.example.demo.core.ret.RetResult;
import com.example.demo.model.TKitchenFoodtype;
import com.example.demo.model.TPrefabricateOrder;
import com.example.demo.service.TKitchenFoodtypeService;
import com.example.demo.service.TPrefabricateOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santint.core.util.ZJ_DateUtils;
import com.santint.core.web.query.QueryFilter;
import com.santint.core.web.util.RequestUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private TPrefabricateOrderService tPrefabricateOrderService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView listView(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/foodtype/foodtypeList");
        return mv;
    }


    /**
     * 增加估清
     * @param model
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/foodtypeAdd",method = RequestMethod.GET)
    public ModelAndView foodtypeAdd(Model model,HttpServletRequest req) throws Exception {
        String foodid =    req.getParameter("foodid");
        String kitchenId  = req.getParameter("kitchenId");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/foodtype/foodtypeAdd");
        mv.addObject("foodid",foodid).addObject("kitchenId",kitchenId)
                .addObject("prefabricateTime",ZJ_DateUtils.format(new Date(),"yyyy-MM-dd"));
        return mv;
    }


    /**
     * 批量增加估清
     * @param
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/batchAdd",method = RequestMethod.GET)
    public ModelAndView batchAdd(HttpServletRequest req) throws Exception {
        String foodid =    req.getParameter("foodid");
        String kitchenId  = req.getParameter("kitchenId");

        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/system/foodtype/batchAdd");
        mv.addObject("foodid",foodid).addObject("kitchenId",kitchenId)
                .addObject("prefabricateTime",ZJ_DateUtils.format(new Date(),"yyyy-MM-dd"));
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


    /**
     * 添加或者更新预制单信息
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/batchUpdate")
    public RetResult<Integer> batchUpdate(HttpServletRequest req,TPrefabricateOrder tPrefabricateOrder) throws Exception{

        // 获取所有菜品 id
        String [] ids = RequestUtil.getStringAryByStr(req, "ids");
        System.out.println(ids);
        String kitchenId = req.getParameter("kitchenId");
        System.out.println("门店id" + kitchenId);
        String [] sums = RequestUtil.getStringAryByStr(req, "sums");



        //1  先根据午餐晚餐 判断门店菜品 是否存在， 如果存在 更新， 如果不存在，添加
        Integer status = 0;
        for(int i = 0; i<ids.length ; i++ ){

            for(int j=0;j<sums.length;j++){
                int type = 1;
                if(j==0){
                    type = 1;
                    System.out.println("午餐");
                }else if(j==1){
                    type = 2;
                    System.out.println("晚餐");
                }else if(j==2){
                    type = 3;
                    System.out.println("外卖");
                }
                System.out.println(sums[j]);

                Map params = new HashMap();
                params.put("foodid",ids[i]);
                params.put("kitchenId",kitchenId);
                params.put("type",type);
                params.put("prefabricateTime",tPrefabricateOrder.getPrefabricateTime());
                Integer num = tPrefabricateOrderService.getMap(params);
                System.out.println("数量是："+num);
                if(num > 0){    //证明门店有次菜品信息  则更新
                    tPrefabricateOrder.setKitchenId(Integer.parseInt(kitchenId));
                    tPrefabricateOrder.setFoodid(Integer.valueOf(ids[i]));
                    tPrefabricateOrder.setType(type);
                    tPrefabricateOrder.setSum(Integer.parseInt(sums[j]));
                    tPrefabricateOrder.setSellNum(0);
                    tPrefabricateOrder.setRemainNum((Integer.parseInt(sums[j])));
                    tPrefabricateOrderService.batchUpdate(tPrefabricateOrder);
                }else{  //添加
                    tPrefabricateOrder.setKitchenId(Integer.parseInt(kitchenId));
                    tPrefabricateOrder.setSum(Integer.parseInt(sums[j]));
                    tPrefabricateOrder.setSellNum(0);
                    tPrefabricateOrder.setRemainNum((Integer.parseInt(sums[j])));
                    tPrefabricateOrder.setFoodid(Integer.valueOf(ids[i]));
                    tPrefabricateOrder.setType(type);
                    status =  tPrefabricateOrderService.insert(tPrefabricateOrder);
                }

            }

        }
        return RetResponse.makeOKRsp(status);
    }


    /**
     * 添加或者更新
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/updatePrefabricate")
    public RetResult<Integer> insertOrUpdate(HttpServletRequest req,TPrefabricateOrder tPrefabricateOrder) throws Exception{

        // 获取所有菜品 id
        String [] ids = RequestUtil.getStringAryByStr(req, "ids");
        String kitchenId = req.getParameter("kitchenId");
        System.out.println("门店id" + kitchenId);
        //1  先根据午餐晚餐 判断门店菜品 是否存在， 如果存在 更新， 如果不存在，添加
        Integer status = 0;
        for(int i = 0; i<ids.length ; i++ ){
            Map params = new HashMap();
            params.put("kitchenId",tPrefabricateOrder.getKitchenId());
            params.put("foodid",ids[i]);
            params.put("type",tPrefabricateOrder.getType());
            Integer num = tPrefabricateOrderService.getMap(params);
            System.out.println("数量是："+num);
            if(num > 0){    //证明门店有次菜品信息  则更新
                tPrefabricateOrder.setFoodid(Integer.valueOf(ids[i]));
                tPrefabricateOrderService.updatePrefabricate(tPrefabricateOrder);
            }else{  //添加
                tPrefabricateOrder.setSellNum(0);
                tPrefabricateOrder.setFoodid(Integer.valueOf(ids[i]));
                status =  tPrefabricateOrderService.insert(tPrefabricateOrder);
            }
        }
        return RetResponse.makeOKRsp(status);
    }


    @GetMapping("/selectById")
    public RetResult<TKitchenFoodtype> selectById(@RequestParam String id) throws Exception {
        TKitchenFoodtype tKitchenFoodtype = tKitchenFoodtypeService.selectById(id);
        return RetResponse.makeOKRsp(tKitchenFoodtype);
    }

    /**
     * 查询菜品分类信息
     * @return
     * @throws Exception
     */
    @GetMapping("/getTypeList")
    public RetResult<List> getTypeList() throws Exception{
        List list = tKitchenFoodtypeService.getTypeList();
        return RetResponse.makeOKRsp(list);
    }



    @GetMapping("/getByKitId")
    public RetResult<List<TKitchenFoodtype>> getByKitId(@RequestParam Long id,@RequestParam String foodid) throws Exception {

        TKitchenFoodtype tf = new TKitchenFoodtype();
        tf.setKitchenId(id);
//        tf.setFoodName(foodName);
        tf.setFoodId(Long.valueOf(foodid));
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
//    @RequiresPermissions("sys:foodtype:foodtype")
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


    @RequestMapping("/getFoodTypeList")
    @ResponseBody
    public LayuiResult getFoodTypeList(HttpServletRequest request, @RequestParam(defaultValue = "0") Integer page,
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
        if(filter.getFilters().get("foodtypeId") != null){
            String foodtypeId = filter.getFilters().get("foodtypeId").toString();
            map.put("foodtypeId",foodtypeId);
        }

        if(filter.getFilters().get("foodName") !=null){
            String foodName = filter.getFilters().get("foodName").toString();
            map.put("foodName",foodName);
        }

        if(filter.getFilters().get("foodType") != null){
            String foodType = filter.getFilters().get("foodType").toString();
            if(foodType.equals("1")){
                map.put("foodType","1");
            }else if(foodType.equals("2")){
                map.put("foodType","2");
            }else{
                map.put("foodType","3");
            }

        }


        List list = tKitchenFoodtypeService.getFoodTypeList(map);
        PageInfo pageInfo = new PageInfo<>(list);
        return  RetResponse.makeRsp(0,"",pageInfo.getList(),pageInfo.getTotal());
    }


    @RequestMapping(value = "createJson",method = RequestMethod.GET)
    public  RetResult createJson(HttpServletRequest req){

        String kitchenId = req.getParameter("kitchenId");
        String str = "";
        if(kitchenId !=null && !kitchenId.equals("")) {
            str  = cn.hutool.http.HttpUtil.get("http://10.10.2.17:8080/myserver/foodtype/createJson?kitchenId"+kitchenId);
        }else{
            str = cn.hutool.http.HttpUtil.get("http://10.10.2.17:8080/myserver/foodtype/createJson/");
        }
        return RetResponse.makeRsp(200,str);
    }


}