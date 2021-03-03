/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;

/**
 *
 * @author doroteia
 */
public class CategoriaDAO extends SQLiteConection {

    public void save(Categoria categoria) {

        StringBuilder sql = new StringBuilder();

        sql.append("INSERT INTO categoria");
        sql.append("(categoria) ");
        sql.append("values(?)");

        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            ps.setString(1, categoria.getCategoria());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("erro " + e.getMessage());
        } finally {
            close();
        }

    }

    public List<Categoria> all() {
        List<Categoria> categorias = new ArrayList<>();

        String sql = "SELECT * FROM categoria";
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setCategoria(rs.getString("categoria"));

                categorias.add(categoria);

            }

        } catch (SQLException e) {
            System.out.println("erro: " + e.getMessage());
        } finally {
            close();
        }

        return categorias;

    }

    public Categoria findIdByName(String nome) {
        Categoria categoria = null;

        String sql = "SELECT * FROM categoria WHERE categoria = ?";
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setCategoria(rs.getString("categoria"));

            }

        } catch (SQLException e) {
            System.out.println("erro: " + e.getMessage());
        } finally {
            close();
        }

        return categoria;

    }
    public Categoria findById(int id) {
        Categoria categoria = null;

        String sql = "SELECT * FROM categoria WHERE id = ?";
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setCategoria(rs.getString("categoria"));

            }

        } catch (SQLException e) {
            System.out.println("erro: " + e.getMessage());
        } finally {
            close();
        }

        return categoria;

    }

}
