package com.utobun.candy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.utobun.candy.domain.User;

@Controller
@RequestMapping("/")
public class CandyController {
    @RequestMapping("index.do")
    public ModelAndView index(){
        ModelAndView data = new ModelAndView();
        User user =new User();
        user.setUserName("hello");
        data.addObject("user",user);
        data.setViewName("index");
        return data;
    }
}
