/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

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
    
    public Date getWorkStart() {
        return this.workStart;
    }
    
    public int getStatusId() {
        return this.statusId;
    }
}

