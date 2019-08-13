package com.example.demo.controller.system;

import com.example.demo.core.aop.AnnotationLog;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by 张帆 on 2018/9/25.
 */
@Controller
public class MainController {


    @Autowired
    private SysMenuService sysMenuService;


    @AnnotationLog("系统主页面")
    @RequiresAuthentication
    @RequestMapping("/index")
    public String index(Model model){
        Subject cur = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) cur.getPrincipal();
        if(sysUser ==null){
            return "views/login";
        }
//        List<SysMenu> sysMenuList = sysMenuService.getListByUserId(sysUser.getId()+"");
        List<SysMenu> sysMenuList = sysMenuService.getListByUserId(sysUser.getId()+"");
//        System.out.println("循环list={1}"+JSonUtils.toJSon(sysMenuList));
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

    @AnnotationLog("跳转到404页面")
    @RequestMapping("/404")
    public String r404(Model model){
        return "views/commons/404";
    }

    @AnnotationLog("跳转到500页面")
    @RequestMapping("/500")
    public String r500(Model model){
        return "views/commons/500";
    }

    @RequestMapping("/600")
    public String r600(Model model){
        return "views/commons/600";
    }

    @RequestMapping("/err")
    public String err(Model model){
        return "views/commons/error";
    }


    @RequestMapping("/logout")
    public String logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }


    /**
     * 在异常抛出的时候，controller 会使用@ExceptionHandler 注解处理异常，而不会抛给Servlet 容器
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public String testErrorHandler(Exception  e){
        return "views/login";
    }

}
