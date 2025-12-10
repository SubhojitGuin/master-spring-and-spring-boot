package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping("login")
    public String login(@RequestParam String name, ModelMap model) {
        // @RequestParam takes the name as a query parameter like ?name=Subho and the ModelMap stores the values to display in the JSP file as ${var_name}
        model.put("name", name);
        System.out.println("Request param is " + name);
        return "login";
    }
}
