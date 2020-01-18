package com.example.demo.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.core.universal.AbstractService;
import com.example.demo.dao.TPrefabricateOrderMapper;
import com.example.demo.model.TPrefabricateOrder;
import com.example.demo.model.TabCalendar;
import com.example.demo.service.MyorderService;
import com.example.demo.service.TPrefabricateOrderService;
import com.example.demo.service.TabCalendarService;
import com.example.demo.util.OrderNoUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description: TPrefabricateOrderService接口实现类
* @author zf
* @date 2019/11/06 10:20
*/
@Service
public class TPrefabricateOrderServiceImpl extends AbstractService<TPrefabricateOrder> implements TPrefabricateOrderService {

    @Resource
    private TPrefabricateOrderMapper tPrefabricateOrderMapper;

    @Resource
    private TabCalendarService tabCalendarService;


    @Resource
    private MyorderService myorderService;

    @Override
    public List<TPrefabricateOrder> getAll(Map map){
        return tPrefabricateOrderMapper.getAll(map);
    }

    @Override
    public Integer getMap(Map params) {
        return tPrefabricateOrderMapper.getMap(params);
    }

    @Override
    public Integer updatePrefabricate(TPrefabricateOrder tPrefabricateOrder) {
        return tPrefabricateOrderMapper.updatePrefabricate(tPrefabricateOrder);
    }

    @Override
    public List getPrefabricateList(Map params) {
        return tPrefabricateOrderMapper.getPrefabricateList(params);
    }

    @Override
    public Integer batchUpdate(TPrefabricateOrder tPrefabricateOrder) {
        return tPrefabricateOrderMapper.batchUpdate(tPrefabricateOrder);
    }

    @Override
    public List getRemainNum(Map params) {
        return tPrefabricateOrderMapper.getRemainNum(params);
    }

    @Override
    public TPrefabricateOrder getPrefabricate(Map params) {
        return tPrefabricateOrderMapper.getPrefabricate(params);
    }

    @Override
    public Map execute(String kitchenId, String date, String pmtype) {

        String type ="";    // 午餐 晚餐
        Map map = new HashMap();
        map.put("code", 200);
        System.out.println("输入的日期是" + date);
        DateTime laskWeek =	DateUtil.offsetDay(DateUtil.parse(date), -7);

        //上周日期
        String laskWeekDay  = laskWeek.toString().substring(0,10);
        System.out.println("上周是:" + laskWeekDay);

        Map params = new HashMap();
        params.put("calendarDate", laskWeekDay);
        TabCalendar tabCalendar = tabCalendarService.getCalendateByDate(params);

        if(tabCalendar !=null){	//如果是节假日，求上上周日期
            laskWeek =	DateUtil.offsetDay(DateUtil.parse(date), -14);
            laskWeekDay  = laskWeek.toString().substring(0,10);
            System.out.println("上周是节假日:" + laskWeekDay);
        }



        params.put("kitchenId", kitchenId);

        if(pmtype.equals("3")){
            type = "4";
        }else{
            type = "1,3,5";
        }
        params.put("pmtype",pmtype);
        params.put("laskWeekDay", laskWeekDay);
        params.put("date", date);
        params.put("ordertime", laskWeekDay);


        List calendarList = this.getCalendates(date);
        params.put("calendarList", calendarList);	//存取时间列表

        params.put("type", type);
        params.put("state","2,3,5,7");
        Integer orderCount = myorderService.getByOrderTypeCount(params);

        params.put("orderCount", orderCount);

        //午餐
        List amList = this.getRemainNum(params);


        // 午餐数
        JSONArray amJsonArray = JSONUtil.parseArray(amList);
        System.out.println("时间:" + laskWeekDay +"售卖情况如下：*********");
        int noCount = 0;
        int okGqCount  = 0;
        int noSqCount  = 0;
        System.out.println("总共" + amJsonArray.size());
        for(int i=0; i< amJsonArray.size(); i++){
            JSONObject jsonObj =amJsonArray.getJSONObject(i);
            //1.若果 销售 份数  都为 0   没有设置估清值      在求上周的，如果没有  设置预制单为0

            params.put("laskWeekDay", laskWeekDay);
            if(jsonObj.get("sell_num").toString().equals("0") ){	// 求上周同星期的销量 如果 销量 ==0  ，直接设置估清值为0

                params.put("yzStatus", 1);
                params.put("foodid", jsonObj.get("foodid").toString());
                params.put("foodName", jsonObj.get("foodName").toString());
//				  System.out.println("菜品没有设置估清值 如下：");
                System.out.println("没有估清值:"+ jsonObj.get("foodid").toString() + "\t"+jsonObj.get("sell_num").toString() +"\t" + jsonObj.get("remain_num").toString());
                this.executePrefabricate(params,null);
                noCount ++;
            }
            // 2. 有销售数据，并且 剩余份数为 0    表示此菜品售罄
            else if(!jsonObj.get("sell_num").toString().equals("0")
                    && jsonObj.get("remain_num").toString().equals("0")){
//				  System.out.println("菜品售罄数据 如下:");
//				  System.out.println(jsonObj.get("foodid").toString() + "\t"+jsonObj.get("sell_num").toString() +"\t" + jsonObj.get("remain_num").toString());
                params.put("yzStatus", 2);
                params.put("foodid", jsonObj.get("foodid").toString());
                params.put("foodName", jsonObj.get("foodName").toString());
                this.executePrefabricate(params,null);
                okGqCount++;
            }
            // 3. 改菜没有售罄
            else if(!jsonObj.get("sell_num").toString().equals("0")
                    && !jsonObj.get("remain_num").toString().equals("0")){

                params.put("yzStatus", 3);
                params.put("foodid", jsonObj.get("foodid").toString());
                params.put("foodName", jsonObj.get("foodName").toString());
//				  System.out.println("菜品没有售罄数据 如下:");
//				  System.out.println(jsonObj.get("foodid").toString() + "\t"+jsonObj.get("sell_num").toString() +"\t" + jsonObj.get("remain_num").toString());
                this.executePrefabricate(params,calendarList);
                noSqCount++;

            }


        }

        System.out.println(noCount + "\t" + okGqCount + "\t"  + noSqCount + "\t"  );

        return null;
    }

    /**
     * 自动化  处理售罄 预制单
     */
    private void executePrefabricate(Map params,List calendarList){
        Integer yzStatus =  Integer.parseInt(params.get("yzStatus").toString());
        TPrefabricateOrder prefabricate = this.getPrefabricate(params);

        //新的预制单数量
        int newTotalPreSum = 0;
        // 1 代表没有设置估清值
        if(yzStatus == 1){
            System.out.println(params.get("date") +"\t " + params.get("foodid") + "\t" + params.get("foodName") +" "  + "新的预制数位: " + newTotalPreSum );


            Map m = new HashMap();
            m.put("foodid",params.get("foodid"));
            m.put("kitchenId",params.get("kitchenId"));
            m.put("type",params.get("pmtype"));
            m.put("prefabricateTime",params.get("date"));
            Integer num = this.getMap(m);
            System.out.println("数量是："+num);
            TPrefabricateOrder po = new TPrefabricateOrder();
            po.setKitchenId(Integer.parseInt(params.get("kitchenId").toString()));
            po.setFoodid(Integer.parseInt(params.get("foodid").toString()));
            po.setSystemSum(newTotalPreSum);
            po.setSum(newTotalPreSum);
            po.setSellNum(0);
            po.setRemainNum(newTotalPreSum);
            po.setStatus(0);
            po.setPrefabricateTime(params.get("date").toString());
            po.setType(Integer.parseInt(params.get("pmtype").toString()));
            if(num > 0){    //有数据 更新
                this.batchUpdate(po);
            }else{          //没有数据 新增
                this.insert(po);
            }



        }
        //		 2 菜品售罄
        else if (yzStatus == 2 ){

            Integer totalSum = Integer.parseInt(params.get("orderCount").toString());	//定义共用多少单
            if(prefabricate !=null ){
//                if(prefabricate.getStatus() == 1 ){	//已售罄
                    Integer sales = prefabricate.getSales();	//多少订单售罄
                    if(sales == null){
                        sales = totalSum;
                    }
                    Integer sum = prefabricate.getSellNum();	//总预制数
                    // 总销量 * 预制量  * 在第几订单 销售完   取摸取余 计算  余数大于0 + 1
                    BigDecimal totalPreSum =  NumberUtil.div( (totalSum * sum)+"" , sales.toString(),1);
                    //转换成double 类型
                    double dbTotalPreSum =  totalPreSum.doubleValue();
                    // 新的预制单数量
                    newTotalPreSum =  OrderNoUtils.roundUp(dbTotalPreSum);
                    System.out.println(params.get("date") +"\t " + params.get("foodid") + "\t" + params.get("foodName") +" "  + "新的预制数位: " + newTotalPreSum );

                    Map m = new HashMap();
                    m.put("foodid",params.get("foodid"));
                    m.put("kitchenId",params.get("kitchenId"));
                    m.put("type",params.get("pmtype"));
                    m.put("prefabricateTime",params.get("date"));
                    Integer num = this.getMap(m);
                    System.out.println("数量是："+num);
                    TPrefabricateOrder po = new TPrefabricateOrder();
                    po.setKitchenId(Integer.parseInt(params.get("kitchenId").toString()));
                    po.setFoodid(Integer.parseInt(params.get("foodid").toString()));
                    po.setSystemSum(newTotalPreSum);
                    po.setSum(newTotalPreSum);
                    po.setSellNum(0);
                    po.setRemainNum(newTotalPreSum);
                    po.setStatus(0);
                    po.setPrefabricateTime(params.get("date").toString());
                    po.setType(Integer.parseInt(params.get("pmtype").toString()));
                    if(num > 0){    //有数据 更新
                        this.batchUpdate(po);
                    }else{          //没有数据 新增
                        this.insert(po);
                    }
//                }
            }
        }
        //		 3. 改菜没有售罄
        else if(yzStatus == 3 ){
            int count = 0;	//记录共几 星期
            int sellTotalNum = 0;

            if(calendarList.size() > 0){

                for(int j=0;j<calendarList.size();j++){
                    System.out.println(calendarList.get(j));
                    params.put("laskWeekDay", calendarList.get(j));
                    prefabricate = this.getPrefabricate(params);
                    if(prefabricate != null ){
                        if(prefabricate.getSellNum() >0 ){		//求上周是否 节假日
                            if(params.get("foodid").toString().equals(prefabricate.getFoodid().toString())){
                                count++;
                                sellTotalNum+=prefabricate.getSellNum();
                            }
                        }

                    }
                }

                System.out.println(params.get("laskWeekDay") +  params.get("foodName").toString()+count
                        + "销售：" + sellTotalNum);
                newTotalPreSum =  OrderNoUtils.roundUp(NumberUtil.div(sellTotalNum,count));

                Map m = new HashMap();
                m.put("foodid",params.get("foodid"));
                m.put("kitchenId",params.get("kitchenId"));
                m.put("type",params.get("pmtype"));
                m.put("prefabricateTime",params.get("date"));
                Integer num = this.getMap(m);
                System.out.println("数量是："+num);
                TPrefabricateOrder po = new TPrefabricateOrder();
                po.setKitchenId(Integer.parseInt(params.get("kitchenId").toString()));
                po.setFoodid(Integer.parseInt(params.get("foodid").toString()));
                po.setSystemSum(newTotalPreSum);
                po.setSum(newTotalPreSum);
                po.setSellNum(0);
                po.setRemainNum(newTotalPreSum);
                po.setStatus(0);
                po.setPrefabricateTime(params.get("date").toString());
                po.setType(Integer.parseInt(params.get("pmtype").toString()));
                if(num > 0){    //有数据 更新
                    this.batchUpdate(po);
                }else{          //没有数据 新增
                    this.insert(po);
                }

            }

        }

    }

    /**
     * 根据当前日期  获取前四周除节假日的 日期
     * @param currentDate 当前日期
     * @return
     */
    private List getCalendates(String currentDate){


        List list = new ArrayList();

        //求出 上周日期
        DateTime laskWeek;

        laskWeek =	DateUtil.offsetDay(DateUtil.parse(currentDate), -7);

        //上2周日期
        DateTime laskWeek2 ;
        //上3周日期
        DateTime laskWeek3 ;

        //上4周日期
        DateTime laskWeek4;

        //判断是否节假日
        //上周日期
        String laskWeekDay;
        laskWeekDay	= laskWeek.toString().substring(0,10);
        //上2周日期
        String laskWeekDay2;
        //上3周日期
        String laskWeekDay3;
        //上四周日期
        String laskWeekDay4;

        Map params = new HashMap();
        params.put("calendarDate", laskWeekDay);
        TabCalendar tabCalendar = tabCalendarService.getCalendateByDate(params);
        int count = 0;
        if( tabCalendar !=null ){	// 证明节假日,需要求 上上 周 日期

            laskWeek =	DateUtil.offsetDay(DateUtil.parse(currentDate), -14);	//求14天 的日期
            laskWeekDay	= laskWeek.toString().substring(0,10);
            params.put("calendarDate", laskWeekDay);
            tabCalendar = tabCalendarService.getCalendateByDate(params);

            if(tabCalendar !=null ){	// 证明节假日，求 上2周日期
                laskWeek2 =	DateUtil.offsetDay(laskWeek, -7);
                laskWeekDay2  = laskWeek2.toString().substring(0,10);
                params.put("calendarDate", laskWeekDay2);
                TabCalendar tab2 = tabCalendarService.getCalendateByDate(params);
                if(tab2 != null){	// 节假日，求上上周日期
                    laskWeek3 =	DateUtil.offsetDay(laskWeek2, -14);
                    laskWeekDay3  = laskWeek3.toString().substring(0,10);
                    params.put("calendarDate", laskWeekDay3);
                    TabCalendar tab3 = tabCalendarService.getCalendateByDate(params);
                    if(tab3 !=null){	// 节假日，求上上周日期
                        laskWeek4 =	DateUtil.offsetDay(laskWeek3, -14);
                        laskWeekDay4  = laskWeek4.toString().substring(0,10);
                        params.put("calendarDate", laskWeekDay4);
                        TabCalendar tab4 = tabCalendarService.getCalendateByDate(params);
                        if(tab4 !=null ){	// 节假日，求上上周  日期
                            laskWeek4 =	DateUtil.offsetDay(laskWeek3, -14);
                            laskWeekDay4  = laskWeek4.toString().substring(0,10);
                            list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                            this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        }else{		// 非节假日， 求上4周日期
                            list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                            this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        }

                    }else{		// 非节假日， 求上4周日期
                        laskWeek4 =	DateUtil.offsetDay(laskWeek3, -7);
                        laskWeekDay4  = laskWeek4.toString().substring(0,10);
                        params.put("calendarDate", laskWeekDay4);
                        TabCalendar tab4 = tabCalendarService.getCalendateByDate(params);
                        if( tab4 !=null ){  //是节假日，求上4周日期
                            laskWeek4 =	DateUtil.offsetDay(laskWeek3, -14);
                            laskWeekDay4  = laskWeek4.toString().substring(0,10);
                            list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                            this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        }else{
                            list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                            this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);

                        }

                    }

                }else{		// 非节假日， 求上3周日期

                    laskWeek3 =	DateUtil.offsetDay(laskWeek2, -7);
                    laskWeekDay3  = laskWeek3.toString().substring(0,10);
                    params.put("calendarDate", laskWeekDay3);
                    TabCalendar tab3 = tabCalendarService.getCalendateByDate(params);
                    if( tab3 !=null ){		//是节假日，求上上周日期
                        laskWeek4 =	DateUtil.offsetDay(laskWeek3, -14);
                        laskWeekDay4  = laskWeek4.toString().substring(0,10);
                        params.put("calendarDate", laskWeekDay4);
                        TabCalendar tab4 = tabCalendarService.getCalendateByDate(params);
                        if(tab4 !=null ){	// 节假日，求上上周  日期
                            laskWeek4 =	DateUtil.offsetDay(laskWeek3, -14);
                            laskWeekDay4  = laskWeek4.toString().substring(0,10);
                            list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                            this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        }else{		// 非节假日， 求上4周日期
                            list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                            this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        }

                    }else{	// 非节假日， 求上4周日期

                        laskWeek4 =	DateUtil.offsetDay(laskWeek3, -7);
                        laskWeekDay4  = laskWeek4.toString().substring(0,10);
                        params.put("calendarDate", laskWeekDay4);
                        TabCalendar tab4 = tabCalendarService.getCalendateByDate(params);
                        if( tab4 !=null ){  //是节假日，求上4周日期
                            laskWeek4 =	DateUtil.offsetDay(laskWeek3, -14);
                            laskWeekDay4  = laskWeek4.toString().substring(0,10);
                            list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                            this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        }else{
                            list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                            this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);

                        }

                    }

                }



            }else{	//非节假日 求上2周日期

                laskWeek2 =	DateUtil.offsetDay(laskWeek, -7);
                laskWeekDay2  = laskWeek2.toString().substring(0,10);
                params.put("calendarDate", laskWeekDay2);
                TabCalendar tab2 = tabCalendarService.getCalendateByDate(params);
                if(tab2 !=null ){	//是节假日，求上上周日期
                    laskWeek3 =	DateUtil.offsetDay(laskWeek2, -14);
                    laskWeekDay3  = laskWeek3.toString().substring(0,10);
                    params.put("calendarDate", laskWeekDay3);
                    TabCalendar tab3 = tabCalendarService.getCalendateByDate(params);
                    if(tab3 !=null){	// 节假日，求上上周日期
                        laskWeek4 =	DateUtil.offsetDay(laskWeek3, -14);
                        laskWeekDay4  = laskWeek4.toString().substring(0,10);
                        params.put("calendarDate", laskWeekDay4);
                        TabCalendar tab4 = tabCalendarService.getCalendateByDate(params);
                        if(tab4 !=null ){	// 节假日，求上上周  日期
                            laskWeek4 =	DateUtil.offsetDay(laskWeek3, -14);
                            laskWeekDay4  = laskWeek4.toString().substring(0,10);
                            list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                            this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        }else{
                            list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                            this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        }

                    }else{		// 非节假日， 求上4周日期
                        laskWeek4 =	DateUtil.offsetDay(laskWeek3, -7);
                        laskWeekDay4  = laskWeek4.toString().substring(0,10);
                        params.put("calendarDate", laskWeekDay4);
                        TabCalendar tab4 = tabCalendarService.getCalendateByDate(params);
                        if( tab4 !=null ){  //是节假日，求上4周日期
                            laskWeek4 =	DateUtil.offsetDay(laskWeek3, -14);
                            laskWeekDay4  = laskWeek4.toString().substring(0,10);
                            list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                            this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        }else{
                            list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                            this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);

                        }

                    }

                }else{		// 非节假日， 求上3周日期


                    laskWeek3 =	DateUtil.offsetDay(laskWeek2, -7);
                    laskWeekDay3  = laskWeek3.toString().substring(0,10);
                    params.put("calendarDate", laskWeekDay3);
                    TabCalendar tab3 = tabCalendarService.getCalendateByDate(params);
                    if( tab3 !=null ){		//是节假日，求上上周日期
                        laskWeek4 =	DateUtil.offsetDay(laskWeek3, -14);
                        laskWeekDay4  = laskWeek4.toString().substring(0,10);
                        params.put("calendarDate", laskWeekDay4);
                        TabCalendar tab4 = tabCalendarService.getCalendateByDate(params);
                        if(tab4 !=null ){	// 节假日，求上上周  日期
                            laskWeek4 =	DateUtil.offsetDay(laskWeek3, -14);
                            laskWeekDay4  = laskWeek4.toString().substring(0,10);
                            list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                            this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        }else{
                            list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                            this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        }

                    }else{	// 非节假日， 求上4周日期

                        laskWeek4 =	DateUtil.offsetDay(laskWeek3, -7);
                        laskWeekDay4  = laskWeek4.toString().substring(0,10);
                        params.put("calendarDate", laskWeekDay4);
                        TabCalendar tab4 = tabCalendarService.getCalendateByDate(params);
                        if( tab4 !=null ){  //是节假日，求上4周日期
                            laskWeek4 =	DateUtil.offsetDay(laskWeek3, -14);
                            laskWeekDay4  = laskWeek4.toString().substring(0,10);
                            list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                            this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        }else{
                            list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                            this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);

                        }

                    }

                }
            }


            //*************** 第一次 非节假日   日期 n = 7;   t = 29   m = t-n*1
        }else{	//非 节假日   求上2周日期

            laskWeek2 =	DateUtil.offsetDay(laskWeek, -7);
            laskWeekDay2  = laskWeek2.toString().substring(0,10);
            params.put("calendarDate", laskWeekDay2);
            TabCalendar tab2 = tabCalendarService.getCalendateByDate(params);
            if(tab2 !=null ){	//是节假日，求上上周日期     上一周是节假日

                laskWeek2 =	DateUtil.offsetDay(laskWeek, -14);
                laskWeekDay2  = laskWeek2.toString().substring(0,10);

                laskWeek3 =	DateUtil.offsetDay(laskWeek2, -7);
                laskWeekDay3  = laskWeek3.toString().substring(0,10);
                params.put("calendarDate", laskWeekDay3);
                TabCalendar tab3 = tabCalendarService.getCalendateByDate(params);
                if(tab3 !=null){	// 节假日，求上上周日期
                    laskWeek4 =	DateUtil.offsetDay(laskWeek3, -7);
                    laskWeekDay4  = laskWeek4.toString().substring(0,10);
                    params.put("calendarDate", laskWeekDay4);
                    TabCalendar tab4 = tabCalendarService.getCalendateByDate(params);
                    if(tab4 !=null ){	// 节假日，求上上周  日期
                        laskWeek4 =	DateUtil.offsetDay(laskWeek3, -7);
                        laskWeekDay4  = laskWeek4.toString().substring(0,10);
                        list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                    }else{		// 非节假日， 求上4周日期
                        list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                    }

                }else{		// 非节假日， 求上4周日期
                    laskWeek4 =	DateUtil.offsetDay(laskWeek3, -7);
                    laskWeekDay4  = laskWeek4.toString().substring(0,10);
                    params.put("calendarDate", laskWeekDay4);
                    TabCalendar tab4 = tabCalendarService.getCalendateByDate(params);
                    if( tab4 !=null ){  //是节假日，求上4周日期
                        laskWeek4 =	DateUtil.offsetDay(laskWeek3, -14);
                        laskWeekDay4  = laskWeek4.toString().substring(0,10);
                        list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                    }else{
                        list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);

                    }

                }
            }else{		// 非节假日， 求上3周日期
                laskWeek3 =	DateUtil.offsetDay(laskWeek2, -7);
                laskWeekDay3  = laskWeek3.toString().substring(0,10);
                params.put("calendarDate", laskWeekDay3);
                TabCalendar tab3 = tabCalendarService.getCalendateByDate(params);
                if( tab3 !=null ){		//是节假日，求上上周日期


                    laskWeek3 =	DateUtil.offsetDay(laskWeek2, -14);
                    laskWeekDay3  = laskWeek3.toString().substring(0,10);

                    laskWeek4 =	DateUtil.offsetDay(laskWeek3, -7);
                    laskWeekDay4  = laskWeek4.toString().substring(0,10);
                    params.put("calendarDate", laskWeekDay4);
                    TabCalendar tab4 = tabCalendarService.getCalendateByDate(params);
                    if(tab4 !=null ){	// 节假日，求上上周  日期
                        laskWeek4 =	DateUtil.offsetDay(laskWeek3, -7);
                        laskWeekDay4  = laskWeek4.toString().substring(0,10);
                        list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                    }else{
                        list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                    }

                }else{	// 非节假日， 求上4周日期

                    laskWeek4 =	DateUtil.offsetDay(laskWeek3, -7);
                    laskWeekDay4  = laskWeek4.toString().substring(0,10);
                    params.put("calendarDate", laskWeekDay4);
                    TabCalendar tab4 = tabCalendarService.getCalendateByDate(params);
                    if( tab4 !=null ){  //是节假日，求上4周日期
                        laskWeek4 =	DateUtil.offsetDay(laskWeek3, -14);
                        laskWeekDay4  = laskWeek4.toString().substring(0,10);
                        list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                    }else{
                        list = this.listAdd(laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);
                        this.printlnDate(currentDate,laskWeekDay,laskWeekDay2,laskWeekDay3,laskWeekDay4);

                    }

                }

            }
        }
        System.out.println(JSONUtil.toJsonPrettyStr(list));
        return list;

    }



    /**
     * 统计上x周的 日期
     * @param n 上一周
     * @param n2 上二周
     * @param n3 上三周
     * @param n4 上4周
     * @return
     */
    private  List listAdd(String n,String n2,String n3,String n4){
        List list  = new ArrayList();
        list.add(n);
        list.add(n2);
        list.add(n3);
        list.add(n4);
        return list;
    }

    /**
     * 输入当前日期
     * @param n 当前日期
     * @param n1 上周日期
     * @param n2 上2周日期
     * @param n3 上3周日期
     * @param n4 上4周日期
     */
    private void printlnDate(String n,String n1,String n2,String n3,String n4){
        System.out.println("选择日期: " + n
                + "\n" + "上一周日期" + n1
                + "\n" + "上二周日期" + n2
                + "\n" + "上三周日期" + n3
                + "\n" + "上四周日期" + n4
        );
    }


    /**
     * 输入当前日期
     * @param n1 上周日期
     * @param n2 上2周日期
     * @param n3 上3周日期
     * @param n4 上4周日期
     */
    private void printlnDate(String n1,String n2,String n3,String n4){
        System.out.println("选择日期: "
                + "\n" + "上一周日期" + n1
                + "\n" + "上二周日期" + n2
                + "\n" + "上三周日期" + n3
                + "\n" + "上四周日期" + n4
        );
    }

}