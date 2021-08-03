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
import mz.sgv.model.Salario;

/**
 *
 * @author doroteia
 */
public class SalarioDAO extends Conexao{

    public void save(Salario salario) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO salario");
        sql.append("(id_funcionario, mes, data, valor) ");
        sql.append(" values(?,?,?,?)");
       
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());

            ps.setInt(1, salario.getId_funcionario());
            ps.setString(2, salario.getMes());
            ps.setDate(3, salario.getData());
            ps.setDouble(4, salario.getValor());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("erro salario insercao");
        } finally {
            close();
        }

    }
    public List<Salario>  findById(int id) {
        List <Salario> salarios =  new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM salario WHERE id_funcionario = ?");
        
       
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            ps.setInt(1, id);
            ResultSet rs;
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Salario salario = new Salario();
                salario.setId(rs.getInt("id"));
                salario.setId_funcionario(rs.getInt("id_funcionario"));
                salario.setMes(rs.getString("mes"));
                salario.setValor((float) rs.getDouble("valor"));
                salario.setData(rs.getDate("data"));
                salarios.add(salario);
            }

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("erro listar insercao"+ex.getMessage());
        } finally {
            close();
        }
        return salarios;
    }
    
    public void delete(int id_funcionario){
    
        
        String sql = "DELETE FROM salario WHERE id_funcionario = ?";
        open();
         try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());

            ps.setInt(1, id_funcionario);
            

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("erro delete salarioo");
        } finally {
            close();
        }
        
    }

}
