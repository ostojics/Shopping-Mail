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
    private Date workStart;
    private Date workEnd;
    
    public Worker(String name, String surname, String username, String password ,String id, Date workStart) {
        super(name, surname, username, "worker", password, id);
        this.workStart = workStart;
    }
    
    public Date getWorkStart() {
        return this.workStart;
    }
}
