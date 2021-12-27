/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Product;
import models.User;
import models.Worker;

/**
 *
 * @author Slobodan
 */
public class FileManager {
    
    public void writeNewStatus(Worker w) {
        String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        DateFormatter df = new DateFormatter();
        String dateString = df.dateToString(w.getWorkStart());
        
        String data = String.format("workerId:%s date:%s time:%s statusId:%s \n", w.getId(), dateString, timeStamp, String.valueOf(w.getStatusId())); 
        
        try {
            FileWriter workerStatusWriter = new FileWriter("data" + File.separator + "workerStatuses.txt", true);
            workerStatusWriter.write(data);
            workerStatusWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void writeNewUser(User u) {
        try {
            new File("data").mkdir();
            FileWriter writer = new FileWriter("data" + File.separator + "users.txt", true);
            String data = String.format("First Name:%s Last Name:%s Username:%s Password:%s Role:%s Id:%s \n", 
                    u.getFirstName(), u.getLastName(), u.getUsername(), u.getPassword(), u.getRole(), u.getId());
            
            writer.write(data);
            writer.close();
        } catch(Throwable throwable) {
             throwable.printStackTrace();
        }   
    }

    public boolean isValidLogin(String username, String password) {
        boolean isValid = false;
        
        try {
            //standard file readed in java
            BufferedReader reader = new BufferedReader(new FileReader("data" + File.separator + "users.txt"));
            String line;
            
            //loop trough lines in the file until we reach the end of file(EOF)
             while ((line = reader.readLine()) != null) {
                //create username and password string that the user entered
                //so we can compare them to the users in the database
                String usernameMatcher = String.format("Username:%s", username);
                String passwordMatcher = String.format("Password:%s", password);
                String[] split = line.split("\\s+");
                String currentUsername = "";
                String currentPassword = "";
                
                if(split.length > 1) {
                   //current username and password that we are checking in the database
                   currentUsername = split[4];
                   currentPassword = split[5];
                }
                
                if(currentUsername.equals(usernameMatcher) && currentPassword.equals(passwordMatcher)) {
                    isValid = true;
                    reader.close();
                    return isValid;
                }
            }
             
            reader.close();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        
        return isValid;
    }
    
    public User loadUser(String username, String password) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data" + File.separator + "users.txt"));
            String line;
            
             while ((line = reader.readLine()) != null) {
                String usernameMatcher = String.format("Username:%s", username);
                String passwordMatcher = String.format("Password:%s", password);
                String[] split = line.split("\\s+");
                String currentUsername = "";
                String currentPassword = "";
                
                if(split.length > 1) {
                   currentUsername = split[4];
                   currentPassword = split[5];
                }
                
                if(currentUsername.equals(usernameMatcher) && currentPassword.equals(passwordMatcher)) {
                    String firstName = "";
                    String lastName = "";
                    String role = "";
                    String id = "";
                   if(split.length > 1) {
                       firstName = split[1].substring(split[1].indexOf(":")+1);
                       lastName = split[3].substring(split[3].indexOf(":")+1);
                       role = split[6].substring(split[6].indexOf(":")+1);
                       id = split[7].substring(split[7].indexOf(":")+1);
                   }
                   
                   User loadedUser = new User(firstName, lastName, currentUsername, role, currentPassword, id);
                    reader.close();
                    return loadedUser;
                }
            }
             
            reader.close();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
       User user = new User("", "", "", "", "", "");
       return user;
    };
    
    public void writeNewProduct(Product p) {
        try {
            new File("data").mkdir();
            FileWriter writer = new FileWriter("data" + File.separator + "products.txt", true);
            String data = String.format("Name:%s Price:%s Id:%s \n", 
                    p.getName(), Double.toString(p.getPrice()), p.getId());
            
            writer.write(data);
            writer.close();
        } catch(Throwable throwable) {
             throwable.printStackTrace();
        } 
    }
    
    
    public java.util.List<Product> loadProducts() {
        java.util.List<Product> products = new ArrayList<>();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data" + File.separator + "products.txt"));
            String line;
            
            while ((line = reader.readLine()) != null) {
                Product p = Product.fromLine(line);
                products.add(p);
            }
            
            return products;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        
        return products;
    }
    
    public void writeNewWorker(Worker w) {
         try {
            new File("data").mkdir();
            DateFormatter df = new DateFormatter();
            String dateString = df.dateToString(w.getWorkStart());
            String statusId = String.valueOf(w.getStatusId());
            FileWriter writer = new FileWriter("data" + File.separator + "workers.txt", true);
            String data = w.toString();
            
            writer.write(data);
            writer.close();
            
            writeNewStatus(w);
        } catch(Throwable throwable) {
             throwable.printStackTrace();
        }
    }
    
    public java.util.List<Worker> loadWorkers() {
         java.util.List<Worker> workers = new ArrayList<>();
         
         try {
            BufferedReader reader = new BufferedReader(new FileReader("data" + File.separator + "workers.txt"));
            String line;
            
            while ((line = reader.readLine()) != null) {
                Worker w = Worker.fromLine(line);
                workers.add(w);
            }
            
            return workers;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
         
      return workers;
    }
    
    public void fireWorker(Worker w) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("data" + File.separator + "workers.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            
            String line;
             while ((line = bufferedReader.readLine()) != null) {

                if (line.contains("id:" + w.getId())) {
                    line = w.toString();
                }
                inputBuffer.append(line);
            }
             
            writeNewStatus(w);
             
            bufferedReader.close();

            FileOutputStream fileOut = new FileOutputStream("data" + File.separator + "workers.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
