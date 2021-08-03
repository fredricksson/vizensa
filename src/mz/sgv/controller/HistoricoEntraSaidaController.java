/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author doroteia
 */
public class HistoricoEntraSaidaController {
    @FXML TextField PesquisaPorNome;
    
        @FXML
    public void initialize() {

    }

    @FXML
    public void imprimirBusca(ActionEvent event) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("iMPRIMIRbUSACA");
        a.show();
    }
    
}
