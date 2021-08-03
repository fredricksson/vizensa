/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.sgv.controller;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author doroteia
 */
public class AddTamanhoController {
    @FXML TextField tamanho;
    @FXML AnchorPane pane;
    public static String tamanho2="";
    
    
   
    
    @FXML public void addTamanho(ActionEvent event){
        tamanho2 +=tamanho.getText()+",";
        tamanho.setText("");
        
    }
    
          public void apenasNumero(TextField textfield){
    
           textfield.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
               String s="";
               for(char c : newValue.toCharArray()){
                   if(((int)c >= 48 && (int)c <= 57 || (int)c == 46)){
                       s+=c;
                   }
               }
               textfield.setText(s);
           });
    }
}
