package cs.dao;

import cs.model.Book;
import cs.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
    public class BookDAO {
        private String sql;
        private PreparedStatement stmt;
        private ResultSet rs;

        public ArrayList<Book> getBooks(int id){
            ArrayList<Book> movies = new ArrayList<Book>();
            Book book = new Book();
            try(Connection connection = new ConnectDB().getConexao()){
                this.sql = "Select * FROM book WHERE id_user = ?";
                this.stmt = connection.prepareStatement(this.sql);
                this.stmt.setInt(1,id);
                this.rs = this.stmt.executeQuery();
                while(rs.next()){
                    book.setId(rs.getInt("id_book"));
                    book.setTitle(rs.getString("title"));
                    book.setNote(rs.getInt("note"));
                    book.setPages(rs.getInt("pages"));
                    book.setPlataform(rs.getString("plataform"));
                    movies.add(book);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return movies;
        }

        public Book getBook(int id){
            Book book = new Book();
            try(Connection connection = new ConnectDB().getConexao()){
                this.sql = "Select * FROM book WHERE id_user = ?";
                this.stmt = connection.prepareStatement(this.sql);
                this.stmt.setInt(1,id);
                this.rs = this.stmt.executeQuery();
                while(rs.next()){
                    book.setId(rs.getInt("id_book"));
                    book.setTitle(rs.getString("title"));
                    book.setNote(rs.getInt("note"));
                    book.setPages(rs.getInt("pages"));
                    book.setPlataform(rs.getString("plataform"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return book;
        }



        public boolean setBook(Book b, int id){
            try(Connection connection =  new ConnectDB().getConexao()){
                this.sql = "INSERT INTO book (title, note, pages, platform, id_user) VALUES (?, ?, ?, ?, ?)";
                this.stmt = connection.prepareStatement(this.sql);
                this.stmt.setString(1, b.getTitle());
                this.stmt.setInt(2,b.getNote());
                this.stmt.setInt(3, b.getPages());
                this.stmt.setString(4, b.getPlataform());
                this.stmt.setInt(5, id);
                this.stmt.execute();
                return true;
            }catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        }

        public boolean upBook(Book b){
            try(Connection connection = new ConnectDB().getConexao()) {
                this.sql = "UPDATE book SET title = ?, note = ?, pages = ?, plataform = ? WHERE id_book = ?";
                this.stmt = connection.prepareStatement(this.sql);
                this.stmt.setString(1, b.getTitle());
                this.stmt.setInt(2, b.getNote());
                this.stmt.setInt(3, b.getPages());
                this.stmt.setString(4, b.getPlataform());
                this.stmt.setInt(5, b.getId());
                this.stmt.execute();
                return true;
            }catch(SQLException e){
                e.printStackTrace();
            }
            return false;
        }

        public boolean dellBook(int id){
            try(Connection connection = new ConnectDB().getConexao()){
                this.sql = "DELETE FROM book WHERE id_book = ?";
                this.stmt = connection.prepareStatement(this.sql);
                this.stmt.setInt(1, id);
                return this.stmt.execute();
            }catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        }

    }

