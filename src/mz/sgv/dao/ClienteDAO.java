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
import mz.sgv.model.Cliente;
import mz.sgv.factory.Conexao;

/**
 *
 * @author doroteia
 */
public class ClienteDAO extends Conexao {

    public void save(Cliente cliente) {

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO CLIENTE ");
        sql.append("(`nome`, `email`, `contacto`) ");
        sql.append(" values(?,?,?)");
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getContacto());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("erro salvar Cliente");
        } finally {
            close();
        }

    }

    public List<Cliente> all() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "select * from cliente";
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs;
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setContacto(rs.getString("contacto"));
                cliente.setEmail(rs.getString("email"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("err buscar clientes: " + e.getMessage());
        } finally {
            close();
        }
        return clientes;

    }

    public Cliente FindById(int id) {
        Cliente cliente = null;
        String sql = "select * from cliente WHERE id = ?";
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs;
            rs = ps.executeQuery();

            while (rs.next()) {
                cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setContacto(rs.getString("contacto"));
                cliente.setEmail(rs.getString("email"));

            }
        } catch (SQLException e) {
            System.out.println("err buscar clientes: " + e.getMessage());
        } finally {
            close();
        }
        return cliente;

    }

}
