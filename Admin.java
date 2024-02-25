package resigter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Admin extends User {

    public Admin() {
        super();
    }

    public Admin(int userId, String UserName, UserType userType, String Password) {
        super(userId, UserName, userType, Password);
    }

    public boolean addUser(User newUser) {
        ArrayList<User> userList = User.readUserFromFile();
        for (User user : userList) {
            if (user.getUserId() == newUser.getUserId() || user.getPassword().equals(newUser.getPassword())) {
                // User already exists
                System.out.println("User already exists: " + newUser.getUserName());
                return false;
            }
        }
        userList.add(newUser);
        User.writeUserToFile(userList);
        System.out.println("User added: " + newUser.getUserName());
        return true;

    }

    public boolean removeUser(int id) {
        ArrayList<User> userList = User.readUserFromFile();
        if (userList == null || userList.isEmpty()) {
            System.out.println("No users found");
            return false;
        } else {
            for (User u : userList) {
                if (id == u.getUserId()) {
                    userList.remove(u);
                    User.writeUserToFile(userList);
                    System.out.println(u.getUserId() + " is removed successfully");
                    return true;
                }
            }
            System.out.println("User not found");
            return false;
        }
    }

    public void editUser(int id, int option, String newValue) {
        boolean found = false;
        ArrayList<User> userList = User.readUserFromFile();
        for (User u : userList) {
            if (u.getUserId() == id) {
                if (option == 1) {
                    u.setUserName(newValue);
                } else if (option == 2) {
                    u.setPassword(newValue);
                }
                found = true;
                break;
            }
        }
        if (found) {
            writeUserToFile(userList);
            System.out.println("User with id: " + id + " edited successfully");
        } else {
            System.out.println("No user with id: " + id);
        }
    }

    public User searchUser(int id) {
        ArrayList<User> userList = User.readUserFromFile();
        for (User u : userList) {
            if (u.getUserId() == id) {
                return u;
            }
        }
        return null;
    }

    public ArrayList<User> displayUsers() {
        ArrayList<User> userList = User.readUserFromFile();

        if (userList.isEmpty()) {
            System.out.println("No Users available.");
        } else {
            for (User user : userList) {
                System.out.println("User ID: " + user.getUserId() + ", Name: " + user.getUserName());
            }
        }
        return userList;
    }

    public boolean addProduct(Product newProduct) {
        ArrayList<Product> productList = Product.readproductfromfile();
        if (productList != null) {
            for (Product product : productList) {
                if (product.getCode() == newProduct.getCode()) {
                    System.out.println("Product already exists: " + newProduct.getName());
                    return false;
                }
            }
            productList.add(newProduct);
            Product.writeproducttofile(productList);
            System.out.println("Product added: " + newProduct.getName());
            return true;
        } else {
            System.out.println("Failed to read product list from file.");
            return false; // Failed to read product list
        }
    }

    public ArrayList<Product> displayProducts() {
        ArrayList<Product> productList = Product.readproductfromfile();

        if (productList.isEmpty()) {
            System.out.println("No Product available.");
        } else {
            for (Product p : productList) {
                System.out.println(p);
            }
        }
        return productList;
    }

    public boolean removeProduct(int code) {
        ArrayList<Product> productList = Product.readproductfromfile();
        if (productList == null || productList.isEmpty()) {
            System.out.println("No products found");
            return false; // Returning false as there are no products to remove
        } else {
            for (Product p : productList) {
                if (p.getCode() == code) {
                    productList.remove(p);
                    Product.writeproducttofile(productList);
                    System.out.println("Product with code: " + code + " removed successfully");
                    return true;
                }
            }
            System.out.println("Product not found");
            return false; // Returning false as the product was not found
        }
    }

    public void editProductString(int code, int option, String newstring) {

        if (option == 1) {
            boolean found = false;
            ArrayList<Product> ProductList = Product.readproductfromfile();
            for (Product p : ProductList) {
                if (p.getCode() == code) {
                    p.setName(newstring);
                    found = true;
                }
            }
            if (found) {
                Product.writeproducttofile(ProductList);
                System.out.println("Product with code :" + code + " edited successfully");
            } else {
                System.out.println("No Product with code :" + code);
            }
        } else if (option == 2) {
            boolean found = false;
            ArrayList<Product> ProductList = Product.readproductfromfile();
            for (Product p : ProductList) {
                if (p.getCode() == code) {
                    p.setSize(newstring);
                    found = true;
                }
            }
            if (found) {
                Product.writeproducttofile(ProductList);
                System.out.println("Product with code :" + code + "edited successfully");
            } else {
                System.out.println("No Product with code :" + code);
            }

        } else if (option == 3) {
            boolean found = false;
            ArrayList<Product> ProductList = Product.readproductfromfile();
            for (Product p : ProductList) {
                if (p.getCode() == code) {
                    p.setColor(newstring);
                    found = true;
                }
            }
            if (found) {
                Product.writeproducttofile(ProductList);
                System.out.println("Product with code :" + code + "edited successfully");
            } else {
                System.out.println("No Product with code :" + code);
            }
        } else if (option == 4) {
            boolean found = false;
            ArrayList<Product> ProductList = Product.readproductfromfile();
            for (Product p : ProductList) {
                if (p.getCode() == code) {
                    p.setDescription(newstring);
                    found = true;
                }
            }
            if (found) {
                Product.writeproducttofile(ProductList);
                System.out.println("Product with code :" + code + "edited successfully");
            } else {
                System.out.println("No Product with code :" + code);
            }
        } else if (option == 5) {
            boolean found = false;
            ArrayList<Product> ProductList = Product.readproductfromfile();
            for (Product p : ProductList) {
                if (p.getCode() == code) {
                    p.setGender(newstring);
                    found = true;
                }
            }
            if (found) {
                Product.writeproducttofile(ProductList);
                System.out.println("Product with code :" + code + "edited successfully");
            } else {
                System.out.println("No Product with code :" + code);
            }
        }
    }

    public void editProductInt(int code, int option, int newint) {
        if (option == 1) {
            boolean found = false;
            ArrayList<Product> ProductList = Product.readproductfromfile();
            for (Product p : ProductList) {
                if (p.getCode() == code) {
                    p.setQuantity(newint);
                    found = true;
                }
            }
            if (found) {
                Product.writeproducttofile(ProductList);
                System.out.println("Product with code :" + code + " edited successfully");
            } else {
                System.out.println("No Product with code :" + code);
            }
        } else if (option == 2) {
            boolean found = false;
            ArrayList<Product> ProductList = Product.readproductfromfile();
            for (Product p : ProductList) {
                if (p.getCode() == code) {
                    p.setPrice(newint);
                    found = true;
                }
            }
            if (found) {
                Product.writeproducttofile(ProductList);
                System.out.println("Product with code :" + code + "edited successfully");
            } else {
                System.out.println("No Product with code :" + code);
            }

        } else if (option == 3) {
            boolean found = false;
            ArrayList<Product> ProductList = Product.readproductfromfile();
            for (Product p : ProductList) {
                if (p.getCode() == code) {
                    p.setCode(newint);
                    found = true;
                }
            }
            if (found) {
                Product.writeproducttofile(ProductList);
                System.out.println("Product with code :" + code + "edited successfully");
            } else {
                System.out.println("No Product with code :" + code);
            }
        }
    }

    public Product searchProduct(int code) {
        ArrayList<Product> productList = Product.readproductfromfile();

        for (Product p : productList) {
            if (p.getCode() == code) {
                return p;
            }
        }
        return null;
    }

    public StringBuilder ViewOrders() {
        StringBuilder sb = new StringBuilder();
        Order.orders = Order.readProductCartfromfile();
        for (Order o : Order.orders) {
            // System.out.println("Customer" + o.getC());
            sb.append(o);
            // c.displayProductsInCart(o);

        }
        return sb;
    }
//Supplier reports and methods------------------------------------------

    public void AddSuppliers(Supplier supplier) {
        Supplier.SupplierList = Supplier.readsupplierfromfile();
        Supplier.SupplierList.add(supplier);
        Supplier.writesuppliertofile(Supplier.SupplierList);
    }

    public StringBuilder viewSupplierproducts() {
        StringBuilder sb = new StringBuilder();

        ArrayList< Product> productList = Product.readproductfromfile();
        Supplier.SupplierList = Supplier.readsupplierfromfile();
        for (Supplier supplier : Supplier.SupplierList) {
            sb.append(supplier).append("\n");
            //System.out.println(supplier);
            for (Product product : productList) {
                if (supplier.Product_id.contains(product.getCode())) {
                    sb.append(product).append("\n");
                    //System.out.println(product);

                }

            }

        }
        return sb;
    }

    public void Add_product_to_supplier(int product_id, int supplier_id) {
        Supplier.SupplierList = Supplier.readsupplierfromfile();
        for (Supplier supplier : Supplier.SupplierList) {
            if (supplier.getSupplierID() == supplier_id) {

                supplier.Product_id.add(product_id);
                System.out.println("product id added to supplier list successfully");
            }

        }
        Supplier.writesuppliertofile(Supplier.SupplierList);
    }

    public String get_Supplier_Max_Orders() {
        ArrayList<Supplier> SupplierList = Supplier.readsupplierfromfile();
        int maxOrders = 0;
        String Supplier_name = null;
        for (Supplier supplier : SupplierList) {
            int orders = 0;
            for (Order order : Order.orders) {
                for (Map.Entry<Integer, Integer> entry : order.productCart.entrySet()) {
                    //product code
                    int key = entry.getKey();
                    //quantity
                    int value = entry.getValue();
                    if (supplier.Product_id.contains(key)) {
                        orders++;
                        //product id is in the order once
                        break;
                    }

                }

            }
            if (orders > maxOrders) {
                maxOrders = orders;
                Supplier_name = supplier.getSupplierName();
            }
        }
        System.out.println("the supplier with max orders = " + maxOrders + " is " + Supplier_name);
        String max = String.valueOf(maxOrders) + " " + Supplier_name;
        return max;
    }

    public String get_Supplier_Max_Revenue() {
        ArrayList< Product> productList = Product.readproductfromfile();

        ArrayList<Supplier> SupplierList = Supplier.readsupplierfromfile();
        double maxRevenue = 0;
        String Supplier_name = null;
        for (Supplier supplier : SupplierList) {
            double revenue = 0;
            for (Order order : Order.orders) {
                for (Map.Entry<Integer, Integer> entry : order.productCart.entrySet()) {
                    //product code
                    int key = entry.getKey();
                    //quantity
                    int value = entry.getValue();

                    if (supplier.Product_id.contains(key)) {
                        for (Product product : productList) {
                            if (product.getCode() == key) {
                                revenue += product.getPrice() * value;

                            }

                        }

                        //product id is in the order once
                        break;
                    }

                }

            }
            if (revenue > maxRevenue) {
                maxRevenue = revenue;
                Supplier_name = supplier.getSupplierName();
            }
        }
        System.out.println("the supplier with max revenue = " + maxRevenue + " is " + Supplier_name);
        String max = String.valueOf(maxRevenue) + " " + Supplier_name;

        return max;
    }

    public StringBuilder get_no_of_orders_details() {
        int orders = 0;

        ArrayList< Product> productList = Product.readproductfromfile();

        ArrayList<Supplier> SupplierList = Supplier.readsupplierfromfile();
        String Supplier_name = null;
        StringBuilder sb = new StringBuilder();

        for (Supplier s : SupplierList) {
            orders = 0;
            sb.append(s).append("\n");

            //System.out.println(s);
            for (Order order : Order.orders) {
                for (Map.Entry<Integer, Integer> entry : order.productCart.entrySet()) {
                    double totalPayment = 0.0;

                    //product code
                    int key = entry.getKey();
                    //quantity
                    int value = entry.getValue();
                    if (s.Product_id.contains(key)) {
                        sb.append("order ID ").append(order.getOrderId()).append("\n");
                        sb.append("Date ").append(order.getOrderDate()).append("\n");
                        sb.append("Items: ").append("\n");

                        //System.out.println("order ID " + order.getOrderId());
                        //System.out.println("Date " + order.getOrderDate());
                        for (Product product : productList) {
                            if (product.getCode() == key) {
                                sb.append(product.getName()).append(" Price : ").append(product.getPrice()).append(" quantity : ").append(value).append("\n");

                                //System.out.println(product.getName() + " Price : " + product.getPrice() + " quantity : " + value);
                                totalPayment += product.getPrice() * value;
                                sb.append("total payment =").append(totalPayment).append("\n");

                                //System.out.println("total payment =" + totalPayment);
                            }

                        }
                        sb.append("------------------------------------------------------------------------------").append("\n");

                        //System.out.println("------------------------------------------------------------------------------");
                        orders++;
                        //product id is in the order once
                        break;
                    }

                }

            }
            sb.append(" no of orders made of his product = ").append(orders).append("\n");

            //System.out.println("the supplier = " + s.getSupplierName() + " no of orders made of his product = " + orders);
            sb.append("------------------------------------------------------------------------------").append("\n");

            //System.out.println("------------------------------------------------------------------------------");
        }
        //System.out.println("the supplier with max orders = " + maxOrders + " is " + Supplier_name);

        return sb;
    }

    public String getCustomerName() {
        return UserName;
    }

    public String getCustomerWithMaxOrders() {
        //Order order = new Order();
        Map<String, Integer> ordersPerCustomer = new HashMap<>();

        for (Order order : Order.orders) {
            String customerName = getCustomerName();
            ordersPerCustomer.put(customerName, ordersPerCustomer.getOrDefault(customerName, 0) + 1);
        }

        String customerWithMaxOrders = null;
        int maxOrders = 0;

        for (Map.Entry<String, Integer> entry : ordersPerCustomer.entrySet()) {
            String customerName = entry.getKey();
            int ordersCount = entry.getValue();

            if (ordersCount > maxOrders) {
                maxOrders = ordersCount;
                customerWithMaxOrders = customerName;
            }
        }

        return customerWithMaxOrders;
    }

    public String getCustomerWithMaxRevenue() {
        HashMap<String, Double> revenuePerCustomer = new HashMap<>();

        for (Order order : Order.orders) {
            String customerName = getCustomerName();
            double orderRevenue = calculateTotalRevenue();
            revenuePerCustomer.put(customerName, revenuePerCustomer.getOrDefault(customerName, 0.0) + orderRevenue);
        }

        String customerWithMaxRevenue = null;
        double maxRevenue = 0.0;

        for (Map.Entry<String, Double> entry : revenuePerCustomer.entrySet()) {
            String customerName = entry.getKey();
            double revenue = entry.getValue();

            if (revenue > maxRevenue) {
                maxRevenue = revenue;
                customerWithMaxRevenue = customerName;
            }
        }

        return customerWithMaxRevenue;
    }
    //__________________________________________________________________________________________________________________________________

    public double calculateTotal() {
        double total = 0;
        for (Product product : Product.ProductList) {
            total += product.getPrice();
        }
        return total;
    }

    public double calculateTotalRevenue() {
        double totalRevenue = 0;
        for (Order order : Order.orders) {
            totalRevenue += calculateTotal();
        }
        return totalRevenue;
    }
    //_____________________________________________________________________________________________________________________________

    public double calculateTotalRevenueOverTime(LocalDate startDate, LocalDate endDate) {
        double totalRevenue = 0;
        for (Order order : Order.orders) {
            if (order != null && order.getOrderDate() != null) {
                if ((order.getOrderDate().isAfter(startDate) || order.getOrderDate().isEqual(startDate)) && (order.getOrderDate().isBefore(endDate) || order.getOrderDate().isEqual(endDate))) {
                    //String orderTime = order.getOrderDateTime();
//            if (isWithinTimePeriod(orderTime, startTime, endTime)) {
//                totalRevenue += calculateTotal();
//            }
                    totalRevenue += order.getTotalAmount();
                }
            }
        }
        return totalRevenue;
    }//tested

    private int getOrdersCount(LocalDate startDate, LocalDate endDate) {
        int count = 0;
        for (Order order : Order.orders) {
            //String orderTime = order.getOrderDateTime();
            if (order != null && order.getOrderDate() != null) {
                if ((order.getOrderDate().isAfter(startDate) || order.getOrderDate().isEqual(startDate)) && (order.getOrderDate().isBefore(endDate) || order.getOrderDate().isEqual(endDate))) {
                    count++;
                }
            }

//            if (isWithinTimePeriod(orderTime, startTime, endTime)) {
//                count++;
//            }
        }
        return count;
    }

    public double calculateAverageRevenue(LocalDate startDate, LocalDate endDate) {
        double totalRevenue = calculateTotalRevenueOverTime(startDate, endDate);
        int orderCount = getOrdersCount(startDate, endDate);

        if (orderCount > 0) {
            return totalRevenue / orderCount;
        } else {
            return 0;
        }
    }//tested
    //_______________________________________________________________________________________________________________________________

    ArrayList<Cashier> cashiers = new ArrayList<>();

    /* public int getCashierWithMaxRevenue() {
        Cashier maxRevenueCashier = null;
        double maxRevenue = 0.0;

        for (Cashier cashier : cashiers) {
            if (calculateTotalRevenue() > maxRevenue) {
                maxRevenue = calculateTotalRevenue();
                maxRevenueCashier = cashier;
            }
        }

        return maxRevenueCashier;
    }
     public int getNumberOfOrders() {
        return orders.size();
    }
    public int getCashierWithMaxOrders() {
        Cashier maxOrdersCashier = null;
        int maxOrders = 0;

        for (Cashier cashier : cashiers) {
            if (getNumberOfOrders() > maxOrders) {
                maxOrders = getNumberOfOrders();
                maxOrdersCashier = cashier;
            }
        }

        return maxOrdersCashier;
    }*/
    //product report______________________________________________
    public int no_of_pieces_sold(LocalDate startDate, LocalDate endDate) {
        int no_of_pieces = 0;
        for (Order order : Order.orders) {
            if (order != null && order.getOrderDate() != null) {
                if ((order.getOrderDate().isAfter(startDate) || order.getOrderDate().isEqual(startDate)) && (order.getOrderDate().isBefore(endDate) || order.getOrderDate().isEqual(endDate))) {

                    for (Map.Entry<Integer, Integer> entry : order.productCart.entrySet()) {
                        //product code
                        int key = entry.getKey();
                        //quantity
                        int value = entry.getValue();
                        no_of_pieces += value;
                    }
                }
            }

        }
        return no_of_pieces;
    }//tested

    public String getProductWithMaxRevenue() {
        Product maxRevenueProduct = null;
        double maxRevenue = 0.0;

        for (Product product : Product.ProductList) {

            if (product.getRevenue() > maxRevenue) {
                maxRevenue = product.getRevenue();
                maxRevenueProduct = product;
            }
        }
        String max = "Revenue =" + maxRevenueProduct.getRevenue() + " Name : " + maxRevenueProduct.getName() + " Id : " + maxRevenueProduct.getCode() + " Price :  " + maxRevenueProduct.getPrice();

        return max;
    }//tested

    public String getBestSellerProduct() {
        Product bestSellerProduct = null;
        int maxQuantitySold = 0;

        for (Product product : Product.ProductList) {
            if (product.getQuantitySold() > maxQuantitySold) {
                maxQuantitySold = product.getQuantitySold();
                bestSellerProduct = product;
            }
        }
        String bestseller = "Name : " + bestSellerProduct.getName() + " Id : " + bestSellerProduct.getCode() + " Price :  " + bestSellerProduct.getPrice();
        return bestseller;
    }
}
