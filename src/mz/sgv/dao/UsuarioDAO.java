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
import mz.sgv.model.Usuario;
import mz.sgv.factory.Conexao;

/**
 *
 * @author doroteia
 */
public class UsuarioDAO extends Conexao {

    public void save(Usuario usuario, int id_funcionario) {
        String sql = "INSERT INTO `usuario`( `senha`, `usuario`, `nivelAcesso`, `id_funcionario`) VALUES (?,?,?,?)";
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getSenha());
            ps.setString(2, usuario.getUsuario());
            ps.setInt(3, usuario.getNivelAcesso());
            ps.setInt(4, id_funcionario);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }

    }

    public List<Usuario> all() {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT * FROM usuario";
        try {

            PreparedStatement ps = conexao.prepareStatement(sql);

            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setNivelAcesso(rs.getInt("nivelAcesso"));
                usuarios.add(usuario);
            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }

        return usuarios;
    }

    public Usuario login(String user, String senha) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";
        open();
        try {

            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, senha);
            ResultSet rs;
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId_funcionario(rs.getInt("id_funcionario"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setNivelAcesso(rs.getInt("nivelAcesso"));
                
            }
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
        return usuario;

    }
}
