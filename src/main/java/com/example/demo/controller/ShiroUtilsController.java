package com.example.demo.controller;

import com.example.demo.model.SysUser;
import com.example.demo.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
@RequestMapping("shiroUtils")
public class ShiroUtilsController {

    @Resource
    private ShiroService shiroService;

    @GetMapping("/noLogin")
    public String noLogin() {
        return "redirect:/login";
    }

    @GetMapping("/noAuthorize")
    public void noAuthorize() {
        throw new UnauthorizedException();
    }


    @PostMapping("/getNowUser")
    public SysUser getNowUser() {
        SysUser u = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return u;
    }

    /**
     * @Description: 重新加载shiro权限
     * @throws Exception
     */
    @PostMapping("/updatePermission")
    public void updatePermission() throws Exception {
        shiroService.updatePermission();
    }

    @RequestMapping("/logout")
    public String logOut() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

}
