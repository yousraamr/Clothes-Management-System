package resigter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    int userId;
    String UserName;
    UserType userType;
    String Password;

    public User() {
    }

    public User(int userId, String UserName, UserType userType, String Password) {
        this.userId = userId;
        this.UserName = UserName;
        this.userType = userType;
        this.Password = Password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public static void writeUserToFile(ArrayList<User> usersList) {
        try {
            FileOutputStream o = new FileOutputStream("users.dat");
            ObjectOutputStream out = new ObjectOutputStream(o);
            out.writeObject(usersList);
            out.close();
            o.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static ArrayList<User> readUserFromFile() {
        ArrayList<User> usersList = new ArrayList<>();

        try {
            FileInputStream i = new FileInputStream("users.dat");
            ObjectInputStream in = new ObjectInputStream(i);
            usersList = (ArrayList<User>) in.readObject();
            in.close();
            i.close();
        } catch (Exception e) {
            System.out.println(e);
            return usersList;
        }
        return usersList;
    }

    @Override
    public String toString() {
        return "User [ " + "User Id = " + userId + ", User Name = " + UserName + ", User Type = " + userType + ",Password = " + Password + "]";
    }

    public static boolean SignUp(String username, String password, String type) {
        ArrayList<User> users = readUserFromFile();
        boolean found = false;

        for (User u : users) {
            if (u.getUserName().equals(username)) {
                found = true;
                break;
            }
        }

        if (!found) {
            int max = 0;
            for (User u : users) {
                if (u.getUserId() > max) {
                    max = u.getUserId();
                }
            }
            if (type.equals("cashier")) {
                Cashier cashier = new Cashier(max + 1, username, UserType.CASHIER, password);
                users.add(cashier);
                System.out.println("Cashier created successfully");
            } else if (type.equals("customer")) {
                Customer customer = new Customer(max + 1, username, UserType.CUSTOMER, password);
                users.add(customer);
                System.out.println("Customer created successfully");
            } else if (type.equals("admin")) {
                Admin admin = new Admin(max + 1, username, UserType.ADMIN, password);
                users.add(admin);
                System.out.println("Admin created successfully");
            }

            writeUserToFile(users);

            return true;
        } else {
            System.out.println("Username is taken");
            return false;
        }
    }

    public static User Login(String username, String password) {
        ArrayList<User> users = readUserFromFile();
        for (User u : users) {
            if (u.getUserName().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        System.out.println("Wrong username or password");
        return null;
    }
}
