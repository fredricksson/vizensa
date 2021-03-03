/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mz.sgv.model.HistoricoEntradaSaida;
import mz.sgv.factory.Conexao;
import mz.sgv.model.Funcionario;

/**
 *
 * @author doroteia
 */
public class HistoricoEntradaSaidaDAO extends Conexao {

    public void save(HistoricoEntradaSaida historico) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO historicoEntradaSaida");
        sql.append("(id_funcionario,horaEntrada,horaSaida) ");
        sql.append("VALUES(?,?,?)");
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            ps.setInt(1, historico.getId_func());
            ps.setDate(2, (java.sql.Date) historico.getDataEntrada());
            ps.setDate(3, (Date) historico.getDataSaida());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(MoradaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }

    }
    //Historico individual

    public List<HistoricoEntradaSaida> findById(Funcionario func) {
        List<HistoricoEntradaSaida> lista = new ArrayList<>();
        String sql = "SELECT * FROM historicoEntradaSaida h join funcionario f WHERE f.id = ?";
        open();
        try {

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, func.getId());
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {

                HistoricoEntradaSaida hes = new HistoricoEntradaSaida();
                hes.setDataEntrada(rs.getDate("dataEntrada"));
                hes.setDataSaida(rs.getDate("dataSaida"));
                hes.setId_func(func.getId());
                lista.add(hes);

            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
        return lista;
    }

    public List<HistoricoEntradaSaida> all() {
        List<HistoricoEntradaSaida> lista = new ArrayList<>();
        String sql = "SELECT * FROM historicoEntradaSaida h join funcionario f where f.id = h.id_funcionario  ";
        try {

            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                HistoricoEntradaSaida hes = new HistoricoEntradaSaida();
                hes.setDataEntrada(rs.getDate("dataEntrada"));
                hes.setDataSaida(rs.getDate("dataSaida"));
                hes.setId_func(rs.getInt("id_funcionario"));
                lista.add(hes);
            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            close();
        }
        
        
        return lista;
    }

 
}
