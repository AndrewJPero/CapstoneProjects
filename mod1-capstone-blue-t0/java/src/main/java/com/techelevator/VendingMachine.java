package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.*;

public class VendingMachine {

    private BigDecimal balance = new BigDecimal(0);
    private List<InventoryItems> listOfInventoryItems = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);
    private Date date = new Date();
    File destinationFile = new File("C:/Users/Student/workspace/mod1-capstone-blue-t0/java/Log.txt");
    PrintWriter destinationFileWrite = null;

    public void makeInventory() {

        try (Scanner scan = new Scanner(new File("vendingmachine.csv"))) {

            while (scan.hasNextLine()) {
                String inventoryLine = scan.nextLine();
                String[] arraySections = inventoryLine.split("\\|");
                String slotLocation = arraySections[0];
                String productName = arraySections[1];
                BigDecimal price = new BigDecimal(arraySections[2]);
                String type = arraySections[3];
                InventoryItems singleInventoryItem = new InventoryItems(slotLocation, productName, price, type);
                listOfInventoryItems.add(singleInventoryItem);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(); //could print to log.txt
        }
    }

    public VendingMachine() {
        makeInventory();
    }

    public void listInventory() {
        for (InventoryItems item : listOfInventoryItems) {
            if (item.getPrice() == null) {
                System.out.println(item.getSlotLocation() + "   " + item.getProductName());
            } else {
                System.out.println(String.format("%1$-4s %2$-20s %3$11s %4$10s", item.getSlotLocation(), item.getProductName(), "$ " + item.getPrice(), item.getType()));
            }
        }
    }

    public void methodFeedMoney() {
        System.out.println("Please Enter the Amount of Money to Deposit into the Vending Machine");
            try {
                BigDecimal moneyEntered = scan.nextBigDecimal();
                scan.nextLine();
                    if (moneyEntered.scale() <= 0) {
                        try {
                            destinationFileWrite = new PrintWriter(new FileWriter(destinationFile, true));
                            destinationFileWrite.print(DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(date) + " ~ FEED MONEY: " + balance.toString() + " ");
                            balance = balance.add(moneyEntered);
                            destinationFileWrite.print(balance.toString() + "\n");
                            destinationFileWrite.close();
                        } catch (IOException e) {
                            System.out.println("IO Exception " + e.getMessage());
                        }
                    } else
                        System.out.println("Please Enter A Whole Dollar Amount");
                    }
            catch (InputMismatchException e) {
                System.out.println("You Didn't Even Enter A Number, Are You Trying To Break Me?");
                scan.nextLine();
            }
    }


    public void selectForSlot() {
        try {
            System.out.println("Please Enter The Slot Location Of The Snack");
            String desiredItem = scan.nextLine();
            destinationFileWrite = new PrintWriter(new FileWriter(destinationFile, true));

            int i = 1;

            for (InventoryItems item : listOfInventoryItems) {
                if (item.getSlotLocation().equalsIgnoreCase(desiredItem) && item.getProductName() == "***SOLD OUT***") {
                    System.out.println("This Item Is Sold Out");
                } else if (item.getSlotLocation().equalsIgnoreCase(desiredItem) && balance.compareTo(item.getPrice()) >= 0) {
                    destinationFileWrite.print(DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(date) + " ~ " + item.getProductName() + " " + item.getSlotLocation() + balance.toString() + " ");
                    balance = balance.subtract(item.getPrice());
                    destinationFileWrite.print(balance.toString() + "\n");
                    destinationFileWrite.close();
                    System.out.println(item.getProductName() + " " + item.getPrice() + " " + " Remaining Balance:  $" + balance);
                    item.getTypeSound();
                    item.setQuantity();
                } else if (item.getSlotLocation().equalsIgnoreCase(desiredItem) && balance.compareTo(item.getPrice()) < 0) {
                    System.out.println("Not Enough Money");
                } else if (!item.getSlotLocation().equalsIgnoreCase(desiredItem) && i++ == listOfInventoryItems.size()) {
                    System.out.println("Invalid Slot Location Entered");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
        }
    }

    public void methodFinishTransaction() {
        double quarter = .25;
        double dime = .10;
        double nickel = .05;

        System.out.println("Total Amount To Return:   $" + balance);

        try {
            destinationFileWrite = new PrintWriter(new FileWriter(destinationFile, true));
            destinationFileWrite.println(DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(date) + " ~ GIVE CHANGE: " + balance + " " + 0);
            destinationFileWrite.close();
        }
        catch(IOException e){
            System.out.println("IO Exception " + e.getMessage());
        }

        while (balance.compareTo(BigDecimal.ZERO) != 0) {
            if (balance.compareTo(BigDecimal.valueOf(quarter)) >= 0) {
                System.out.println("Number of Quarters:   " + balance.divide(BigDecimal.valueOf(quarter)).intValue());
                balance = balance.remainder(BigDecimal.valueOf(quarter));
            } else if (balance.compareTo(BigDecimal.valueOf(dime)) >= 0) {
                System.out.println("Number of Dimes:   " + balance.divide(BigDecimal.valueOf(dime)).intValue());
                balance = balance.remainder(BigDecimal.valueOf(dime));
            } else if (balance.compareTo(BigDecimal.valueOf(nickel)) >= 0) {
                System.out.println("Number of Nickels:   " + balance.divide(BigDecimal.valueOf(nickel)).intValue());
                balance = balance.remainder(BigDecimal.valueOf(nickel));
            }
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }
}

