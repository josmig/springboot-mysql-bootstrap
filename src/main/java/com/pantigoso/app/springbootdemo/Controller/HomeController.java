package com.pantigoso.app.springbootdemo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping({"home","/"})
    public String index(Model model){
        String titulo = "Home";
        model.addAttribute("title",titulo);
        return "index";
    }


}
