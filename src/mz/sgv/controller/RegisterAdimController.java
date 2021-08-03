/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import mz.sgv.model.Adim;
import mz.sgv.model.Caminhos;

/**
 *
 * @author doroteia
 */
public class RegisterAdimController {

    @FXML
    VBox janela;
    @FXML
    private AnchorPane header;
    @FXML
    private Label title;
    @FXML
    private Label subTitle;
    @FXML
    private Label nome;
    @FXML
    private TextField nomeField;
    @FXML
    private Label apelido;
    @FXML
    private TextField apelidoField;
    @FXML
    private Label senha;
    @FXML
    private TextField senhaField;
    @FXML
    private Label contacto;
    @FXML
    private TextField contactoField;
    @FXML
    private Label email;
    @FXML
    private TextField emailField;
    
    public static Adim adim = new Adim();
    
    public void initialize() {
        
        apenasNumero(contactoField);
        addTextLimiter(contactoField, 9);
    }

    private void mostrarJanela(String caminho, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(janela.getScene().getWindow());
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void seguinte(ActionEvent event) throws IOException {

        if (nomeField.getText().isEmpty()) {
            thread(nomeField, nome);
        } else if (contactoField.getText().isEmpty()) {
            thread(contactoField, contacto);
        } else if (apelidoField.getText().isEmpty()) {
            thread(apelidoField, apelido);
        } else if (emailField.getText().isEmpty()) {
            thread(emailField, email);
        } else if (senhaField.getText().isEmpty()) {
            thread(senhaField, senha);
        } else {
            adim.setNome(nomeField.getText());
            adim.setApelido(apelidoField.getText());
            adim.setSenha(senhaField.getText());
            adim.setContacto(contactoField.getText());
            adim.setEmail(emailField.getText());
            
            
            mostrarJanela(Caminhos.NovaEmpresa, "Registo de Empresa");
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
    
        public  void addTextLimiter(final TextField tf, final int maxLength) {
    tf.textProperty().addListener((final ObservableValue<? extends String> ov, final String oldValue, final String newValue) -> {
        if (tf.getText().length() > maxLength) {
            String s = tf.getText().substring(0, maxLength);
            tf.setText(s);
        }
    });

    }
    
    public void apenasNumero(TextField textField){
    
           textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
               String s="";
               for(char c : newValue.toCharArray()){
                   if(((int)c >= 48 && (int)c <= 57 || (int)c == 46)){
                       s+=c;
                   }
               }
               textField.setText(s);
           });
    }

}
