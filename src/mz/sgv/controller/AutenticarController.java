/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import mz.sgv.dao.FuncionarioDAO;
import mz.sgv.model.Funcionario;
import mz.sgv.model.Usuario;

/**
 *
 * @author doroteia
 */
public class AutenticarController {
    
    FuncionarioDAO fd = new FuncionarioDAO();
    @FXML
    ComboBox combo_nomeFuncionario;
   

    @FXML
    public void initialize() {
      
      
        for (Funcionario funcionario : Funcionario.all()) {
            combo_nomeFuncionario.getItems().add(funcionario.getId()+" "+funcionario.getNome());   
        }
    }

    @FXML
    public void autenticar(ActionEvent event) {
         Alert a = new Alert(Alert.AlertType.CONFIRMATION);
         String user = combo_nomeFuncionario.getSelectionModel().getSelectedItem().toString();   
         Usuario usuario = new Usuario();
         usuario.setSenha("1234");
         usuario.setUsuario(user);
         int idFuncionario = Integer.parseInt(user.substring(0,2));
         usuario.save(idFuncionario);
        a.setContentText("autenticaticado com sucesso");
        a.show();
    }

    @FXML
    public void desautenticar(ActionEvent event) {
                 Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("desautenticar");
        a.show();
    }
}
