/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import mz.sgv.model.Caminhos;

/**
 *
 * @author doroteia
 */
public class PaginaPrincipalController {
    
    @FXML AnchorPane janela;
    @FXML Button sair;
    @FXML Label time;
    
    @FXML public void initialize(){
        dataRun();
    }
    //Carrega a tela do caixa
    @FXML public void caixa() throws IOException{
        mostrarJanela(Caminhos.Caixa,"Caixa");
    }
    //Carrega a tela de produtos
    @FXML public void produtos(ActionEvent event) throws IOException{
       mostrarJanela(Caminhos.produtos,"Produtos");
    }
    //Carrega a tela de Funcionario
    @FXML public void funcionarios(ActionEvent event) throws IOException{
       mostrarJanela(Caminhos.Funcionarios,"Funcionarios");
    }
    //Carrega a tela de vendas
    @FXML public void vendas(ActionEvent event) throws IOException{
        mostrarJanela(Caminhos.Vendas, "Vendas");
    }
    //Carrega a tela de clientes
    @FXML public void clientes(ActionEvent event) throws IOException{
        mostrarJanela(Caminhos.Clientes, "Clientes");
    
    }
    //Sair do Programa
    @FXML public void sair(ActionEvent event) throws IOException{
        System.exit(0);
    }
    
    
      private void mostrarJanela(String caminho, String title) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
        Parent parent = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(janela.getScene().getWindow());
        stage.setMaximized(true);
        //stage.setResizable(false);
        stage.show();
    }
      
         public void dataRun(){
            Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {

            LocalTime currentTime = LocalTime.now();
            LocalDate date = LocalDate.now();
            time.setText(date.getDayOfMonth() + ":" + date.getMonth() + ":" + date.getYear());
            time.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    
    }
    
}
