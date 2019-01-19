package com.sunshine.basepro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * thymeleaf  其中的标签必须进行闭合  3.0之前的版本
 */
@Controller
@RequestMapping(value = "/page")
public class PageController {
    @RequestMapping(value = "/index")
    public ModelAndView index(){
        ModelAndView mv=new ModelAndView("index");
//        map.put("name","徐彦春");
        mv.addObject("name","徐彦春");
        return mv;
    }

    @RequestMapping(value = "/indexFreeMarker")
    public ModelAndView indexFreeMarker(){
        ModelAndView mv=new ModelAndView("indexFreeMarker");
//        map.put("name","徐彦春");
        mv.addObject("name","徐彦春");
        return mv;
    }

}
