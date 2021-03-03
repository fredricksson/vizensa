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
import mz.sgv.model.Categoria;

/**
 *
 * @author doroteia
 */
public class CategoriaDAO extends Conexao {

    public void save(Categoria categoria) {

        String sql = "INSERT INTO categoria(categoria) values(?)";
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, categoria.getCategoria());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            System.err.println("Erro ao salvar o cartao " + categoria.getCategoria() + ":" + e.getMessage());
        } finally {
            close();
        }

    }

    public List<Categoria> all() {
        List<Categoria> categorias = new ArrayList<>();

        String sql = "select * from categoria";
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs;
            rs = ps.executeQuery();

            while (rs.next()) {
                categorias.add(new Categoria(rs.getString(2)));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar o cartao " + e.getMessage());
        } finally {
            close();
        }

        return categorias;
    }
    public Categoria FindByName(String nome) {
        Categoria categoria = null;

        String sql = "select * from categoria WHERE categoria = ?";
        open();
        try {
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs;
            rs = ps.executeQuery();

            while (rs.next()) {
                categoria = new Categoria(rs.getString("categoria"));
                categoria.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar o categoria " + e.getMessage());
        } finally {
            close();
        }

        return categoria;
    }
    public Categoria FindById(int id) {
        Categoria categoria = null;

        String sql = "select * from categoria WHERE id = ?";
        open();
        try {
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs;
            rs = ps.executeQuery();

            while (rs.next()) {
                categoria = new Categoria(rs.getString("categoria"));
                categoria.setId(rs.getInt("id"));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar o cartao " + e.getMessage());
        } finally {
            close();
        }

        return categoria;
    }
    

}
