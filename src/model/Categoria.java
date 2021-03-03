/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.CategoriaDAO;
import java.util.List;

/**
 *
 * @author doroteia
 */
public class Categoria {
    
    private int id;
    
    private String categoria;
    
    private static CategoriaDAO dao = new CategoriaDAO();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public static Categoria findIdByName(String nome){
        return dao.findIdByName(nome);
        
    }
    public static List<Categoria> all(){
        return dao.all();
        
    }
    
    public static Categoria findById(int id){
        return dao.findById(id);
    }
    
}
