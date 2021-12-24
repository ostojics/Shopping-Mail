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
    
    public static Product fromLine(String line) {
         String[] split = line.split("\\s+");
         String name = "";
         String price = "";
         String id = "";
         
         if(split.length > 1) {
            name = split[0].substring(split[0].indexOf(":")+1);
            price = split[1].substring(split[1].indexOf(":")+1);
            id=split[2].substring(split[2].indexOf(":")+1);
         }
         
         Product p = new Product(name, Double.parseDouble(price), id);
         
         return p;
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
