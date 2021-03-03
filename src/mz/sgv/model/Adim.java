/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.model;

import mz.sgv.dao.AdimDAO;

/**
 *
 * @author doroteia
 */
public class Adim {
    
    private String nome;
    private String apelido;
    private String email;
    private String contacto;
    private String senha;
    private static AdimDAO dao = new AdimDAO();
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    public void save(){
        dao.save(this);
    }
    public static Adim login(String nome, String senha){
        return dao.login(nome, senha);
    }
    public static Adim getAdim(){
        return dao.getAdim();
    }
    
    
    
    
}
