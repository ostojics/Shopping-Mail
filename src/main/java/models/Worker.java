/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.DateFormatter;

/**
 *
 * @author Slobodan
 */
public class Worker extends User {
    private int statusId;
    private Date workStart;
    private Date workEnd;
    
    public Worker(String name, String surname, String username, String password ,String id) {
        super(name, surname, username, "Worker", password, id);
        this.workStart = new Date();
        this.statusId = 0;
    }
    
    public static Worker fromLine(String line) {
        String[] split = line.split("\\s+");
        String name = "";
        String surname = "";
        String username = "";
        String password = "";
        String id = "";
        String dateStart = "";
        String statusId = "";
        DateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");
  
        if(split.length > 1) {
            id = split[0].substring(split[0].indexOf(":")+1);
            name = split[1].substring(split[1].indexOf(":")+1);
            surname = split[2].substring(split[2].indexOf(":")+1);
            username = split[3].substring(split[3].indexOf(":")+1);
            password = split[4].substring(split[4].indexOf(":")+1);
            dateStart = split[6].substring(split[6].indexOf(":")+1);
            statusId = split[7].substring(split[7].indexOf(":")+1);
        }
        
        try {
            Date date = formatter.parse(dateStart);
            Worker w = new Worker(name, surname, username, password, id);
            w.setWorkStart(date);
            w.setStatusId(Integer.parseInt(statusId));
            
            return w;
        } catch (ParseException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Worker("","","","","");
    }
    
    public String toString() {
        DateFormatter df = new DateFormatter();
        String dateString = df.dateToString(this.getWorkStart());
        String data = String.format("id:%s name:%s surname:%s username:%s password:%s role:%s dateStart:%s status:%s \n", 
                    this.getId(), this.getFirstName(), this.getLastName(), this.getUsername(), this.getPassword(), this.getRole(), dateString, String.valueOf(this.getStatusId()));
        
        return data;
    }
    
    public Date getWorkStart() {
        return this.workStart;
    }
    
    public int getStatusId() {
        return this.statusId;
    }
    
    public void setWorkStart(Date d) {
        this.workStart = d;
    }
    
    public void setStatusId(int id) {
        this.statusId = id;
    }
   
}

