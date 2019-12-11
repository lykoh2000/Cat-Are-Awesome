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

public class ConfirmBox {
    static boolean ans;
    
    public static boolean display(String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
        
        Label label = new Label();
        label.setText(message);
        
        Button yesBtn = new Button("Yes");
        Button cancelBtn = new Button("Cancel");
        
        yesBtn.setOnAction(e -> {
            ans = true;
            window.close();
        });
        
        cancelBtn.setOnAction(e -> {
            ans = false;
            window.close();
        });
        
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label, yesBtn, cancelBtn);
        
        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.showAndWait();
       
        return ans;
        
    }
    
}
