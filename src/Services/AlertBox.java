/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import javafx.scene.control.Alert;

/**
 *
 * @author doroteia
 */

    
    
    public class AlertBox extends Alert {

    public AlertBox(String message, String title, Alert alert) {
        super(alert.getAlertType());
        this.setTitle(title);
        this.setHeaderText(message);
        this.showAndWait();
    }

}

