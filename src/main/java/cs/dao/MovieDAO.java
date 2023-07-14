package cs.dao;

import cs.model.Movie;
import cs.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieDAO {
    private String sql;
    private PreparedStatement stmt;
    private ResultSet rs;

    public ArrayList<Movie> getMovies(int id){
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Movie movie = new Movie();
        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "Select * FROM movie WHERE id_user = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setInt(1,id);
            this.rs = this.stmt.executeQuery();
            while(rs.next()){
                movie.setId(rs.getInt("id_filme"));
                movie.setTitle(rs.getString("title"));
                movie.setNote(rs.getInt("note"));
                movie.setDuration(rs.getInt("duration"));
                movie.setPlataform(rs.getString("plataform"));
                movies.add(movie);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return movies;
    }

    public Movie getMovie(int id){
        Movie movie = new Movie();
        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "SELECT * FROM movie WHERE id_filme = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setInt(1, id);
            this.rs = this.stmt.executeQuery();
            while(rs.next()){
                movie.setId(rs.getInt("id_filme"));
                movie.setTitle(rs.getString("title"));
                movie.setNote(rs.getInt("note"));
                movie.setDuration(rs.getInt("duration"));
                movie.setPlataform(rs.getString("plataform"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return movie;
    }

    public boolean setMovie(Movie m, int id){
        try(Connection connection =  new ConnectDB().getConexao()){
            this.sql = "INSERT INTO movie (title, note, duration, platform, id_user) VALUES (?, ?, ?, ?, ?)";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setString(1, m.getTitle());
            this.stmt.setInt(2, m.getNote());
            this.stmt.setInt(3, m.getDuration());
            this.stmt.setString(4, m.getPlataform());
            this.stmt.setInt(5, id);
            this.stmt.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean upMovie(Movie m){
        try(Connection connection = new ConnectDB().getConexao()) {
            this.sql = "UPDATE movie SET title = ?, note = ?, duration = ?, plataform = ? WHERE id_movie = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setString(1, m.getTitle());
            this.stmt.setInt(2, m.getNote());
            this.stmt.setInt(3, m.getDuration());
            this.stmt.setString(4, m.getPlataform());
            this.stmt.setInt(5, m.getId());
            this.stmt.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean dellMovie(int id){
        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "DELETE FROM movie WHERE id_movie = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setInt(1, id);
            return this.stmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
