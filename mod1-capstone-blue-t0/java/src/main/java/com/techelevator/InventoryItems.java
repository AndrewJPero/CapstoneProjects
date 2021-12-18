package com.techelevator;

import java.math.BigDecimal;

public class InventoryItems {

    private String slotLocation;
    private String productName;
    private BigDecimal price;
    private String type;
    private int quantity;


    public InventoryItems(String slotLocation, String productName, BigDecimal price, String type) {
        this.quantity = 5;
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.price = price;
        this.type = type;
    }

    public String getSlotLocation() {
        return this.slotLocation;
    }

    public String getProductName() {
        if (this.quantity == 0) {
            return "***SOLD OUT***";
        }
        return this.productName;
    }

    public BigDecimal getPrice() {
        if (this.quantity == 0) {
            this.price = null;  //could make a method isSoldOut
        }
        return this.price;
    }

    public String getType() {
        return this.type;
    }

    public void setQuantity() {
        this.quantity -= 1;
    }

    public void getTypeSound() {
        if (getType().equalsIgnoreCase("chip")) {
            System.out.println("Crunch Crunch, Yum!");
        } else if (getType().equalsIgnoreCase("candy")) {
            System.out.println("Munch Munch, Yum!");
        } else if (getType().equalsIgnoreCase("drink")) {
            System.out.println("Glug Glug, Yum!");
        } else if (getType().equalsIgnoreCase("gum")) {
            System.out.println("Chew Chew, Yum!");
        }
    }
}

