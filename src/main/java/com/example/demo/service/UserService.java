package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.core.universal.Service;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
* @Description: UserService接口
* @author zf
* @date 2019/03/15 11:46
*/
public interface UserService extends Service<User> {


    List<User> getAll(Map map);
}