/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import mz.sgv.model.Adim;
import mz.sgv.model.Caminhos;

import mz.sgv.model.Usuario;

/**
 *
 * @author doroteia
 */
public class LoginController {

    @FXML
    private Label title;
    @FXML
    private Label title1;
    @FXML
    private TextField username;
    @FXML
    private PasswordField senha;
    @FXML
    private Label userLabel;
    @FXML
    private Label senhaLabel;

    public static Adim adimSession;
    public static Usuario usuarioSession;

    public void initialize() {

    }

    @FXML
    public void onClick(ActionEvent event) throws IOException {

        if (username.getText().isEmpty()) {
            thread(username, userLabel);

        } else if (senha.getText().isEmpty()) {
            thread(username, senhaLabel);
           

        } else {

            if (Adim.login(username.getText(), senha.getText()) != null) {
                
                carregar(event,Caminhos.PaginaPrincipal);
                
                adimSession = Adim.login(username.getText(), senha.getText());
            } else if (Usuario.login(username.getText(), senha.getText()) != null) {
                usuarioSession = Usuario.login(username.getText(), senha.getText());
             
                 carregar(event,Caminhos.Caixa);
            }else{
             threadValidacao(username, senha, userLabel, senhaLabel);
            }
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

    public void threadValidacao(TextField textField, TextField textField2, Label label, Label label2) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                title1.setVisible(true);
                textField.setStyle("-fx-border-color: red;");
                textField2.setStyle("-fx-border-color: red;");
                label.setStyle("-fx-text-fill: red;");
                label2.setStyle("-fx-text-fill: red;");
                FadeTransition transition = new FadeTransition(Duration.millis(2000), label);
                transition.setFromValue(0.0);
                transition.setToValue(1.0);

                FadeTransition transition2 = new FadeTransition(Duration.millis(2000), label2);
                transition2.setFromValue(0.0);
                transition2.setToValue(1.0);

                FadeTransition transition3 = new FadeTransition(Duration.millis(2000), title);
                transition2.setFromValue(0.0);
                transition2.setToValue(1.0);
                
                transition3.play();
                transition2.play();
                transition.play();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                Platform.runLater(() -> {
                    textField.setStyle("-fx-border-color: #E4EFF1;");
                    textField2.setStyle("-fx-border-color: #E4EFF1;");
                    label.setStyle("-fx-text-fill: #E4EFF1;");
                    label2.setStyle("-fx-text-fill: #E4EFF1;");
                    title1.setVisible(false);
                    transition.pause();
                    transition2.pause();
                    transition3.pause();
                    

                });
                return null;
            }
        };
        new Thread(task).start();

    }

    public void carregar(ActionEvent event,String caminho) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent home_page_parent = loader.load();

        //InitialController controller = loader.getController();
        // controller.setUser(user);
        Scene home_page_scene = new Scene(home_page_parent);
        Stage main_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.close();
        main_stage.setScene(home_page_scene);
        main_stage.setTitle("Vizensa");
        main_stage.setResizable(true);
        main_stage.setMaximized(true);
        main_stage.show();
    }

}
