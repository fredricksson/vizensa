/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.model;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import mz.sgv.dao.VendaDAO;

/**
 *
 * @author doroteia
 */
public class Venda {

    private int id;
    private Date dataVenda;
    private String codigoProdutos;
    private String quantidades;
    private int quantidadeTotal;
    private String precos;

    private String tamanhos;
    private float totalPago;
    //foregin key
    private int id_funcionario;
    private int id_Cliente;
    private int id_produto;

    private VendaDAO dao = new VendaDAO();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getCodigoProdutos() {
        return codigoProdutos;
    }

    public void setCodigoProdutos(String codigoProdutos) {
        this.codigoProdutos = codigoProdutos;
    }

    public String getQuantidades() {
        return quantidades;
    }

    public void setQuantidades(String quantidade) {
        this.quantidades = quantidade;
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(int quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }
    
    

    public float getTotalPago() {
        return totalPago;
    }

    public String getPrecos() {
        return precos;
    }

    public void setPrecos(String precos) {
        this.precos = precos;
    }

    public String getTamanhos() {
        return tamanhos;
    }

    public void setTamanhos(String tamanhos) {
        this.tamanhos = tamanhos;
    }

    public void setTotalPago(float totalPago) {
        this.totalPago = totalPago;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public int getId_Cliente() {
        return id_Cliente;
    }

    public void setId_Cliente(int id_Cliente) {
        this.id_Cliente = id_Cliente;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public void save() {
        try {
            dao.save(this);
        } catch (ParseException ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
