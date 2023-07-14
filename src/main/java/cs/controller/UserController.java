package cs.controller;


import cs.model.User;
import cs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class UserController {

    private UserService us;
    private User u;
    private ArrayList<User> users = new ArrayList<>();

    @GetMapping("/login")
    public String login(){
        System.out.println("login");

        return "index";
    }

    @GetMapping("cadastro")
    public String cadastro(){
        System.out.println("cadastro");
        return "insertUser";
    }
}
