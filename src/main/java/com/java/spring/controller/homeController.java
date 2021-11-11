package com.java.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class homeController {
    @GetMapping("/home/{myname}")
    @ResponseBody
    public String index(@PathVariable(value = "myname") String name) {
        return String.format("Hello %s!", name);
    }

//    @GetMapping("/test")

//    public ModelAndView test() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index.html");
//        return modelAndView;
//    }
    public String getWelcomePage(){
      return "test.html";
  }
}
