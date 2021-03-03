/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Button;
import mz.sgv.dao.VendaDAO;

/**
 *
 * @author doroteia
 */
public class HistoricoVenda {

    //chaves estrangeiras
    private String nomeFuncionario;

    private Funcionario funcionario;
    private Cliente cliente;
    // fim

    private String nomeCliente;

    private float totalPago;
    private int quantidadeTotal;
    private Date data;

    private Button acao;
    private Venda venda;
    private Time hora;

    private static final VendaDAO dao = new VendaDAO();

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public float getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(float totalPago) {
        this.totalPago = totalPago;
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(int quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public Button getAcao() {
        return acao;
    }

    public void setAcao(Button acao) {
        this.acao = acao;
    }

    public Date getData() {
        return data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public static List<HistoricoVenda> all() {
        return dao.historicoVendas();
    }
    
    public static List<HistoricoVenda> findByName(String nome) {
        return dao.findByName(nome,"");
    }
    public static List<HistoricoVenda> findByName(String nome,String coluna) {
        return dao.findByName(nome,coluna);
    }

}
