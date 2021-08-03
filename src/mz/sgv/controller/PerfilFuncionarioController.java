/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import com.github.gbfragoso.JasperViewerFX;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mz.sgv.factory.Conexao;
import mz.sgv.model.Funcionario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author doroteia
 */
public class PerfilFuncionarioController {

    @FXML
    Label nome, apelido, dataNascimento,
            genero, estadoCivil, tipoFuncionario, nrBi, contactoOpcional, contacto,
            email, cargo, autenticavel, nr_conta, salario, codigo, avenida, quarteirao, rua;
    public static Funcionario funcionario;

    @FXML
    public void initialize() {
        nome.setText(funcionario.getNome());
        cargo.setText(funcionario.getCargo());
        apelido.setText(funcionario.getApelido());
        dataNascimento.setText(funcionario.getDataNascimento().toString());

        genero.setText(funcionario.getSexo());
        nrBi.setText(funcionario.getNrBi());
        contacto.setText(funcionario.getContacto() + "");
        contactoOpcional.setText(funcionario.getContactoOpcional() + "");
        email.setText(funcionario.getEmail());
        codigo.setText(funcionario.getId() + "");
        salario.setText(funcionario.getSalario() + "");
        autenticavel.setText(funcionario.isAutenticavel() ? "Auteticavel" : "Nao autenticavel");
        avenida.setText(funcionario.getMorada().getAvenida().isEmpty() ? "vazio" : "" + funcionario.getMorada().getAvenida());
        quarteirao.setText(funcionario.getMorada().getQuarteirao().isEmpty() ? "vazio" : "" + funcionario.getMorada().getQuarteirao());
        rua.setText(funcionario.getMorada().getRua().isEmpty() ? "vazio" : "" + funcionario.getMorada().getRua());

    }

    @FXML
    public void imprimir(ActionEvent event) throws JRException {

        HashMap param = new HashMap();
        param.put("idParameter", funcionario.getId());

        Conexao conexao = new Conexao();

        URL uri = getClass().getResource("/mz/sgv/relatorios/FuncionarioReport.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(uri);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, conexao.conect());//null: caso não existam filtros
        //JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);//false: não deixa fechar a aplicação principal
        new JasperViewerFX().viewReport("report", jasperPrint);
        // jasperViewer.setVisible(true);
        conexao.close();
    }

    public static void setFuncionario(Funcionario funcionario) throws JRException {
        PerfilFuncionarioController.funcionario = funcionario;

    }

 }
