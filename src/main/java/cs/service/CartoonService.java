package cs.service;

import cs.dao.CartoonDAO;
import cs.model.Cartoon;

import java.util.ArrayList;

public class CartoonService {

    private CartoonDAO cd = new CartoonDAO();

    public Cartoon listCartoon(int id){
        Cartoon cartoon = new Cartoon();
        cartoon = cd.getCartoon(id);
        return cartoon;
    }

    public ArrayList<Cartoon> listCartoons(int idUser){
        ArrayList<Cartoon> cartoons = new ArrayList<>();
        cartoons = cd.getCartoons(idUser);
        return cartoons;
    }

    public boolean insertCartoon(Cartoon c, int id){
        if(cd.setCartoon(c, id)){
            System.out.println("desenho inserido com sucesso");
            return true;
        }else{
            System.out.println("de pau");
            return false;
        }
    }

    public boolean editCartoon(Cartoon c){
        if(cd.upCartoon(c)){
            System.out.println("editou com sucesso");
            return true;
        }else{
            System.out.println("deu pau");
            return false;
        }
    }

    public boolean dellCartoon(int id){
        if(cd.dellCartoon(id)){
            System.out.println("exlcuido com sucesso");
            return true;
        }else{
            System.out.println("deu pau");
            return false;
        }
    }

}
