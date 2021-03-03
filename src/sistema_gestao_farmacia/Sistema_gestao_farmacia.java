/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema_gestao_farmacia;

import Controller.Paths;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author doroteia
 */
public class Sistema_gestao_farmacia extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(Paths.login));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
       // stage.initStyle(StageStyle.TRANSPARENT);
       // scene.setFill(Color.TRANSPARENT);
        //stage.setOpacity(0.5);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
