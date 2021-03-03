/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author doroteia
 */
public class MenuController {

    @FXML
    private void medicamentos(ActionEvent event) throws IOException {
        
        janela(Paths.medicamentos , event,true,true);
    }

    @FXML
    private void caixa(ActionEvent event) throws IOException {
        janela(Paths.caixa, event,true,false);
    }

    @FXML
    private void estatisca(ActionEvent event) throws IOException {
        janela(Paths.estatistica, event, false,false);
    }

    @FXML
    private void definicoes(ActionEvent event) throws IOException {
        janela(Paths.definicoes, event, false,false);
    }

    private void janela(String caminho , ActionEvent event,boolean maximized,boolean resizable) throws IOException {

     
      
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
            Parent home_page_parent = loader.load();

            

            Scene home_page_scene = new Scene(home_page_parent);
            Stage main_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            main_stage.close();
            main_stage.setScene(home_page_scene);

            main_stage.setResizable(resizable);
            main_stage.setMaximized(maximized);
            main_stage.show();
        
    }

}
