package aula2403.model.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

//cria conexão com banco de dados H2
public class ConexaoH2 implements ConexaoJDBC {

    public static void main(String[] args) {
        //testar conexão
        System.out.println(new ConexaoH2().criarConexao());
    }


    @Override
    public Connection criarConexao() {
        try {
            //carregar o driver de conexão
            Class.forName("org.h2.Driver");
            //parâmetros
            String url = "jdbc:h2:mem:dbname";
            String usuario = "sa";
            String senha = "";
            //retorna a conexão com o banco de dados
            return DriverManager.getConnection(url, usuario, senha);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexaoH2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}

