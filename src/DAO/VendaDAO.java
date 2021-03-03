/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Venda;

/**
 *
 * @author doroteia
 */
public class VendaDAO extends SQLiteConection{
    
    public void save(Venda venda){
        
         
       StringBuilder sql = new StringBuilder();
       
       sql.append("INSERT INTO venda");
       sql.append("(idProdutos, quantidades, id_usuario, data, nomeCliente) ");
       sql.append("values(?)");
       
       open();
       try{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
       PreparedStatement ps = conexao.prepareStatement(sql.toString());
       ps.setString(1,venda.getIdProdutos());
       ps.setString(2,venda.getQuantidades());
       ps.setInt(3, venda.getId_usuario());
       ps.setDate(4, (java.sql.Date) format.parse(format.format(new Date())));
       ps.setString(5, venda.getNomeCliente());
       ps.executeUpdate();
       
       }catch(SQLException e){
           System.out.println("erro "+e.getMessage());
       } catch (ParseException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           close();
       }
       
    
    }
    

    
    public static void main(String[] args) {
        
    }
    
}
