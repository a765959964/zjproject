package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.core.universal.Service;
import com.santint.core.web.query.QueryFilter;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
* @Description: PersonService接口
* @author zf
* @date 2018/09/26 16:23
*/
public interface PersonService extends Service<Person> {


    List<Person> getAll(Map map);

    List<Person> getAll(QueryFilter filter);
}