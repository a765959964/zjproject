package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 张帆 on 2018/9/25.
 */
@Controller
public class MainController {

    @RequestMapping("/index")
    public String index(Model model){
        return "views/index";
    }

    @RequestMapping("/login")
    public String login(Model model){
        return "views/login";
    }
}
