/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catareawesome;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Cat {
    
    private static int catId;
    private static String catName;
    private static double happiness;
    private static double hunger;
    private static int level;
    private static double experience;
    private static long timeOfBirth;
    private static String timeOfAlive;
    private static String timeToLive;
    // default experience limit
    private static double experienceLimit = 100;
    static long initialTime;
    static long happinessTimeLeft;
    static long hungerTimeLeft;
   
    public static int getCatId() {
        return catId;
    }
    
    public static void setCatId(int catId) {
        Cat.catId = catId;
    }
    
    public static String getCatName() {
        return catName;
    }
    
    public static void setCatName(String catName) {
        Cat.catName = catName;
    }
    
    public static double getHappiness() {
        return happiness;
    }
    
    public static void setHappiness(double happiness) {
        Cat.happiness = happiness;
    }
    
    public static void increaseHappiness(double increment) {
        Cat.happiness += increment;
    }
    
    public static void decreaseHappiness(double decrement) {
        Cat.happiness -= decrement;
    }

    public static double getHunger() {
        return hunger;
    }
    
    public static void setHunger(double hunger) {
        Cat.hunger = hunger;
    }
    
    public static void increaseHunger(double increment) {
        Cat.hunger += increment;
    }
    
    public static void decreaseHunger(double decrement) {
        Cat.hunger -= decrement;
    }
    
    public static int getLevel() {
        return level;
    }
    
    public static void setLevel(int level) {
        Cat.level = level;
    }
    
    public static void levelUp() {
        double extra = experience - experienceLimit;
        level++;
        experience = extra;
        AlertBox.display("Your cat level up to lvl" + Cat.level);
    }
    
    public static double getExperience() {
        return experience;
    }
    
    public static void setExperience(double experience) {
        Cat.experience = experience;
    }
    
    public static void increaseExperience(double increment) {
        Cat.experience += increment;
    }
    
    public static long getTimeOfBirth() {
        return timeOfBirth;
    }
    
    public static void getTimeOfBirthFromFile() {
        try {
            Scanner scan = new Scanner(new FileInputStream("cat.txt"));
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");
                if (User.getUserId() == Integer.parseInt(line[0])) {
                    timeOfBirth = Long.parseLong(line[3]);
                }
            }
        } catch (IOException e) {
            
        }
        
    }
    
    public static void setTimeOfBirth(long time) {
        Cat.timeOfBirth = time;
    }
    
    public static String getTimeOfAlive() {
        return timeOfAlive;
    }
    
    public static void calculateTimeOfAlive() {
        long time = System.currentTimeMillis()/1000L - timeOfBirth;
        System.out.println("Time: " + time);
        int hour = 0, minutes = 0;
        while (time >= 3600) {
            hour = (int) time / 3600;
            time %= 3600;
        } while (time >= 60) {
            minutes = (int) time / 60;
            time = time % 60;
        }
        timeOfAlive = hour + " hours " + minutes + " minutes ";

    }
    
    
    public static void calculateTimeToLive() {
        long timeLeft;
        int hour = 0;
        int minutes = 0;
        if (Cat.happinessTimeLeft < Cat.hungerTimeLeft) {
            timeLeft = happinessTimeLeft;
        } else 
            timeLeft = hungerTimeLeft;
        
        while (timeLeft >= 3600) {
            hour = (int) timeLeft / 3600;
            timeLeft %= 3600;
        }
        
        while (timeLeft >= 60) {
            minutes = (int) timeLeft / 60;
            timeLeft %= 60;
        }
        timeToLive = hour + " hours " + minutes + " minutes";
    }
    
    public static String getTimeToLive() {
        return timeToLive;
    }
    
    // used in log in 
    public static void getCatNameAndId() {
        try {
            // get cat name and cat id
            Scanner scan = new Scanner(new FileInputStream("cat.txt"));
            while (scan.hasNextLine()) {
                String[] cat = scan.nextLine().split(",");
                String user = cat[0];
                if (Integer.parseInt(user) == User.getUserId()) {
                    catName = cat[1];
                    catId = Integer.parseInt(cat[2]);
                }
            }
            
            scan.close();
        } catch (IOException e) {
            System.out.println("Problem with file output");
        }
    }
    
    public static void setExperienceLimit(int exp) {
        Cat.experienceLimit = exp;
    }

    
    public static void getCatAttribute() {
        
        try {
            Scanner scan = new Scanner(new FileInputStream("cat attribute.txt"));
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");
                if (line[0].equals(Integer.toString(User.getUserId()))) {
                    Cat.catId = Integer.parseInt(line[1]);
                    Cat.happiness = Double.parseDouble(line[2]);
                    Cat.hunger = Double.parseDouble(line[3]);
                    Cat.level = Integer.parseInt(line[4]);
                    Cat.experience = Double.parseDouble(line[5]);
                    Cat.initialTime = Long.parseLong(line[6]);
                    Cat.happinessTimeLeft = Long.parseLong(line[7]);
                    Cat.hungerTimeLeft = Long.parseLong(line[8]);
                }
               
                
            }
            scan.close();
        } catch (IOException e) {
            
        }
    }
    
    
    public static double getExperienceLimit() {
        return experienceLimit;
    }
    
    public static void calculateExpLimit() {
        for (int i = 1; i < level; i++) {
            experienceLimit *= 1.5;
        }
    }
    
    
    
}
