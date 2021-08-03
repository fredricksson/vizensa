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
import mz.sgv.model.Produto;
import mz.sgv.factory.Conexao;
import mz.sgv.model.Categoria;
import mz.sgv.model.Tamanho;

/**
 *
 * @author doroteia
 */
public class ProdutoDAO extends Conexao {

    public void save(Produto produto, String tamanh, String quantidade) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO `produto` ");
        sql.append("(`codigo`, `nome`, `cor`, `preco`,`id_categoria`) ");
        sql.append("VALUES (?,?,?,?,?) ");
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());

            ps.setInt(1, produto.getCodigo());
            ps.setString(2, produto.getNome());
            ps.setString(3, produto.getCor());
            ps.setFloat(4, produto.getPreco());
            ps.setInt(5, produto.getId_categoria());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {

            System.out.println("produto erro");
        } finally {
            close();
        }

        Tamanho tamanho = new Tamanho();
        tamanho.setId_produto(produto.getCodigo());
        tamanho.setQuantidateString(quantidade);
        tamanho.setTamanhoString(tamanh);
        tamanho.save();

    }

    public void update(Produto produto) {

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE  `produto` ");
        sql.append("SET `codigo` = ?, `nome` = ?, `cor` = ?, `preco = ?` ");
        sql.append("WHERE codigo = ?");
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());

            ps.setInt(1, produto.getCodigo());
            ps.setString(2, produto.getNome());
            ps.setString(3, produto.getCor());
            ps.setFloat(4, produto.getPreco());
            ps.setInt(5, produto.getCodigo());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {

            System.out.println("produto erro");
        } finally {
            close();
        }

    }

    public List<Produto> allWithCategory(String name) {

        ArrayList<Produto> produtos = new ArrayList<>();
        open();
        try {
            PreparedStatement stm = conexao.prepareStatement("SELECT * FROM produto p JOIN  categoria c   WHERE p.id_categoria=c.id  and c.categoria = ? and visivel = 1");
            stm.setString(1, name);
            ResultSet rs;
            rs = stm.executeQuery();
            while (rs.next()) {

                Produto produto = new Produto();
                produto.setCodigo(rs.getInt("codigo"));
                produto.setNome(rs.getString("nome"));
                produto.setCor(rs.getString("cor"));
                produto.setPreco(rs.getFloat("preco"));
                String categoria = Categoria.findById(rs.getInt("id_categoria")).getCategoria();
                produto.setCategoria(categoria);
                produtos.add(produto);

            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as produtos: " + e.getMessage());
        } finally {
            close();
        }
        return produtos;
    }

    public List<Produto> findByName(String name) {

        ArrayList<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produto  as p WHERE p.nome LIKE  ? and visivel = 1";
        open();
        try {
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            ResultSet rs;
            rs = stm.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setCodigo(rs.getInt("codigo"));
                produto.setNome(rs.getString("nome"));
                produto.setCor(rs.getString("cor"));
                produto.setPreco(rs.getFloat("preco"));
                produto.setTamanhos(Tamanho.findByCodProduto(rs.getInt("codigo")));
                String categoria = Categoria.findById(rs.getInt("id_categoria")).getCategoria();
                produto.setCategoria(categoria);
                produtos.add(produto);

            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as produtos: " + e.getMessage());
        } finally {
            close();
        }
        
        return produtos;
    }

    public List<Produto> findByCategory(String categoria) {
        ArrayList<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto p JOIN categoria c Where p.id_categoria = c.id AND nome = ? end visivel = 1";
        open();
        try {
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setString(1, "%" + categoria + "%");
            ResultSet rs;
            rs = stm.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setCodigo(rs.getInt("codigo"));
                produto.setNome(rs.getString("nome"));
                produto.setCor(rs.getString("cor"));
                produto.setPreco(rs.getFloat("preco"));

                String categori = Categoria.findById(rs.getInt("id_categoria")).getCategoria();
                produto.setCategoria(categori);

                produto.setTamanhos(Tamanho.findByCodProduto(rs.getInt("codigo")));

                produtos.add(produto);

            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as produtos: " + e.getMessage());
        } finally {
            close();
        }
        return produtos;
    }
    public Produto findByCod(int cod) {
        Produto produto = null;
        String sql = "SELECT * FROM produto  Where codigo = ? and visivel = 1";
        open();
        try {
            PreparedStatement stm = conexao.prepareStatement(sql);
            stm.setInt(1, cod);
            ResultSet rs;
            rs = stm.executeQuery();
            while (rs.next()) {
                produto = new Produto();
                produto.setCodigo(rs.getInt("codigo"));
                produto.setNome(rs.getString("nome"));
                produto.setCor(rs.getString("cor"));
                produto.setPreco(rs.getFloat("preco"));

                String categori = Categoria.findById(rs.getInt("id_categoria")).getCategoria();
                produto.setCategoria(categori);

                produto.setTamanhos(Tamanho.findByCodProduto(rs.getInt("codigo")));

                

            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar as produtos: " + e.getMessage());
        } finally {
            close();
        }
        return produto;
    }

    public void delete(Produto produto) {

        String sql = "UPDATE `produto` SET `visivel`= 0 WHERE codigo = ?";
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, produto.getCodigo());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("erro: produto eliminar");
        } finally {
            close();
        }

    }

    public List<Produto> all() {

        List<Produto> produtos = new ArrayList<>();
        String sql = "select * from produto where visivel = 1";

        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs;
            rs = ps.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setCodigo(rs.getInt("codigo"));
                produto.setNome(rs.getString("nome"));
                produto.setCor(rs.getString("cor"));
                produto.setPreco(rs.getFloat("preco"));

                String categoria = Categoria.findById(rs.getInt("id_categoria")).getCategoria();
                produto.setCategoria(categoria);
                produto.setTamanhos(Tamanho.findByCodProduto(rs.getInt("codigo")));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            System.out.println("erro " + e.getMessage());
        } finally {
            close();
        }
        return produtos;

    }

}
