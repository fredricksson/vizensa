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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import mz.sgv.model.Cargo;

/**
 *
 * @author doroteia
 */
public class NovoCargoController {

    @FXML
    private TextField cargo;
    @FXML
    private Label msg_erro;
    @FXML
    private Label sucedido;
    @FXML
    private Label existente;
    
    public void initialize(){
        msg_erro.setVisible(false);
        sucedido.setVisible(false);
        existente.setVisible(false);
    }

    @FXML
    private void save(ActionEvent event) {

        if (!cargo.getText().isEmpty()) {
            
            Cargo carg = new Cargo();
            carg.setCargo(cargo.getText());
            if(carg.save() == 1){
            
                Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    
                    sucedido.setVisible(true);
                    FadeTransition transition = new FadeTransition(Duration.millis(2000), sucedido);
                    transition.setFromValue(0.0);
                    transition.setToValue(1.0);
                    transition.play();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }

                    Platform.runLater(() -> {
                        transition.pause();
                        
                        sucedido.setVisible(false);
                    });
                    return null;
                }
            };
                 new Thread(task).start();
                 cargo.setText("");
            }else{

            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    
                    cargo.setStyle("-fx-border-color: red");
                    FadeTransition transition = new FadeTransition(Duration.millis(2000), existente);
                    transition.setFromValue(0.0);
                    transition.setToValue(1.0);
                    existente.setVisible(true);
                    transition.play();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }

                    Platform.runLater(() -> {
                        transition.pause();
                        cargo.setStyle("-fx-border-color: blue");
                        existente.setVisible(false);
                    });
                    return null;
                }
            };
                 new Thread(task).start();
            
            }
           
            
            
            
        } else {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    cargo.setStyle("-fx-border-color: red");
                    msg_erro.setVisible(true);
                    FadeTransition transition = new FadeTransition(Duration.millis(2000), msg_erro);
                    transition.setFromValue(0.0);
                    transition.setToValue(1.0);
                    transition.play();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }

                    Platform.runLater(() -> {
                        transition.pause();
                        cargo.setStyle("-fx-border-color: blue");
                        msg_erro.setVisible(false);
                    });
                    return null;
                }
            };
            new Thread(task).start();
        }
    }

}
