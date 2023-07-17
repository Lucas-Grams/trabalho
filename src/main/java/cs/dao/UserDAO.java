package cs.dao;

import cs.model.User;
import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    private String sql;
    private PreparedStatement stmt;
    private ResultSet rs;
    public ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList<User>();

        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "SELECT * FROM user_;";
            this.stmt = connection.prepareStatement(this.sql);
            this.rs = stmt.executeQuery();
            while(this.rs.next()){
                User user = new User();
                user.setId(this.rs.getInt("id_user"));
                user.setName(this.rs.getString("name"));
                user.setCpf(this.rs.getString("cpf"));
                user.setEmail(this.rs.getString("email"));
                user.setPassword(this.rs.getString("password"));
                users.add(user);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("buscou todos os users");
        return users;
    }

    public User getUser(int id){
        User user = new User();
        try(Connection connection = new ConnectDB().getConexao()){
        this.sql = "SELECT * FROM user_ WHERE id_user = ?";
        this.stmt = connection.prepareStatement(this.sql);
        this.stmt.setInt(1, id);
        this.rs = stmt.executeQuery();
        while(this.rs.next()){
            user.setId(this.rs.getInt("id_user"));
            user.setName(this.rs.getString("name"));
            user.setCpf(this.rs.getString("cpf"));
            user.setEmail(this.rs.getString("email"));
            user.setPassword(this.rs.getString("password"));
        }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public User getUserPerId(int id){
        User user = new User();
        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "SELECT * FROM user_ WHERE id_user = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setInt(1, id);
            this.rs = this.stmt.executeQuery();
            while(this.rs.next()){
                user.setId(this.rs.getInt("id_user"));
                user.setName(this.rs.getString("name"));
                user.setCpf(this.rs.getString("cpf"));
                user.setEmail(this.rs.getString("email"));
                user.setPassword(this.rs.getString("password"));
            }
            return user;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    public boolean setUser(User u){//Método utilizado para inserir um usuário no banco de dados//
        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "INSERT INTO user_ (name, cpf, email, password) VALUES (?,?,?,?)";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setString(1, u.getName());
            this.stmt.setString(2, u.getCpf());
            this.stmt.setString(3, u.getEmail());
            this.stmt.setString(4, u.getPassword());
            this.stmt.execute();
            System.out.println("inseriu o user");
            return true;
            }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean upUser(User u){//Método utilizadp para editar um usuário no banco de dados//
        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "UPDATE user_ SET name = ?, cpf = ?, email = ?, password = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setString(1, u.getName());
            this.stmt.setString(2, u.getCpf());
            this.stmt.setString(3, u.getEmail());
            this.stmt.setString(4, u.getPassword());
            this.stmt.executeQuery();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean dellUser(int id){//método utilizado para deletar um usuário do banco de dados//
        try(Connection connection = new ConnectDB().getConexao()){
            this.sql = "DELETE FROM user_ WHERE id_user = ?";
            this.stmt = connection.prepareStatement(this.sql);
            this.stmt.setInt(1, id);
            this.stmt.executeQuery();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
