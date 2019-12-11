/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catareawesome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import static catareawesome.firstScene.window;


public class OptionPanelScene {
    
    
    
    public static Scene optionPanel() throws FileNotFoundException {
        
        BorderPane pane = new BorderPane();
        //initialize all the button
        Button feedBtn = new Button();
        Button logOutBtn = new Button();
        Button foodStoreBtn = new Button();
        Button playBtn = new Button();
        Button chatBtn = new Button();
        Button catFighterBtn = new Button();
        
//        Image image = new Image(new FileInputStream("C:\\Users\\ken_t\\Documents\\Li Yang\\UM\\Assignment\\FOP\\Cats-Are-Awesome\\images\\background1.jfif"));
//        //BackgroundSize bSize = new BackgroundSize(600, 600, false, false, true, false);
//        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT,
//            BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//        Background background = new Background(backgroundImage);
//        pane.setBackground(background); 
        BackgroundFill myBF = new BackgroundFill(Color.CORNFLOWERBLUE, new CornerRadii(1), new Insets(0.0,0.0,0.0,0.0));// or null for the padding
        //then you set to your node or container or layout
       // pane.setBackground(new Background(myBF));
        
        Image feed = new Image(new FileInputStream("C:images\\feed.jfif"));
        Image logOut = new Image(new FileInputStream("images\\logOut.png"));
        Image foodStore = new Image(new FileInputStream("images\\food store.jpg"));
        Image play = new Image(new FileInputStream("images\\play.jfif"));
        Image chat = new Image(new FileInputStream("images\\chat.jfif"));
        Image catFighter = new Image(new FileInputStream("images\\cat fighter icon.jfif"));
        
        ImageView feedIv = new ImageView(feed);
        ImageView logOutIv = new ImageView(logOut);
        ImageView foodStoreIv = new ImageView(foodStore);
        ImageView playIv = new ImageView(play);
        ImageView chatIv = new ImageView(chat);
        ImageView catFighterIv = new ImageView(catFighter);
        
        Image catImage = null;
        switch(Cat.getCatId()) {
            case 1:
                catImage = new Image(new FileInputStream("images\\cat 1.gif"));
                break;
            case 2:
                catImage = new Image(new FileInputStream("images\\cat 2.gif"));
                break;
            case 3:
                catImage = new Image(new FileInputStream("images\\cat 3.gif"));
                break;
            case 4:
                catImage = new Image(new FileInputStream("images\\cat 4.gif"));
                break;
        }
        
        ImagePattern catIv = new ImagePattern(catImage);
//        catIv.setFitWidth(300);
//        catIv.setFitHeight(300);
        Rectangle rec = new Rectangle(300, 300);
        rec.setFill(catIv);
        
        feedIv.setFitWidth(70);
        feedIv.setFitHeight(70);
        
        logOutIv.setFitWidth(50);
        logOutIv.setFitHeight(50);
        
        playIv.setFitHeight(70);
        playIv.setFitWidth(70);
        
        chatIv.setFitWidth(70);
        chatIv.setFitHeight(70);
        
        foodStoreIv.setFitWidth(70);
        foodStoreIv.setFitHeight(70);
        
        chatIv.setFitHeight(70);
        chatIv.setFitWidth(70);
        
        catFighterIv.setFitWidth(70);
        catFighterIv.setFitHeight(70);
        
        feedBtn.setGraphic(feedIv);
        logOutBtn.setGraphic(logOutIv);
        foodStoreBtn.setGraphic(foodStoreIv);
        playBtn.setGraphic(playIv);
        chatBtn.setGraphic(chatIv);
        catFighterBtn.setGraphic(catFighterIv);
        
        // action button clicked
        playBtn.setOnAction(e -> {
            try {
                window.setScene(miniGame.miniGameScene());
            } catch (FileNotFoundException ex) {

            }
        });
        
        feedBtn.setOnAction(e -> {
            try {
                window.setScene(foodInventory.displayFoodInventory());
            } catch (FileNotFoundException ex) {

            }
        });
        
        foodStoreBtn.setOnAction(e -> {
            try {
                window.setScene(foodStoreScene.foodStoreScene());
            } catch (FileNotFoundException ex) {

            }
        });
        
        logOutBtn.setOnAction(e -> {
            closeProgram();
        });
        
        chatBtn.setOnAction(e -> {
            window.setScene(Chat.chattingScene());
        });
        catFighterBtn.setOnAction(e -> {
            try {
                window.setScene(CatFighter.catFighterScene());
            } catch (FileNotFoundException ex) {

            }
        });
        
        // middle part
        GridPane grid = new GridPane();
        grid.setHgap(0);
        grid.setVgap(30);
        grid.setPadding(new Insets(0, 150, 0, 150));
        grid.add(rec, 1, 1);
        //grid.add(feedBtn, 0, 1);
     //   grid.add(playBtn, 0, 1);
      //  grid.add(foodStoreBtn, 2, 1);
        
        
        // display cat attributes
        Text lvlText = new Text();
        lvlText.setText("Level: " + Cat.getLevel());
        Text experienceText = new Text();
        experienceText.setText("Experience: " + new DecimalFormat("##.##").format(Cat.getExperience()) + " / " +
                new DecimalFormat("##.##").format(Cat.getExperienceLimit()));
        Text happinessText = new Text();
        happinessText.setText("Happiness: " + new DecimalFormat("##.##").format(Cat.getHappiness()) + "%");
        Text hungerText = new Text();
        hungerText.setText("Hunger: " + new DecimalFormat("##.##").format(Cat.getHunger()) + "%");
        Text timeOfAliveText = new Text();
        timeOfAliveText.setText("Time of Birth: " + Cat.getTimeOfAlive());
        Text timeToLiveText = new Text();
        timeToLiveText.setText("Time to live: " + Cat.getTimeToLive());
        
        lvlText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        happinessText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        hungerText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        experienceText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        timeOfAliveText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        timeToLiveText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        
        VBox attributeBox = new VBox(10);
        attributeBox.getChildren().addAll(lvlText, experienceText, happinessText, hungerText, timeOfAliveText, timeToLiveText);
        attributeBox.setPadding(new Insets(10, 0, 0, 20));
        
        
        HBox topHbox = new HBox(200);
        HBox.setMargin(logOutBtn, new Insets(20, 20, 0, 0));
        topHbox.setAlignment(Pos.TOP_LEFT);
        
        topHbox.getChildren().addAll(attributeBox, logOutBtn);
        
        
        HBox bottomHbox = new HBox(30);
        bottomHbox.setPadding(new Insets(10, 0, 20, 30));
        bottomHbox.setAlignment(Pos.CENTER_LEFT);
        bottomHbox.getChildren().addAll(feedBtn, foodStoreBtn, chatBtn, playBtn, catFighterBtn);
//        bottomHbox.getChildren().add(catFighterBtn);
       // bottomHbox.setPadding(new Insets(0, 0, 30, 200));
        
        //pane.setPadding(new Insets(20, 30, 0, 10));

        pane.setTop(topHbox);
        BorderPane.setAlignment(grid, Pos.CENTER);
        pane.setCenter(grid);
        pane.setBottom(bottomHbox);
//        BorderPane.setAlignment(catIv, Pos.CENTER);
//        pane.setCenter(catIv);
//        BorderPane.setAlignment(feedBtn, Pos.TOP_CENTER);
            
        
        Scene scene = new Scene(pane, 600, 600);

        return scene;
    }
    
    private static void closeProgram() {
        Boolean ans = ConfirmBox.display("Log out?");
        if (ans) {
            System.out.println("Saving data...");
            SaveData.saveCatAttribute();
            SaveData.saveFoodInventory();
            SaveData.saveUser("user.txt");
            window.close();
            System.out.println("Data saved");
            System.exit(0);
        } 
    }

}
