package com.example.demo.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.core.universal.AbstractService;
import com.example.demo.dao.TFoodWorkMapper;
import com.example.demo.model.TFoodWork;
import com.example.demo.service.TFoodWorkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description: TFoodWorkService接口实现类
* @author zf
* @date 2020/01/15 09:43
*/
@Service
public class TFoodWorkServiceImpl extends AbstractService<TFoodWork> implements TFoodWorkService {

    @Resource
    private TFoodWorkMapper tFoodWorkMapper;

    @Override
    public List<TFoodWork> getAll(Map map){
        return tFoodWorkMapper.getAll(map);
    }

    @Override
    public List getFoodWorkList(Map params) {
        Map m = new HashMap();
        if(params.get("kitchenId") != null){
            String kitchenId  = params.get("kitchenId").toString();
            m.put("kitchenId",kitchenId);
        }

        List<Map> listMap = new ArrayList<>();
        List list = tFoodWorkMapper.getFoodWorkList(params);
        JSONArray jsonArray = JSONUtil.parseArray(list);
        List li = new ArrayList();
        for(int i=0;i<jsonArray.size();i++){
            String str = "";
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            str = this.getSwitchWeek(jsonObject.get("week").toString());
            jsonObject.put("week",str);
            li.add(jsonObject);
        }
        return li;
    }

    @Override
    public List getKitFoodList(Map params) {

        return tFoodWorkMapper.getKitFoodList(params);
    }

    @Override
    public List getKitZzList(Map params) {
        return tFoodWorkMapper.getKitZzList(params);
    }

    @Override
    public Integer delByWorkDate(Map params) {

        return tFoodWorkMapper.delByWorkDate(params);
    }

    @Override
    public Integer getFoodWorkCount(Map params) {
        return tFoodWorkMapper.getFoodWorkCount(params);
    }

    /**
     * 根据当前值 显示 周一 周二    0 代表周一
     * @param week
     * @return
     */
    private String getSwitchWeek(String week){
        String str = "";
        switch (week) {
            case "0":
                str  = "星期一";
                break;
            case "1":
                str  = "星期二";
                break;
            case "2":
                str  = "星期三";
                break;
            case "3":
                str  = "星期四";
                break;
            case "4":
                str  = "星期五";
                break;
            case "5":
                str  = "星期六";
                break;
            case "6":
                str  = "星期日";
                break;
            default:
                break;
        }

        return str;
    }

}