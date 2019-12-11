/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catareawesome;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SaveData {
    
    
    public SaveData() {
        
    }
    

    
    public static void saveUser(String file) {
        try {
           
            String userId = Integer.toString(User.getUserId());
            File inFile = new File(file);

           

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File("temp.txt");

            Scanner scan = new Scanner(inFile);
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String id;

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] splited = line.split(",");
                id = line.split(",")[0];
                if (!id.trim().equals(userId)) {

                    pw.println(line);
                    pw.flush();
                } else {
                    
                    if (User.hasCat()) {
                        splited[3] = "true";
                        line = String.join(",", splited);
                        pw.println(line);
                        pw.flush();
                    } else {
                        splited[3] = "false";
                        line = String.join(",", splited);
                        pw.println(line);
                        pw.flush();
                    }     
                }
                    

            }
            
   
            pw.close();
            scan.close();
            
            
            if (inFile.exists()) {
                System.gc();
                Thread.sleep(2000);
            }
            
            //Delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete file");

                
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inFile)) 
                System.out.println("Could not rename file");

        }
        catch (FileNotFoundException ex) {
           
        }
        catch (IOException | InterruptedException ex) {
           
        }
    }
    
    
    public static void saveCatAttribute() {
        try {
            File inFile = new File("Cat attribute.txt");
            File tempFile = new File("temp.txt");
            PrintWriter p = new PrintWriter(new FileOutputStream("temp.txt"));
            Scanner scan = new Scanner(new FileInputStream("Cat attribute.txt"));
            
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");
                if (User.getUserId() == Integer.parseInt(line[0])) {
                    if (User.hasCat()) {
                        line[1] = Integer.toString(Cat.getCatId());
                        line[2] = Double.toString(Cat.getHappiness());
                        line[3] = Double.toString(Cat.getHunger());
                        line[4] = Integer.toString(Cat.getLevel());
                        line[5] = Double.toString(Cat.getExperience());
                        line[6] = Long.toString(Cat.initialTime);
                        line[7] = Long.toString(Cat.happinessTimeLeft);
                        line[8] = Long.toString(Cat.hungerTimeLeft);
                        
                    } else {
                        continue;
                    }
                }
                String str = String.join(",", line);
                p.println(str);
                
                p.flush();
                
            }
            p.close();
            scan.close();
            if (inFile.exists()) {
                System.gc();
                Thread.sleep(2000);
            }
            if(!inFile.delete()) {
                System.out.println("Cannot delete file");
            }
            
            if (!tempFile.renameTo(inFile)) {
                System.out.println("Cannot rename file");
            }
            
            
        } catch (IOException | InterruptedException e) {
            
        }
    }
    
    public static void saveFoodInventory() {
        try {
            File inFile = new File("food inventory.txt");
            File tempFile = new File("temp.txt");
            PrintWriter p = new PrintWriter(new FileOutputStream("temp.txt"));
            Scanner scan = new Scanner(new FileInputStream("food inventory.txt"));
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");
                if (User.getUserId() == Integer.parseInt(line[0])) {
                    line[1] = Integer.toString(User.getBreadQuantity());
                    line[2] = Integer.toString(User.getDoughnutQuantity());
                    line[3] = Integer.toString(User.getPizzaQuantity());
                    line[4] = Integer.toString(User.getFishQuantity());
                    line[5] = Integer.toString(User.getMunny());
                }
                String str = String.join(",", line);
                p.println(str);
                p.flush();
            }
            
            p.close();
            scan.close();
            
            if (inFile.exists()) {
                System.gc();
                Thread.sleep(2000);
            }
            if(!inFile.delete()) {
                System.out.println("Cannot delete file");
            }
            
            if (!tempFile.renameTo(inFile)) {
                System.out.println("Cannot rename file");
            }
            
            
        } catch (IOException | InterruptedException e ) {
            
        }
        
       
    }
   
    public static void saveCat() throws InterruptedException {
        try {
            File inFile = new File("cat.txt");
            File tempFile = new File("temp.txt");
            Scanner scan = new Scanner(new FileInputStream("cat.txt"));
            PrintWriter p = new PrintWriter(new FileOutputStream("temp.txt"));
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");
                if (line[0].equals(Integer.toString(User.getUserId()))) {
                    if (!User.hasCat())
                        continue;
                }
                String str = String.join(",", line);
                p.println(str);
            }
            p.close();
            scan.close();
            if (inFile.exists()) {
                System.gc();
                Thread.sleep(2000);
            }
            if(!inFile.delete()) {
                System.out.println("Cannot delete file");
            }
            
            if (!tempFile.renameTo(inFile)) {
                System.out.println("Cannot rename file");
            }
        } catch (IOException e) {
            
        }
    }
}
