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
import mz.sgv.factory.Help;
import mz.sgv.model.Tamanho;

/**
 *
 * @author doroteia
 */
public class TamanhoDAO extends Conexao {

    int cont;

    public void save(Tamanho tamanho) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO `tamanho`");
        sql.append("(`id_produto`, `quantidade`, `tamanho`) ");
        sql.append("VALUES (?,?,?) ");

        //Inserindo os tamanho
        List<Tamanho> tamanhos = new ArrayList<>();
        List<Float> arrayTamanhos = Help.StringToArrayFloat(tamanho.getTamanhoString());
        List<Integer> arrayQuantidade = Help.StringToArrayInt(tamanho.getQuantidateString());

        cont = -1;
        arrayTamanhos.forEach((tamanhoFloat) -> {

            cont++;

            open();
            try {
                PreparedStatement ps = conexao.prepareStatement(sql.toString());

                ps.setInt(1, tamanho.getId_produto());
                ps.setInt(2, arrayQuantidade.get(cont));
                ps.setFloat(3, tamanhoFloat);

                ps.executeUpdate();
                ps.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                close();
            }
        });

    }

    public List<Tamanho> all() {

        ArrayList<Tamanho> tamanhos = new ArrayList<>();
        String sql = "select * from tamanho ";
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);

            ResultSet rs;
            rs = ps.executeQuery();

            while (rs.next()) {
                Tamanho tamanho = new Tamanho();
                tamanho.setId(rs.getInt("id"));
                tamanho.setId_produto(rs.getInt("id_produto"));
                tamanho.setQuantidade(rs.getInt("quantidade"));
                tamanho.setTamanho((float) rs.getDouble("tamanho"));
                tamanhos.add(tamanho);
            }
        } catch (SQLException e) {

        }finally{
            close();
        }
        return tamanhos;
    }
    
    public List<Tamanho> findByCodProduto(int id){
    ArrayList<Tamanho> tamanhos = new ArrayList<>();
        String sql = "SELECT * FROM tamanho  WHERE id_produto = ? ";
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs;
            rs = ps.executeQuery();

            while (rs.next()) {
                Tamanho tamanho = new Tamanho();
                tamanho.setId(rs.getInt("id"));
                tamanho.setId_produto(rs.getInt("id_produto"));
                tamanho.setQuantidade(rs.getInt("quantidade"));
                tamanho.setTamanho((float) rs.getDouble("tamanho"));
                tamanhos.add(tamanho);
            }
        } catch (SQLException e) {

        }finally{
            close();
        }
        return tamanhos;
        
    }

}
