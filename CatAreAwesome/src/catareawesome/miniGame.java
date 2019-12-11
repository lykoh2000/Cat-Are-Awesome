/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catareawesome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import static catareawesome.firstScene.window;

public class miniGame {
    
    private static final char[][] map = new char[20][20];
    private static final Rectangle[][] rects = new Rectangle[20][20];
    private static GridPane grid = new GridPane();
    private static int position_X = 0, position_Y = 5;
    private static int munnyEarn = 0;
    private static final int ENDX = 19;
    private static ImagePattern movingCat;
    private static ImagePattern coin;
    
    public static Scene miniGameScene() throws FileNotFoundException {
        // initialize variable
        position_X = 0;
        position_Y = 5;
        munnyEarn = 0;
       
        movingCat = new ImagePattern(new Image(new FileInputStream("images\\cat moving.gif")));
        coin = new ImagePattern(new Image(new FileInputStream("images\\animated coin.gif")));
        
        loadMap();
        displayMap();
        
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> {
            boolean ans = ConfirmBox.display("All your items will be lost. Continue to quit? ");
            if (ans) {
                
                try {
                    window.setScene(OptionPanelScene.optionPanel());
                } catch (FileNotFoundException ex) {
                    
                }
            }
             
            
        });
        
        BorderPane pane = new BorderPane();
        BorderPane.setAlignment(grid, Pos.CENTER);
        grid.setPadding(new Insets(50, 50, 50, 50));
        pane.setTop(backBtn);
        pane.setCenter(grid);
        Scene scene = new Scene(pane, 600, 600);
        scene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.UP) {
                playerMoveUp();
            } else if (event.getCode() == KeyCode.DOWN) {
                playerMoveDown();
            } else if (event.getCode() == KeyCode.LEFT) {
                playerMoveLeft();
            } else if (event.getCode() == KeyCode.RIGHT) {
                playerMoveRight();
            }
            
            // check whether finish the game
            if (position_X == ENDX) {
                AlertBox.display("Congratulation, you have beaten the level. You earned $"
                        + munnyEarn + " munny");
                // add munny
                User.increaseMunny(munnyEarn);
                // happiness add 110/100 of munnyearn
                Cat.increaseHappiness(munnyEarn * 1.1);
                // each munny add two exp
                Cat.increaseExperience(munnyEarn*2);
                if (Cat.getHappiness() >= 100) {
                    Cat.setHappiness(100);
                }
                
                if (Cat.getExperience() >= Cat.getExperienceLimit()) {
                    Cat.levelUp();
                    Cat.calculateExpLimit();
                }
                Cat.happinessTimeLeft = (long) (Cat.getHappiness() / 100.0 + 18000);
                try {
                    window.setScene(OptionPanelScene.optionPanel());
                } catch (FileNotFoundException ex) {
                    
                }
            }
        });
        
//        for (int i = 0; i < 20; i++) {
//            for (int j = 0; j < 20; j++) {
//                System.out.print(map[i][j]);
//                
//            }
//            System.out.println("");
//        }
        
        return scene;
        
    }
    
    private static void loadMap() {

        try {
            
            int row = 0;
            Scanner scan = new Scanner(new FileInputStream("map.txt"));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    map[i][row] = line.charAt(i);
                }
                row++;
            }
            scan.close();
        } catch(IOException e) {
            
        }
        
        // generate munny
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
        int num1 = rand.nextInt(20);
        int num2 = rand.nextInt(20);
            for (int j = 0; j < 20; j++) {
                if ((j == num1 || j == num2) && map[i][j] == '1') {
                    map[i][j] = '2';
                }
            }
        }

    }
    
    
    private static void displayMap() {
        
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                rects[i][j] = new Rectangle(25,25);
                // player initial place
                if (i == 0 && j == 5) {
                    rects[i][j].setFill(movingCat);
                }
                else if (map[i][j] == '0') {
                    rects[i][j].setFill(Color.GRAY);
                } else if (map[i][j] == '1') {
                    rects[i][j].setFill(Color.WHITE);
                    
                } else  {
                    rects[i][j].setFill(coin);
                } 
                grid.add(rects[i][j], i, j);
                
            }
        }
       
        
    }
    
    private static void playerMoveUp() {
        if (position_Y > 0 && map[position_X][position_Y-1] != '0') {
            rects[position_X][position_Y].setFill(Color.WHITE);
            position_Y--;
            rects[position_X][position_Y].setFill(movingCat);
            if (map[position_X][position_Y] == '2') {
                map[position_X][position_Y] = '1';
                munnyEarn++;
                System.out.println("$" + munnyEarn); 
            }
            
        } 
    }
    
    private static void playerMoveDown() {
        if (position_Y < 19 && map[position_X][position_Y+1] != '0') {
            rects[position_X][position_Y].setFill(Color.WHITE);
            position_Y++;
            rects[position_X][position_Y].setFill(movingCat);
            if (map[position_X][position_Y] == '2') {
                map[position_X][position_Y] = '1';
                munnyEarn++;
                System.out.println("$" + munnyEarn); 
            }
            
        } 
    }
    
    private static void playerMoveLeft() {
        if (position_X > 0 && map[position_X-1][position_Y] != '0') {
            rects[position_X][position_Y].setFill(Color.WHITE);
            position_X--;
            rects[position_X][position_Y].setFill(movingCat);
            if (map[position_X][position_Y] == '2') {
                map[position_X][position_Y] = '1';
                munnyEarn++;
                System.out.println("$" + munnyEarn); 
            }
            
        } 
    }
    
    private static void playerMoveRight() {
        if (position_X < 19 && map[position_X+1][position_Y] != '0') {
            rects[position_X][position_Y].setFill(Color.WHITE);
            position_X++;
            rects[position_X][position_Y].setFill(movingCat);
            if (map[position_X][position_Y] == '2') {
                map[position_X][position_Y] = '1';
                munnyEarn++;
                System.out.println("$" + munnyEarn); 
            }
            
        } 
    }
    
}
