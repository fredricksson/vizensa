/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author doroteia
 */
public class SQLiteConection {
    
    
    protected Connection conexao;
 
     String url="sistema_gestao_farmacia.db";
    
    public void open()   {
         try{
          
             try {
                 Class.forName("org.sqlite.JDBC");
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(SQLiteConection.class.getName()).log(Level.SEVERE, null, "erro: "+ex);
             }
          conexao = DriverManager.getConnection("jdbc:sqlite:" +url);
         if(conexao != null){
             
         }
         
         
         
        }catch(SQLException ex){
            System.err.println("erro"+ex);
        }
    }

    public void close() {
        try {
            if (conexao != null)
                conexao.close();
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão com o banco:" + e);
        }
    }
    
    
}
