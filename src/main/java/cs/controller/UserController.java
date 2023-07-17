package cs.controller;


import cs.model.User;
import cs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;

@Controller
public class UserController {

    private UserService us = new UserService();
    private User u;
    private ArrayList<User> users = new ArrayList<>();

    @GetMapping("/login")
    public ModelAndView login(){
        System.out.println("login");
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @PostMapping("/signIn")
    public ModelAndView signIn(User user){
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println("logando");
        System.out.println(user);
        this.u = us.login(user);
        if(this.u != null) {
            ModelAndView mv = new ModelAndView("mainMenu");
            mv.addObject("user", this.u);
            return mv;
        }else {
            ModelAndView mv = new ModelAndView("index");
            return mv;
        }
    }


    @GetMapping("/cadastro")
    public ModelAndView cadastro(){
        System.out.println("cadastro");
        ModelAndView mv = new ModelAndView("insertUser");
        return mv;
    }

    @PostMapping("/register")
    public ModelAndView register(User u){
        System.out.println("registrando usuário");
        if(us.insertUser(u)){
            User u2 = us.login(u);
            ModelAndView mv = new ModelAndView("mainMenu");
            mv.addObject("user", u2);
            return mv;
        }else{
            ModelAndView mv = new ModelAndView("index");
            mv.addObject("error", "Já existe um usuário com este e-mail! Use outro.");
            return mv;
        }
    }

    @GetMapping("/user/logout")
    public ModelAndView logout(){
        System.out.println("deslogando");
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @GetMapping("/voltarUser/{id}")
    public ModelAndView voltarCartoon(@PathVariable int id){
        User user = us.getUserId(id);
        ModelAndView mv =  new ModelAndView("mainMenu");
        mv.addObject("user", user);
        return mv;
    }


}
