package cs.controller;

import cs.model.Cartoon;
import cs.model.Movie;
import cs.service.MovieService;
import cs.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;

@Controller
public class MovieController {

    private MovieService ms = new MovieService();
    private Movie m;
    private ArrayList<Movie> movies;

    private UserService us = new UserService();


    @GetMapping("listMovie/{id}")
    public ModelAndView listMovie(@PathVariable int id){
        movies = ms.listMovies(id);
        int soma = 0;
        for (Movie ma:movies) {
            soma += ma.getDuration();
        };
        ModelAndView mv = new ModelAndView("listMovie");
        mv.addObject("movies", movies);
        mv.addObject("id", id);
        mv.addObject("soma", soma);
        return mv;
    }

    @GetMapping("addMovie/{id}")
    public ModelAndView addMovie(@PathVariable int id){
        ModelAndView mv = new ModelAndView("insertMovie");
        mv.addObject("id", id);
        return mv;
    }


    @PostMapping("insertMovie/{id}")
    public ModelAndView insertMovie(Movie m, @PathVariable int id){
        ms.insertMovie(m, id);
        return this.listMovie(id);
    }

    @GetMapping("editMovie/{idMovie}/{id}")
    public ModelAndView editMovie(@PathVariable int idMovie, @PathVariable int id){
        Movie m = ms.listMovie(idMovie);
        ModelAndView mv = new ModelAndView("editMovie");
        mv.addObject("movie", m);
        mv.addObject("id", id);
        return mv;
    }

    @PostMapping("updateMovie/{id}")
    public ModelAndView updateMovie( Movie m, @PathVariable int id){
        if(ms.editMovie(m)){
            return this.listMovie(id);
        }else{
            return this.listMovie(id);
        }
    }

    @GetMapping("dellMovie/{idMovie}/{id}")
    public ModelAndView dellMovie(@PathVariable int idMovie, @PathVariable int id){
        if(ms.dellMovie(idMovie)){
            return this.listMovie(id);
        }else{
            return this.listMovie(id);
        }
    }

}
