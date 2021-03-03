/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.model;

import mz.sgv.dao.MoradaDAO;

/**
 *
 * @author doroteia
 */
public class Morada {

    private int id;
    private int id_funcionario;
    private String avenida;
    private String quarteirao;
    private String rua;

    private static MoradaDAO dao = new MoradaDAO();

    

    
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

    public String getAvenida() {
        return avenida;
    }

    public void setAvenida(String avenida) {
        this.avenida = avenida;
    }

    public String getQuarteirao() {
        return quarteirao;
    }

    public void setQuarteirao(String quarteirao) {
        this.quarteirao = quarteirao;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public static Morada findById(int id) {
        return dao.findById(id);
    }
    public static void delete(int id_funcionario){
        dao.delete(id_funcionario);
    }
}
