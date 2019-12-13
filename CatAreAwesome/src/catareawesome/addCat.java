/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catareawesome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import static catareawesome.firstScene.window;


public class addCat {
    static TextField catNameField;

    static RadioButton cat1Btn;
    static RadioButton cat2Btn;
    static RadioButton cat3Btn;
    static RadioButton cat4Btn;
    
    public static Scene addCatScene() throws FileNotFoundException {
        Text title = new Text();
        Image cat1;
        Image cat2;
        Image cat3;
        Image cat4;
        
        cat1 = new Image(new FileInputStream("C:\\Users\\ken_t\\Documents\\Li Yang\\UM\\Assignment\\FOP\\Cats-Are-Awesome\\images\\cat 1.gif"));
        cat2 = new Image(new FileInputStream("C:\\Users\\ken_t\\Documents\\Li Yang\\UM\\Assignment\\FOP\\Cats-Are-Awesome\\images\\cat 2.gif"));
        cat3 = new Image(new FileInputStream("C:\\Users\\ken_t\\Documents\\Li Yang\\UM\\Assignment\\FOP\\Cats-Are-Awesome\\images\\cat 3.gif"));
        cat4 = new Image(new FileInputStream("C:\\Users\\ken_t\\Documents\\Li Yang\\UM\\Assignment\\FOP\\Cats-Are-Awesome\\images\\cat 4.gif"));
        
        ImagePattern cat1Iv = new ImagePattern(cat1);
        ImagePattern cat2Iv = new ImagePattern(cat2);
        ImagePattern cat3Iv = new ImagePattern(cat3);
        ImagePattern cat4Iv = new ImagePattern(cat4);
        
        
        Rectangle rec1 = new Rectangle(150,150,150,150);
        rec1.setFill(cat1Iv);
        
        Rectangle rec2 = new Rectangle(150,150,150,150);
        rec2.setFill(cat2Iv);
        
        Rectangle rec3 = new Rectangle(150,150,150,150);
        rec3.setFill(cat3Iv);
        
        Rectangle rec4 = new Rectangle(150,150,150,150);
        rec4.setFill(cat4Iv);
        
        cat1Btn = new RadioButton();
        cat1Btn.setGraphic(rec1);
        cat2Btn = new RadioButton();
        cat2Btn.setGraphic(rec2);
        cat3Btn = new RadioButton();
        cat3Btn.setGraphic(rec3);
        cat4Btn = new RadioButton();
        cat4Btn.setGraphic(rec4);
        
        ToggleGroup catType = new ToggleGroup();
        cat1Btn.setToggleGroup(catType);
        cat2Btn.setToggleGroup(catType);
        cat3Btn.setToggleGroup(catType);
        cat4Btn.setToggleGroup(catType);
        
        cat1Btn.setSelected(true);
        
        GridPane gp = new GridPane();
        gp.setHgap(50);
        gp.setVgap(50);
        gp.setPadding(new Insets(30, 70, 40, 70));
        
        gp.add(cat1Btn, 1, 0);
        gp.add(cat2Btn, 2, 0);
        gp.add(cat3Btn, 1, 1);
        gp.add(cat4Btn, 2, 1);
        
        
        title.setText("Add your own cat first");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(30,10,0,150));
        hbox.getChildren().add(title);
        
        Button confirmBtn = new Button("Confirm");
        confirmBtn.setOnAction(e -> {
            if (!catNameField.getText().trim().equals("")) {
                addCat();
                TimeTracker tt = new TimeTracker();
                Cat.calculateTimeOfAlive();
                Cat.calculateTimeToLive();
                
                AlertBox.display("Your cat, " + Cat.getCatName() + " is added");
                try {
                    window.setScene(OptionPanelScene.optionPanel());
                } catch (FileNotFoundException ex) {

                }
                System.out.println("cat added");
            } else {
                AlertBox.display("Please give your cat a name");
                catNameField.clear();
            }
        });
        
        Text text = new Text();
        text.setText("Give your cat a name: ");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        catNameField = new TextField();
        catNameField.setPromptText("cat name");
        
        HBox hbox2 = new HBox(10);
        hbox2.setPadding(new Insets(30, 50, 50, 50));
        hbox2.getChildren().addAll(text, catNameField, confirmBtn);
        
        BorderPane bp = new BorderPane();
        bp.setTop(hbox);
        bp.setCenter(gp);
        bp.setBottom(hbox2);
        
        
        
        Scene scene = new Scene(bp, 600, 600);
        
        return scene;
        
    }
    
    
    private static void addCat() {
        
        try {
            
            PrintWriter p = new PrintWriter(new FileOutputStream("cat.txt", true));
            Cat.setCatName(catNameField.getText());
            Cat.setTimeOfBirth(System.currentTimeMillis()/1000L);
            
            if (cat1Btn.isSelected())
                Cat.setCatId(1);
            else if (cat2Btn.isSelected())
                Cat.setCatId(2);
            else if (cat3Btn.isSelected())
                Cat.setCatId(3);
            else if (cat4Btn.isSelected())
                Cat.setCatId(4);
                    
            p.println(User.getUserId() + "," + Cat.getCatName() + "," + Cat.getCatId() +
                    "," + Cat.getTimeOfBirth());
            User.setHasCat(true);
            
            p.close();
        } catch (IOException e) {
            
        }
        // add cat attribute
        try {
            
            PrintWriter p = new PrintWriter(new FileOutputStream("cat attribute.txt", true));
            Cat.setHappiness(100);
            Cat.setHunger(0);
            Cat.setExperience(0);
            Cat.setLevel(1);
            Cat.setExperienceLimit(100);
            
            // record the current time
            Cat.initialTime = System.currentTimeMillis()/1000L;
            Cat.hungerTimeLeft = (long) ((100-Cat.getHunger()) / 100.0 * 18000 + 18000);
            Cat.happinessTimeLeft = (long) (Cat.getHappiness() / 100.0 * 18000 + 18000);
            p.println(User.getUserId()+","+Cat.getCatId() + "," +Cat.getHappiness()+","
                    +Cat.getHunger()+","+Cat.getLevel()+","
                    +Cat.getExperience()+","+Cat.initialTime+","+Cat.happinessTimeLeft+","+Cat.hungerTimeLeft);
            p.close();
            
            
        } catch (IOException e) {
            
        }
        
    }
}
