/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mz.sgv.factory.Conexao;
import mz.sgv.model.Cargo;

/**
 *
 * @author doroteia
 */
public class CargoDAO extends Conexao {

    public int  save(Cargo cargo) {
        int resultado = 0;
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO cargo");
        sql.append("(cargo) VALUES(?)");

        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            ps.setString(1, cargo.getCargo());
            resultado = ps.executeUpdate();
            
            ps.close();
        } catch (SQLException e) {
        }finally{
            close();
        }
        return resultado;
    }
    
    
     public Cargo FindById(int id) {
        Cargo cargo = null;

        String sql = "select * from cargo WHERE id = ?";
        open();
        try {
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs;
            rs = ps.executeQuery();

            while (rs.next()) {
                cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setCargo(rs.getString("cargo"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar o cartao " + e.getMessage());
        } finally {
            close();
        }

        return cargo;
    }
     public List<Cargo> all() {
        List<Cargo> cargos = new ArrayList<>();

        String sql = "select * from cargo";
        open();
        try {
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            
            ResultSet rs;
            rs = ps.executeQuery();

            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(rs.getInt("id"));
                cargo.setCargo(rs.getString("cargo"));
                cargos.add(cargo);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar o cartao " + e.getMessage());
        } finally {
            close();
        }

        return cargos;
    }
     
     public int findIdByName(String nome){
         int id = 0;
         String sql = "select * from cargo WHERE cargo = ?";
        open();
        try {
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs;
            rs = ps.executeQuery();

            while (rs.next()) {
               id = rs.getInt("id");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar o cartao " + e.getMessage());
        } finally {
            close();
        }
        return id;
     }
    

}
