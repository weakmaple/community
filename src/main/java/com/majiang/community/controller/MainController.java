package com.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @objective :
 * @date :2019/9/22- 8:50
 */
@Controller
public class MainController {

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("!!!!");
        return "he";
    }
}
