package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name") //Stores the data in the session storage, so it is present in the jsp through the model
public class WelcomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String gotoWelcomePage() {
        return "welcome";
    }
}
