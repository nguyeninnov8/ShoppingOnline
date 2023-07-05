/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import entity.ProductList;
import entity.ShoppingCart;
import entity.User;
import entity.UserList;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu {

    private boolean exit = false;
    private static User currentUser;
    private UserList userList = new UserList();
    private ProductList prdList = new ProductList();
    private ShoppingCart sCart = new ShoppingCart();

    public final static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }

    public void displayMainMenu() {
        System.out.println("Welcome to Our Shopping Online - The SOP PE");
        System.out.println("You are a: ");
        System.out.println("1. User.");
        System.out.println("2. Shoplifter.");
        System.out.println("3. Exit");
    }

    public void mainMenu() {
        exit = false;

        while (!exit) {
            displayMainMenu();
            int choice = MyUtils.inputInteger("Enter your choice", 1, 3);
            switch (choice) {
                case 1:
                    userMenu();
                    break;
                case 2:
                    displayShopMenu();
                    break;
                case 3:
                    System.out.println("Thanks for visiting us");
                    exit = true;
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    public void displayUserMainMenu() {
        System.out.println("==== Main Menu ====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Back to the Identification");
    }

    public void register() {
        System.out.println("==== Register ====");
        userList.registerUser();
        do {
            System.out.println("Press 1 to back to main menu ");
        } while(!"1".equals(MyUtils.inputString("Enter: ")));

    }

    public void login() {
        System.out.println("==== Login ====");
        currentUser = userList.loginUser();
        if (currentUser == null) {
            System.out.println("Your username or password is incorrect. Please enter again or create a new one.");
        } else {
            shopMenu();
        }
    }

    public void logout() {
        System.out.println("Logout Successfully");
        mainMenu();
    }

    public void userMenu() {
        exit = false;
        while (!exit) {
            displayUserMainMenu();
            int choice = MyUtils.inputInteger("Please enter your choice", 1, 3);

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    mainMenu();
                    break;
            }
        }
    }

    public void shopMenu() {
        exit = false;

        while (!exit) {
            displayShopMenu();
            int choice = MyUtils.inputInteger("Enter your choice", 1, 4);

            switch (choice) {
                case 1:
                    displayAllProducts();
                    break;
                case 2:
                    addProductToCart();
                    break;
                case 3:
                    cartMenu();
                    displayAddProductToCart();
                    break;
                case 4:
                    displayCartItem();
                    break;
                case 5:
                    exit = true;
                    userMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void displayShopMenu() {
        System.out.println("==== Shop Menu ====");
        System.out.println("1. See All Products");
        System.out.println("2. Add Product to Cart");
        System.out.println("3. Cart View");
        System.out.println("4. Log out");
    }

    public void displayAllProducts() {
        System.out.println("==== All Products ====");
        // Implement logic to display all products in the shop
    }

    public void addProductToCart() {
        System.out.println("==== Add Product to Cart ====");
        // Implement logic to add a product to the cart
    }

    public void cartMenu() {
        System.out.println("==== Cart Menu ====");
        // Implement logic for the cart menu
    }
     
    
    public void displayAddProductToCart() {
        displayAllProducts();
        String prdID = MyUtils.inputString("Enter Product ID of the Product you want to add: ");
        int quantity = MyUtils.inputInteger("Enter Quantity: ", 1, Integer.MAX_VALUE);
        addProductToCart(prdID, quantity);
    }
    
    public void displayCartItem() {
        sCart.displayCartItems(currentUser);
    }
    
    public void addProductToCart(String prdID, int quantity) {
        sCart.addProductToCart(currentUser, prdID, quantity);
    }
}
