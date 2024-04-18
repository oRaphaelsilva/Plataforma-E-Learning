/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Raphael
 */
public class Usuario {
    private String login;
    private String senha;

    // Construtor
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    // Getters e Setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Método para verificar se o login e senha são válidos
    public boolean verificarCredenciais() {
        return login.equals("admin") && senha.equals("admin");
    }
}
