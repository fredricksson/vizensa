/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.model;

import java.sql.Date;
import java.util.List;
import mz.sgv.dao.SalarioDAO;

/**
 *
 * @author doroteia
 */
public class Salario {
    
    private int id;
    private int id_funcionario ;//FOREGIN KEY
    
    private String mes;
    private Date data;
    private float valor;
    private static SalarioDAO dao = new SalarioDAO();
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

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    public void save(){
       dao.save(this);
    }
    public static List<Salario> FindById(int id){
        return dao.findById(id);
    }
    
    public static void delete(int id_funcionario){
        dao.delete(id_funcionario);
    }
    
}
