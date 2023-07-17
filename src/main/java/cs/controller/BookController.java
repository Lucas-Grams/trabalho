package cs.controller;

import cs.model.Book;
import cs.model.Cartoon;
import cs.model.User;
import cs.service.BookService;
import cs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;

@Controller
public class BookController {

    private BookService bs = new BookService();
    private Book b;
    private ArrayList<Book> books;

    private UserService us = new UserService();


    @GetMapping("listBook/{id}")
    public ModelAndView listBook(@PathVariable int id){
        books = bs.listBooks(id);
        int soma = 0;
        for (Book ba:books) {
            soma += ba.getPages();
        };
        ModelAndView mv = new ModelAndView("listBook");
        mv.addObject("book", books);
        mv.addObject("id", id);
        mv.addObject("soma", soma);
        return mv;
    }

    @GetMapping("addBook/{id}")
    public ModelAndView addBook(@PathVariable int id){
        ModelAndView mv = new ModelAndView("insertBook");
        mv.addObject("id", id);
        return mv;
    }


    @PostMapping("insertBook/{id}")
    public ModelAndView insertBook(Book b, @PathVariable int id){
        bs.insertBook(b, id);
        return this.listBook(id);
    }

    @GetMapping("editBook/{idBook}/{id}")
    public ModelAndView editBook(@PathVariable int idBook, @PathVariable int id){
        Book b = bs.listBook(idBook);
        ModelAndView mv = new ModelAndView("editBook");
        mv.addObject("book", b);
        mv.addObject("id", id);
        return mv;
    }

    @PostMapping("updateBook/{id}")
    public ModelAndView updateBook(Book b, @PathVariable int id){
        if(bs.editBook(b)){
            return this.listBook(id);
        }else{
            return this.listBook(id);
        }
    }

    @GetMapping("dellBook/{idBook}/{id}")
    public ModelAndView dellMovie(@PathVariable int idBook, @PathVariable int id){
        if(bs.dellBook(idBook)){
            return this.listBook(id);
        }else{
            return this.listBook(id);
        }
    }

}
