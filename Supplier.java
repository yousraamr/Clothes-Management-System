package resigter;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Supplier implements Serializable{
   
    //private int max_orders, max_revenue, no_orders;
    static ArrayList<Supplier> SupplierList = new ArrayList<>();

    ArrayList<Integer> Product_id = new ArrayList();

    // private String supplierID;
    private String supplierName;
    // private String supplierAddress;
    private String supplierPhoneNumber;
    private String supplierEmail;
    private int SupplierID;

    public void setSupplierID(int SupplierID) {
        this.SupplierID = SupplierID;
    }

    public int getSupplierID() {
        return SupplierID;
    }

    public Supplier(String supplierName, String supplierPhoneNumber, String supplierEmail, int SupplierID) {
        this.supplierName = supplierName;
        this.supplierPhoneNumber = supplierPhoneNumber;
        this.supplierEmail = supplierEmail;
        this.SupplierID = SupplierID;
    }

    public Supplier() {
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    
    public String getSupplierPhoneNumber() {
        return supplierPhoneNumber;
    }

    public void setSupplierPhoneNumber(String supplierPhoneNumber) {
        this.supplierPhoneNumber = supplierPhoneNumber;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public static void writesuppliertofile(ArrayList<Supplier> supplierList) {
        try {
            FileOutputStream o = new FileOutputStream("Suppliers.dat");
            ObjectOutputStream out = new ObjectOutputStream(o);
            out.writeObject(supplierList);
            out.close();
            o.close();
            // System.out.println("write in file successfully");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static ArrayList readsupplierfromfile() {

        ArrayList<Supplier> supplierList = new ArrayList<>();

        try {
            FileInputStream i = new FileInputStream("Suppliers.dat");
            ObjectInputStream in = new ObjectInputStream(i);
            supplierList = (ArrayList<Supplier>) in.readObject();
            in.close();
            i.close();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return supplierList;
    }

    @Override
  public String toString() {
return "Supplier{" +"supplierName='" + supplierName + '\'' +", supplierPhoneNumber='" + supplierPhoneNumber + '\'' +", supplierEmail='" + supplierEmail + '\'' +", SupplierID='" + SupplierID + '\'' +'}';
}
}