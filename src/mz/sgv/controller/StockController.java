/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author doroteia
 */
public class StockController implements Initializable {

    @FXML
    private TextField pesquisaField;
    @FXML
    private TableView<?> produtos;
    @FXML
    private TableColumn<?, ?> quantidades;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void pesquisar(ActionEvent event) {
    }

    @FXML
    private void imprimirRelatorio(ActionEvent event) {
    }
    
}
