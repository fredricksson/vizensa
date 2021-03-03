/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.model;

import java.util.List;
import mz.sgv.dao.TamanhoDAO;

/**
 *
 * @author doroteia
 */
public class Tamanho {
    
    private int id;
    
    private int id_produto; // chave estrageira
    private int quantidade;
    private float tamanho;
    
    private String quantidateString;
    private String tamanhoString;
    
    public static TamanhoDAO dao = new TamanhoDAO();
            
            
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
  
    

  
   
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.quantidade = Quantidade;
    }

  

    public void setTamanho(float tamanho) {
        this.tamanho = tamanho;
    }

    public float getTamanho() {
        return tamanho;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    


    public String getQuantidateString() {
        return quantidateString;
    }

    public void setQuantidateString(String quantidateString) {
        this.quantidateString = quantidateString;
    }

    public String getTamanhoString() {
        return tamanhoString;
    }

    public void setTamanhoString(String tamanhoString) {
        this.tamanhoString = tamanhoString;
    }
    
    public void save(){
        
        dao.save(this);
    }
    
    public static List<Tamanho> findByCodProduto(int id){
        return dao.findByCodProduto(id);
    };

}
