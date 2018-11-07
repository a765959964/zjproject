package com.example.demo.service.impl;

import com.example.demo.dao.TFoodCommonTypeMapper;
import com.example.demo.dto.TreeDto;
import com.example.demo.model.TFoodCommonType;
import com.example.demo.service.TFoodCommonTypeService;
import com.example.demo.core.universal.AbstractService;
import com.santint.core.web.query.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @Description: TFoodCommonTypeService接口实现类
* @author zf
* @date 2018/11/02 13:45
*/
@Service
public class TFoodCommonTypeServiceImpl extends AbstractService<TFoodCommonType> implements TFoodCommonTypeService {

    @Resource
    private TFoodCommonTypeMapper tFoodCommonTypeMapper;

    @Override
    public List<TFoodCommonType> getAll(Map map){
        return tFoodCommonTypeMapper.getAll(map);
    }

    @Override
    public List<TFoodCommonType> getFoodFiledLevel(Map map) {
        TFoodCommonType food = new TFoodCommonType();
        List<TFoodCommonType> listType =  tFoodCommonTypeMapper.getFoodFiledLevel(map);
        return    getParen(listType);
    }

    @Override
    public List<TreeDto> getTreeDto(Map map) {
//        List<TreeDto> treeDtoList =  tFoodCommonTypeMapper.getTreeDto(map);
//        return getParenTreeDto(treeDtoList);
        return  tFoodCommonTypeMapper.getTreeDto(map);

    }


    private List<TreeDto> getParenTreeDto(List<TreeDto> foodList){
        List<TreeDto> tfList = new ArrayList<>();

        for (TreeDto tf  :foodList) {
            //查找根目录
            if(tf.getPcode().equals("0")){
                tfList.add(getChildrenDto(tf,foodList));
            }
        }
        return tfList;

    }

    private TreeDto getChildrenDto(TreeDto tf,List<TreeDto> foodList){
        for(TreeDto  ct:foodList){
            if(ct.getPcode().equals(tf.getCode())){
                if(tf.getChildren()==null){
                    tf.setChildren(new ArrayList<TreeDto>());
                }
                tf.getChildren().add(getChildrenDto(ct,foodList));
            }
        }
        return tf;
    }


    /**
     * 获得全部菜系分类  递归实现
     * @param foodList
     * @return
     */
    private List<TFoodCommonType> getParen(List<TFoodCommonType> foodList){
        List<TFoodCommonType> tfList = new ArrayList<>();

        for (TFoodCommonType tf  :foodList) {
            //查找根目录
            if(tf.getPcode().equals("0")){
                tfList.add(getChildren(tf,foodList));
            }
        }
        return tfList;

    }

    private TFoodCommonType getChildren(TFoodCommonType tf,List<TFoodCommonType> foodList){
            for(TFoodCommonType  ct:foodList){
                if(ct.getPcode().equals(tf.getCode())){
                    if(tf.getChildren()==null){
                        tf.setChildren(new ArrayList<TFoodCommonType>());
                    }
                    tf.getChildren().add(getChildren(ct,foodList));
                }
            }
        return tf;
    }

    @Override
    public List findByCode(Map map){
        return tFoodCommonTypeMapper.findByCode(map);
    }

    @Override
    public Integer findByCount(Map map) {
        return tFoodCommonTypeMapper.findByCount(map);
    }
}