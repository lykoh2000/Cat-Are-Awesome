

// size 500 x 500
// size of a corner 
package catareawesome;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import static catareawesome.firstScene.window;

/**
 *
 * @author Hello
 */
public class CatFighter {
    private static  VBox pane;
    private static int foodX;
    private static int foodY;
    private static int ratsCaught = 0;
    private static boolean gameOver = false;
    private static Text score = new Text();
    private static int speed = 5;
    private static GraphicsContext gc;
    private static Corner cat;
    private static String direction = "left";
    private static AnimationTimer timer;
    private static ImagePattern catImage = null;
    
    public static Scene catFighterScene() throws FileNotFoundException {

        
        cat = new Corner(10, 10);
       // generate a food first
        generateNewFood();
        pane = new VBox();
        
        Canvas c = new Canvas(525, 525);
        gc = c.getGraphicsContext2D();
        VBox.setMargin(c, new Insets(50, 50, 50, 50));
        pane.getChildren().addAll(score, c);


        score.setText("Rats caught: " + ratsCaught);
        score.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        // timer to let cat move 
        timer = new AnimationTimer() {
            // a timer to make the cat move continuously
            long lastTick = 0;
            
            @Override
            public void handle(long now) {
                
                if (lastTick == 0) {
                    lastTick = now;
                    try {  
                        tick(gc);
                    } catch (FileNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                // create a timer to make the speed of the cat constant
                if (now - lastTick > 1000000000 / speed) {
                    
                    lastTick = now;
                    try {
                        tick(gc);
                    } catch (FileNotFoundException ex) {

                        System.out.println(ex.getMessage());                    }
                }
                
            }

        };
        timer.start();

        Scene scene = new Scene(pane, 600, 600);
        // event handler
        scene.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.UP) {
                direction = "up";
            } else if (event.getCode() == KeyCode.DOWN) {
                direction = "down";
            } else if (event.getCode() == KeyCode.LEFT) {
                direction = "left";
            } else if (event.getCode() == KeyCode.RIGHT) {
                direction = "right";
            }
        });
        
        
        return scene;
    }
    
    
    
    private static void tick(GraphicsContext gc) throws FileNotFoundException {
        if (gameOver) {
            
            for (int i = 0; i < ratsCaught; i++) {
                // increase 5 munny per rats 
                User.increaseMunny(5);
                // increase 5 exp per rats
                Cat.increaseExperience(5);
                Cat.increaseHappiness(3);
                if (Cat.getHappiness() > 100) {
                    Cat.setHappiness(100);
                }
                if (Cat.getExperience() >= Cat.getExperienceLimit()) {
                    
                    double extra = Cat.getExperience() - Cat.getExperienceLimit();
                    Cat.setExperience(extra);
                    Cat.setLevel(Cat.getLevel()+1);
                    Cat.calculateExpLimit();
                    
                }
            }
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 100, 250);
            timer.stop();
            gameOver();
            
            return;            
        }
        
        
         
        switch (direction) {
            case "up":
                cat.y--;
                if (cat.y == 0) {
                    gameOver = true;
                }
                catImage = new ImagePattern(new Image(new FileInputStream("images\\cat up.png")));
                break;
            case "down":
                cat.y++;
                if (cat.y == 20) {
                    gameOver = true;
                }
                catImage = new ImagePattern(new Image(new FileInputStream("images\\cat down.png")));
                break;
            case "left":
              
                cat.x--;
                if (cat.x == 0) {
                    gameOver = true;
                }
                catImage = new ImagePattern(new Image(new FileInputStream("images\\cat left.png")));
                break;
            case "right":
                cat.x++;
                if (cat.x == 20) {
                    gameOver = true;
                }
                catImage = new ImagePattern(new Image(new FileInputStream("images\\cat right.png")));
                break;

        }
        
        // if rat is caught
        if (foodX == cat.x && foodY == cat.y) {
            // increase variable for rats caught
            ratsCaught++;
            
            
            // increase speed for every 3 rats caught
            if (ratsCaught % 3 == 0)
                speed++;
            score.setText("Rats caught: " + ratsCaught);
            generateNewFood();
        }
        
        // bcakground color
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 500, 500);
        
        for (int i = 0; i <= 20; i++) {
            gc.setFill(Color.BLACK);
            gc.fillRect(i*25, 0 * 25, 25, 25);
            gc.fillRect(0*25, i*25, 25, 25);
            gc.fillRect(20*25, i*25, 25, 25);
            gc.fillRect(i*25, 20*25, 25, 25);
        }
        
        ImagePattern rat = new ImagePattern(new Image(new FileInputStream("images\\rat.gif")));
        gc.setFill(rat);
        gc.fillRect(foodX * 25, foodY * 25, 25, 25);


        gc.setFill(catImage);
        gc.fillRect(cat.x * 25, cat.y*25, 25,25);
            
    }

    private static class Corner {
        int x;
        int y;

        public Corner(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
    
    // generate random rats 
    public static void generateNewFood() throws FileNotFoundException {
        ImagePattern rat = new ImagePattern(new Image(new FileInputStream("images\\rat.gif")));
        Random rand = new Random();
        while (true) {
            foodX = rand.nextInt(19)+1;
            foodY = rand.nextInt(19)+1;
            
            if (cat.x == foodX && cat.y == foodY) {
                continue;
            }

            break;

        }
    }
    private static void gameOver() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("Good game. You caught " + ratsCaught + " rats");
        alert.setOnHidden(evt -> {
            try {
                window.setScene(OptionPanelScene.optionPanel());
            } catch (FileNotFoundException ex) {

            }
        });
        alert.show(); 
        ratsCaught = 0;
        speed = 5;
        gameOver = false;
        gc.setFill(catImage);
        gc.fillRect(cat.x * 25, cat.y*25, 25,25);
    }
}
