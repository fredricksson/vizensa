/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mz.sgv.factory.Conexao;
import mz.sgv.factory.Help;
import mz.sgv.model.Adim;

/**
 *
 * @author doroteia
 */
public class AdimDAO extends Conexao {

    public void save(Adim adim) {
        String sql = "INSERT INTO `adim`( `nome`, `apelido`, `email`,`contacto`,`senha`) VALUES (?,?,?,?,?) ";
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, adim.getNome());
            ps.setString(2, adim.getApelido());
            ps.setString(3, adim.getEmail());
            ps.setString(4, adim.getContacto());
            ps.setString(5,Help.incriptar_senha(adim.getSenha()));

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("eroo save empresa: " + e.getMessage());
        } finally {
            close();
        }
    }
    public Adim getAdim(){
         Adim adim = null;
          String sql = "SELECT * FROM adim limit 1";
            open();
      
            try {
                  PreparedStatement ps = conexao.prepareStatement(sql);
                  ResultSet rs;
               
                  rs = ps.executeQuery();
                  
                  while (rs.next() ) {
                      adim = new Adim();
                      adim.setNome(rs.getString("nome"));
                      adim.setApelido(rs.getString("apelido"));
                      adim.setContacto("contacto");
                      adim.setEmail("email");
                      
                }
                  
            } catch (SQLException e) {
                System.out.println("erro busca empresa: "+e.getMessage());
            }finally{
                close();
            }
     
            return adim;
    }
     public  Adim login(String nome , String senha){
         Adim adim = null;
         String sql = "SELECT * FROM adim WHERE nome = ? AND senha = ?";
            open();
      
            try {
                  PreparedStatement ps = conexao.prepareStatement(sql);
                  ResultSet rs;
                  ps.setString(1, nome);
                  ps.setString(2, Help.incriptar_senha(senha));
                  rs = ps.executeQuery();
                  
                  while (rs.next() ) {
                      adim = new Adim();
                      adim.setNome(rs.getString("nome"));
                      adim.setApelido(rs.getString("apelido"));
                      adim.setContacto("contacto");
                      adim.setEmail("email");
                      
                }
                  
            } catch (SQLException e) {
                System.out.println("erro busca empresa: "+e.getMessage());
            }finally{
                close();
            }
     
            return adim;
     }
}
     


