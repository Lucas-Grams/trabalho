package cs.service;

import cs.dao.UserDAO;
import cs.model.User;

import java.util.ArrayList;

public class UserService {
    private UserDAO ud = new UserDAO();
    public User login(User u){
        ArrayList<User> users = new ArrayList<User>();
        users = this.ud.getUsers();
        for (User us: users) {
            if(u.getEmail().equals(us.getEmail()) && u.getPassword().equals(us.getPassword())){
                return us;
            }
        }
    System.out.println("não achou o User");
        return null;
    }

    public User getUserId(int id){
        User u = this.ud.getUserPerId(id);
        return u;
    }


    public boolean insertUser(User u){
        if(this.ud.setUser(u)){
            return true;
        }
        System.out.println("Não inseriu o user");
        return false;
    }

    public boolean editUser(User u){
        if(this.ud.upUser(u)){
            return true;
        }
        System.out.println("noa editou o user");
        return false;
    }

    public boolean dellUser(int id){
        if(this.ud.dellUser(id)){
            return true;
        }
        System.out.println("nao deletou o user");
        return false;
    }


}
