package cs.service;

import cs.dao.BookDAO;
import cs.model.Book;
import java.util.ArrayList;

public class BookService {

    private BookDAO bd = new BookDAO();

    public Book listBook(int id){
        Book book = new Book();
        book = bd.getBook(id);
        return book;
    }

    public ArrayList<Book> listBooks(int idUser){
        ArrayList<Book> books = new ArrayList<>();
        books = bd.getBooks(idUser);
        return books;
    }

    public boolean insertBook(Book b, int id){
        if(bd.setBook(b, id)){
            System.out.println("livro inserido com sucesso");
            return true;
        }else{
            System.out.println("de pau");
            return false;
        }
    }

    public boolean editBook(Book b){
        if(bd.upBook(b)){
            System.out.println("editou com sucesso");
            return true;
        }else{
            System.out.println("deu pau");
            return false;
        }
    }

    public boolean dellBook(int id){
        if(bd.dellBook(id)){
            System.out.println("exlcuido com sucesso");
            return true;
        }else{
            System.out.println("deu pau");
            return false;
        }
    }

}
