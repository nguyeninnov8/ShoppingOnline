package entity;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ShopOwner extends User {
    private String shopName;
    private final List<Product> products;
    private static final String shopListFile = "src\\data\\shopList.txt";

    public ShopOwner(String username, String password, String shopName) {
        super(username, password, shopName);
        this.shopName = shopName;
        this.products = new ArrayList<>();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    
    public void writeToShopList() {
      try (FileOutputStream fos = new FileOutputStream(shopListFile);
         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        for (Product product : products) {
            oos.writeObject(product);
        }
        oos.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }  

   public void readFromShopList() {
    File f = new File(shopListFile);
        if (!f.canRead()) {
            System.out.println("File cannot be read");
            return;
        }
    try (FileInputStream fis = new FileInputStream(shopListFile);
         ObjectInputStream ois = new ObjectInputStream(fis)) {
        products.clear();
        while (true) {
            try {
                Product product = (Product) ois.readObject();
                products.add(product);
            } catch (EOFException e) {
                break; 
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

   public void addProductToShop(Product product) {
        readFromShopList(); 
        products.add(product);
        writeToShopList(); 
        System.out.println("Product added to the shop: " + product.getProductName());
    } 

    public void removeProductFromShop(Product product) {
         readFromShopList();
         if (products.remove(product)) {
              writeToShopList(); 
              System.out.println("Product removed from the shop: " + product.getProductName());
         } else {
              System.out.println("Product not found in the shop.");
        }
    }

    public void updateProductInShop(Product product) {
         readFromShopList(); 
         for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductID().equals(product.getProductID())) {
                products.set(i, product);
                writeToShopList();
                System.out.println("Product updated in the shop: " + product.getProductName());
                return;
             }
         }
         System.out.println("Product not found in the shop.");
        }
}