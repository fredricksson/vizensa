/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author doroteia
 */
public class SettingsController  {

    @FXML
    private AnchorPane anchor;
    @FXML
    private Label data;

    
   
    public void initialize()  {
        // TODO
        dataRun();
       
    }    
    
       private void mostrarJanela(String caminho, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(anchor.getScene().getWindow());
        stage.setResizable(false);
        stage.show();
        
    }

    @FXML
    private void trocarSenha(ActionEvent event) throws IOException {
        
        mostrarJanela(Paths.trocarSenha, "trcoar Senha");
    }
    
    public void dataRun(){
            Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {

            LocalTime currentTime = LocalTime.now();
            LocalDate date = LocalDate.now();
            data.setText(date.getDayOfMonth() + ":" + date.getMonth() + ":" + date.getYear());
            data.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    
    }
    
    
}
