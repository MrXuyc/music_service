package com.sunshine.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * thymeleaf  其中的标签必须进行闭合  3.0之前的版本
 */
@Controller
@RequestMapping(value = "/page")
public class PageController {
    @RequestMapping(value = "/index")
    public ModelAndView index(){
        ModelAndView mv=new ModelAndView("index");
        mv.addObject("name","index");
        return mv;
    }

    @RequestMapping(value = "/indexFreeMarker")
    public ModelAndView indexFreeMarker(){
        ModelAndView mv=new ModelAndView("indexFreeMarker");
        mv.addObject("name","indexFreeMarker");
        return mv;
    }

}
