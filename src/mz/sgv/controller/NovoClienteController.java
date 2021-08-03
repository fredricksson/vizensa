/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import mz.sgv.model.Cliente;
import mz.sgv.factory.Help;

/**
 *
 * @author doroteia
 */
public class NovoClienteController {

    @FXML
    VBox janela;
    @FXML
    TextField nomeCliente;
    @FXML
    TextField email;
    @FXML
    TextField contacto;
    
    public static String nomeCliente1="";
    @FXML
    private Label erroEmail;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelContacto;

    @FXML
    public void salvar(ActionEvent event) {
        Cliente cliente;

        Alert a = new Alert(Alert.AlertType.INFORMATION);

        if (!nomeCliente.getText().equals("")) {
            if (!email.getText().equalsIgnoreCase("")) {
                if (Help.validar_email(email.getText())) {
                    cliente = new Cliente();
                    cliente.setNome(nomeCliente.getText());
                    cliente.setEmail(email.getText());
                   

                    cliente.save();

                    
                    a.setContentText("Cliente salvo");
                    a.show();

                    nomeCliente.clear();
                    email.clear();
                    contacto.clear();
                    janela.getScene().getWindow().hide();

                } else {

                    threadEmail(email, labelEmail);

                }

            } else {
                nomeCliente1 = nomeCliente.getText();
                 cliente = new Cliente();
                    cliente.setNome(nomeCliente.getText());
                    cliente.setEmail(email.getText());
                    cliente.setContacto(contacto.getText());

                    cliente.save();

                    
                    a.setContentText("Cliente salvo");
                    a.show();

                nomeCliente.clear();
                email.clear();
                contacto.clear();

                janela.getScene().getWindow().hide();
            }
        }else{
            thread(nomeCliente, labelNome);
        }

    }
    
      public void thread(TextField textField, Label label) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                textField.setStyle("-fx-border-color: red;");
                label.setStyle("-fx-text-fill: red;");
                FadeTransition transition = new FadeTransition(Duration.millis(2000), label);
                transition.setFromValue(0.0);
                transition.setToValue(1.0);
                transition.play();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                Platform.runLater(() -> {
                    textField.setStyle("-fx-border-color: #fff;");
                    label.setStyle("-fx-text-fill: #fff;");
                    transition.pause();

                });
                return null;
            }
        };
        new Thread(task).start();

    }
      public void threadEmail(TextField textField, Label label) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                erroEmail.setVisible(true);
                textField.setStyle("-fx-border-color: red;");
                label.setStyle("-fx-text-fill: red;");
                FadeTransition transition = new FadeTransition(Duration.millis(2000), label);
                transition.setFromValue(0.0);
                transition.setToValue(1.0);
                transition.play();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                Platform.runLater(() -> {
                    textField.setStyle("-fx-border-color: #FFF;");
                    label.setStyle("-fx-text-fill: #FFF;");
                    erroEmail.setVisible(false);
                    transition.pause();

                });
                return null;
            }
        };
        new Thread(task).start();

    }

}
