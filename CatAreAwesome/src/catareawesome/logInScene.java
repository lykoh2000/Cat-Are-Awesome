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
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import static catareawesome.firstScene.window;

public class logInScene {
        
    static TextField userTextField;
    static PasswordField pwBox;
    

    public static Scene logInSignUpScene() {
 
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text sceneTitle = new Text("Welcome");
        grid.add(sceneTitle, 0, 0, 2, 1);
        
        Label userName = new Label("Username:");
        grid.add(userName, 0, 1);

        userTextField = new TextField();
        userTextField.setPromptText("username");
        userTextField.setPrefWidth(150);
        userTextField.setMaxWidth(150);
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        pwBox = new PasswordField();
        pwBox.setPromptText("password");
        pwBox.setPrefWidth(150);
        pwBox.setMaxWidth(150);
        grid.add(pwBox, 1, 2);
        
        Button logInBtn = new Button("Log In");
        Button signUpBtn = new Button("Sign Up");
        
        HBox signInBtn = new HBox(10);
        signInBtn.getChildren().addAll(signUpBtn, logInBtn);
        signInBtn.setAlignment(Pos.BOTTOM_RIGHT);
        
        grid.add(signInBtn, 1, 4);
        
        Text logInText = new Text();
        Text signUpText = new Text();
        

        
        // if log in is clicked
        logInBtn.setOnAction((ActionEvent e) -> {
        grid.getChildren().remove(logInText);
        grid.getChildren().remove(signUpText);
        if (!logInButtonClicked()) {
            userTextField.clear();
            pwBox.clear();
            logInText.setText("Username or password not exist");

        } else {
                
            logInText.setText("Log in successful");
           // User.logInState = true;
            User.getUserIdFromFile();
            User.checkHasCat();
            User.getFoodInventory();
           // System.out.println("User id: " + User.userId);
            if (User.hasCat()) {
                Cat.getCatNameAndId();
                Cat.getCatAttribute();
                Cat.calculateExpLimit();
                Cat.getTimeOfBirthFromFile();
                Cat.calculateTimeOfAlive();
                Cat.calculateTimeToLive();
               // System.out.println("exp limit" + Cat.experienceLimit);
                long time = System.currentTimeMillis()/1000L;
                long timePass = time - Cat.initialTime;
                // check whether cat dies
                System.out.println("timeleft:"+ (Cat.happinessTimeLeft - timePass));
                if (Cat.happinessTimeLeft - timePass <= 0 || Cat.hungerTimeLeft - timePass<= 0) {

                    User.setHasCat(false);

                    AlertBox.display("Your cat has died...Add a new cat");
                    try {

                        SaveData.saveCat();
                        SaveData.saveCatAttribute();
                        window.setScene(addCat.addCatScene());

                        } catch (FileNotFoundException ex) {
                            System.out.println("Problem with file output");
                        } catch (InterruptedException ex) {

                        }
                        
                    }
                    // if cat not dies, timer start
                    // show option panel
                else {
                        TimeTracker tt = new TimeTracker();
                        try {
                            
                            window.setScene(OptionPanelScene.optionPanel());
                            if (Cat.getHappiness() <= 0) {
                                AlertBox.display("Your cat is boring now...");

                            }
                            if (Cat.getHunger() >= 100) {
                                AlertBox.display("Your cat is hungry now...Please feed him");
                            }
                        } catch (FileNotFoundException ex) {

                        }
                  
                    }
                } else {
                    // if user does not own a cat before
                    try {
                        
                        window.setScene(addCat.addCatScene());
                        
                    } catch (FileNotFoundException ex) {

                    }
                }
                
            }
                
            logInText.setFill(Color.FIREBRICK);      
            grid.add(logInText, 1, 6); 
        });
        
        // if sign up is clicked
        signUpBtn.setOnAction(e -> {
            grid.getChildren().remove(signUpText);
            grid.getChildren().remove(logInText);
            if (signUpButtonClicked()) {
                signUpText.setText("New account created");
            }
            else {
                if (userTextField.getText().equals("") || pwBox.getText().equals("")) {
                    signUpText.setText("Please fill in username and password");
                    userTextField.clear();
                    pwBox.clear();
                } else {
                    userTextField.clear();
                    pwBox.clear();
                    signUpText.setText("Username exists already");
                }
                
            }
            signUpText.setFill(Color.FIREBRICK); 
            grid.add(signUpText, 1, 6);
        });
        
        Scene scene = new Scene(grid, 500, 600);
        return scene;
      
    }

            
    private static boolean logInButtonClicked() {

        User.setUsername(userTextField.getText());
        User.setPassword(pwBox.getText());
        String correctPassword;
        try {
            Scanner scan = new Scanner(new FileInputStream("user.txt"));
            
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");
                if (User.getUsername().equals(line[1])) {
                    correctPassword = line[2];
                    if (User.getPassword().equals(correctPassword)) {
                        scan.close();
                        return true;
                    } else {
                        scan.close();
                        return false;
                    }
                    
                }
            }
            
                
            
        } catch (IOException e) {
            
        }
        return false;
        
    }

    private static boolean signUpButtonClicked() {
        String usernameInput = userTextField.getText();
        String passwordInput = pwBox.getText();
        Scanner scan;
        try {
            scan = new Scanner(new FileInputStream("user.txt"));
            // check username duplicate
            boolean duplicate = false;
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");
                if (usernameInput.equals(line[1])) {
                    return false;
                }
            }
            if (usernameInput.equals("") || passwordInput.equals("")) {
                return false;
            }
            
            if (!duplicate) {
            // update user.txt
                PrintWriter p = new PrintWriter(new FileOutputStream("user.txt", true));
                Scanner sc = new Scanner(System.in);
                User.setUserIdInFile();
                p.println(User.getUserId()+","+usernameInput+","+passwordInput+",false");
                p.close();  
            // update food inventory
                PrintWriter pw = new PrintWriter(new FileOutputStream("food inventory.txt", true));
            // userid, bread , doughnut, pizza, fish, munny
                
                pw.println(User.getUserId() + ",0,0,0,0,100");
                pw.close();

            }
            
            scan.close();
        } catch(IOException e) {
            
        }
        return true;
    }
    
    
}
