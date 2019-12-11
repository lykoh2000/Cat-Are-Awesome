/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catareawesome;

import java.util.Timer;
import java.util.TimerTask;

public class TimeTracker {
    long currentTime;
    final int MINUTES = 1; // The delay in minutes
    
     public TimeTracker() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
        @Override
        public void run() { // Function runs every MINUTES minutes.
            System.out.println("timer running");
            currentTime = getCurrentTime();// Run the code you want here
            updateAttribute();
            Cat.calculateTimeOfAlive();
            
            System.out.println("Current Hunger: " + Cat.getHunger());
            System.out.println("Happiness: " + Cat.getHappiness());
            Cat.calculateTimeToLive();
            System.out.println(Cat.getTimeToLive());
        }
    }, 0, 1000 * 60 * MINUTES);
    // 1000 milliseconds in a second * 60 per minute * the MINUTES variable.
    }
    public long getCurrentTime() {
        return System.currentTimeMillis()/1000L;
    }
     
    public void updateAttribute() {
        //System.out.println("Timer running...");
        long timePassed = currentTime - Cat.initialTime;
        if (timePassed < 180) {
            return;
        }
        
        Cat.initialTime = currentTime;
        Cat.increaseHunger(timePassed/3600.0 * 20);
        Cat.decreaseHappiness(timePassed/3600.0 * 20);
        // alert message
        if (Cat.getHappiness() <= 0 || Cat.getHunger() >= 100) {
           
            if (Cat.getHappiness() <= 0) {
                Cat.setHappiness(0);
                System.out.println("Cat is bored");
               // AlertBox.display("Your cat is bored now. Please play with it...");
               // System.out.println(Cat.hunger);
            }
            if (Cat.getHunger() >= 100) {
                System.out.println("Cat is hungry");
                Cat.setHunger(100);
                
                //AlertBox.display("Your cat is hungry now. Please feed it before it dies");
               
            }
            return;
        }

        

        // FIVE HOURS AFTER HAPPINESS OR HUNGER BECOME ZERO, CAT DIES
        Cat.happinessTimeLeft = (long) (Cat.getHappiness() / 100.0 * 18000.0 + 18000);
        Cat.hungerTimeLeft = (long) ((100 - Cat.getHunger()) / 100.0 * 18000.0 + 18000);
        
        
        
    }
     

     
}
