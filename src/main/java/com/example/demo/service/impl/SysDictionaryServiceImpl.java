package com.example.demo.service.impl;

import com.example.demo.dao.SysDictionaryMapper;
import com.example.demo.model.SysDictionary;
import com.example.demo.service.SysDictionaryService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: SysDictionaryService接口实现类
* @author zf
* @date 2018/12/12 10:02
*/
@Service
public class SysDictionaryServiceImpl extends AbstractService<SysDictionary> implements SysDictionaryService {

    @Resource
    private SysDictionaryMapper sysDictionaryMapper;

    @Override
    public List<SysDictionary> getAll(Map map){
        return sysDictionaryMapper.getAll(map);
    }

}