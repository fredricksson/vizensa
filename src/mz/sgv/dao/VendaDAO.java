/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import mz.sgv.model.HistoricoVenda;
import mz.sgv.model.Venda;
import mz.sgv.factory.Conexao;
import mz.sgv.model.Cliente;
import mz.sgv.model.Funcionario;

/**
 *
 * @author doroteia
 */
public class VendaDAO extends Conexao {
    
    public void save(Venda venda) throws ParseException {
        
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO `venda`");
        sql.append("( `dataVenda`, `codigoProdutos`, `quantidades` ,`tamanhos`, ");
        sql.append("`totalPago`, `id_funcionario`, `id_cliente`,`hora`,`precos`,`quantidadeTotal`) ");
        sql.append("VALUES (?,?,?,?,?,?,?,?,?,?)");
        open();
        try {
            LocalTime currentTime = LocalTime.now();
            
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            
            ps.setDate(1, (Date) venda.getDataVenda());
            ps.setString(2, venda.getCodigoProdutos());
            ps.setString(3, venda.getQuantidades());
            ps.setString(4, venda.getTamanhos());
            ps.setDouble(5, venda.getTotalPago());
            ps.setInt(6, venda.getId_funcionario());
            ps.setInt(7, venda.getId_Cliente());
            ps.setTime(8, new Time(currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond()));
            ps.setString(9, venda.getPrecos());
            ps.setInt(10, venda.getQuantidadeTotal());
            
            ps.executeUpdate();
            saveHistorico(venda.getId_funcionario(), 1,lastIdVenda() );
            ps.close();
            
            
        } catch (SQLException e) {
            
            System.out.println("erro " + e.getMessage());
        } finally {
            close();
        }
        
    }
    
    public void saveHistorico(int id_funcionario, int id_cliente, int id_venda) {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into HistoricoVenda");
        sql.append("(id_funcionario,id_cliente,id_venda) ");
        sql.append("values(?,?,?)");
        
        open();
        try {
           
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            
           
           
            ps.setInt(1, id_funcionario);
            ps.setInt(2, id_cliente);
            ps.setInt(3, id_venda);
            
            ps.executeUpdate();
            
            ps.close();
        } catch (SQLException e) {
            
            System.out.println("erro historico venda metodo saveHistorico de vendas " + e.getMessage());
        } finally {
            close();
        }
        
    }

    public List<HistoricoVenda> historicoVendas() {
        List<HistoricoVenda> historico = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM funcionario f, cliente c,  venda v JOIN historicoVenda iv  ");
        
        sql.append(" WHERE iv.id_cliente = c.id ");
        sql.append("AND iv.id_funcionario = f.id AND iv.id_venda = v.id");

//        sql.append("SELECT * FROM funcionario f , ");
//        sql.append("produto p , cliente c JOIN venda vv ");
//        sql.append("WHERE f.id = vv.id_funcionario ");
        //  sql.append("and c.id = vv.id_cliente ");
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            ResultSet rs;
            rs = ps.executeQuery();
            
            while (rs.next()) {
                HistoricoVenda hv = new HistoricoVenda();
                hv.setData(rs.getDate("dataVenda"));
                hv.setTotalPago((float) rs.getDouble("totalPago"));
                Funcionario f = Funcionario.findById(rs.getInt(1));
                hv.setVenda(FindById(rs.getInt(19)));
                hv.setFuncionario(f);
                hv.setCliente(Cliente.findById(rs.getInt(15)));
                hv.setNomeCliente(Cliente.findById(rs.getInt(15)).getNome());
                hv.setNomeFuncionario(f.getNome());
                
                hv.setQuantidadeTotal(FindById(rs.getInt(19)).getQuantidadeTotal());
                hv.setHora(rs.getTime("hora"));
                hv.setTotalPago((float) rs.getDouble("totalPago"));
                historico.add(hv);
                
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            close();
        }
        
        return historico;
    }
    
    public List<HistoricoVenda> findByName(String nome, String coluna) {
        
        if (coluna.isEmpty()) {
            coluna = "f.nome";
        }
        
        List<HistoricoVenda> historico = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM funcionario f, cliente c,  venda v JOIN historicoVenda iv  ");
        
        sql.append(" WHERE iv.id_cliente = c.id ");
        sql.append("AND iv.id_funcionario = f.id AND iv.id_venda = v.id ");
        sql.append("AND " + coluna + " like ?");

//        sql.append("SELECT * FROM funcionario f , ");
//        sql.append("produto p , cliente c JOIN venda vv ");
//        sql.append("WHERE f.id = vv.id_funcionario ");
        //  sql.append("and c.id = vv.id_cliente ");
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            ResultSet rs;
            ps.setString(1, "%" + nome + "%");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                HistoricoVenda hv = new HistoricoVenda();
                hv.setData(rs.getDate("dataVenda"));
                hv.setTotalPago((float) rs.getDouble("totalPago"));
                Funcionario f = Funcionario.findById(rs.getInt(1));
                hv.setVenda(FindById(rs.getInt(19)));
                hv.setFuncionario(f);
                hv.setCliente(Cliente.findById(rs.getInt(15)));
                hv.setNomeCliente(Cliente.findById(rs.getInt(15)).getNome());
                hv.setNomeFuncionario(f.getNome());
                
                hv.setQuantidadeTotal(FindById(rs.getInt(19)).getQuantidadeTotal());
                hv.setHora(rs.getTime("hora"));
                hv.setTotalPago((float) rs.getDouble("totalPago"));
                historico.add(hv);
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            close();
        }
        
        return historico;
    }
    
    public Venda FindById(int id) {
        
        Venda venda = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM venda WHERE id = ? ");
        
        open();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            ps.setInt(1, id);
            ResultSet rs;
            rs = ps.executeQuery();
            
            while (rs.next()) {
                venda = new Venda();
                venda.setCodigoProdutos(rs.getString("codigoProdutos"));
                venda.setQuantidades(rs.getString("quantidades"));
                venda.setPrecos(rs.getString("precos"));
                venda.setQuantidadeTotal(rs.getInt("quantidadeTotal"));
                venda.setTamanhos(rs.getString("tamanhos"));
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } finally {
            close();
        }
        
        return venda;
        
    }
    
    public List<HistoricoVenda> historico_vendas(int cliCod) {
        List<HistoricoVenda> historico = FXCollections.observableArrayList();
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM funcionario f , ");
        sql.append("produto p , cliente c , venda vv ");
        sql.append("JOIN venda_compra vc WHERE f.funCodigo=vc.Funcionario_codigo ");
        sql.append("and p.codigo = vc.produto_codigo ");
        sql.append("and '" + cliCod + "'= c.idCliente ");
        sql.append("and vv.idVenda = vc.venda_codigo ");
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            ResultSet rs;
            rs = ps.executeQuery();
            System.out.println("brm");
            while (rs.next()) {
//        
//                historico.add(new HistoricoVenda(rs.getString("funNome"),rs.getString("cliNome"),
//                rs.getDate("venData_venda"),rs.getString("venHora_venda"),rs.getString("prodNome"),
//                rs.getString("prodCor"),rs.getInt("venQuantidade"),rs.getFloat("venValor_Pago")));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return historico;
    }
    
    public List<HistoricoVenda> hist_vend_fun(int funCod) {
        List<HistoricoVenda> historico = FXCollections.observableArrayList();
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM funcionario f , ");
        sql.append("produto p , cliente c , venda vv ");
        sql.append("JOIN venda_compra vc WHERE f.funCodigo= 1 ");
        sql.append("and p.codigo = vc.produto_codigo ");
        sql.append("and c.idCliente= vc.cliente_codigo ");
        sql.append("and vv.idVenda = vc.venda_codigo ");
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql.toString());
            ResultSet rs;
            rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
//                
//                historico.add(new HistoricoVenda(rs.getString("funNome"),rs.getString("cliNome"),
//                rs.getDate("venData_venda"),rs.getString("venHora_venda"),rs.getString("prodNome"),
//                rs.getString("prodCor"),rs.getInt("venQuantidade"),rs.getFloat("venValor_Pago")));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return historico;
    }
    
       public int lastIdVenda() {
        int id = -1;
        String sql = "select max(id) from venda ";
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
   
    
    public static void main(String[] args) throws ParseException {
        VendaDAO vd = new VendaDAO();
        //  vd.registar_venda(new Venda(date_from_string("12/07/2000"),"15:12",12,14f));
        System.out.println(vd.historico_vendas(1));
    }
    
}
