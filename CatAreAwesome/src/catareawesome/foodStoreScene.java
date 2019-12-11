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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import static catareawesome.firstScene.window;

public class foodStoreScene {
    private static int breadQuantity = 0;
    private static int doughnutQuantity = 0;
    private static int pizzaQuantity = 0;
    private static int fishQuantity = 0;
    private static int totalPrice = 0;
    private static final int breadPrice = 10;
    private static final int doughnutPrice = 15;
    private static final int pizzaPrice = 30;
    private static final int fishPrice = 50;
    
    public static Scene foodStoreScene() throws FileNotFoundException {
        // later need to change
        // declare price of food
        
        
        
        
        
        Button backBtn = new Button();
        Button confirmBtn = new Button();
        Button addBreadBtn = new Button();
        Button minusBreadBtn = new Button();
        Button addDoughnutBtn = new Button();
        Button minusDoughnutBtn = new Button();
        Button addPizzaBtn = new Button();
        Button minusPizzaBtn = new Button();
        Button addFishBtn = new Button();
        Button minusFishBtn = new Button();
        
        Image backImage = new Image(new FileInputStream("images\\backButton.png"));
        Image breadImage = new Image(new FileInputStream("images\\bread.jpg"));
        Image fishImage = new Image(new FileInputStream("images\\fish.png"));
        Image doughnutImage = new Image(new FileInputStream("images\\doughnut.png"));
        Image pizzaImage = new Image(new FileInputStream("images\\pizza.png"));
        Image addImage = new Image(new FileInputStream("images\\add.jfif"));
        Image minusImage = new Image(new FileInputStream("images\\minus.jfif"));
        
        ImageView breadIv = new ImageView(breadImage);
        ImageView fishIv = new ImageView(fishImage);
        ImageView pizzaIv = new ImageView(pizzaImage);
        ImageView doughnutIv = new ImageView(doughnutImage);
        
        ImageView backIv = new ImageView(backImage);
        
        ImageView addIv1 = new ImageView(addImage);
        ImageView addIv2= new ImageView(addImage);
        ImageView addIv3 = new ImageView(addImage);
        ImageView addIv4 = new ImageView(addImage);
        
        ImageView minusIv1 = new ImageView(minusImage);
        ImageView minusIv2= new ImageView(minusImage);
        ImageView minusIv3 = new ImageView(minusImage);
        ImageView minusIv4 = new ImageView(minusImage);
        
        backIv.setFitWidth(50);
        backIv.setFitHeight(50);
        
        addIv1.setFitHeight(30);
        addIv1.setFitWidth(30);
        addIv2.setFitHeight(30);
        addIv2.setFitWidth(30);
        addIv3.setFitHeight(30);
        addIv3.setFitWidth(30);
        addIv4.setFitHeight(30);
        addIv4.setFitWidth(30);
     
        minusIv1.setFitHeight(30);
        minusIv1.setFitWidth(30);
        minusIv2.setFitHeight(30);
        minusIv2.setFitWidth(30);
        minusIv3.setFitHeight(30);
        minusIv3.setFitWidth(30);
        minusIv4.setFitHeight(30);
        minusIv4.setFitWidth(30);
        
        breadIv.setFitWidth(150);
        doughnutIv.setFitWidth(150);
        pizzaIv.setFitWidth(150);
        fishIv.setFitWidth(150);
        
        breadIv.setFitHeight(150);
        doughnutIv.setFitHeight(150);
        pizzaIv.setFitHeight(150);
        fishIv.setFitHeight(150);
        
        
        
        addBreadBtn.setGraphic(addIv1);
        addDoughnutBtn.setGraphic(addIv2);
        addPizzaBtn.setGraphic(addIv3);
        addFishBtn.setGraphic(addIv4);
        
        minusBreadBtn.setGraphic(minusIv1);
        minusDoughnutBtn.setGraphic(minusIv2);
        minusPizzaBtn.setGraphic(minusIv3);
        minusFishBtn.setGraphic(minusIv4);
        
        backBtn.setGraphic(backIv);
        backBtn.setOnAction(e -> {
            try {
                window.setScene(OptionPanelScene.optionPanel());
            } catch (FileNotFoundException ex) {

            }
        });
        
        
        BorderPane pane = new BorderPane();
        GridPane grid = new GridPane();
        // top part
        HBox topHbox = new HBox(80);
        
        topHbox.setPadding(new Insets(10, 0, 0, 0));
        Text title = new Text("FOOD STORE");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        Text munny = new Text();
        munny.setText("Munny: $" + User.getMunny());
        munny.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        HBox.setMargin(title, new Insets(20, 0,0 ,0));
        topHbox.setAlignment(Pos.TOP_LEFT);
        topHbox.getChildren().addAll(backBtn, title, munny);
        
        //middle part
        
        HBox hbox1 = new HBox(100);
        hbox1.setPadding(new Insets(20, 100, 20, 100));
        hbox1.getChildren().addAll(breadIv, doughnutIv);
        
        HBox hbox2 = new HBox();
        hbox2.setPadding(new Insets(0, 80, 20, 70));
        
        HBox.setMargin(minusDoughnutBtn,new Insets(0, 50, 0, 50));
        HBox.setMargin(minusFishBtn,new Insets(0, 50, 0, 50));
        
        Text breadQuantitytxt = new Text();
        breadQuantitytxt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        HBox.setMargin(breadQuantitytxt, new Insets(10, 50, 0, 50));
        
        Text doughnutQuantitytxt = new Text();
        doughnutQuantitytxt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        HBox.setMargin(doughnutQuantitytxt, new Insets(0, 50, 0, 0));
        
        Text pizzaQuantitytxt = new Text();
        pizzaQuantitytxt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        HBox.setMargin(pizzaQuantitytxt, new Insets(10, 50, 0, 50));
        
        Text fishQuantitytxt = new Text();
        fishQuantitytxt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        HBox.setMargin(fishQuantitytxt, new Insets(0, 50, 0, 0));
        
        breadQuantitytxt.setText(Integer.toString(breadQuantity));
        doughnutQuantitytxt.setText(Integer.toString(doughnutQuantity));
        pizzaQuantitytxt.setText(Integer.toString(pizzaQuantity));
        fishQuantitytxt.setText(Integer.toString(fishQuantity));
        
        hbox2.getChildren().addAll(minusBreadBtn, breadQuantitytxt, addBreadBtn, minusDoughnutBtn, 
                doughnutQuantitytxt, addDoughnutBtn);
        
        HBox hbox3 = new HBox(100);
        hbox3.setPadding(new Insets(0, 100, 0, 100));
        hbox3.getChildren().addAll(pizzaIv, fishIv);
        
        HBox hbox4 = new HBox();
        hbox4.getChildren().addAll(minusPizzaBtn, pizzaQuantitytxt, addPizzaBtn, minusFishBtn, 
                fishQuantitytxt, addFishBtn);
        hbox4.setPadding(new Insets(20, 80, 0, 70));
        
        GridPane middlePane = new GridPane();
        middlePane.addRow(0, hbox1);
        middlePane.addRow(1, hbox2);
        middlePane.addRow(2, hbox3);
        middlePane.addRow(3, hbox4);
        
        // bottom part
        HBox bottomHbox = new HBox();
        bottomHbox.setAlignment(Pos.BOTTOM_RIGHT);        
        bottomHbox.setPadding(new Insets(10, 50, 10, 10));
        Text priceTag = new Text();
        HBox.setMargin(priceTag, new Insets(0, 30, 10, 0));
        priceTag.setText("Total price: $" + totalPrice);
        priceTag.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        bottomHbox.getChildren().addAll(priceTag, confirmBtn);
        
        
        addBreadBtn.setOnAction(e -> {
            increaseBreadQuantity();
            breadQuantitytxt.setText(Integer.toString(breadQuantity));
            priceTag.setText("Total price: $" + totalPrice);
        }); 
        
        addDoughnutBtn.setOnAction(e -> {
            increaseDoughnutQuantity();
            doughnutQuantitytxt.setText(Integer.toString(doughnutQuantity));
            priceTag.setText("Total price: $" + totalPrice);
        }); 
        
        addPizzaBtn.setOnAction(e -> {
            increasePizzaQuantity();
            pizzaQuantitytxt.setText(Integer.toString(pizzaQuantity));
            priceTag.setText("Total price: $" + totalPrice);
        }); 
        
        addFishBtn.setOnAction(e -> {
            increaseFishQuantity();
            fishQuantitytxt.setText(Integer.toString(fishQuantity));
            priceTag.setText("Total price: $" + totalPrice);
        }); 
    
        minusBreadBtn.setOnAction(e -> {
            decreaseBreadQuantity();
            breadQuantitytxt.setText(Integer.toString(breadQuantity));
            priceTag.setText("Total price: $" + totalPrice);
        });
        
        minusDoughnutBtn.setOnAction(e -> {
            decreaseDoughnutQuantity();
            doughnutQuantitytxt.setText(Integer.toString(doughnutQuantity));
            priceTag.setText("Total price: $" + totalPrice);
        });
        
        minusPizzaBtn.setOnAction(e -> {
            decreasePizzaQuantity();
            pizzaQuantitytxt.setText(Integer.toString(pizzaQuantity));
            priceTag.setText("Total price: $" + totalPrice);
        });
        
        minusFishBtn.setOnAction(e -> {
            decreaseFishQuantity();
            fishQuantitytxt.setText(Integer.toString(fishQuantity));
            priceTag.setText("Total price: $" + totalPrice);
        });
        
        confirmBtn.setText("Confirm");
        confirmBtn.setOnAction(e -> {
            confirmBuy();
            priceTag.setText("Total price: $" + totalPrice);
            breadQuantitytxt.setText(Integer.toString(breadQuantity));
            doughnutQuantitytxt.setText(Integer.toString(doughnutQuantity));
            pizzaQuantitytxt.setText(Integer.toString(pizzaQuantity));
            fishQuantitytxt.setText(Integer.toString(fishQuantity));
            munny.setText("Munny: $" + User.getMunny());
        });
        

        
        pane.setTop(topHbox);
        pane.setCenter(middlePane);
        pane.setBottom(bottomHbox);
        Scene scene = new Scene(pane, 600, 600);
        return scene;
    }
    
    
    private static void increaseBreadQuantity() {
        breadQuantity++;
        totalPrice += breadPrice;
    }
    private static void increaseDoughnutQuantity() {
        doughnutQuantity++;
        totalPrice += doughnutPrice;
    }
    private static void increasePizzaQuantity() {
        pizzaQuantity++;
        totalPrice += pizzaPrice;
    }
    private static void increaseFishQuantity() {
        fishQuantity++;
        totalPrice += fishPrice;
    }
    
    private static void decreaseBreadQuantity() {
        if (breadQuantity>0) {
            breadQuantity--;
            totalPrice -= breadPrice;
        }
    }
    private static void decreaseDoughnutQuantity() {
        if (doughnutQuantity>0) {
            doughnutQuantity--;
            totalPrice -= doughnutPrice;
        }
    }
    private static void decreasePizzaQuantity() {
        if (pizzaQuantity>0) {
            pizzaQuantity--;
            totalPrice -= pizzaPrice;
        }
    }
    private static void decreaseFishQuantity() {
        if (fishQuantity>0) {
            fishQuantity--;
            totalPrice -= fishPrice;
        }
    }
    
    private static void confirmBuy() {
        if (totalPrice <= User.getMunny()) {
            User.decreaseMunny(totalPrice);
            User.increaseBread(breadQuantity);
            User.increaseDoughnut(doughnutQuantity);
            User.increasePizza(pizzaQuantity);
            User.increaseFish(fishQuantity);
            
            breadQuantity = 0;
            doughnutQuantity = 0;
            pizzaQuantity = 0;
            fishQuantity = 0;
            totalPrice = 0;
            AlertBox.display("You buy succeesful");
        } else {
            AlertBox.display("You do not have enough munny");
        }
    }
}

