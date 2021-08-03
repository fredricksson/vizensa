/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.model;

import java.util.List;
import mz.sgv.dao.CategoriaDAO;

/**
 *
 * @author doroteia
 */
public class Categoria {

    private int id;
    private String categoria;

    private static CategoriaDAO dao = new CategoriaDAO();

    public Categoria(String nome) {
        this.categoria = nome;
    }

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

    public void save() {
        dao.save(this);

    }

//    public List<Categoria> getAll(){
//        
//        
//        return null;
//    }
    public void delete() {
        dao.save(this);
    }

    public static Categoria findByName(String nome) {
        return dao.FindByName(nome);
    }
    public static Categoria findById(int id) {
        return dao.FindById(id);
    }
    public static List<Categoria> all() {
        return dao.all();
    }

    @Override
    public String toString() {
        return "Category{" + "category_id=" + id + ", catNome=" + categoria + '}';
    }

}
