package com.example.demo.dao;

import com.example.demo.core.universal.Mapper;
import com.example.demo.model.Person;
import com.santint.core.web.query.QueryFilter;

import java.util.List;
import java.util.Map;

public interface PersonMapper extends Mapper<Person> {


    List<Person> getAll(Map map);

    List<Person> getAll(QueryFilter filter);
}