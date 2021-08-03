/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.model;

import java.util.List;
import mz.sgv.dao.ClienteDAO;

/**
 *
 * @author doroteia
 */
public class Cliente {

    private int id;
    private String nome;
    private String contacto;
    private String email;
    
    private static ClienteDAO dao = new ClienteDAO();
    public int getId() {
        return id;
    }

   public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void save(){
        dao.save(this);
    }
    
    public static List<Cliente> all(){
    
        return dao.all();
    }
    
    
    public static Cliente findById(int id){
        return dao.FindById(id);
    }
    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + id + ", cliNome=" + nome + ", cliContacto=" + contacto + ", cliemail=" + email + '}';
    }

}
