/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catareawesome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import static catareawesome.firstScene.window;

/**
 *
 * @author Hello
 */
public class foodInventory {
    
    public static Scene displayFoodInventory() throws FileNotFoundException {
        
        Image bread;
        Image fish;
        Image doughnut; 
        Image pizza;
        
        Button breadBtn;
        Button fishBtn;
        Button doughnutBtn;
        Button pizzaBtn;
        
        Button backBtn = new Button();
        Image backImage = new Image(new FileInputStream("images\\backButton.png"));
        ImageView backIv = new ImageView(backImage);
        backIv.setFitWidth(60);
        backIv.setFitHeight(60);
        backBtn.setGraphic(backIv);
        backBtn.setOnAction(e -> {
            try {
                window.setScene(OptionPanelScene.optionPanel());
            } catch (FileNotFoundException ex) {

            }
        });
        
        BorderPane pane = new BorderPane();
        // top
        HBox topHbox = new HBox();
        
        topHbox.getChildren().add(backBtn);
        
        // middle
        // testing purpose only

        
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
//        catIv.setFitWidth(350);
//        catIv.setFitHeight(350);
        Rectangle rec = new Rectangle(350, 350, 350, 350);
        rec.setFill(catIv);
        
        
        
        // bottom part
        
        GridPane grid = new GridPane();
        
        HBox bottomHbox1 = new HBox(50);
        HBox bottomHbox2 = new HBox(125);
        
        bread = new Image(new FileInputStream("images\\breadWithoutPrice.jpeg"));
        fish = new Image(new FileInputStream("images\\fish.jpg"));
        doughnut = new Image(new FileInputStream("images\\doughnutWithoutPrice.png"));
        pizza = new Image(new FileInputStream("images\\pizza.jpg"));
        
        
        ImageView breadIv = new ImageView(bread);
        ImageView fishIv = new ImageView(fish);
        ImageView doughnutIv = new ImageView(doughnut);
        ImageView pizzaIv = new ImageView(pizza);
        
        breadIv.setFitWidth(70);
        fishIv.setFitWidth(70);
        doughnutIv.setFitWidth(70);
        pizzaIv.setFitWidth(70);
        
        breadIv.setFitHeight(70);
        fishIv.setFitHeight(70);
        doughnutIv.setFitHeight(70);
        pizzaIv.setFitHeight(70);
        
        breadBtn = new Button();
        breadBtn.setGraphic(breadIv);
        
        fishBtn = new Button();
        fishBtn.setGraphic(fishIv);
        
        doughnutBtn = new Button();
        doughnutBtn.setGraphic(doughnutIv);
        
        pizzaBtn = new Button();
        pizzaBtn.setGraphic(pizzaIv);
        
        bottomHbox1.getChildren().addAll(breadBtn, doughnutBtn, pizzaBtn, fishBtn);
        bottomHbox1.setPadding(new Insets(0, 0, 20, 40));
        
        Text breadQuantityText = new Text();
        breadQuantityText.setText("Quantity:\t" + User.getBreadQuantity());
        breadQuantityText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Text doughnutQuantityText = new Text();
        doughnutQuantityText.setText(Integer.toString(User.getDoughnutQuantity()));
        Text pizzaQuantityText = new Text();
        pizzaQuantityText.setText(Integer.toString(User.getPizzaQuantity()));
        Text fishQuantityText = new Text();
        fishQuantityText.setText(Integer.toString(User.getFishQuantity()));
        
        doughnutQuantityText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        pizzaQuantityText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        fishQuantityText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        
        bottomHbox2.getChildren().addAll(breadQuantityText, doughnutQuantityText, pizzaQuantityText, fishQuantityText);
        bottomHbox2.setPadding(new Insets(0, 0, 20, 0));
        
        grid.add(bottomHbox1, 0, 0);
        grid.add(bottomHbox2, 0, 1);
        
        
        // action 
        breadBtn.setOnAction(e -> {
            feedBread();
            breadQuantityText.setText("Quantity:\t" + User.getBreadQuantity());
        });
        
        doughnutBtn.setOnAction(e -> {
            feedDoughnut();
            doughnutQuantityText.setText(Integer.toString(User.getDoughnutQuantity()));
        });
        
        pizzaBtn.setOnAction(e -> {
            feedPizza();
            pizzaQuantityText.setText(Integer.toString(User.getPizzaQuantity()));
        });
        
        fishBtn.setOnAction(e -> {
            feedFish();
            fishQuantityText.setText(Integer.toString(User.getFishQuantity()));
        });
        
        
        pane.setTop(topHbox);
        BorderPane.setAlignment(rec, Pos.TOP_CENTER);
        pane.setCenter(rec);
        BorderPane.setAlignment(grid, Pos.TOP_CENTER);
        pane.setBottom(grid);
        Scene scene = new Scene(pane, 600, 600);
        return scene;
        
       
    }
    
    
    
    private static void feedBread() {
        if (User.getBreadQuantity() > 0) {
            
            User.decreaseBread(1);
            Cat.decreaseHunger(10);
            Cat.increaseHappiness(10);
            Cat.increaseExperience(15);
            AlertBox.display("You feed your cat a bread");
            if (Cat.getHunger() <= 0) {
                Cat.setHunger(0);
                AlertBox.display("Your cat is full now");
            }
            if (Cat.getHappiness() >= 100) {
                Cat.setHappiness(100);
            }
            if (Cat.getExperience() >= Cat.getExperienceLimit()) {
                Cat.levelUp();
                Cat.calculateExpLimit();
            }
            Cat.happinessTimeLeft = (long) (Cat.getHappiness() / 100.0 + 18000);
            Cat.hungerTimeLeft = (long) (100 - Cat.getHunger() / 100.0 + 18000);
        } else {
            AlertBox.display("You do not have enough bread");
        }
        
    }
    
    private static void feedDoughnut() {
        if (User.getDoughnutQuantity() > 0) {
            
            User.decreaseDoughnut(1);
            Cat.decreaseHunger(15);
            Cat.increaseHappiness(15);
            Cat.increaseExperience(25);
            AlertBox.display("You feed your cat a doughnut");
            if (Cat.getHunger() <= 0) {
                Cat.setHunger(0);
                AlertBox.display("Your cat is full now");
            }
            if (Cat.getHappiness() >= 100) {
                Cat.setHappiness(100);
            }
            if (Cat.getExperience() >= Cat.getExperienceLimit()) {
                Cat.levelUp();
                Cat.calculateExpLimit();
            }
            
            Cat.happinessTimeLeft = (long) (Cat.getHappiness() / 100.0 + 18000);
            Cat.hungerTimeLeft = (long) (100 - Cat.getHunger() / 100.0 + 18000);
        } else {
            AlertBox.display("You do not have enough doughnut");
        }
    }
    private static void feedPizza() {
        if (User.getPizzaQuantity() > 0) {
            
            User.decreasePizza(1);
            Cat.decreaseHunger(30);
            
            Cat.increaseHappiness(30);
            Cat.increaseExperience(40);
            AlertBox.display("You feed your cat a pizza");
            if (Cat.getHunger() <= 0) {
                Cat.setHunger(0);
                AlertBox.display("Your cat is full now");
            }
            if (Cat.getHappiness() >= 100) {
                Cat.setHappiness(100);
            }
            if (Cat.getExperience() >= Cat.getExperienceLimit()) {
                Cat.levelUp();
                Cat.calculateExpLimit();
            }
            Cat.happinessTimeLeft = (long) (Cat.getHappiness() / 100.0 + 18000);
            Cat.hungerTimeLeft = (long) (100 - Cat.getHunger() / 100.0 + 18000);
        } else {
            AlertBox.display("You do not have enough pizza");
        }
    }
    private static void feedFish() {
        if (User.getFishQuantity() > 0) {
            
            User.decreaseFish(1);
            Cat.decreaseHunger(50);
            Cat.increaseHappiness(50);
            Cat.increaseExperience(60);
            AlertBox.display("You feed your cat a fish");
            if (Cat.getHunger() <= 0) {
                Cat.setHunger(0);
                AlertBox.display("Your cat is full now");
            }
            if (Cat.getHappiness() >= 100) {
                Cat.setHappiness(100);
            }
            if (Cat.getExperience() >= Cat.getExperienceLimit()) {
                Cat.levelUp();
                Cat.calculateExpLimit();
            }
            Cat.happinessTimeLeft = (long) (Cat.getHappiness() / 100.0 + 18000);
            Cat.hungerTimeLeft = (long) (100 - Cat.getHunger() / 100.0 + 18000);
        } else {
            AlertBox.display("You do not have enough fish");
        }
    }
}
