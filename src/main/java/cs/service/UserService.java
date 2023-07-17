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
            System.out.println(us.getEmail() +"/"+ us.getPassword() +"///\n"+ u.getEmail() +"/+"+ u.getPassword());
            if(us.getEmail().equals(u.getEmail()) && us.getPassword().equals(u.getPassword())){
                System.out.println("achou o usuario");
                return us;
            }else{
                System.out.println("não achou o User");
            }
        }
        return null;
    }

    public User getUserId(int id){
        User u = this.ud.getUserPerId(id);
        return u;
    }


    public boolean insertUser(User u){
        ArrayList<User> u2 = ud.getUsers();
        for (User user: u2) {
            System.out.println(user.getEmail());
            System.out.println(u.getEmail());
            if(user.getEmail().equals(u.getEmail())){
                return false;
            }
        }
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
