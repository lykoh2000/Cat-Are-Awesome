/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catareawesome;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import static catareawesome.firstScene.window;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setScene(firstScene.firstScene());
        window.setResizable(false);
        window.setOnCloseRequest(e -> {
            e.consume();
            try {
                closeProgram();
            } catch (InterruptedException ex) {

            }
        });
        window.show();
    }
    
    private static void closeProgram() throws InterruptedException {
        Boolean ans = ConfirmBox.display("Log out?");
        if (ans) {
            System.out.println("Saving data...");
            SaveData.saveCatAttribute();
            SaveData.saveFoodInventory();
            SaveData.saveUser("user.txt");
            
            if (User.hasCat() == false)
                SaveData.saveCat();
            window.close();
            System.out.println("Data saved");
            System.exit(0);
        } 
    }
}
