package cs.controller;

import cs.model.Cartoon;
import cs.model.User;
import cs.service.CartoonService;
import cs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class CartoonController {

    private CartoonService cs = new CartoonService();
    private UserService us = new UserService();
    private Cartoon c;
    private ArrayList<Cartoon> cartoons =  new ArrayList<>();


    @GetMapping("/listCartoon/{id}")
    public ModelAndView listCartoon(@PathVariable int id){
        cartoons = cs.listCartoons(id);
        int soma = 0;
        for (Cartoon ca:cartoons) {
            soma += ca.getEpisodes();
        };
        ModelAndView mv = new ModelAndView("listCartoon");
        mv.addObject("cartoon", cartoons);
        mv.addObject("id", id);
        mv.addObject("soma", soma);
        return mv;
    }

    @GetMapping("addCartoon/{id}")
    public ModelAndView addCartoon(@PathVariable int id){
        ModelAndView mv = new ModelAndView("insertCartoon");
        mv.addObject("id", id);
        return mv;
    }


    @PostMapping("insertCartoon/{id}")
    public ModelAndView insertCartoon(Cartoon c, @PathVariable int id){
        cs.insertCartoon(c, id);
        return this.listCartoon(id);
    }

    @GetMapping("editCartoon/{idCartoon}/{id}")
    public ModelAndView editCartoon(@PathVariable int idCartoon, @PathVariable int id){
        System.out.println(idCartoon);
        System.out.println(id);
        Cartoon c = cs.listCartoon(idCartoon);
        ModelAndView mv = new ModelAndView("editCartoon");
        mv.addObject("cartoon", c);
        mv.addObject("id", id);
        return mv;
    }

    @PostMapping("updateCartoon/{id}")
    public ModelAndView updateCartoon(Cartoon c, @PathVariable int id){
        if(cs.editCartoon(c)){
            return this.listCartoon(id);
        }else{
            return this.listCartoon(id);
        }
    }

    @GetMapping("dellCartoon/{idCartoon}/{id}")
    public ModelAndView dellCartoon(@PathVariable int idCartoon, @PathVariable int id){
        if(cs.dellCartoon(idCartoon)){
            return this.listCartoon(id);
        }else{
            return this.listCartoon(id);
        }
    }


}
