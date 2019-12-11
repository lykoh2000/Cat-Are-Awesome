/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catareawesome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class firstScene  {

    static Stage window;


    
    

    public static Scene firstScene() throws FileNotFoundException {
        // background image
        Image image = new Image(new FileInputStream("C:\\Users\\ken_t\\Documents\\Li Yang\\UM\\Assignment\\FOP\\Cats-Are-Awesome\\images\\catgameinterface.jpg"), 600, 600, false, false);
        
        
        BorderPane pane = new BorderPane();
        BackgroundImage myBI= new BackgroundImage(image, BackgroundRepeat.REPEAT, 
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        pane.setBackground(new Background(myBI));
        
        
        // start button
        ImageView start = new ImageView(new Image(new FileInputStream("C:\\Users\\ken_t\\Documents\\Li Yang\\UM\\Assignment\\FOP\\Cats-Are-Awesome\\images\\start game.jfif")));
        ImageView quit = new ImageView(new Image(new FileInputStream("C:\\Users\\ken_t\\Documents\\Li Yang\\UM\\Assignment\\FOP\\Cats-Are-Awesome\\images\\quit game.jfif")));
        
        start.setFitHeight(40);
        start.setFitWidth(80);
        
        quit.setFitHeight(40);
        quit.setFitWidth(80);
        
        // background image
        //start and quit button
        Button startBtn = new Button();
        startBtn.setGraphic(start);
        startBtn.setOnAction(e -> {
            window.setScene(logInScene.logInSignUpScene());
        });
        Button quitBtn = new Button();
        quitBtn.setGraphic(quit);
        quitBtn.setOnAction(e -> {
            System.exit(0);
        });
        VBox vb = new VBox(20);
        vb.setPadding(new Insets(10,10,0,0));
        vb.getChildren().addAll(startBtn, quitBtn);
        pane.setRight(vb);
        
        Scene scene1 = new Scene(pane, 600, 600);
        //playMusic();
        return scene1;
        
        
    }
    
    
    private static void playMusic() {
        AudioPlayer ap = AudioPlayer.player;
        AudioStream as;
        AudioData ad; 
        
        ContinuousAudioDataStream loop = null;
        
        try {
            as = new AudioStream(new FileInputStream("background music.wmv"));
            ad = as.getData();
            loop = new ContinuousAudioDataStream(ad);
        } catch (IOException e) {
            
        }
        ap.start(loop);
    }
    
}
