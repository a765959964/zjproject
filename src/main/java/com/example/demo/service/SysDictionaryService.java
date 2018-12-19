package com.example.demo.service;

import com.example.demo.model.SysDictionary;
import com.example.demo.core.universal.Service;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
* @Description: SysDictionaryService接口
* @author zf
* @date 2018/12/12 10:02
*/
public interface SysDictionaryService extends Service<SysDictionary> {


    List<SysDictionary> getAll(Map map);
}