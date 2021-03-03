/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.UsuarioDAO;
import java.util.List;

/**
 *
 * @author doroteia
 */
public class Usuario {

    private int id;
    private String nome;
    private String senha;

    private static UsuarioDAO dao = new UsuarioDAO();

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario() {
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void save() {
        dao.save(this);
    }

    public static List<Usuario> all() {
        return dao.all();

    }
    
    public static  Usuario login(String  user,String senha){
        return dao.login(user,senha);
    }
}
