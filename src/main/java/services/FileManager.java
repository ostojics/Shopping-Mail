/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

    public void readUsers() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data" + File.separator + "users.txt"));
            String line;
             
             while ((line = reader.readLine()) != null) {
                 System.out.println(line);
            }
             
            reader.close();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
