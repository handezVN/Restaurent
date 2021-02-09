/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author handez
 */
public class DetailOrderDTO {
    private  String orderid;
    private String name;
    private int quantity;
    private int price;
    private String product_id;

    public DetailOrderDTO(String orderid, String name, int quantity, int price, String product_id) {
        this.orderid = orderid;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.product_id = product_id;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
    
    
}
