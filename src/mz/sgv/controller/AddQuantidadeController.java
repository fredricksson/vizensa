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
public class AddQuantidadeController {

    
    
    
    
    @FXML
    private AnchorPane pane;
    @FXML
    private TextField quantidade;
    public static String quantidade2 = "";
    
   
    @FXML
    private void addQuantidade(ActionEvent event) {
        quantidade2 += quantidade.getText()+",";

        quantidade.setText("");
        
    }
          public void apenasNumero(TextField textfield){
    
           textfield.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
               String s="";
               for(char c : newValue.toCharArray()){
                   System.out.println((int)c);
                   if(((int)c >= 48 && (int)c <= 57 || (int)c == 46)){
                       s+=c;
                   }
               }
               textfield.setText(s);
           });
    }
}
