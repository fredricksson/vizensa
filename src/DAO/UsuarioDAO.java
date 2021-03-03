/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Services.Help;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

/**
 *
 * @author doroteia
 */
public class UsuarioDAO extends SQLiteConection{
    
    public void save(Usuario usuario) {
        
       StringBuilder sql = new StringBuilder();
       
       sql.append("INSERT INTO usuario");
       sql.append("(nome,senha) ");
       sql.append("values(?,?)");
       
       open();
       try{
       PreparedStatement ps = conexao.prepareStatement(sql.toString());
       ps.setString(1,usuario.getNome());
       ps.setString(2,Help.incriptar_senha(usuario.getSenha()));
       ps.executeUpdate();
       
       }catch(SQLException e){
           System.out.println("erro "+e.getMessage());
       }finally{
           close();
       }
       
    
    }
    
    public List<Usuario> all(){
        
        List<Usuario> usuarios = new ArrayList<>();
        
        String sql = "SELECT * FROM usuario";
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                Usuario  u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                usuarios.add(u);
                
            }
            
        } catch (SQLException e) {
            System.out.println("erro: "+e.getMessage());
        }finally{
            close();
        }
        
        return usuarios;
    
    }
    
    
    public Usuario login(String nome,String senha){
        
       
        Usuario user = null;
        String sql = "SELECT * FROM usuario WHERE nome = ? AND senha = ?";
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, nome);
            //ps.setString(2, Help.incriptar_senha(senha));
            ps.setString(2, senha);
            ResultSet rs = ps.executeQuery();
           
            while (rs.next()) {
                
                user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setSenha(rs.getString("senha"));
                
                
                
            }
            
        } catch (SQLException e) {
            System.out.println("erro: "+e.getMessage());
        }finally{
            close();
        }
        
        return user;
    
    }
    
    public void delete(Usuario  u){
        
        String sql = "DELETE FROM usuario WHERE id = ?";
        
        try{
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, u.getId());
        
        ps.executeUpdate();
         
        }catch(SQLException e){
            System.out.println("erro: "+e.getMessage());
        }finally{
            close();
        }
    
    }
    
}
