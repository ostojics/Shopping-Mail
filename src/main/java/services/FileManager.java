/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import models.User;

/**
 *
 * @author Slobodan
 */
public class FileManager {
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
}
