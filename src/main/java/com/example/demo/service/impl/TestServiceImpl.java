package com.example.demo.service.impl;

import com.example.demo.dao.TestMapper;
import com.example.demo.model.Test;
import com.example.demo.service.TestService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: TestService接口实现类
* @author zf
* @date 2019/03/15 15:21
*/
@Service
public class TestServiceImpl extends AbstractService<Test> implements TestService {

    @Resource
    private TestMapper testMapper;

    @Override
    public List<Test> getAll(Map map){
        return testMapper.getAll(map);
    }

}