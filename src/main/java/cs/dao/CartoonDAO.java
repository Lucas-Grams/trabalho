package cs.dao;

import cs.model.Cartoon;
import cs.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CartoonDAO {
    private String sql;
    private PreparedStatement stmt;
    private ResultSet rs;
    public ArrayList<Cartoon> getCartoons(int id){
        ArrayList<Cartoon> cartoons = new ArrayList<Cartoon>();
        Cartoon cartoon = new Cartoon();
        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "Select * FROM cartoon WHERE id_user = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setInt(1,id);
            this.rs = this.stmt.executeQuery();
            while(rs.next()){
                cartoon.setId(rs.getInt("id_cartoon"));
                cartoon.setTitle(rs.getString("title"));
                cartoon.setNote(rs.getInt("note"));
                cartoon.setEpisodes(rs.getInt("episodes"));
                cartoon.setPlataform(rs.getString("plataform"));
                cartoons.add(cartoon);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cartoons;
    }

    public Cartoon getCartoon(int id){
        Cartoon cartoon = new Cartoon();
        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "Select * FROM cartoon WHERE id_cartoon = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setInt(1,id);
            this.rs = this.stmt.executeQuery();
            while(rs.next()){
                cartoon.setId(rs.getInt("id_cartoon"));
                cartoon.setTitle(rs.getString("title"));
                cartoon.setNote(rs.getInt("note"));
                cartoon.setEpisodes(rs.getInt("episodes"));
                cartoon.setPlataform(rs.getString("plataform"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cartoon;
    }
    public boolean setCartoon(Cartoon c, int id){
        try(Connection connection =  new ConnectDB().getConexao()){
            this.sql = "INSERT INTO cartoon (title, note, episodes, platform, id_user) VALUES (?, ?, ?, ?, ?)";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setString(1, c.getTitle());
            this.stmt.setInt(2, c.getNote());
            this.stmt.setInt(3, c.getEpisodes());
            this.stmt.setString(4, c.getPlataform());
            this.stmt.setInt(5, id);
            this.stmt.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean upCartoon(Cartoon c){
        try(Connection connection = new ConnectDB().getConexao()) {
            this.sql = "UPDATE cartoon SET title = ?, note = ?, episodes = ?, plataform = ? WHERE id_cartoon = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setString(1, c.getTitle());
            this.stmt.setInt(2, c.getNote());
            this.stmt.setInt(3, c.getEpisodes());
            this.stmt.setString(4, c.getPlataform());
            this.stmt.setInt(5, c.getId());
            this.stmt.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean dellCartoon(int id){
        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "DELETE FROM cartton WHERE id_cartoon = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setInt(1, id);
            return this.stmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
