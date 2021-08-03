/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.model;

import mz.sgv.dao.EmpresaDAO;

/**
 *
 * @author doroteia
 */
public class Empresa {
    private int id;
    private String nome;
    private String telfone;
   
    private String email;

    private static EmpresaDAO dao = new EmpresaDAO();

    
    
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

    public String getTelfone() {
        return telfone;
    }

    public void setTelfone(String telfone) {
        this.telfone = telfone;
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
    
}
