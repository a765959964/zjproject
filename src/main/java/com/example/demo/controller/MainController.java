package com.example.demo.controller;

import com.example.demo.model.SysMenu;
import com.example.demo.model.SysUser;
import com.example.demo.service.SysMenuService;
import com.santint.core.util.JSonUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by 张帆 on 2018/9/25.
 */
@Controller
public class MainController {


    @Autowired
    private SysMenuService sysMenuService;

    @RequiresAuthentication
    @RequestMapping("/index")
    public String index(Model model){
        Subject cur = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) cur.getPrincipal();
//        List<SysMenu> sysMenuList = sysMenuService.getListByUserId(sysUser.getId()+"");
        List<SysMenu> sysMenuList = sysMenuService.getListByUserId(1+"");
        System.out.println("循环list={1}"+JSonUtils.toJSon(sysMenuList));
        model.addAttribute("username",sysUser.getName());
        model.addAttribute("sysMenuList",sysMenuList);
        return "views/index";
    }




    @RequestMapping("/login")
    public String login(Model model){
        return "views/login";
    }

    @RequestMapping("/reg")
    public String reg(Model model){
        return "views/reg";
    }

    @RequestMapping("/logout")
    public String logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

}
