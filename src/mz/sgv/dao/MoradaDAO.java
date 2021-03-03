/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mz.sgv.model.Morada;
import mz.sgv.factory.Conexao;

/**
 *
 * @author doroteia
 */
public class MoradaDAO extends Conexao {

    public void save(Morada morada) {
        String sql = "INSERT INTO `morada`(`avenida`, `quarteirao`,`rua`,`id_funcionario`) VALUES (?,?,?,?)";

        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setString(1, morada.getAvenida());
            ps.setString(2, morada.getQuarteirao());
            ps.setString(3, morada.getRua());
            ps.setInt(4, morada.getId_funcionario());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("erro morada insercao");
        } finally {
            close();
        }

    }

    public Morada findById(int id_funcionario) {
        Morada morada = null;
        String sql = "select * from morada where id_funcionario= ?";
        open();
        try {

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id_funcionario);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {

                morada = new Morada();
                morada.setId(rs.getInt("id"));
                morada.setAvenida(rs.getString("avenida"));
                morada.setQuarteirao(rs.getString("quarteirao"));
                morada.setRua(rs.getString("rua"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(MoradaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }

        return morada;
    }

 
    public void delete(int id_funcionario) {

        String sql = "DELETE FROM `morada`  WHERE id_funcionario = ? ";
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id_funcionario);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("erro: Apagar morada");
        }finally{
        
            close();
        }

    }


}
