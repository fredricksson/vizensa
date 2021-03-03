/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Produto;

/**
 *
 * @author doroteia
 */
public class ProdutoDAO extends SQLiteConection {

    public void save(Produto produto) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO produto");
        sql.append("(id_categoria, nome, preco, quantidade, dataCriacao, dataExpiracao) ");
        sql.append("values(?, ?, ?, ?, ?, ?)");

        open();
        try {

            PreparedStatement ps = conexao.prepareStatement(sql.toString());

            ps.setInt(1, produto.getId_categoria());
            ps.setString(2, produto.getNome());
            ps.setDouble(3, produto.getPreco());
            ps.setInt(4, produto.getQuantidade());
            ps.setDate(5, (Date) produto.getDataCriacao());
            ps.setDate(6, (Date) produto.getDataExpiracao());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("erro" + e.getMessage());
        } finally {
            close();
        }

    }

    public List<Produto> all() {

        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produto";

        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setId_categoria(rs.getInt("id_categoria"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setDataCriacao(rs.getDate("dataCriacao"));
                produto.setDataExpiracao(rs.getDate("dataExpiracao"));
                //preciso alterar
                produto.setCategoria(Categoria.findById(rs.getInt("id_categoria")).getCategoria());
                produtos.add(produto);
            }

        } catch (SQLException e) {
            System.out.println("eroo" + e.getMessage());
        } finally {
            close();
        }
        
        return produtos;

    }

    public List<Produto> findById(int id) {

        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produto where id = ?";

        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setId_categoria(rs.getInt("id_categoria"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setDataCriacao(rs.getDate("dataCriacao"));
                produto.setDataExpiracao(rs.getDate("dataExpiracao"));
                //preciso alterar
                produto.setCategoria(Categoria.findById(rs.getInt("id_categoria")).getCategoria());
            }

        } catch (SQLException e) {

        } finally {
            close();
        }
        return produtos;

    }

    public List<Produto> findByName(String nome) {

        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produto WHERE nome LIKE ?";

        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, "%"+nome+"%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setId_categoria(rs.getInt("id_categoria"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setDataCriacao(rs.getDate("dataCriacao"));
                produto.setDataExpiracao(rs.getDate("dataExpiracao"));
                //preciso alterar
                produto.setCategoria(Categoria.findById(rs.getInt("id_categoria")).getCategoria());
                produtos.add(produto);
            }

        } catch (SQLException e) {

        } finally {
            close();
        }
        return produtos;

    }

    public List<Produto> findWithCategory(String categoria) {
        List<Produto> produtos = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM produto p ");
        sql.append("JOIN categoria c WHERE ");
        sql.append("p.id_categoria = c.id AND ");
        sql.append("nome = ?");

        open();

        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            ps.setString(1, categoria);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setId_categoria(rs.getInt("id_categoria"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setDataCriacao(rs.getDate("dataCriacao"));
                produto.setDataExpiracao(rs.getDate("dataExpiracao"));
                //preciso alterar
                produto.setCategoria(Categoria.findById(rs.getInt("id_categoria")).getCategoria());
                produtos.add(produto);

            }

        } catch (SQLException e) {
            System.out.println("erro: " + e.getMessage());
        }
        return produtos;
    }

    public void delete(Produto produto) {

        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM produto ");
        sql.append("WHERE id = ?");
        open();
        try {

            PreparedStatement ps = conexao.prepareStatement(sql.toString());

            ps.setInt(1, produto.getId());

        } catch (SQLException e) {
        } finally {
            close();
        }
    }

}
