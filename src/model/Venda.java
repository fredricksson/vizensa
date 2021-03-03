/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author doroteia
 */
public class Venda {
    
    private int id;
    private String idProdutos;
    private String precos;
    private String Quantidades;
    private Date data;
    private String nomeCliente;
    private String nomeUsuario;
    private int id_usuario;
    
    private double total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdProdutos() {
        return idProdutos;
    }

    public void setIdProdutos(String idProdutos) {
        this.idProdutos = idProdutos;
    }

    public String getPrecos() {
        return precos;
    }

    public void setPrecos(String precos) {
        this.precos = precos;
    }

    public String getQuantidades() {
        return Quantidades;
    }

    public void setQuantidades(String Quantidades) {
        this.Quantidades = Quantidades;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    
    
   
    
}
