package resigter;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class Customer extends User implements Serializable {

    ArrayList<Order> productordered;

    public Customer() {
    }

    public Customer(int userId, String UserName, UserType userType, String Password) {
        super(userId, UserName, userType, Password);
    }

    public int getCustomerId() {
        return userId;
    }

    public void setCustomerId(int customerId) {
        this.userId = customerId;
    }

    public String getCustomerName() {
        return UserName;
    }

    public void setCustomerName(String customerName) {
        this.UserName = customerName;
    }

    public void rateOrder(int order_id, int rating) {
        // Check if the rating  between 1 and 5
        boolean found = false;
        if (rating >= 1 && rating <= 5) {
            for (Order order : productordered) {
                if (order.getOrderId() == order_id) {
                    order.setRating(rating);
                    System.out.println("Order rated successfully");
                    writeHistorytofile(productordered);
                    found = true;
                    break;
                }

            }
        } else {
            System.out.println("Invalid rating. Please provide a rating between 1 and 5");

        }
        if (!found) {

            System.out.println("invalid order id");
        }

    }//tested

    public String viewOrderHistory(Customer customer) {

        ArrayList<Product> productList = Product.readproductfromfile();
        System.out.println("Order History:");
        for (Order order : customer.productordered) {
            if (order.productCart == null || order.productCart.isEmpty()) {
                System.out.println("No products found in the cart");
                System.out.println("---------------------------------------------------------------------------------");
                continue;
            }

            System.out.println("order ID " + order.getOrderId());
            System.out.println("Date " + order.getOrderDate());
            System.out.println("Order Rating " + order.getRating());
            System.out.println("Items ");
            for (Map.Entry<Integer, Integer> entry : order.productCart.entrySet()) {
                //product code
                int key = entry.getKey();
                //quantity
                int value = entry.getValue();
                for (Product product : productList) {
                    if (product.getCode() == key) {
                        System.out.println(product.getName() + " Price : " + product.getPrice() + " quantity : " + value);

                    }

                }

            }
            //System.out.println(order.productCart);
            System.out.println("Total amount " + order.getTotalAmount());
            System.out.println("------------------------------------------------------------");

        }
        return null;
    }

    public String ViewCart() {
        //System.out.println(productordered);
        Order order = productordered.get(productordered.size() - 1);
        System.out.println(order);
        return null;
    }

    public void writeHistorytofile(ArrayList<Order> ProductCart) {
        try {
            FileOutputStream outt = new FileOutputStream(UserName + ".dat");
            ObjectOutputStream out = new ObjectOutputStream(outt);
            out.writeObject(ProductCart);
            out.close();
            outt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<Order> readHistoryfromfile() {
        ArrayList<Order> ProductCart = new ArrayList<>();

        try {
            FileInputStream inn = new FileInputStream(UserName + ".dat");
            ObjectInputStream in = new ObjectInputStream(inn);
            ProductCart = (ArrayList<Order>) in.readObject();
            in.close();
            inn.close();
        } catch (Exception e) {
            System.out.println(e);
            return ProductCart;
        }
        return ProductCart;
    }

}
