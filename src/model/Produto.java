/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.ProdutoDAO;
import java.util.Date;
import java.util.List;
import javafx.scene.control.ButtonBar;

/**
 *
 * @author doroteia
 */
public class Produto {
    private int id;
    private int id_categoria;
    private String nome;
    private double preco;
    private int quantidade;
    private String categoria;
    private Date dataExpiracao;
    private Date dataCriacao;
    
    
    private ButtonBar acoes;
    
    private static ProdutoDAO dao = new ProdutoDAO();
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public ButtonBar getAcoes() {
        return acoes;
    }

    public void setAcoes(ButtonBar acoes) {
        this.acoes = acoes;
    }
    
    
    public void save(){
        dao.save(this);
    }
    
    public void delete(){
        dao.delete(this);
    }
    public static List<Produto> all(){
        return dao.all();
    }
    
    
    public static List<Produto> findByName(String nome){
        return dao.findByName(nome);
    }
    
    public static List<Produto> findById(int id){
        return dao.findById(id);
    }
    
    public static List<Produto> findWithCategory(String categoria) {
        return dao.findWithCategory(categoria);
    }
}
