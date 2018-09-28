package com.example.demo.service.impl;

import com.example.demo.dao.PersonMapper;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.example.demo.core.universal.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @Description: PersonService接口实现类
* @author zf
* @date 2018/09/26 16:23
*/
@Service
public class PersonServiceImpl extends AbstractService<Person> implements PersonService {

    @Resource
    private PersonMapper personMapper;

}