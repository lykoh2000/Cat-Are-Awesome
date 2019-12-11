
package catareawesome;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class User {
    
    private static String username;
    private static String password;
    private static int userId;
   // static boolean logInState = false;
    private static boolean hasCat;
    private static int munny;
    private static int breadQuantity, doughnutQuantity, pizzaQuantity, fishQuantity;
       
    public static String getUsername() {
        return username;
    }
    
    public static void setUsername(String username) {
        User.username = username;
    }
    
    public static String getPassword() {
        return password;
    }
    
    public static void setPassword(String password) {
        User.password = password;
    }
    
    public static int getMunny() {
        return munny;
    }
    
    public static void setMunny(int munny) {
        User.munny = munny;
    }
    
    public static void increaseMunny(int increment) {
        munny += increment;
    }
    
    public static void decreaseMunny(int decrement) {
        munny -= decrement;
    }
    
    public static int getBreadQuantity() {
        return breadQuantity;
    }
    
    public static int getDoughnutQuantity() {
        return doughnutQuantity;
    }
    
    public static int getPizzaQuantity() {
        return pizzaQuantity;
    }
    
    public static int getFishQuantity() {
        return fishQuantity;
    }
    
    public static void setBreadQuantity(int quantity) {
        breadQuantity = quantity;
    }
    
    public static void setDoughnutQuantity(int quantity) {
        doughnutQuantity = quantity;
    }
    public static void setPizzaQuantity(int quantity) {
        pizzaQuantity = quantity;
    }
    public static void setFishQuantity(int quantity) {
        fishQuantity = quantity;
    }
    
    public static void increaseBread(int quantity) {
        breadQuantity += quantity;
    }
    public static void increaseDoughnut(int quantity) {
        doughnutQuantity += quantity;
    }
    public static void increasePizza(int quantity) {
        pizzaQuantity += quantity;
    }
    public static void increaseFish(int quantity) {
        fishQuantity += quantity;
    }
    
    public static void decreaseBread(int quantity) {
        breadQuantity -= quantity;
    }
    
    public static void decreaseDoughnut(int quantity) {
        doughnutQuantity -= quantity;
    }
    public static void decreasePizza(int quantity) {
        pizzaQuantity -= quantity;
    }
    public static void decreaseFish(int quantity) {
        fishQuantity -= quantity;
    }
    
    public static void checkHasCat() {
        try {
            Scanner scan = new Scanner(new FileInputStream("user.txt"));
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");
                if (Integer.toString(userId).equals(line[0]))
                    User.hasCat = line[3].equals("true");
            } 

            scan.close();
        } catch (IOException e) {

        }
    }
    
    public static void getFoodInventory() {
        try {
            Scanner scan = new Scanner(new FileInputStream("food inventory.txt"));
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");
                if (line[0].equals(Integer.toString(userId))) {
                    User.munny = Integer.parseInt(line[5]);
                    User.breadQuantity = Integer.parseInt(line[1]);
                    User.doughnutQuantity = Integer.parseInt(line[2]);
                    User.pizzaQuantity = Integer.parseInt(line[3]);
                    User.fishQuantity = Integer.parseInt(line[4]);
                }
            }
            scan.close();
            
        } catch (IOException e) {
            
        }
    }
    
    public static void getUserIdFromFile() {
        try {
            Scanner scan = new Scanner (new FileInputStream("user.txt"));
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");
                if (line[1].equals(username)) {
                    userId = Integer.parseInt(line[0]);
                }
                
            }
            scan.close();
            
        } catch(IOException e) {
            
        }
    }
    
    public static void setUserIdInFile() {
        try {
            Scanner scan = new Scanner(new FileInputStream("user.txt"));
            int counter = 0;
            while (scan.hasNextLine()) {
                scan.nextLine();
                counter++;
            }
            userId = counter+1;
            
            
        } catch(IOException e) {
            
        }
    }
        
    public static int getUserId() {
        return userId;
    } 
    
    public static boolean hasCat() {
        return hasCat;
    }
    
    public static void setHasCat(boolean hasCat) {
        User.hasCat = hasCat;
    }
    
}
