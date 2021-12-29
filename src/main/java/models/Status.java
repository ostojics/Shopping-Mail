/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Slobodan
 */
public class Status {
    String date;
    String time;
    String id;
    
    public Status(String date, String time, String id) {
        this.date = date;
        this.time = time;
        this.id = id;
    }
    
    public static Status fromLine(String line) {
        String[] split = line.split("\\s+");
        String date = "";
        String time = "";
        String id = "";
        
        if(split.length > 1) {
            date = split[1].substring(split[1].indexOf(":")+1);
            time = split[2].substring(split[2].indexOf(":")+1);
            id = split[3].substring(split[3].indexOf(":")+1);
        }
        Status s = new Status(date, time, id);
        
        return s;
    }
    
    public String getDate() {
        return this.date;
    }
    
    public String getTime() {
        return this.time;
    }
    
    public String getId() {
        return this.id;
    }
}
