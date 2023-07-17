package cs.service;

import cs.dao.MovieDAO;
import cs.model.Movie;

import java.util.ArrayList;

public class MovieService {

    private MovieDAO md = new MovieDAO();

    public Movie listMovie(int id){
        Movie movie = new Movie();
        movie = md.getMovie(id);
        return movie;
    }

    public ArrayList<Movie> listMovies(int idUser){
        ArrayList<Movie> movies = new ArrayList<>();
        movies = md.getMovies(idUser);
        return movies;
    }

    public boolean insertMovie(Movie m, int id){
        if(md.setMovie(m, id)){
            System.out.println("deu tudo certo negao");
            return true;
        }else{
            System.out.println("queboru o brique do filme");
            return false;
        }
    }

    public boolean editMovie(Movie m){
        System.out.println(m.getId());
        if(md.upMovie(m)){
            System.out.println("deu certo pexe");
            return true;
        }else{
            System.out.println("deu merda no brique");
            return false;
        }
    }

    public boolean dellMovie(int id){
        if(md.dellMovie(id)){
            System.out.println("exlcuido com sucesso");
            return true;
        }else{
            System.out.println("deu pau na exclusao");
            return false;
        }
    }

}
