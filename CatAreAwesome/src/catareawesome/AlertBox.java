/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catareawesome;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox {
    public static void display(String message) {
        Stage window = new Stage();
        window.setMinWidth(250);
        window.initModality(Modality.APPLICATION_MODAL);
        
        Label label = new Label();
        label.setText(message);
        
        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> window.close());
        
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label, closeBtn);
        Scene scene = new Scene(vbox);
        
        window.setScene(scene);
        window.showAndWait();
        
    }
}
