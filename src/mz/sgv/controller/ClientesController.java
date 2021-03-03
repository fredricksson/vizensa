/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mz.sgv.model.Cliente;
import mz.sgv.factory.Help;

/**
 * FXML Controller class
 *
 * @author TOMAS MONDLANE TSM
 */
public class ClientesController {

//    @FXML
//    private TextField tfpesquisar;
//    @FXML
//    private TableView<?> tabelaCliente;
//    @FXML
//    private TextField tfnome;
//    @FXML
//    private TextField tfcontacto;
//    @FXML
//    private TextField tfemail;
    @FXML
    TextField tfnome;
    @FXML
    TextField tfemail;
    @FXML
    TextField tfcontacto;
    @FXML
    TableView<Cliente> tabelaCliente;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        lista();
        tabelaCliente.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

   
    @FXML
    public void cadastrar(ActionEvent event) throws IOException {
        Cliente cliente;

        if (Help.validar_email(tfemail.getText()) || 
                tfemail.getText().isEmpty() && 
                !tfcontacto.getText().isEmpty() &&
                !tfnome.getText().isEmpty()) {
            
            cliente = new Cliente();
            cliente.setNome(tfnome.getText());
            cliente.setEmail(tfemail.getText());
            cliente.setContacto(tfcontacto.getText());

            cliente.save();

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("salvar Cliente");
            a.show();

        } else {
            tfemail.setStyle("-fx-border-color: red");
        }
    }

    public void lista() {

        ObservableList<Cliente> list = FXCollections.observableArrayList(Cliente.all());

        tabelaCliente.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nome"));
        tabelaCliente.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("contacto"));
        tabelaCliente.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));

        tabelaCliente.setItems(list);

    }


}
