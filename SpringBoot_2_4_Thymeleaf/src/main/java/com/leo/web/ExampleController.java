package com.leo.web;

import com.leo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ExampleController {

    @RequestMapping("/inline")
    public String inline(ModelMap map) {
        map.addAttribute("userName", "leo");
        return "inline";
    }

    @RequestMapping("/object")
    public String object(HttpServletRequest request) {
        request.setAttribute("request", "i am request");
        request.getSession().setAttribute("session", "i am session");
        return "object";
    }

    @RequestMapping("/utility")
    public String utility(ModelMap map) {
        map.addAttribute("userName", "leo");
        map.addAttribute("users", getUserList());
        map.addAttribute("count", 12);
        map.addAttribute("date", new Date());
        return "utility";
    }

    private List<User> getUserList(){
        List<User> list=new ArrayList<User>();
        User user1=new User("大牛",12,"123456");
        User user2=new User("小牛",6,"123563");
        User user3=new User("牛魔王",66,"666666");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return list;
    }
}
