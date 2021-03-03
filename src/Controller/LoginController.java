/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Services.AlertBox;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Dictionary;
import model.Usuario;

/**
 *
 * @author doroteia
 */
public class LoginController {

    @FXML
    private TextField usuario;
    @FXML
    private PasswordField senha;

    @FXML
    private void entrar(ActionEvent event) throws IOException {
        
         Usuario user = Usuario.login(usuario.getText(), senha.getText());
        if (user != null)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.menu));
            Parent home_page_parent = loader.load();

            MenuController controller = loader.getController();
            

            Scene home_page_scene = new Scene(home_page_parent);
            Stage main_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            main_stage.close();
            main_stage.setScene(home_page_scene);
            
            main_stage.setResizable(false);
            main_stage.setMaximized(false);
            main_stage.show();
        }
        else
        {
            new AlertBox(Dictionary.userPassIncorrect, Dictionary.error, new Alert(Alert.AlertType.WARNING));
        }
    }
    
   
    
}
