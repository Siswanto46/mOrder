package com.example.siswanto.morder;

/**
 * Created by Siswanto on 12/27/2017.
 */

public class Menu {
    private String image, namemenu, price, stock, desc;

    public Menu(){

    }

    public Menu(String image, String namemenu, String price, String stock, String desc) {
        this.image = image;
        this.namemenu = namemenu;
        this.price = price;
        this.stock = stock;
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNamemenu() {
        return namemenu;
    }

    public void setNamemenu(String namemenu) {
        this.namemenu = namemenu;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
