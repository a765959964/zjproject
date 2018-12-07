package com.example.demo.service.impl;

import com.example.demo.dao.Demo3Mapper;
import com.example.demo.model.Demo3;
import com.example.demo.service.Demo3Service;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: Demo3Service接口实现类
* @author zf
* @date 2018/12/07 16:18
*/
@Service
public class Demo3ServiceImpl extends AbstractService<Demo3> implements Demo3Service {

    @Resource
    private Demo3Mapper demo3Mapper;

    @Override
    public List<Demo3> getAll(Map map){
        return demo3Mapper.getAll(map);
    }

}