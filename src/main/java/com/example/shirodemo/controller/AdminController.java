package com.example.shirodemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/{id}")
    public String v1(@PathVariable("id") int id){
        return "admin/"+id;
    }

}
