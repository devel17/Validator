/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.validator.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author devel
 */
@Controller
@RequestMapping("/ui")
public class IndexController {

    @RequestMapping(value = "/") 
    public String index() {
        return "index";
    }
    
    @RequestMapping(value = "/page") 
    public String page() {
        return "page";
    }

}
