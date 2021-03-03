/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.model;

import java.util.List;
import mz.sgv.dao.CargoDAO;

/**
 *
 * @author doroteia
 */
public class Cargo {
    private int id;
    private String cargo;

    private static CargoDAO dao = new CargoDAO();
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public int save(){
       return dao.save(this);
    
    }
    
    public static Cargo findById(int id){
        return dao.FindById(id);
    }
    
    public static List<Cargo> all(){
        return dao.all();
    }
    
    public static int FindIdByName(String cargo) {
        return dao.findIdByName(cargo);
    }
}
