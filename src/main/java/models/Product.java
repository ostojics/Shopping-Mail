/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Slobodan
 */
public class Product {
    private String productName;
    private double productPrice;
    private String productId;
    
    public Product(String name, double price, String id) {
        this.productName = name;
        this.productPrice = price;
        this.productId = id;
    }
    
    public String getName() {
        return this.productName;
    }
    
    public double getPrice() {
        return this.productPrice;
    }
    
    public String getId() {
        return this.productId;
    }
}
