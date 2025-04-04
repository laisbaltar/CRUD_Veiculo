package aula2403.model.jdbc;

import java.sql.Connection;

public class MinhaConexao {

    public static Connection conexao(){
        ConexaoJDBC conexao = new ConexaoH2();
        return conexao.criarConexao();
    }

}
