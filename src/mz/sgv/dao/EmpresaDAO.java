/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mz.sgv.model.Empresa;
import mz.sgv.factory.Conexao;

/**
 *
 * @author doroteia
 */
public class EmpresaDAO extends Conexao{
    

    
    public void save(Empresa empresa){
        
        String sql = "INSERT INTO `empresa`( `nome`, `telfone`, `email`) VALUES (?,?,?) ";
        open();
            try {
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setString(1, empresa.getNome());
                ps.setString(2, empresa.getTelfone());
               
                ps.setString(3, empresa.getEmail());
                
                ps.executeUpdate();
                ps.close();
            } catch (SQLException e) {
                System.out.println("eroo save empresa: "+e.getMessage());
            }finally{
                close();
            }
    }
    
    public Empresa getEmpresa(){
        Empresa empresa = new Empresa();
        String sql = "SELECT * FROM `empresa`  ";
        open();
      
            try {
                  PreparedStatement ps = conexao.prepareStatement(sql);
                  ResultSet rs;
                  rs = ps.executeQuery();
                  rs.next();
                  empresa.setNome(rs.getString(2));
                  empresa.setTelfone(rs.getString(3));
                 
                  empresa.setEmail(rs.getString(3));
            } catch (SQLException e) {
                System.out.println("erro busca empresa: "+e.getMessage());
            }finally{
                close();
            }
            return empresa;
    }
  
}
