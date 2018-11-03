package com.example.demo.controller;

import com.example.demo.dto.SysMenuDto;
import com.example.demo.model.SysMenu;
import com.example.demo.model.SysUser;
import com.example.demo.service.SysMenuService;
import com.santint.core.util.BeanUtils;
import com.santint.core.util.JSonUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 张帆 on 2018/9/25.
 */
@Controller
public class MainController {


    @Autowired
    private SysMenuService sysMenuService;


    @RequestMapping("/index")
    public String index(Model model){
        Subject cur = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) cur.getPrincipal();
        List<SysMenu> sysMenuList = sysMenuService.getListByUserId("4");
        System.out.println("循环list={1}"+JSonUtils.toJSon(sysMenuList));
        model.addAttribute("sysMenuList",sysMenuList);
        return "views/index";
    }




    @RequestMapping("/login")
    public String login(Model model){
        return "views/login";
    }
}
