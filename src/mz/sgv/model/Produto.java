/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.model;

import java.util.List;
import javafx.scene.control.ButtonBar;
import javafx.scene.shape.Rectangle;
import mz.sgv.dao.ProdutoDAO;

/**
 *
 * @author fred
 */
public class Produto {

    private int codigo; // primary key
    private int id_categoria; // foreign key

    private String nome;
    private String cor;
    private float preco;
    private String categoria;

    
    
    
    private List<Tamanho> tamanhos;
    private Rectangle prodcor;
    private ButtonBar acoes;

    
    //Atributos nao existentes na base de dados
    private int quantidade;
    private float tamanho;
    
    private static  ProdutoDAO dao = new ProdutoDAO();

    public static int codigoAcao;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Tamanho> getTamanhos() {
        return tamanhos;
    }

    public void setTamanhos(List<Tamanho> tamanhos) {
        this.tamanhos = tamanhos;
    }

    public Rectangle getProdcor() {
        return prodcor;
    }

    public void setProdcor(Rectangle prodcor) {
        this.prodcor = prodcor;
    }

    public ButtonBar getAcoes() {
        return acoes;
    }

    public void setAcoes(ButtonBar acoes) {
        this.acoes = acoes;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getTamanho() {
        return tamanho;
    }

    public void setTamanho(float tamanho) {
        this.tamanho = tamanho;
    }

    public void save(String tamanho, String qantidade) {
        dao.save(this, tamanho, qantidade);
    }

    public void delete() {
        dao.delete(this);
    }

    public static List<Produto> all() {

        return dao.all();
    }

    public static List<Produto> findByName(String name) {

        return dao.findByName(name);

      

    }

    public static Produto findByCod(int cod) {
        return dao.findByCod(cod);

    }

    public static List<Produto> allWithCategory(String name) {

        return dao.allWithCategory(name);
       
    }

    @Override
    public String toString() {
        return "Produto{" + "codigo=" + codigo + ", id_categoria=" + id_categoria + ", nome=" + nome + ", cor=" + cor + ", preco=" + preco + ", categoria=" + categoria + ", tamanhos=" + tamanhos + ", prodcor=" + prodcor + '}';
    }

}
