package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //To tell Spring that it handles web requests
public class SayHelloController {
    @RequestMapping("say-hello")
    @ResponseBody // Returns the message as it is to the browser
    public String sayHello() {
        return "Hello! What are you learning today?";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title> My First HTML Page </title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h1> My First HTML Page with body </h1>");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }

    // Without the @ResponseBody, the string refers to the name of the jsp file
    // For handling jsp, we need the dependency tomcat-embed-jasper
    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp() {
        return "sayHello";
    }
}
