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
import static mz.sgv.controller.RegisterAdimController.adim;
import mz.sgv.model.Empresa;

/**
 *
 * @author doroteia
 */
public class RegistoEmpresaController {

    @FXML
    private VBox vb;
    @FXML
    private Label empNome;

    @FXML
    private Label empTel;

    @FXML
    private Label empEmail;

    @FXML
    private TextField nomeEmp;
    @FXML
    private TextField telfoneEmp;
    @FXML
    private TextField emailEmp;

    public void initialize() {

    }

    @FXML
    private void cadastrar(ActionEvent event) {
        if (nomeEmp.getText().isEmpty()) {
            thread(nomeEmp, empNome);

        } else if (telfoneEmp.getText().isEmpty()) {

            thread(telfoneEmp, empTel);

        } else if (emailEmp.getText().isEmpty()) {

            thread(emailEmp, empEmail);

        } else {

            Empresa empresa = new Empresa();
            empresa.setNome(nomeEmp.getText());
            empresa.setTelfone(telfoneEmp.getText());
            empresa.setEmail(emailEmp.getText());

            empresa.save();
            adim.save();
            vb.getScene().getWindow().hide();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("ADMNISTRADOR E EMPRESA CADASTRADOS");
            a.show();

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
                    textField.setStyle("-fx-border-color: #E4EFF1;");
                    label.setStyle("-fx-text-fill: #E4EFF1;");
                    transition.pause();

                });
                return null;
            }
        };
        new Thread(task).start();

    }
}
