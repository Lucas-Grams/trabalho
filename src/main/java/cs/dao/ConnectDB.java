package cs.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
        private static final String DRIVER = "org.postgresql.Driver";
        private static final String URL = "jdbc:postgresql://localhost/poo";
        private static final String USERDB = "postgres";
        private static final String SENHA ="1234";

        public static void main(String[] args) {
            new ConnectDB().getConexao();
            System.out.println("Conexão Executada Com Sucesso!");
        }
        public Connection getConexao(){
            Connection conn = null;
            try{
                Class.forName(this.DRIVER);
                conn = DriverManager.getConnection(this.URL, this.USERDB, this.SENHA);
            }catch(ClassNotFoundException e){
                System.out.println("Classe De Conexão Com O Banco Não Configurada");
                e.printStackTrace();
            }catch(SQLException e){
                System.out.println("Url, Usuáriio ou Senha De Banco de Dados Errados");
                e.printStackTrace();
            }
            return conn;
        }
}


