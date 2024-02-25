package resigter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {

    private String Name, size, color, description, Gender;
    private Supplier s;
    private int quantity, price, code, QuantitySold;
    static ArrayList<Product> ProductList = Product.readproductfromfile();

    public Product(String Name, String size, String color, String description, String Gender, Supplier s, int quantity, int price, int code) {
        this.Name = Name;
        this.size = size;
        this.color = color;
        this.description = description;
        this.Gender = Gender;
        this.s = s;
        this.quantity = quantity;
        this.price = price;
        this.code = code;
        this.QuantitySold = 0;
    }

    public Product() {
    }

    public String getName() {
        return Name;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public String getGender() {
        return Gender;
    }

    public Supplier getS() {
        return s;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getCode() {
        return code;
    }

    public int getQuantitySold() {
        return QuantitySold;

    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setS(Supplier s) {
        this.s = s;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setQuantitySold(int QuantitySold) {
        this.QuantitySold = QuantitySold;
    }

    public double getRevenue() {
        return price * QuantitySold;
    }

    public int getQuantitySold(Product product, int quantity) {
        product.setQuantitySold(product.getQuantitySold() + quantity);
        product.setQuantity(product.getQuantity() - quantity);
        return product.getQuantitySold();
    }

    static void writeproducttofile(ArrayList<Product> productList) {

        try {
            FileOutputStream o = new FileOutputStream("product.dat");
            ObjectOutputStream out = new ObjectOutputStream(o);
            out.writeObject(productList);
            out.close();
            o.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ArrayList<Product> readproductfromfile() {
        ArrayList<Product> productList = new ArrayList<>();

        try {
            FileInputStream i = new FileInputStream("product.dat");
            ObjectInputStream in = new ObjectInputStream(i);
            productList = (ArrayList<Product>) in.readObject();
            in.close();
            i.close();
        } catch (Exception e) {
            System.out.println(e);
            return productList;
        }
        return productList;
    }

    @Override
    public String toString() {
        return "Product [ " + "Name =" + Name + ", size =" + size + ", color =" + color + ", description =" + description + ", Gender =" + Gender +/* ", s =" + s + */ ", quantity =" + quantity + ",\n price =" + price + ", code=" + code + ", QuantitySold=" + QuantitySold + "]";
    }

}
