package com.example.demo.service.impl;

import com.example.demo.dao.PersonMapper;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.example.demo.core.universal.AbstractService;
import com.santint.core.web.query.QueryFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Description: PersonService接口实现类
* @author zf
* @date 2018/09/26 16:23
*/
@Service
public class PersonServiceImpl extends AbstractService<Person> implements PersonService {

    @Resource
    private PersonMapper personMapper;

    @Override
    public List<Person> getAll(Map map) {
        return personMapper.getAll(map);
    }

    @Override
    public List<Person> getAll(QueryFilter filter) {
        return personMapper.getAll(filter);
    }
}