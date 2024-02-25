package resigter;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ListIterator;

public class Cashier extends User {

    public Cashier() {
    }

    public Cashier(int userId, String UserName, UserType userType, String Password) {
        super(userId, UserName, userType, Password);
    }

    public int getCashierId() {
        return userId;
    }

    public void setCashierId(int cashierId) {
        this.userId = cashierId;
    }

    public String getCashierName() {
        return UserName;
    }

    public void setCashierName(String cashierName) {
        this.UserName = cashierName;
    }

    public void createCart(Order order) {
        order.setOrderDate(order.getDate());
        System.out.println("Cart created successfully");
    }

    public void addProductToCart(int productid, Order order, int quantity) {
        boolean found = false;

        ArrayList<Product> productList = Product.readproductfromfile();

        for (Product product : productList) {

            if (product.getCode() == productid && product.getQuantity() > quantity) {
                order.productCart.put(product.getCode(), quantity);
                System.out.println("Product added to cart: " + product.getName());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("no product found with this code or no quantity available ");
        }
    }

    public void displayProductsInCart(Order order) {
        if (order.productCart == null || order.productCart.isEmpty()) {
            System.out.println("No products found in the cart");
            System.out.println("---------------------------------------------------------------------------------");
            return;
        }
        ArrayList<Product> productList = Product.readproductfromfile();
        System.out.println("order ID " + order.getOrderId());
        System.out.println("Date " + order.getOrderDate());
        //System.out.println("Order Rating " + order.getRating());
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
        System.out.println("Total payment: " + order.getTotalAmount());
        System.out.println("---------------------------------------------------------------------------------");

    }

    public void AddOrderToCustomer(Customer c, Order o) {
        c.productordered.add(o);
        c.writeHistorytofile(c.productordered);

    }//tested

    //parameter product code and an object from the the order
    public void removeProductFromCart(int code, Order order) {
        if (order.productCart == null || order.productCart.isEmpty()) {
            System.out.println("No products found");
        } else {
            boolean found = false;

            for (Map.Entry<Integer, Integer> entry : order.productCart.entrySet()) {
                int key = entry.getKey();
                int value = entry.getValue();
                if (key == code) {
                    order.productCart.remove(key, value);

                    found = true;
                    break;
                }
            }

            if (found) {

                System.out.println("Product with code :" + code + " removed successfully");
                calculateTotalPayment(order);

            } else {
                System.out.println("Product no found");
            }

        }

    }

    public double calculateTotalPayment(Order order) {
        double totalPayment = 0.0;
        ArrayList<Product> productList = Product.readproductfromfile();
        for (Map.Entry<Integer, Integer> entry : order.productCart.entrySet()) {
            //product code
            int key = entry.getKey();
            //quantity
            int value = entry.getValue();
            for (Product product : productList) {
                if (product.getCode() == key) {
                    totalPayment += product.getPrice() * value;

                }

            }

        }
        order.setTotalAmount(totalPayment);
        System.out.println("Total payment: " + totalPayment);
        return totalPayment;
    }//tested

    public void cancelCart(int order_id, Customer customer) {
        //avoiding the ConcurrentModificationException
        ListIterator<Order> iterator = Order.orders.listIterator();

        LocalDate date2 = LocalDate.now();

        boolean orderFound = false;

        while (iterator.hasNext()) {

            Order o = iterator.next();
            if (o.getOrderId() == order_id) {
                orderFound = true;
                LocalDate date = o.getDate();
                long date3 = ChronoUnit.DAYS.between(date, date2);
                System.out.println("the order was placed " + date3 + " days ago");

                if (date3 < 5) {
                    customer.productordered = customer.readHistoryfromfile();
                    for (Order order : customer.productordered) {
                        if (order.getOrderId() == order_id) {
                            customer.productordered.remove(order);
                            break;
                        }

                    }
                    customer.writeHistorytofile(customer.productordered);
                    iterator.remove();
                    Order.writeordertofile(Order.orders);
                    ArrayList<Product> productList = Product.readproductfromfile();
                    for (Map.Entry<Integer, Integer> entry : o.productCart.entrySet()) {
                        int productCode = entry.getKey();
                        int productQuantity = entry.getValue();
                        for (Product product : productList) {
                            if (product.getCode() == productCode) {
                                product.setQuantity(product.getQuantity() + productQuantity);
                                product.setQuantitySold(product.getQuantitySold() - productQuantity);
                                break;
                            }
                        }
                    }
                    Product.writeproducttofile(productList);
                    System.out.println("cart cancelled");
                } else {
                    System.out.println("order cancellation expired after 5 days");
                }
                break;
            }
        }

        if (!orderFound) {
            System.out.println("order id not found");
        }
    }

    //place an order and write it to file
    public void placeOrder(Order order, Customer customer) {
        ArrayList<Product> productList = Product.readproductfromfile();
        for (Map.Entry<Integer, Integer> entry : order.productCart.entrySet()) {
            int productCode = entry.getKey();
            int productQuantity = entry.getValue();
            for (Product product : productList) {
                if (product.getCode() == productCode) {
                    product.setQuantity(product.getQuantity() - productQuantity);
                    product.setQuantitySold(product.getQuantitySold() + productQuantity);
                    Product.writeproducttofile(productList);
                    break;
                }
            }
        }

        customer.productordered = customer.readHistoryfromfile();
        Order.orders.add(order);
        Order.writeordertofile(Order.orders);
        this.AddOrderToCustomer(customer, order);
        System.out.println("Order placed successfully");
    }
}
