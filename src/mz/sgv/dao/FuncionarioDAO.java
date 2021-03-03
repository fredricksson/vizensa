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
import java.util.logging.Level;
import java.util.logging.Logger;

import mz.sgv.model.Funcionario;
import mz.sgv.model.Morada;
import mz.sgv.factory.Conexao;
import mz.sgv.model.Cargo;
import mz.sgv.model.Salario;

/**
 *
 * @author doroteia
 */
public class FuncionarioDAO extends Conexao {

    public void save(Funcionario f) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO funcionario");
        sql.append("(nome, apelido, nrBi, sexo, estadoCivil, ");
        sql.append("contacto, contactoOpcional, email, dataNascimento,");
        sql.append("salario, cargo) ");
        sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?)");
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());

            ps.setString(1, f.getNome());
            ps.setString(2, f.getApelido());
            ps.setString(3, f.getNrBi());
            ps.setString(4, f.getSexo());
            ps.setString(5, f.getEstadoCivil());
            ps.setString(6, f.getContacto());
            ps.setString(7, f.getContactoOpcional());
            ps.setString(8, f.getEmail());
            ps.setDate(9, (java.sql.Date) f.getDataNascimento());
            ps.setFloat(10, f.getSalario());
            
            ps.setInt(11, f.getId_cargo());
             ps.executeUpdate();
             MoradaDAO md = new MoradaDAO();
             System.out.println(lastIdFuncionario());
             f.getMorada().setId_funcionario(lastIdFuncionario());
             md.save(f.getMorada());
             
            
           

            
           
            

            ps.close();

        } catch (SQLException e) {
            System.out.println("erro na insercao" + e.getMessage());

        } finally {
            close();
        }

    }

    public List<Funcionario> all() {
        List<Funcionario> funcionarios = new ArrayList<>();
        
        String sql = "SELECT * FROM funcionario WHERE visivel = 1";
        open();
        try {

            PreparedStatement ps = conexao.prepareStatement(sql);

            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                 
                Funcionario func = new Funcionario();
                
                
                func.setId(rs.getInt("id"));
                func.setNome(rs.getString("nome"));
                func.setApelido(rs.getString("apelido"));
                func.setNrBi(rs.getString("nrBi"));
                func.setSexo(rs.getString("sexo"));
                func.setEstadoCivil(rs.getString("estadoCivil"));
                func.setContacto(rs.getString("contacto"));
                func.setContactoOpcional(rs.getString("contactoOpcional"));
                func.setEmail(rs.getString("email"));
                func.setDataNascimento(rs.getDate("dataNascimento"));
                func.setSalario(rs.getFloat("salario"));
                int id_cargo = rs.getInt("cargo");
                func.setId_cargo(id_cargo);
                func.setCargo(Cargo.findById(id_cargo).getCargo());
                func.setMorada(Morada.findById(rs.getInt("id")));
                //falta Botao

                funcionarios.add(func);
            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            close();
        }

        return funcionarios;
    }
    public List<Funcionario> findByName(String nome) {
        List<Funcionario> funcionarios = new ArrayList<>();
        
        String sql = "SELECT * FROM funcionario WHERE visivel = 1 AND nome like ?";
        open();
        try {

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, "%"+nome+"%");
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                 
                Funcionario func = new Funcionario();
                
                
                func.setId(rs.getInt("id"));
                func.setNome(rs.getString("nome"));
                func.setApelido(rs.getString("apelido"));
                func.setNrBi(rs.getString("nrBi"));
                func.setSexo(rs.getString("sexo"));
                func.setEstadoCivil(rs.getString("estadoCivil"));
                func.setContacto(rs.getString("contacto"));
                func.setContactoOpcional(rs.getString("contactoOpcional"));
                func.setEmail(rs.getString("email"));
                func.setDataNascimento(rs.getDate("dataNascimento"));
                func.setSalario(rs.getFloat("salario"));
                int id_cargo = rs.getInt("cargo");
                func.setId_cargo(id_cargo);
                func.setCargo(Cargo.findById(id_cargo).getCargo());
                func.setMorada(Morada.findById(rs.getInt("id")));
                //falta Botao

                funcionarios.add(func);
            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            close();
        }

        return funcionarios;
    }
    public Funcionario findById(int id) {
       Funcionario func = null ;
        
        String sql = "SELECT * FROM funcionario WHERE visivel = 1 AND id = ?";
        open();
        try {

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                Morada morada = new Morada();
                func = new Funcionario();
                
                func.setId(rs.getInt("id"));
                func.setNome(rs.getString("nome"));
                func.setApelido(rs.getString("apelido"));
                func.setNrBi(rs.getString("nrBi"));
                func.setSexo(rs.getString("sexo"));
                func.setEstadoCivil(rs.getString("estadoCivil"));
                func.setContacto(rs.getString("contacto"));
                func.setContactoOpcional(rs.getString("contactoOpcional"));
                func.setEmail(rs.getString("email"));
                func.setDataNascimento(rs.getDate("dataNascimento"));
                func.setSalario(rs.getFloat("salario"));
                int id_cargo = rs.getInt("cargo");
                func.setId_cargo(id_cargo);
                func.setCargo(Cargo.findById(id_cargo).getCargo());
                
                //falta Botao

               
            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            close();
        }

        return func;
    }

    public void delete(Funcionario func) {
        Salario.delete(func.getId());
        String sql = "DELETE FROM `funcionario`  WHERE id = ? ";
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, func.getId());

            ps.executeUpdate();
            Morada.delete(func.getId());
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("erro: Apagar funcionario");
        }finally{
        
            close();
        }

    }

    public void update(Funcionario f) {

          StringBuilder sql = new StringBuilder();
        sql.append("UPDATE  funcionario SET ");
        sql.append("nome = ?, apelido = ?, nrBi = ?, sexo = ?, estadoCivil = ?, ");
        sql.append("contacto = ?, contactoOpcional = ?, email = ?, dataNascimento = ?, ");
        sql.append("salario = ?, cargo = ? ");
        sql.append("WHERE id = ?");
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());

            ps.setString(1, f.getNome());
            ps.setString(2, f.getApelido());
            ps.setString(3, f.getNrBi());
            ps.setString(4, f.getSexo());
            ps.setString(5, f.getEstadoCivil());
            ps.setString(6, f.getContacto());
            ps.setString(7, f.getContactoOpcional());
            ps.setString(8, f.getEmail());
            ps.setDate(9, (java.sql.Date) f.getDataNascimento());
            ps.setFloat(10, f.getSalario());
           
            ps.setInt(11, f.getId_cargo());
            ps.setInt(12, f.getId());
            
            ps.executeUpdate();

            

            ps.close();

        } catch (SQLException e) {
            System.out.println("erro na insercao" + e.getMessage());

        } finally {
            close();
        }
    }
   public int lastIdFuncionario() {
        int id = -1;
        String sql = "select max(id) from funcionario ";
        open();
        try {

            PreparedStatement ps = conexao.prepareStatement(sql);

            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(MoradaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return id;
    }
   
   
    
}
