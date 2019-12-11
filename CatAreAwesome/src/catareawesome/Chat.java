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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import static catareawesome.firstScene.window;

public class Chat {
    
    private static VBox leftChatArea;
    private static VBox rightChatArea;
    private static TextField inputField;
    private static Rectangle rightRect;
    private static Rectangle leftRect;

    public static Scene chattingScene() {
        BorderPane pane = new BorderPane();
        inputField = new TextField();
        inputField.setPrefWidth(450);
        ScrollPane scroll1 = new ScrollPane();
        ScrollPane scroll2 = new ScrollPane();
        Button sendBtn = new Button("Send");
        Button backBtn = new Button("Back");
        backBtn.setOnAction(e -> {
            try {
                window.setScene(OptionPanelScene.optionPanel());
            } catch (FileNotFoundException ex) {
                
            }
        });
        Text title = new Text("Chat With Your Cat");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        sendBtn.setOnAction(e -> {
            replyMsg();
        });
        leftChatArea = new VBox(70);
        rightChatArea = new VBox(70);
        rightChatArea.setPadding(new Insets(0, 0, 30, 150));
        leftChatArea.setPadding(new Insets(40, 0, 0, 0));
        
      //  leftChatArea.setMinHeight();
        //rightChatArea.setMinHeight(0);
        
        Group group = new Group(rightChatArea, leftChatArea);
       // group.setAutoSizeChildren(true);
        
        HBox bottom = new HBox(10);
        bottom.getChildren().addAll(inputField, sendBtn);
        //bottom.setPadding(new Insets(100, 0, 0, 0));
        scroll1.setContent(group);
        //scroll2.setContent(rightChatArea);
        scroll1.setPadding(new Insets(30, 100, 30, 100));
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setAlignment(bottom, Pos.CENTER_LEFT);
        bottom.setPadding(new Insets(0, 20, 30, 50));
        HBox topHbox = new HBox(50);
        topHbox.getChildren().addAll(backBtn, title);
        pane.setTop(topHbox);
//        pane.setLeft(leftChatArea);
//        pane.setRight(rightChatArea);
        //pane.setLeft(scroll1);
        //pane.setRight(scroll2);
        BorderPane.setAlignment(scroll1, Pos.CENTER);
        pane.setCenter(scroll1);
        pane.setBottom(bottom);
        
        Scene scene = new Scene(pane, 600, 600);
        return scene;
    }
    
    
    private static void replyMsg() {
        
        String str = inputField.getText();
        Text text = new Text(str);
        if (str.trim().equals("")) {
            inputField.clear();
            return;
        }
        leftRect = new Rectangle(200, 30);
        leftRect.setFill(Color.WHITE);
        
        rightRect = new Rectangle(200, 30);
        StackPane leftSp = new StackPane();
        StackPane rightSp = new StackPane();
        rightRect.setFill(Color.LIGHTGREEN);
        rightSp.getChildren().addAll(rightRect, text);
        leftSp.getChildren().addAll(leftRect, getRandomMsg());
        inputField.clear();
        //rightChatArea.getChildren().add(text);
        //leftChatArea.getChildren().add(getRandomMsg());
        leftChatArea.getChildren().add(leftSp);
        rightChatArea.getChildren().add(rightSp);
        
        
    }
    
    private static Text getRandomMsg() {
        Text msg = new Text();
        try {
            String msg1 = "CAT ฅ^•ﻌ•^ฅ->", msg2 = "CAT ฅ^•ﻌ•^ฅ->";
            Scanner scan = new Scanner(new FileInputStream("chat.txt"));
            Random rand = new Random();
            int randLevel = rand.nextInt(Cat.getLevel()) + 1;
            int randMsg = rand.nextInt(2);
            while (scan.hasNextLine()) {
                if (Integer.toString(randLevel).equals(scan.nextLine())) {
                    msg1 += scan.nextLine();
                    msg2 += scan.nextLine();
                    break;
                }
                
            }
            switch (randMsg) {
                case 0:
                    msg.setText(msg1);
                    break;
                case 1:
                    msg.setText(msg2);
                    break;
            }
            
            scan.close();
        } catch (IOException e) {
            System.out.println("Problem with file output");
        }
        return msg;
        
    }
    
}
