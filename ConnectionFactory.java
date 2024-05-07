/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Raphael
 */
   import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory {
 
    public static Connection obtemConexao() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/cadastro?useTimezone=true&serverTimezone=America/Sao_Paulo";
        String usuario = "root";
        String senha = "usjt";
        return DriverManager.getConnection(url, usuario, senha);
    }
}


