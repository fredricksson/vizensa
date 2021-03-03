/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import mz.sgv.model.Caminhos;

/**
 *
 * @author doroteia
 */
public class PaginaInicialController  {
    
    @FXML VBox janela;
    
    @FXML public void initialize(){
     
    }
    @FXML public void onClickEntrar(ActionEvent event) throws IOException{
        mostrarJanela(Caminhos.Login, "Login");
    }
    
    @FXML public void OnActionCriarConta(ActionEvent event) throws IOException{
        mostrarJanela(Caminhos.NovoAdim,"Registo Administrador");   
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
        stage.setResizable(false);
        
        stage.setOnHidden((event)->{
            LoginController.adimSession = null;
            LoginController.usuarioSession = null;
        });
        stage.show();
    }
    
}
