/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.model;

import mz.sgv.dao.UsuarioDAO;

/**
 *
 * @author fred
 */
public class Usuario {

    private int id;
    private int id_funcionario;
    private String senha;
    private String usuario;
    private int nivelAcesso;

   private static UsuarioDAO dao = new UsuarioDAO();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }
    
    public void save(int id_funcionario){
      dao.save(this, id_funcionario);
    }
    
    public static Usuario login(String usuario,String senha){
       return dao.login(usuario, senha);
    }

    
    
    @Override
    public String toString() {
        return "Utilizador{" + "idUtilizador=" + id + ", utiSenha=" + senha + ", utiUtilizador=" + usuario + '}';
    }

}
