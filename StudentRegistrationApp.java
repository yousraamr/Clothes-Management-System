package resigter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StudentRegistrationApp extends Application {

    private Stage primaryStage;// hana
    private GridPane mainLayout;// hana
    private Scene mainScene;//mariam back
    private Cashier cashier = new Cashier();//yousra
    private Order order = new Order();//yousra
    private Customer customer = new Customer();//yousra
    private int selectedRating = 0; //nourhan

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Clothing Management System");

        mainLayout = new GridPane();
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setHgap(10);
        mainLayout.setVgap(10);
        mainLayout.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(mainLayout, 600, 400);
        mainLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        primaryStage.setScene(scene);
        primaryStage.show();

        showExteriorScene();
    }

    private void showExteriorScene() {
        mainLayout.getChildren().clear();
        Image image = new Image("file:C:/Users/hp/Downloads/download.jpeg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(300); // Set width as needed
        imageView.setFitHeight(200); // Set height as needed

        Label welcomeLabel = new Label("Welcome to Clothing Management System");
        welcomeLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            mainLayout.getChildren().clear();
            Label loginLabel = new Label("Login to Clothing Management System");
            loginLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

            TextField usernameField = new TextField();
            usernameField.setPromptText("Username");

            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Password");

            Button loginBtn = new Button("Login");//**************************************
            loginBtn.setOnAction(ev -> {

                String username = usernameField.getText();
                String password = passwordField.getText();

                User user1 = User.Login(username, password);
                UserType userType = user1.getUserType();

                if (userType == UserType.ADMIN) {
                    AdminDashboard(primaryStage);
                } else if (userType == UserType.CASHIER) {
                    cashierDashboard(primaryStage);
                } else if (userType == UserType.CUSTOMER) {
                    customerDashboard();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Failed");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid username or password!");
                    alert.showAndWait();
                }
            }
            );

            Button backBtn = new Button("Back");
            backBtn.setOnAction(ev -> showExteriorScene());

            mainLayout.add(loginLabel, 0, 0, 2, 1);
            mainLayout.add(usernameField, 0, 1, 2, 1);
            mainLayout.add(passwordField, 0, 2, 2, 1);
            mainLayout.add(loginBtn, 0, 3);
            mainLayout.add(backBtn, 1, 3);
        });

        Button signupButton = new Button("Signup");
        signupButton.setOnAction(e -> {
            mainLayout.getChildren().clear();
            Label signupLabel = new Label("Signup for Clothing Management System");
            signupLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

            List<String> userTypes = new ArrayList<>();
            userTypes.add("admin");
            userTypes.add("customer");
            userTypes.add("cashier");

            ComboBox<String> userTypeComboBox = new ComboBox<>();
            userTypeComboBox.getItems().addAll(userTypes);
            userTypeComboBox.setPromptText("Select User Type");

            TextField usernameField = new TextField();
            usernameField.setPromptText("Username");

            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Password");

            Button signupBtn = new Button("Signup");
            signupBtn.setOnAction(ev -> {
                String userType = userTypeComboBox.getValue();
                String username = usernameField.getText();
                String password = passwordField.getText();

                if ("admin".equals(userType)) {
                    Admin admin = new Admin();
                    admin.SignUp(username, password, userType);
                } else if ("customer".equals(userType)) {
                    Customer customer = new Customer();
                    customer.SignUp(username, password, userType);
                } else if ("cashier".equals(userType)) {
                    Cashier cashier = new Cashier();
                    cashier.SignUp(username, password, userType);
                }
            });

            Button backBtn = new Button("Back");
            backBtn.setOnAction(ev -> showExteriorScene());

            mainLayout.add(signupLabel, 0, 0, 2, 1);
            mainLayout.add(userTypeComboBox, 0, 1, 2, 1);
            mainLayout.add(usernameField, 0, 2, 2, 1);
            mainLayout.add(passwordField, 0, 3, 2, 1);
            mainLayout.add(signupBtn, 0, 4);
            mainLayout.add(backBtn, 1, 4);
        });

        mainLayout.add(imageView, 0, 0, 2, 1);
        mainLayout.add(welcomeLabel, 0, 1, 2, 1);
        mainLayout.add(loginButton, 0, 2);
        mainLayout.add(signupButton, 1, 2);

    }

    private void AdminDashboard(Stage primaryStage) {

        GridPane mainLayout = new GridPane();
        mainLayout.setAlignment(Pos.CENTER_LEFT);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setVgap(30);
        mainLayout.setHgap(10);

        Image backgroundImage = new Image("file:C:/Users/hp/Downloads/online-fashion-shopping-with-tablet.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(600, 400, false, false, false, false);
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        mainLayout.setBackground(new Background(backgroundImg));

        Button manageUsersButton = maincreateStyledButton("Manage Users");
        manageUsersButton.setOnAction(e -> openManageUsersScene(primaryStage));
        Button manageProductsButton = maincreateStyledButton("Manage Products");
        manageProductsButton.setOnAction(e -> openManageProductsScene(primaryStage));
        Button reportsButton = maincreateStyledButton("Reports");
        reportsButton.setOnAction(e -> openReportsScene(primaryStage));

        mainLayout.add(manageUsersButton, 0, 0);
        mainLayout.add(manageProductsButton, 0, 1);
        mainLayout.add(reportsButton, 0, 2);

        mainScene = new Scene(mainLayout, 600, 400);
        primaryStage.setTitle("Admin Dashboard");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void openManageUsersScene(Stage primaryStage) {
        Admin admin = new Admin();
        GridPane userLayout = createGridPane();

        Button addUserButton = createStyledButton("Add User");
        addUserButton.setPrefSize(100, 20);
        addUserButton.setOnAction(e -> {
            GridPane adduserLayout = createGridPane();

            adduserLayout.add(new Label("Username:"), 0, 0);
            TextField userNameField = new TextField();
            adduserLayout.add(userNameField, 1, 0);

            adduserLayout.add(new Label("User ID:"), 0, 1);
            TextField userIdField = new TextField();
            adduserLayout.add(userIdField, 1, 1);

            adduserLayout.add(new Label("Password:"), 0, 2);
            PasswordField passwordField = new PasswordField();
            adduserLayout.add(passwordField, 1, 2);

            ComboBox<String> userTypeComboBox = new ComboBox<>();
            userTypeComboBox.setItems(FXCollections.observableArrayList("Admin", "Cashier", "Customer"));
            adduserLayout.add(new Label("User Type:"), 0, 3);
            adduserLayout.add(userTypeComboBox, 1, 3);

            Button submitUserButton = createStyledButton("Submit User");
            submitUserButton.setOnAction(submitEvent -> {
                String username = userNameField.getText();
                String userId = userIdField.getText();
                String password = passwordField.getText();
                String userType = userTypeComboBox.getValue();
                // Parse the user ID text to an integer
                int userIdint = Integer.parseInt(userId);
                boolean userAdded = false;
                if ("Admin".equals(userType)) {
                    User newUser = new User(userIdint, username, UserType.ADMIN, password);
                    userAdded = admin.addUser(newUser);
                } else if ("Cashier".equals(userType)) {
                    User newUser = new User(userIdint, username, UserType.CASHIER, password);
                    userAdded = admin.addUser(newUser);
                } else if ("Customer".equals(userType)) {
                    User newUser = new User(userIdint, username, UserType.CUSTOMER, password);
                    userAdded = admin.addUser(newUser);
                }
                if (!userAdded) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("User Exists");
                        alert.setHeaderText(null);
                        alert.setContentText("User id or password already exists: ");
                        alert.showAndWait();
                    });
                }

            });
            adduserLayout.add(submitUserButton, 1, 4);
            Scene addUserScene = new Scene(adduserLayout, 600, 400);
            primaryStage.setScene(addUserScene);

            Button B1 = createStyledButton("Back to Main Menu");
            B1.setOnAction(ev -> primaryStage.setScene(mainScene));
            adduserLayout.add(B1, 0, 5);
        });
        userLayout.add(addUserButton, 0, 0);

        Button removeUserButton = createStyledButton("Remove User");
        removeUserButton.setPrefSize(100, 20);
        removeUserButton.setOnAction(e -> {
            GridPane removeUserLayout = createGridPane();
            Label userIdLabel = new Label("User ID:");
            TextField userIdField = new TextField();
            removeUserLayout.add(userIdLabel, 0, 0);
            removeUserLayout.add(userIdField, 1, 0);

            Button submitRemoveButton = new Button("Remove User");
            submitRemoveButton.setOnAction(removeEvent -> {
                try {
                    int userId = Integer.parseInt(userIdField.getText());
                    boolean userRemoved = admin.removeUser(userId);
                    Platform.runLater(() -> {
                        Alert alert = new Alert(userRemoved ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                        alert.setTitle(userRemoved ? "User Removed" : "User Not Found");
                        alert.setHeaderText(null);
                        alert.setContentText(userRemoved ? "User successfully removed." : "User with ID " + userId + " not found.");
                        alert.showAndWait();
                    });
                } catch (NumberFormatException ex) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Input");
                        alert.setHeaderText(null);
                        alert.setContentText("User ID must be an integer.");
                        alert.showAndWait();
                    });
                }
            });
            removeUserLayout.add(submitRemoveButton, 1, 1);

            Scene removeUserScene = new Scene(removeUserLayout, 600, 400);
            primaryStage.setScene(removeUserScene);

            Button backButtonToMainMenu = createStyledButton("Back to Main Menu");
            backButtonToMainMenu.setOnAction(ev -> primaryStage.setScene(mainScene));
            removeUserLayout.add(backButtonToMainMenu, 0, 5);

        });
        userLayout.add(removeUserButton, 0, 1);

        Button displayUsersButton = createStyledButton("Display Users");
        displayUsersButton.setPrefSize(100, 20);
        displayUsersButton.setOnAction(e -> {
            ArrayList<User> users = admin.displayUsers();
            GridPane displayUserLayout = createGridPane();
            displayUserLayout.setAlignment(Pos.TOP_CENTER);

            TextArea textArea = new TextArea();
            textArea.setEditable(false); // Make the TextArea uneditable

            if (users.isEmpty()) {
                textArea.setText("No Users available.");
            } else {
                StringBuilder userText = new StringBuilder();
                for (User user : users) {
                    userText.append(user.toString()).append("\n");
                }
                textArea.setText(userText.toString());
            }

            displayUserLayout.add(textArea, 0, 0); // Adjust grid position as needed

            Scene scene = new Scene(displayUserLayout, 600, 400);
            primaryStage.setScene(scene);

            Button B3 = createStyledButton("Back to Main Menu");
            B3.setOnAction(ev -> primaryStage.setScene(mainScene));
            displayUserLayout.add(B3, 0, 1); // Adjust grid position as needed
        });
        userLayout.add(displayUsersButton, 0, 2);

        Button searchUserButton = createStyledButton("Search User");
        searchUserButton.setPrefSize(100, 20);
        searchUserButton.setOnAction(e -> {
            GridPane searchUserLayout = createGridPane();

            Label userIdLabel = new Label("User ID:");
            TextField userIdField = new TextField();
            userIdField.setMaxWidth(100); //*********************************
            searchUserLayout.add(userIdLabel, 0, 0);
            searchUserLayout.add(userIdField, 1, 0);

            Label searchResultLabel = new Label();
            searchUserLayout.add(searchResultLabel, 0, 2, 2, 1);
            searchUserLayout.setAlignment(Pos.TOP_LEFT);

            Button submitSearchButton = createStyledButton("Search");
            submitSearchButton.setOnAction(searchEvent -> {
                try {
                    int userId = Integer.parseInt(userIdField.getText());
                    User foundUser = admin.searchUser(userId);

                    Platform.runLater(() -> {
                        if (foundUser != null) {
                            searchResultLabel.setText("User found: " + foundUser.toString());
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Search Result");
                            alert.setHeaderText(null);
                            alert.setContentText("No user with ID " + userId + " found.");
                            alert.showAndWait();
                        }
                    });
                } catch (NumberFormatException ex) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Input");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter a valid user ID.");
                        alert.showAndWait();
                    });
                }
            });
            searchUserLayout.add(submitSearchButton, 1, 1);

            Scene searchUserScene = new Scene(searchUserLayout, 600, 400);
            primaryStage.setScene(searchUserScene);

            Button B4 = createStyledButton("Back to Main Menu");
            B4.setOnAction(ev -> primaryStage.setScene(mainScene));
            searchUserLayout.add(B4, 0, 5);
        });
        userLayout.add(searchUserButton, 0, 3);

        Button editUserButton = createStyledButton("Edit User");
        editUserButton.setPrefSize(100, 20);
        editUserButton.setOnAction(e -> {
            GridPane editUserLayout = createGridPane();
            Label userIdLabel = new Label("User ID:");
            TextField userIdField = new TextField();
            editUserLayout.add(userIdLabel, 0, 0);
            editUserLayout.add(userIdField, 1, 0);

            Label optionLabel = new Label("Option:");
            ComboBox<String> optionComboBox = new ComboBox<>();
            optionComboBox.setItems(FXCollections.observableArrayList("Username", "Password"));
            editUserLayout.add(optionLabel, 0, 1);
            editUserLayout.add(optionComboBox, 1, 1);

            Label newValueLabel = new Label("New Value:");
            TextField newValueField = new TextField();
            editUserLayout.add(newValueLabel, 0, 2);
            editUserLayout.add(newValueField, 1, 2);

            Button submitEditButton = createStyledButton("Submit Edit");
            submitEditButton.setOnAction(editEvent -> {
                try {
                    int userId = Integer.parseInt(userIdField.getText());
                    String selectedOption = optionComboBox.getValue();
                    int option = selectedOption.equals("Username") ? 1 : 2;
                    String newValue = newValueField.getText();

                    admin.editUser(userId, option, newValue);
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("User Edited");
                        alert.setHeaderText(null);
                        alert.setContentText("User with ID " + userId + " has been successfully edited.");
                        alert.showAndWait();
                    });
                } catch (NumberFormatException ex) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Input");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter valid numbers for User ID and Option.");
                        alert.showAndWait();
                    });
                }

            });
            editUserLayout.add(submitEditButton, 1, 3);

            Scene editUserScene = new Scene(editUserLayout, 600, 400);
            primaryStage.setScene(editUserScene);

            Button B5 = createStyledButton("Back to Main Menu");
            B5.setOnAction(ev -> primaryStage.setScene(mainScene));
            editUserLayout.add(B5, 0, 5);
        });
        userLayout.add(editUserButton, 0, 4);

        Button backButton = createStyledButton("Back to Main Menu");
        backButton.setOnAction(e -> primaryStage.setScene(mainScene));
        userLayout.add(backButton, 0, 5);

        primaryStage.setScene(new Scene(userLayout, 600, 400));

    }

    private void openManageProductsScene(Stage primaryStage) {
        Admin admin = new Admin();
        GridPane productLayout = createGridPane();
        Button addProductButton = createStyledButton("Add Product");
        addProductButton.setPrefSize(120, 20);
        addProductButton.setOnAction(e -> {
            GridPane addProductLayout = createGridPane();

            TextField nameField = new TextField();
            TextField sizeField = new TextField();
            TextField colorField = new TextField();
            TextField descriptionField = new TextField();
            ComboBox<String> genderComboBox = new ComboBox<>();
            genderComboBox.setItems(FXCollections.observableArrayList("Male", "Female"));
            TextField supplierField = new TextField(); //*****************
            TextField quantityField = new TextField();
            TextField priceField = new TextField();
            TextField codeField = new TextField();

            addProductLayout.add(new Label("Name:"), 0, 0);
            addProductLayout.add(nameField, 1, 0);
            addProductLayout.add(new Label("Size:"), 0, 1);
            addProductLayout.add(sizeField, 1, 1);
            addProductLayout.add(new Label("Color:"), 0, 2);
            addProductLayout.add(colorField, 1, 2);
            addProductLayout.add(new Label("Description:"), 0, 3);
            addProductLayout.add(descriptionField, 1, 3);
            addProductLayout.add(new Label("Gender:"), 0, 4);
            addProductLayout.add(genderComboBox, 1, 4);
            addProductLayout.add(new Label("Supplier id:"), 2, 0);
            addProductLayout.add(supplierField, 3, 0);
            addProductLayout.add(new Label("Quantity:"), 2, 1);
            addProductLayout.add(quantityField, 3, 1);
            addProductLayout.add(new Label("Price:"), 2, 2);
            addProductLayout.add(priceField, 3, 2);
            addProductLayout.add(new Label("Code:"), 2, 3);
            addProductLayout.add(codeField, 3, 3);

            Button submitProductButton = createStyledButton("Submit Product");
            submitProductButton.setOnAction(ev -> {
                try {
                    String name = nameField.getText();
                    String size = sizeField.getText();
                    String color = colorField.getText();
                    String description = descriptionField.getText();
                    String gender = genderComboBox.getValue();
                    int supplierid = Integer.parseInt(supplierField.getText());
                    int quantity = Integer.parseInt(quantityField.getText());
                    int price = Integer.parseInt(priceField.getText());
                    int code = Integer.parseInt(codeField.getText());

                    Product newProduct = new Product(name, size, color, description, gender, null, quantity, price, code);
                    admin.Add_product_to_supplier(code, supplierid);//*******************************************************
                    boolean productAdded = admin.addProduct(newProduct);

                    // Alert based on product addition result
                    Platform.runLater(() -> {
                        Alert alert = new Alert(productAdded ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                        alert.setTitle(productAdded ? "Product Added" : "Add Product Failed");
                        alert.setHeaderText(null);
                        alert.setContentText(productAdded ? "Product added successfully." : "The product code already exists.");
                        alert.showAndWait();
                    });
                } catch (NumberFormatException ex) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Input");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter valid numbers for quantity, price, and code.");
                        alert.showAndWait();
                    });
                }
            });
            addProductLayout.add(submitProductButton, 1, 9);

            Scene addProductScene = new Scene(addProductLayout, 600, 400);
            primaryStage.setScene(addProductScene);

            Button B1 = createStyledButton("Back to Main Menu");
            B1.setOnAction(ev -> primaryStage.setScene(mainScene));
            addProductLayout.add(B1, 0, 9);
        });
        productLayout.add(addProductButton, 0, 0);

        Button removeProductButton = createStyledButton("Remove Product");
        removeProductButton.setPrefSize(120, 20);
        removeProductButton.setOnAction(e -> {
            GridPane removeProductLayout = createGridPane(); // Assuming createGridPane() sets up your layout

            // Product Code Field
            Label productCodeLabel = new Label("Product Code:");
            TextField productCodeField = new TextField();
            removeProductLayout.add(productCodeLabel, 0, 0);
            removeProductLayout.add(productCodeField, 1, 0);

            // Submit Remove Button
            Button submitRemoveButton = new Button("Remove Product");
            submitRemoveButton.setOnAction(removeEvent -> {
                try {
                    int productCode = Integer.parseInt(productCodeField.getText());
                    boolean productRemoved = admin.removeProduct(productCode);
                    Platform.runLater(() -> {
                        Alert alert = new Alert(productRemoved ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
                        alert.setTitle(productRemoved ? "Product Removed" : "Product Not Found");
                        alert.setHeaderText(null);
                        alert.setContentText(productRemoved ? "Product with code " + productCode + " removed successfully." : "Product with code " + productCode + " not found.");
                        alert.showAndWait();
                    });
                } catch (NumberFormatException ex) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Input");
                        alert.setHeaderText(null);
                        alert.setContentText("Product code must be an integer.");
                        alert.showAndWait();
                    });
                }
            });
            removeProductLayout.add(submitRemoveButton, 1, 1);

            // Set the new scene
            Scene removeProductScene = new Scene(removeProductLayout, 600, 400);
            primaryStage.setScene(removeProductScene);

            Button B2 = createStyledButton("Back to Main Menu");
            B2.setOnAction(ev -> primaryStage.setScene(mainScene));
            removeProductLayout.add(B2, 0, 5);
        });
        productLayout.add(removeProductButton, 0, 1);

        Button displayProductsButton = createStyledButton("Display Product");
        displayProductsButton.setPrefSize(120, 20);
        displayProductsButton.setOnAction(e -> {
            ArrayList<Product> products = admin.displayProducts();
            GridPane displayProductLayout = createGridPane();
            displayProductLayout.setAlignment(Pos.TOP_CENTER);

            TextArea textArea = new TextArea();
            textArea.setEditable(false);

            if (products.isEmpty()) {
                textArea.setText("No Products available.");
            } else {
                StringBuilder productText = new StringBuilder();
                for (Product product : products) {
                    productText.append(product.toString()).append("\n");
                }
                textArea.setText(productText.toString());
            }

            displayProductLayout.add(textArea, 0, 0);

            Scene scene = new Scene(displayProductLayout, 600, 400);
            primaryStage.setScene(scene);

            Button B3 = createStyledButton("Back to Main Menu");
            B3.setOnAction(ev -> primaryStage.setScene(mainScene));
            displayProductLayout.add(B3, 0, 1);
        });
        productLayout.add(displayProductsButton, 0, 2);

        Button searchProductButton = createStyledButton("Search Product");
        searchProductButton.setPrefSize(120, 20);
        searchProductButton.setOnAction(e -> {
            GridPane searchProductLayout = createGridPane();

            Label lblProductCode = new Label("Enter Product Code:");
            TextField txtProductCode = new TextField();
            txtProductCode.setMaxWidth(100);
            searchProductLayout.add(lblProductCode, 0, 0);
            searchProductLayout.add(txtProductCode, 1, 0);

            Label lblProductName = new Label();
            Label lblProductSize = new Label();
            Label lblProductColor = new Label();
            Label lblProductDescription = new Label();
            Label lblProductGender = new Label();
            Label lblProductQuantity = new Label();
            Label lblProductPrice = new Label();

            searchProductLayout.add(lblProductName, 0, 2);
            searchProductLayout.add(lblProductSize, 0, 3);
            searchProductLayout.add(lblProductColor, 0, 4);
            searchProductLayout.add(lblProductDescription, 0, 5);
            searchProductLayout.add(lblProductGender, 1, 2);
            searchProductLayout.add(lblProductQuantity, 1, 3);
            searchProductLayout.add(lblProductPrice, 1, 4);

            Button btnSearchProduct = createStyledButton("Search Product");
            btnSearchProduct.setOnAction(searchEvent -> {
                try {
                    int productCode = Integer.parseInt(txtProductCode.getText());
                    Product foundProduct = admin.searchProduct(productCode);

                    Platform.runLater(() -> {
                        if (foundProduct != null) {
                            lblProductName.setText("Name: " + foundProduct.getName());
                            lblProductSize.setText("Size: " + foundProduct.getSize());
                            lblProductColor.setText("Color: " + foundProduct.getColor());
                            lblProductDescription.setText("Description: " + foundProduct.getDescription());
                            lblProductGender.setText("Gender: " + foundProduct.getGender());
                            lblProductQuantity.setText("Quantity: " + foundProduct.getQuantity());
                            lblProductPrice.setText("Price: " + foundProduct.getPrice());
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Search Result");
                            alert.setHeaderText(null);
                            alert.setContentText("No product with code " + productCode + " found.");
                            alert.showAndWait();
                        }
                    });
                } catch (NumberFormatException ex) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Input");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter a valid product code.");
                        alert.showAndWait();
                    });
                }
            });
            searchProductLayout.add(btnSearchProduct, 1, 1);
            Scene searchProductScene = new Scene(searchProductLayout, 600, 400);
            primaryStage.setScene(searchProductScene);

            Button B4 = createStyledButton("Back to Main Menu");
            B4.setOnAction(ev -> primaryStage.setScene(mainScene));
            searchProductLayout.add(B4, 0, 5);

        });
        productLayout.add(searchProductButton, 0, 3);

        Button editProductButton = createStyledButton("Edit Product");
        editProductButton.setPrefSize(120, 20);
        editProductButton.setOnAction(e -> {
            GridPane editProductLayout = createGridPane();

            Label productCodeLabel = new Label("Product Code:");
            TextField productCodeField = new TextField();
            editProductLayout.add(productCodeLabel, 0, 0);
            editProductLayout.add(productCodeField, 1, 0);

            Label optionLabel = new Label("Option:");
            ComboBox<String> optionComboBox = new ComboBox<>();
            optionComboBox.setItems(FXCollections.observableArrayList("Name", "Size", "Color", "Description", "Gender", "Quantity", "Price", "Code"));
            editProductLayout.add(optionLabel, 0, 1);
            editProductLayout.add(optionComboBox, 1, 1);

            Label newValueLabel = new Label("New Value:");
            TextField newValueField = new TextField();
            editProductLayout.add(newValueLabel, 0, 2);
            editProductLayout.add(newValueField, 1, 2);

            Button submitEditButton = createStyledButton("Submit Edit");
            submitEditButton.setOnAction(editEvent -> {
                try {
                    int code = Integer.parseInt(productCodeField.getText());
                    String selectedOption = optionComboBox.getValue();
                    int optionIndex = optionComboBox.getItems().indexOf(selectedOption) + 1;
                    String newValue = newValueField.getText();

                    if (optionIndex >= 1 && optionIndex <= 5) {
                        admin.editProductString(code, optionIndex, newValue);
                    } else if (optionIndex >= 6 && optionIndex <= 8) {
                        int newIntValue = Integer.parseInt(newValue);
                        admin.editProductInt(code, optionIndex - 5, newIntValue);
                    }

                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Product Edited");
                        alert.setHeaderText(null);
                        alert.setContentText("Product with code " + code + " has been successfully edited.");
                        alert.showAndWait();
                    });
                } catch (NumberFormatException ex) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Input");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter valid numbers for Product Code and Option.");
                        alert.showAndWait();
                    });
                } catch (IllegalArgumentException ex) {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid Option");
                        alert.setHeaderText(null);
                        alert.setContentText(ex.getMessage());
                        alert.showAndWait();
                    });
                }
            });
            editProductLayout.add(submitEditButton, 1, 3);

            Scene editProductScene = new Scene(editProductLayout, 600, 400);
            primaryStage.setScene(editProductScene);

            Button B5 = createStyledButton("Back to Main Menu");
            B5.setOnAction(ev -> primaryStage.setScene(mainScene));
            editProductLayout.add(B5, 0, 5);

        });
        productLayout.add(editProductButton, 0, 4);

        Button B = createStyledButton("Back to Main Menu");
        B.setOnAction(e -> primaryStage.setScene(mainScene));
        productLayout.add(B, 0, 5);

        primaryStage.setScene(new Scene(productLayout, 600, 400));
    }

    private void openReportsScene(Stage primaryStage) {

        Admin admin = new Admin(123, "admin", UserType.ADMIN, "kkk");
        // Create a TextArea object
        TextArea textArea = new TextArea();
        Label label = new Label("Order's History details");
        // Set the size of the TextArea
        textArea.setPrefSize(300, 200);
        // Set the style of the TextArea
        textArea.setStyle("-fx-font-family: Arial; -fx-font-size: 16px;");

        // Set the editable property of the TextArea to false
        textArea.setEditable(false);
//        textArea.setStyle("-fx-background-color:  grey; -fx-text-fill: blue;");
        textArea.setStyle("-fx-background-color:  #D3D3D3; -fx-text-fill: #00008B;");
        // Create a VBox as the root node
        VBox root = new VBox();
        VBox root2 = new VBox();
        root2.getChildren().add(label);

        // Add the TextArea to the VBox
        root.getChildren().add(textArea);
        textArea.setText(admin.get_no_of_orders_details().toString());

        Button backButton = new Button();
        backButton.setText("Back"); //set the text of the button
        backButton.setTranslateX(200); //set the x position of the button
        backButton.setTranslateY(100); //set the y position of the button
        GridPane grid = new GridPane();
//set the EventHandler object as the action of the back button
        backButton.setOnAction(e -> {
            Scene scene4 = new Scene(new StackPane(grid), 800, 800);

            primaryStage.setScene(scene4);

        });

        StackPane root1 = new StackPane();
        //add the TextArea and the Button objects to the StackPane object

        root1.getChildren().addAll(textArea, backButton);
        StackPane.setAlignment(textArea, Pos.TOP_CENTER);
        Scene scene = new Scene(root1, 500, 500);

        //------------------------------------------------------------------------------------------------------------------------------------------
        TextArea textArea2 = new TextArea();
        textArea2.setPrefSize(100, 100);
        textArea2.setStyle("-fx-font-family: Arial; -fx-font-size: 30px;");

        // Set the editable property of the TextArea to false
        textArea2.setEditable(false);
//        textArea.setStyle("-fx-background-color:  grey; -fx-text-fill: blue;");
        textArea2.setStyle("-fx-background-color:  #D3D3D3; -fx-text-fill: #00008B;");

        Button backButton1 = new Button();
        backButton1.setText("Back"); //set the text of the button
        backButton1.setTranslateX(200); //set the x position of the button
        backButton1.setTranslateY(100); //set the y position of the button

        backButton1.setOnAction(e -> {
            Scene scene4 = new Scene(new StackPane(grid), 800, 800);

            primaryStage.setScene(scene4);

        });
        // Create a VBox as the root node
        VBox root6 = new VBox();
        root6.getChildren().add(textArea2);
        textArea2.setText(admin.viewSupplierproducts().toString());
        StackPane root5 = new StackPane();
        root5.getChildren().addAll(textArea2, backButton1);
        Scene scene2 = new Scene(root5, 500, 300);
        //________________________________________________________________________________________________________________________________________
        Label Cashier_orders = new Label("No. of Orders per each Cashier and their details.");
        Button backButton8 = new Button();
        backButton8.setText("Back"); //set the text of the button
        backButton8.setTranslateX(200); //set the x position of the button
        backButton8.setTranslateY(100); //set the y position of the button

        backButton8.setOnAction(e -> {
            Scene scene4 = new Scene(new StackPane(grid), 800, 800);

            primaryStage.setScene(scene4);

        });
        StackPane r4 = new StackPane();
        r4.getChildren().addAll(Cashier_orders, backButton8);
        Scene scene6 = new Scene(r4, 500, 300);
        //________________________________________________________________________________________________________________________________________

        Label Customer_Orders = new Label("No. of Orders per each Customer and their details");
        Button backButton9 = new Button();
        backButton9.setText("Back"); //set the text of the button
        backButton9.setTranslateX(200); //set the x position of the button
        backButton9.setTranslateY(100); //set the y position of the button

        backButton9.setOnAction(e -> {
            Scene scene4 = new Scene(new StackPane(grid), 800, 800);

            primaryStage.setScene(scene4);

        });

        StackPane r5 = new StackPane();
        r5.getChildren().addAll(Customer_Orders, backButton9);
        Scene scene7 = new Scene(r5, 500, 300);
        //____________________________________________________________________________________________________________________________________________
        TextArea textArea3 = new TextArea();
        //Label label6 = new Label("Order's History details");
        // Set the size of the TextArea
        textArea3.setPrefSize(100, 100);

        // Set the style of the TextArea
        textArea3.setStyle("-fx-font-family: Arial; -fx-font-size: 16px;");

        // Set the editable property of the TextArea to false
        textArea3.setEditable(false);
//        textArea.setStyle("-fx-background-color:  grey; -fx-text-fill: blue;");
        textArea3.setStyle("-fx-background-color:  #D3D3D3; -fx-text-fill: #00008B;");

        Button backButton4 = new Button();
        backButton4.setText("Back"); //set the text of the button
        backButton4.setTranslateX(200); //set the x position of the button
        backButton4.setTranslateY(100); //set the y position of the button

        backButton4.setOnAction(e -> {
            Scene scene4 = new Scene(new StackPane(grid), 800, 800);

            primaryStage.setScene(scene4);

        });
        // Create a VBox as the root node
        VBox root10 = new VBox();
        root10.getChildren().add(textArea3);
        //textArea3.setText(c.ViewCart());
        textArea3.setText(admin.ViewOrders().toString());
        StackPane root11 = new StackPane();
        root11.getChildren().addAll(textArea3, backButton4);
        Scene scene5 = new Scene(root11, 500, 300);

        // ________________________________________________________________________Add Supplier_____________________________________________
        Label label8 = new Label("Add supplier");
        GridPane grid1 = new GridPane();
        grid1.setAlignment(javafx.geometry.Pos.CENTER);
        grid1.setHgap(10);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(25, 25, 25, 25));

        Label NameLabel = new Label("First Name:");
        TextField NameField = new TextField();

        Label NumberLabel = new Label("Number:");
        TextField NumberField = new TextField();

        Label emailLabel = new Label("email:");
        TextField emailField = new TextField();

        Label IdLabel = new Label("Id:");
        PasswordField IdField = new PasswordField();

        Button addSupplierButton = new Button("Add Supplier");
        Button eraseAllButton = new Button("Erase All");

        Button backButton2 = new Button();
        backButton2.setText("Back"); //set the text of the button
        backButton2.setTranslateX(200); //set the x position of the button
        backButton2.setTranslateY(100); //set the y position of the button

        backButton2.setOnAction(e -> {
            Scene scene4 = new Scene(new StackPane(grid), 800, 800);

            primaryStage.setScene(scene4);

        });

        grid1.add(NameLabel, 0, 0);
        grid1.add(NameField, 1, 0);
        grid1.add(NumberLabel, 0, 1);
        grid1.add(NumberField, 1, 1);
        grid1.add(emailLabel, 0, 2);
        grid1.add(emailField, 1, 2);
        grid1.add(IdLabel, 0, 3);
        grid1.add(IdField, 1, 3);
        grid1.add(addSupplierButton, 0, 8);
        grid1.add(eraseAllButton, 1, 8);

        //grid1.add(backButton2, 0, 9);
        addSupplierButton.setOnAction(e -> {
            String name = NameField.getText();
            String number = NumberField.getText();
            String email = emailField.getText();
            String id = IdField.getText();
            int id_supplier = Integer.parseInt(id);
            Supplier supplier = new Supplier(name, number, email, id_supplier);
            admin.AddSuppliers(supplier);

        });

        StackPane root7 = new StackPane();
        root7.getChildren().addAll(grid1, backButton2);
        Scene scene3 = new Scene(root7, 500, 300);
        //-------------------------------------------------------------------------------------------------------------------------------------------
        //GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
// ______________________________________________Supplier reports______________________________________________
        //create a Label object and set its initial text and position
        Label label2 = new Label("Supplier reports");
        label2.setStyle("-fx-background-color: #6699ff;");
        label2.setFont(new Font("Arial", 24));
        Button btn = new Button();
        btn.setText("get max order"); //set the text of the button
        // btn.setStyle("-fx-background-color: grey;");
        Label label1 = new Label();
        label1.setStyle("-fx-background-color:  white");
        btn.setOnAction(e -> label1.setText(admin.get_Supplier_Max_Orders()));

        Button btn1 = new Button();
        btn1.setText("get max revenue"); //set the text of the button
        Label label3 = new Label();
        label3.setStyle("-fx-background-color:  white");
        btn1.setOnAction(e -> label3.setText(admin.get_Supplier_Max_Revenue()));

        Button btn2 = new Button();
        btn2.setText(" No. of Orders per each Supplier and details of each Order."); //set the text of the button
        Label label4 = new Label();
        label4.setStyle("-fx-background-color:  white");
        btn2.setOnAction(e -> primaryStage.setScene(scene));

        Button btn3 = new Button();
        btn3.setText(" List of Suppliers and Pricing"); //set the text of the button
        Label label5 = new Label();
        label5.setStyle("-fx-background-color:  white");
        btn3.setOnAction(e -> primaryStage.setScene(scene2));

        Button btn4 = new Button();
        btn4.setText("Add Supplier"); //set the text of the button
        btn4.setOnAction(e -> primaryStage.setScene(scene3));
// ______________________________________________Product reports______________________________________________

        Label label6 = new Label("Product reports");
        label6.setStyle("-fx-background-color: #6699ff;");
        label6.setFont(new Font("Arial", 24));
        Label Date = new Label("format dd/MM/yyyy");

        TextField StartDate = new TextField();
        StartDate.setPromptText("Start Date");
        TextField EndDate = new TextField();
        EndDate.setPromptText("End Date");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Button btn5 = new Button();
        btn5.setText("get  No. of Pieces sold over a specific period of time"); //set the text of the button
        Label label7 = new Label();
        label7.setStyle("-fx-background-color:  white");

        btn5.setOnAction(e -> {

            String startDateText = StartDate.getText();
            String endDateText = EndDate.getText();
            LocalDate startDate = LocalDate.parse(startDateText, formatter);
            LocalDate endDate = LocalDate.parse(endDateText, formatter);
            String piece_sold = Integer.toString(admin.no_of_pieces_sold(startDate, endDate));
            label7.setText(piece_sold);

        });

        Button btn6 = new Button();
        btn6.setText("getBest seller Product "); //set the text of the button
        Label label9 = new Label();
        label9.setStyle("-fx-background-color:  white");
        btn6.setOnAction(e -> label9.setText(admin.getBestSellerProduct()));

        Button btn7 = new Button();
        btn7.setText("get  Most revenue Product "); //set the text of the button
        Label label10 = new Label();
        label10.setStyle("-fx-background-color:  white");
        btn7.setOnAction(e -> label10.setText(admin.getProductWithMaxRevenue()));

        //Cashier reports___________________________________________________
        Label label11 = new Label("Cashier reports");
        label11.setStyle("-fx-background-color: #6699ff;");
        label11.setFont(new Font("Arial", 24));

        Button btn8 = new Button();
        btn8.setText("get max order"); //set the text of the button
        // btn.setStyle("-fx-background-color: grey;");
        Label label12 = new Label();
        label12.setStyle("-fx-background-color:  white");
        //btn8.setOnAction(e -> label12.setText());

        Button btn9 = new Button();
        btn9.setText("get max revenue"); //set the text of the button
        Label label13 = new Label();
        label13.setStyle("-fx-background-color:  white");
        //btn9.setOnAction(e -> label13.setText());

        Button btn10 = new Button();
        btn10.setText(" No. of Orders per each Cashier and details of each Order."); //set the text of the button
        Label label14 = new Label();
        label14.setStyle("-fx-background-color:  white");
        btn10.setOnAction(e -> primaryStage.setScene(scene6));

//Customer Reports____________________________________________________________________
        Label label15 = new Label("Customer reports");
        label15.setStyle("-fx-background-color: #6699ff;");
        label15.setFont(new Font("Arial", 24));

        Button btn11 = new Button();
        btn11.setText("get max order"); //set the text of the button
        // btn.setStyle("-fx-background-color: grey;");
        Label label16 = new Label();
        label16.setStyle("-fx-background-color:  white");
        btn11.setOnAction(e -> label16.setText(admin.getCustomerWithMaxOrders()));

        Button btn12 = new Button();
        btn12.setText("get max revenue"); //set the text of the button
        Label label17 = new Label();
        label17.setStyle("-fx-background-color:  white");
        btn12.setOnAction(e -> label17.setText(admin.getCustomerWithMaxRevenue()));

        Button btn13 = new Button();
        btn13.setText(" No. of Orders per each Customer and details of each Order."); //set the text of the button
        Label label18 = new Label();
        label18.setStyle("-fx-background-color:  white");
        btn13.setOnAction(e -> primaryStage.setScene(scene7));
//order reports_______________________________________________________________________________

        Label label19 = new Label("Orders reports");
        label19.setStyle("-fx-background-color: #6699ff;");
        label19.setFont(new Font("Arial", 24));

        Button btn14 = new Button();
        btn14.setText("View Order’s details"); //set the text of the button
        // btn.setStyle("-fx-background-color: grey;");
        Label label20 = new Label();
        label20.setStyle("-fx-background-color:  white");
        btn14.setOnAction(e -> primaryStage.setScene(scene5));

        TextField Start = new TextField();
        Start.setPromptText("Start Date");
        TextField End = new TextField();
        End.setPromptText("End Date");

        Button btn15 = new Button();
        btn15.setText("Total Revenue "); //set the text of the button

        Button btn16 = new Button();
        btn16.setText("Avarege Revenue "); //set the text of the button

        Label label21 = new Label();
        label21.setStyle("-fx-background-color:  white");

        btn15.setOnAction(e -> {

            String startDateText = Start.getText();
            String endDateText = End.getText();
            LocalDate startDate = LocalDate.parse(startDateText, formatter);
            LocalDate endDate = LocalDate.parse(endDateText, formatter);
            String piece_sold = Double.toString(admin.calculateTotalRevenueOverTime(startDate, endDate));
            label21.setText(piece_sold);

        });

        btn16.setOnAction(e -> {

            String startDateText = Start.getText();
            String endDateText = End.getText();
            LocalDate startDate = LocalDate.parse(startDateText, formatter);
            LocalDate endDate = LocalDate.parse(endDateText, formatter);
            String piece_sold = Double.toString(admin.calculateAverageRevenue(startDate, endDate));
            label21.setText(piece_sold);

        });

//__________________________________________________________________________________________________________________
        Button backButton3 = new Button();
        backButton3.setText("Back"); //set the text of the button
        backButton3.setTranslateX(200); //set the x position of the button
        backButton3.setTranslateY(100); //set the y position of the button

        backButton3.setOnAction(e -> {
            //   primaryStage.setScene(mainScene));
        });

//________________________________________________________
        //set the EventHandler object as the action of the Button object
        //btn.setOnAction(event);
        grid.add(label2, 0, 0);
        grid.add(btn, 0, 1);
        grid.add(label1, 1, 1);
        grid.add(btn1, 0, 2);
        grid.add(label3, 1, 2);
        grid.add(btn2, 0, 3);
        grid.add(label4, 1, 3);
        grid.add(btn3, 0, 4);
        grid.add(label5, 1, 4);
        grid.add(btn4, 0, 5);
        grid.add(label6, 0, 6);
        grid.add(Date, 1, 6);
        grid.add(btn5, 0, 7);
        grid.add(label7, 3, 7);

        grid.add(StartDate, 1, 7);
        grid.add(EndDate, 2, 7);

        grid.add(btn6, 0, 8);
        grid.add(label9, 1, 8);
        grid.add(btn7, 0, 9);
        grid.add(label10, 1, 9);
        grid.add(label11, 0, 10);

        grid.add(btn8, 0, 11);
        grid.add(label12, 1, 11);

        grid.add(btn9, 0, 12);
        grid.add(label13, 1, 12);

        grid.add(btn10, 0, 13);
        grid.add(label14, 1, 13);

        grid.add(label15, 0, 14);

        grid.add(btn11, 0, 15);
        grid.add(label16, 1, 15);

        grid.add(btn12, 0, 16);
        grid.add(label17, 1, 16);

        grid.add(btn13, 0, 17);
        grid.add(label18, 1, 17);

        grid.add(label19, 0, 18);

        grid.add(btn14, 0, 19);
        grid.add(label20, 1, 19);

        grid.add(btn15, 0, 20);
        grid.add(btn16, 1, 20);

        grid.add(label21, 4, 20);

        grid.add(Start, 2, 20);
        grid.add(End, 3, 20);

        grid.add(backButton3, 2, 16);

        grid.setAlignment(Pos.TOP_LEFT); //set the alignment of the grid to center
        grid.setHalignment(label2, HPos.LEFT); //set the horizontal alignment of label2 to left
        grid.setValignment(btn, VPos.BOTTOM);
        //create a StackPane object
        StackPane root3 = new StackPane();
        root3.getChildren().addAll(grid);
        Scene main = new Scene(root3, 800, 800);
        scene.setFill(Color.BLUE);
        primaryStage.setScene(main);
        primaryStage.setTitle("TextArea Example");
        primaryStage.show();
        // return main;

    }

    private Button maincreateStyledButton(String text) {//*********************************************
        Button button = new Button(text);
        String buttonColor = "#1E40AF"; // Define the button color

        button.setStyle(
                "-fx-background-color: " + buttonColor + "; "
                + // Use the same color for background
                "-fx-text-fill: #ffffff; "
                + // White text
                "-fx-font-weight: bold; "
                + "-fx-padding: 10 20; "
                + "-fx-border-color: " + buttonColor + "; "
                + // Use the same color for the border
                "-fx-border-width: 2px; "
                + "-fx-border-radius: 5px; "
                + "-fx-background-radius: 5px;"
        );

        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: #2349C0; "
                + // Slightly lighter blue for hover
                "-fx-text-fill: #ffffff; "
                + "-fx-font-weight: bold; "
                + "-fx-padding: 10 20; "
                + "-fx-border-color: #2349C0; "
                + // Use the same color for hover border
                "-fx-border-width: 2px; "
                + "-fx-border-radius: 5px; "
                + "-fx-background-radius: 5px;"
        ));

        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: " + buttonColor + "; "
                + // Revert to the original color
                "-fx-text-fill: #ffffff; "
                + "-fx-font-weight: bold; "
                + "-fx-padding: 10 20; "
                + "-fx-border-color: " + buttonColor + "; "
                + // Use the same color for the border
                "-fx-border-width: 2px; "
                + "-fx-border-radius: 5px; "
                + "-fx-background-radius: 5px;"
        ));

        return button;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        String buttonColor = "#B0B0B0"; // Define the brighter gray button color
        String hoverColor = "#D3D3D3"; // Define the hover color

        button.setStyle(
                "-fx-background-color: " + buttonColor + "; "
                + // Use the brighter gray color for background
                "-fx-text-fill: #ffffff; "
                + // White text
                "-fx-font-weight: bold; "
                + "-fx-padding: 3 6; "
                + // Smaller padding
                "-fx-border-color: " + buttonColor + "; "
                + // Use the brighter gray color for the border
                "-fx-border-width: 2px; "
                + "-fx-border-radius: 5px; "
                + "-fx-background-radius: 5px;"
        );

        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: " + hoverColor + "; "
                + // Slightly lighter gray for hover
                "-fx-text-fill: #ffffff; "
                + "-fx-font-weight: bold; "
                + "-fx-padding: 3 6; "
                + // Smaller padding
                "-fx-border-color: " + hoverColor + "; "
                + // Use the hover color for hover border
                "-fx-border-width: 2px; "
                + "-fx-border-radius: 5px; "
                + "-fx-background-radius: 5px;"
        ));

        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: " + buttonColor + "; "
                + // Revert to the original brighter gray color
                "-fx-text-fill: #ffffff; "
                + "-fx-font-weight: bold; "
                + "-fx-padding: 3 6; "
                + // Smaller padding
                "-fx-border-color: " + buttonColor + "; "
                + // Use the brighter gray color for the border
                "-fx-border-width: 2px; "
                + "-fx-border-radius: 5px; "
                + "-fx-background-radius: 5px;"
        ));

        return button;
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT); // Center alignment for the GridPane
        gridPane.setPadding(new Insets(40));
        gridPane.setVgap(20);
        gridPane.setHgap(10);
        // Load the background image
        Image backgroundImage = new Image("file:C:\\Users\\hp\\Downloads\\colorful-shopping-bags.jpg");
        // Set the width and height 
        double desiredWidth = 600;
        double desiredHeight = 400;
        // Create a BackgroundSize with the desired dimensions
        BackgroundSize backgroundSize = new BackgroundSize(desiredWidth, desiredHeight, false, false, false, false);
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundImg);
        gridPane.setBackground(background);

        return gridPane;
    }

    private void cashierDashboard(Stage primaryStage) {
        // Create the place order button
        ToggleButton placeOrderButton = new ToggleButton("Place Order");
        placeOrderButton.setPrefWidth(150);
        placeOrderButton.setPrefHeight(55);
        placeOrderButton.setStyle("-fx-font-size: 16;");
        placeOrderButton.setOnAction(event -> {
            openPlaceOrderScene(primaryStage);
        });

        // Create the cancel order button
        ToggleButton cancelOrderButton = new ToggleButton("Cancel Order");
        cancelOrderButton.setPrefWidth(150);
        cancelOrderButton.setPrefHeight(55);
        cancelOrderButton.setStyle("-fx-font-size: 16;");
        cancelOrderButton.setOnAction(event -> {
            cancelOrderScene(primaryStage);
        });
        // Load the background image
        Image backgroundImage = new Image("file:///C:/Users/Administrator/Desktop/oopProject/pic.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO,true, true, true, true);
        BackgroundImage bgImage = new BackgroundImage(backgroundImage,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, backgroundSize);

        // Create a GridPane and set the background
        GridPane gridPane = new GridPane();
        gridPane.setBackground(new Background(bgImage));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));

        // Add buttons to the GridPane
        gridPane.add(placeOrderButton, 0, 0);
        gridPane.add(cancelOrderButton, 0, 1);

        // Create the scene and add the GridPane
        Scene scene = new Scene(gridPane, 600, 400);
        primaryStage.setTitle("Cashier Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openPlaceOrderScene(Stage primaryStage) {
        Stage inputStage = new Stage();

        // Create input fields and labels
        Label userIdLabel = new Label("Enter customer userId:");
        TextField userIdField = new TextField();
        userIdLabel.setTextFill(Color.BLACK);

        Label userNameLabel = new Label("Enter customer userName:");
        TextField userNameField = new TextField();
        userNameLabel.setTextFill(Color.BLACK);

        Label userPasswordLabel = new Label("Enter customer userPassword:");
        PasswordField userPasswordField = new PasswordField();
        userPasswordLabel.setTextFill(Color.BLACK);

        Label orderIdLabel = new Label("Enter order Id:");
        TextField orderIdField = new TextField();
        orderIdLabel.setTextFill(Color.BLACK);

        ToggleButton submitButton = new ToggleButton("Submit");
        // [Submit button action]
        submitButton.setOnAction(event -> {
            try {
                int userId = Integer.parseInt(userIdField.getText());
                String userName = userNameField.getText();
                String userPassword = userPasswordField.getText();
                int orderId = Integer.parseInt(orderIdField.getText());

                // Create customer and order objects
                customer = new Customer(userId, userName, UserType.CUSTOMER, userPassword);
                order = new Order(orderId, customer);

                inputStage.close();
                primaryStage.toFront();
                showMenuButtons(primaryStage);
            } catch (NumberFormatException e) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid numeric values.");
                alert.showAndWait();

            }
        });

        Text welcomeText = new Text("Welcome, Please Enter The Following Information");
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        welcomeText.setFill(Color.BLACK);

        // Creating a GridPane layout
        GridPane inputGrid = new GridPane();
        inputGrid.setAlignment(Pos.CENTER);
        inputGrid.setVgap(10);
        inputGrid.setHgap(10);
        inputGrid.setPadding(new Insets(50, 50, 50, 50));

        // Adding elements to the GridPane
        inputGrid.add(welcomeText, 0, 0, 2, 1);
        inputGrid.add(userIdLabel, 0, 1);
        inputGrid.add(userIdField, 1, 1);
        inputGrid.add(userNameLabel, 0, 2);
        inputGrid.add(userNameField, 1, 2);
        inputGrid.add(userPasswordLabel, 0, 4);
        inputGrid.add(userPasswordField, 1, 4);
        inputGrid.add(orderIdLabel, 0, 3);
        inputGrid.add(orderIdField, 1, 3);
        inputGrid.add(submitButton, 0, 5, 2, 1);

        // Styling
        userIdLabel.setStyle("-fx-font-size: 16");
        userIdField.setStyle("-fx-font-size: 14");
        userNameLabel.setStyle("-fx-font-size: 16");
        userNameField.setStyle("-fx-font-size: 14");
        userPasswordLabel.setStyle("-fx-font-size: 16");
        userPasswordField.setStyle("-fx-font-size: 14");
        orderIdLabel.setStyle("-fx-font-size: 16");
        orderIdField.setStyle("-fx-font-size: 14");
        submitButton.setStyle("-fx-font-size: 14");

        Scene inputScene = new Scene(inputGrid, 600, 400);
        inputScene.setFill(Color.LIGHTGRAY);

        inputStage.setScene(inputScene);
        inputStage.setTitle("Place Order Input");
        inputStage.show();

        // Hide the main stage while the input stage is open
        primaryStage.hide();
    }

    private void cancelOrderScene(Stage primaryStage) {
        Stage inputStage = new Stage();

        // Create input fields and labels
        Label userIdLabel = new Label("Enter customer userId:");
        TextField userIdField = new TextField();
        userIdLabel.setTextFill(Color.BLACK);

        Label userNameLabel = new Label("Enter customer userName:");
        TextField userNameField = new TextField();
        userNameLabel.setTextFill(Color.BLACK);

        Label userPasswordLabel = new Label("Enter customer userPassword:");
        PasswordField userPasswordField = new PasswordField();
        userPasswordLabel.setTextFill(Color.BLACK);

        Label orderIdLabel = new Label("Enter order Id:");
        TextField orderIdField = new TextField();
        orderIdLabel.setTextFill(Color.BLACK);

        ToggleButton submitButton = new ToggleButton("Submit");
        // [Submit button action code]
        submitButton.setOnAction(event -> {
            try {
                int userId = Integer.parseInt(userIdField.getText());
                String userName = userNameField.getText();
                String userPassword = userPasswordField.getText();
                int orderId = Integer.parseInt(orderIdField.getText());

                // Create customer and order objects
                customer = new Customer(userId, userName, UserType.CUSTOMER, userPassword);
                order = new Order(orderId, customer);
                
                cashier.cancelCart(orderId, customer);
                
                inputStage.close();
                primaryStage.toFront();
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid numeric values.");
                alert.showAndWait();
            }
        });

        Text welcomeText = new Text("Welcome, Please Enter The Following Information");
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        welcomeText.setFill(Color.BLACK);

        // Creating a GridPane layout
        GridPane inputGrid = new GridPane();
        inputGrid.setAlignment(Pos.CENTER);
        inputGrid.setVgap(10);
        inputGrid.setHgap(10);
        inputGrid.setPadding(new Insets(50, 50, 50, 50));

        // Adding elements to the GridPane
        inputGrid.add(welcomeText, 0, 0, 2, 1);
        inputGrid.add(userIdLabel, 0, 1);
        inputGrid.add(userIdField, 1, 1);
        inputGrid.add(userNameLabel, 0, 2);
        inputGrid.add(userNameField, 1, 2);
        inputGrid.add(userPasswordLabel, 0, 4);
        inputGrid.add(userPasswordField, 1, 4);
        inputGrid.add(orderIdLabel, 0, 3);
        inputGrid.add(orderIdField, 1, 3);
        inputGrid.add(submitButton, 0, 5, 2, 1);

        // Styling
        userIdLabel.setStyle("-fx-font-size: 16");
        userIdField.setStyle("-fx-font-size: 14");
        userNameLabel.setStyle("-fx-font-size: 16");
        userNameField.setStyle("-fx-font-size: 14");
        orderIdLabel.setStyle("-fx-font-size: 16");
        orderIdField.setStyle("-fx-font-size: 14");
        submitButton.setStyle("-fx-font-size: 14");
        userPasswordLabel.setStyle("-fx-font-size: 16");
        userPasswordField.setStyle("-fx-font-size: 14");

        Scene inputScene = new Scene(inputGrid, 600, 400);
        inputScene.setFill(Color.LIGHTGRAY);

        inputStage.setScene(inputScene);
        inputStage.setTitle("Place Order Input");
        inputStage.show();

        // Hide the main stage while the input stage is open
        primaryStage.hide();

    }

    private void showMenuButtons(Stage primaryStage) {
        Text chooseText = new Text("Choose Which Function You Want");
        chooseText.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        chooseText.setFill(Color.BLACK);

        ToggleButton createCartButton = new ToggleButton("Create New Cart");
        createCartButton.setStyle("-fx-font-size: 16");

        ToggleButton addProductButton = new ToggleButton("Add Product To Cart");
        addProductButton.setStyle("-fx-font-size: 16");

        ToggleButton removeProductButton = new ToggleButton("Remove Product from Cart");
        removeProductButton.setStyle("-fx-font-size: 16");

        ToggleButton viewProductsButton = new ToggleButton("View Products in Cart");
        viewProductsButton.setStyle("-fx-font-size: 16");

        ToggleButton calculatePaymentButton = new ToggleButton("Calculate Total Payment");
        calculatePaymentButton.setStyle("-fx-font-size: 16");

        ToggleButton placeOrderButton = new ToggleButton("Place Order");
        placeOrderButton.setStyle("-fx-font-size: 16");

        ToggleButton returnToMenuButton2 = new ToggleButton("Return to Cashier Menu");
        returnToMenuButton2.setStyle("-fx-font-size: 16");

        if (order == null) {
            order = new Order(); // or initialize it using the appropriate constructor
        }

        // Set the event handlers for each button
        createCartButton.setOnAction(event -> {
            if (order != null) {
                order.setOrderDate(order.getDate());

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Cart Created");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Cart created successfully");
                successAlert.showAndWait();
            } else {
                // Handle the case where order is not initialized
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Error: Order is not initialized");
                errorAlert.showAndWait();
            }
        });

        addProductButton.setOnAction(event -> {
            // Check and initialize order
            if (order == null) {
                order = new Order(); // or initialize it
            }

            try {
                // Create a label to display available products
                VBox productListVBox = new VBox();
                Label productsLabel = new Label("Available Products:");
                productListVBox.getChildren().add(productsLabel);
                productsLabel.setStyle("-fx-font-size: 16");
                productsLabel.setUnderline(true);

                ArrayList<Product> productList = Product.readproductfromfile();
                for (Product product : productList) {
                    Label productLabel = new Label("Product: " + product.getName() + "| Code: " + product.getCode() + "| Price: " + product.getPrice() +"Size: "+product.getSize()+ "| Gender: " + product.getGender() + ")");
                    productListVBox.getChildren().add(productLabel);
                }

                // Create input fields and labels
                Label productCodeLabel = new Label("Enter product code:");
                TextField productCodeField = new TextField();
                productCodeLabel.setPadding(new Insets(20, 10, 10, 10));
                productCodeLabel.setTextFill(Color.BLACK);

                Label productQuantityLabel = new Label("Enter product quantity:");
                TextField productQuantityField = new TextField();
                productQuantityLabel.setPadding(new Insets(20, 10, 10, 10));
                productQuantityLabel.setTextFill(Color.BLACK);

                HBox productCodeHBox = new HBox(productCodeLabel, productCodeField);
                productCodeHBox.setAlignment(Pos.CENTER);
                productCodeHBox.setSpacing(10);

                HBox productQuantityHBox = new HBox(productQuantityLabel, productQuantityField);
                productQuantityHBox.setAlignment(Pos.CENTER);
                productQuantityHBox.setSpacing(10);

                ToggleButton submitButton = new ToggleButton("Submit");
                submitButton.setPadding(new Insets(10));

                // Set the event handler for the submit button
                submitButton.setOnAction(submitEvent -> {
                    try {
                        //Get data from input fields
                        int productCode = Integer.parseInt(productCodeField.getText());
                        int productQuantity = Integer.parseInt(productQuantityField.getText());

                        // Call the addProductToCart method
                        cashier.addProductToCart(productCode, order, productQuantity);

                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Invalid input");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter valid numeric values.");
                        alert.showAndWait();

                    }
                });
                ToggleButton returnButton = createReturnButton(primaryStage);

                // Create a VBox for the submit button and return button with spacing
                VBox buttonContainer = new VBox(10, submitButton, returnButton);
                buttonContainer.setAlignment(Pos.CENTER);

                // Create a VBox for the entire input form
                VBox inputRoot = new VBox(10, productListVBox, productCodeHBox, productQuantityHBox, buttonContainer);
                inputRoot.setAlignment(Pos.CENTER);
                inputRoot.setSpacing(10);

                Scene inputScene = new Scene(inputRoot, 650, 400);
                inputScene.setFill(Color.LIGHTGRAY);

                // Create a new stage for the input
                Stage mainStage = (Stage) addProductButton.getScene().getWindow();
                mainStage.hide();

                Stage inputStage = new Stage();
                inputStage.setScene(inputScene);
                inputStage.setTitle("Add Product to Cart");
                inputStage.show();

            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid input");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid numeric values.");
                alert.showAndWait();
            }

        });//okay

        removeProductButton.setOnAction(event -> {
            // Create a label to display available products
            VBox productListVBox = new VBox();
            Label productsLabel = new Label("Available Products:");
            productListVBox.getChildren().add(productsLabel);
            productsLabel.setStyle("-fx-font-size: 16");
            productsLabel.setUnderline(true);

            for (Map.Entry<Integer, Integer> entry : order.productCart.entrySet()) {
                int productCode = entry.getKey();
                int quantity = entry.getValue();

                ArrayList<Product> productList = Product.readproductfromfile();
                for (Product product : productList) {
                    if (product.getCode() == productCode) {
                        Label productLabel = new Label("Product Code: " + productCode + " | Product Name: " + product.getName() + " | Price: " + product.getPrice() + "Size: "+product.getSize()+" | Quantity: " + quantity);
                        productListVBox.getChildren().add(productLabel);
                    }
                }
            }

// Create input fields and labels
            Label productCodeLabel = new Label("Enter product code:");
            TextField productCodeField = new TextField();
            productCodeLabel.setPadding(new Insets(20, 10, 10, 10));
            productCodeLabel.setTextFill(Color.BLACK);

            ToggleButton submitButton = new ToggleButton("Submit");
            submitButton.setPadding(new Insets(10));

// Set the event handler for the submit button
            submitButton.setOnAction(submitEvent -> {
                try {
                    // Get data from input field
                    int productCode = Integer.parseInt(productCodeField.getText());

                    // Call the removeProductFromCart method
                    cashier.removeProductFromCart(productCode, order);

                    // Close the input stage
                    Stage inputStage = (Stage) submitButton.getScene().getWindow();
                    inputStage.close();

                    // Show the menu buttons
                    showMenuButtons(primaryStage);

                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Invalid input");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter valid numeric values.");
                    alert.showAndWait();
                }
            });

// Create the return button
            ToggleButton returnButton = createReturnButton(primaryStage);

// Create a VBox for the submit button and return button with spacing
            VBox submitButtonVBox = new VBox(10, submitButton, returnButton);
            submitButtonVBox.setAlignment(Pos.CENTER);

// Create an HBox for product code
            HBox productCodeHBox = new HBox(productCodeLabel, productCodeField);
            productCodeHBox.setAlignment(Pos.CENTER);
            productCodeHBox.setSpacing(10);

// Create a VBox for the available products and input fields
            VBox inputRoot = new VBox(productListVBox, productCodeHBox, submitButtonVBox);
            inputRoot.setAlignment(Pos.TOP_LEFT);
            inputRoot.setSpacing(10);

            Scene inputScene = new Scene(inputRoot, 650, 400);

// Create a new stage for the input
            Stage mainStage = (Stage) addProductButton.getScene().getWindow();
            mainStage.hide();

            Stage inputStage = new Stage();
            inputStage.setScene(inputScene);
            inputStage.setTitle("Remove Product from Cart");
            inputStage.show();
        });//okay

        viewProductsButton.setOnAction(event -> {
            try {
                if (order.productCart == null || order.productCart.isEmpty()) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Cart Empty");
                    alert.setHeaderText(null);
                    alert.setContentText("No products found in the cart.");
                    alert.showAndWait();
                } else {
                    ArrayList<Order> ProductCart = order.readProductCartfromfile();
                    if (ProductCart.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Product List Empty");
                        alert.setHeaderText(null);
                        alert.setContentText("No products available.");
                        alert.showAndWait();
                        return;
                    }

                    Stage cartInfoStage = new Stage();
                    cartInfoStage.setTitle("Products in Cart");

                    // Create a TextArea to display the cart information
                    TextArea cartInfoTextArea = new TextArea();
                    cartInfoTextArea.setEditable(false);

                    cartInfoTextArea.appendText(order.toString());

                    // Create a VBox to hold the TextArea
                    VBox vbox = new VBox(cartInfoTextArea);
                    vbox.setPadding(new Insets(10));

                    // Create a Scene and set it to the Stage
                    Scene scene = new Scene(vbox, 400, 400);
                    cartInfoStage.setScene(scene);

                    // Show the Stage
                    cartInfoStage.show();
                }
            } catch (Exception e) {
                System.out.println("Error" + "An error occurred: " + e.getMessage());
            }
        });//okay

        calculatePaymentButton.setOnAction(event -> {
            try {
                // order.getTotalAmount();
                cashier.calculateTotalPayment(order);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Total Payment");
                alert.setHeaderText(null);
                alert.setContentText("Total payment: " + order.getTotalAmount());
                alert.showAndWait();

            } catch (Exception e) {
                System.out.println("Error" + "An error occurred: " + e.getMessage());
            }
        });//okay

        placeOrderButton.setOnAction(event -> {
            try {
                cashier.placeOrder(order, customer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Order Placed");
                alert.setHeaderText(null);
                alert.setContentText("Order placed successfully");
                alert.showAndWait();

            } catch (Exception e) {
                System.out.println("Error" + "An error occurred: " + e.getMessage());
            }
        });//okay

        returnToMenuButton2.setOnAction(event -> {
            // Close the current stage
            Stage currentStage = (Stage) returnToMenuButton2.getScene().getWindow();
            currentStage.close();

            // Open the Cashier Dashboard scene
            cashierDashboard(primaryStage);
        });//okay

        VBox optionsLayout = new VBox(15, chooseText, createCartButton, addProductButton, removeProductButton, placeOrderButton, calculatePaymentButton, viewProductsButton, returnToMenuButton2);
        optionsLayout.setAlignment(Pos.CENTER);
        optionsLayout.setSpacing(20);

        Scene optionsScene = new Scene(optionsLayout, 650, 550);

        // Set the scene for the options stage
        Stage optionsStage = new Stage();
        optionsStage.setScene(optionsScene);
        optionsStage.setTitle("Placing an Order");
        optionsStage.show();
    }

    private ToggleButton createReturnButton(Stage primaryStage) {
        ToggleButton returnButton = new ToggleButton("Return to Menu");
        returnButton.setOnAction(returnEvent -> {
            showMenuButtons(primaryStage);
            Stage inputStage = (Stage) returnButton.getScene().getWindow();
            inputStage.close();
        });
        return returnButton;
    }

    private void customerDashboard() {

        // Create a VBox to hold the elements
        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);

        Image i = new Image("file:C:\\Users\\hp\\Downloads\\shopping-bag.png");
        ImageView imageView = new ImageView(i);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        HBox imageBox = new HBox(imageView);
        imageBox.setAlignment(Pos.CENTER);

        // Buttons for user choice
        Button rateOrderButton = new Button("Rate Your Order");
        Button viewPreviousOrdersButton = new Button("View Previous Orders");
        Button viewCartButton = new Button("View Cart");

        // Set actions for the buttons
        rateOrderButton.setOnAction(e -> showRatingScene());
        viewPreviousOrdersButton.setOnAction(e -> showPreviousOrdersScene());
        viewCartButton.setOnAction(e -> viewCart());

        // Add buttons to the root VBox
        root.getChildren().addAll(imageBox, rateOrderButton, viewPreviousOrdersButton, viewCartButton);

        // Set up the scene and show the stage
        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("Choice Selection");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showRatingScene() {
        VBox ratingRoot = new VBox();
        ratingRoot.setPadding(new Insets(20));
        ratingRoot.setSpacing(20);
        ratingRoot.setAlignment(Pos.CENTER);

        Image icon = new Image("file:C:\\Users\\hp\\Downloads\\614dfaed9cdcb08628f3b1cc_5-star-rating-p-800.jpeg");
        ImageView imageView = new ImageView(icon);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        HBox imageBox = new HBox(imageView);
        imageBox.setAlignment(Pos.CENTER);

        Label label = new Label("Rate Your Order:");
        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton rb1 = new RadioButton("1");
        rb1.setToggleGroup(toggleGroup);
        RadioButton rb2 = new RadioButton("2");
        rb2.setToggleGroup(toggleGroup);
        RadioButton rb3 = new RadioButton("3");
        rb3.setToggleGroup(toggleGroup);
        RadioButton rb4 = new RadioButton("4");
        rb4.setToggleGroup(toggleGroup);
        RadioButton rb5 = new RadioButton("5");
        rb5.setToggleGroup(toggleGroup);

        HBox ratingBox = new HBox(10, rb1, rb2, rb3, rb4, rb5);

        Label customerIdLabel = new Label("Customer ID:");
        TextField customerIdField = new TextField();
        customerIdField.setPrefWidth(200);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(200);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(200);

        Label orderIdLabel = new Label("Order Id:");
        TextField orderIdField = new TextField();
        orderIdField.setPrefWidth(200);

        Button submitButton = new Button("Submit Rating");
        submitButton.setPrefWidth(200);
        submitButton.setOnAction(e -> {
            if (toggleGroup.getSelectedToggle() != null) {
                try {
                    int customerID = Integer.parseInt(customerIdField.getText());
                    String username = usernameField.getText();
                    String password = passwordField.getText();
                    int orderID = Integer.parseInt(orderIdField.getText());
                    RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
                    int selectedRating = Integer.parseInt(selectedRadioButton.getText());

                    Customer c = new Customer(customerID, username, UserType.CUSTOMER, password);
                    c.rateOrder(orderID, selectedRating);

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Rating Submitted");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Your rating has been successfully submitted.");
                    successAlert.showAndWait();

                } catch (NumberFormatException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Input Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter valid numeric values for Customer ID and Order ID");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please select a rating");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setPrefWidth(200);
        backButton.setOnAction(e -> showMainSelectionScene());

        ratingRoot.getChildren().addAll(imageBox, label, ratingBox, customerIdLabel, customerIdField,
                usernameLabel, usernameField, passwordLabel, passwordField,
                orderIdLabel, orderIdField, submitButton, backButton);

        Scene ratingScene = new Scene(new BorderPane(ratingRoot, null, null, null, null), 800, 700);
        primaryStage.setTitle("Rate Your Order");
        primaryStage.setScene(ratingScene);
        primaryStage.show();
    }

    private void showPreviousOrdersScene() {

        VBox ordersRoot = new VBox();
        ordersRoot.setPadding(new Insets(20));
        ordersRoot.setSpacing(20);
        ordersRoot.setAlignment(Pos.CENTER);

        Image i = new Image("file:C:\\Users\\hp\\Downloads\\download (1).jpeg");
        ImageView imageView = new ImageView(i);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        HBox imageBox = new HBox(imageView);
        imageBox.setAlignment(Pos.CENTER);
        // TextField for customer name
        TextField nameField = new TextField();
        nameField.setPromptText("Enter Customer Name");

        // TextField for customer ID
        TextField idField = new TextField();
        idField.setPromptText("Enter Customer ID");

        // PasswordField for password
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        Button submitButton = new Button("Submit Customer");

        TextArea textArea = new TextArea();
        textArea.setPrefSize(300, 200);
        textArea.setStyle("-fx-font-family: Arial; -fx-font-size: 16px;");
        textArea.setEditable(false);
        VBox root = new VBox();
        // Add the TextArea to the VBox
        root.getChildren().add(textArea);
        // Back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showMainSelectionScene());

        // Add elements to the VBox
        ordersRoot.getChildren().addAll(nameField, idField, passwordField, submitButton, backButton, textArea);

        // Set up the scene and show the stage
        Scene ordersScene = new Scene(new BorderPane(ordersRoot, null, null, null, null), 600, 500);
        //--------------------------------------------------------------------------------------------------------------------------

        submitButton.setOnAction(e -> {

            String customerName = nameField.getText();
            String customerId = idField.getText();
            String password = passwordField.getText();
            int id = Integer.parseInt(customerId);
            Customer c = new Customer(id, customerName, UserType.CUSTOMER, password);
            String str = c.viewOrderHistory(c);
            textArea.setText(str);
        });

        //------------------------------------------------------------------------------------------------------------
        primaryStage.setTitle("Previous Orders");
        primaryStage.setScene(ordersScene);
        primaryStage.show();
    }

    private void showMainSelectionScene() {
        start(primaryStage);
    }

    private void viewCart() {
        VBox cartRoot = new VBox();
        cartRoot.setPadding(new Insets(20));
        cartRoot.setSpacing(20);
        cartRoot.setAlignment(Pos.CENTER);

        Image cartImage = new Image("file:C:\\Users\\hp\\Downloads\\4502994.png");
        ImageView cartImageView = new ImageView(cartImage);
        cartImageView.setFitWidth(150);
        cartImageView.setFitHeight(150);
        HBox cartImageBox = new HBox(cartImageView);
        cartImageBox.setAlignment(Pos.CENTER);

        TextArea textArea = new TextArea();
        textArea.setPrefSize(300, 200);
        textArea.setStyle("-fx-font-family: Arial; -fx-font-size: 16px;");
        textArea.setEditable(false);

        // New fields for Customer ID, Username, and Password
        Label customerIdLabel = new Label("Customer ID:");
        TextField customerIdField = new TextField();
        customerIdField.setPrefWidth(200);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(200);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(200);

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            int customerID = Integer.parseInt(customerIdField.getText());

            String username = usernameField.getText();
            String password = passwordField.getText();
            Customer c = new Customer(customerID, username, UserType.CUSTOMER, password);
            String cartDetails = c.ViewCart();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> showMainSelectionScene());

        cartRoot.getChildren().addAll(cartImageBox, textArea, customerIdLabel, customerIdField,
                usernameLabel, usernameField, passwordLabel, passwordField,
                submitButton, backButton);

        Scene cartScene = new Scene(new BorderPane(cartRoot, null, null, null, null), 600, 500);
        primaryStage.setTitle("Your Cart");
        primaryStage.setScene(cartScene);
        primaryStage.show();
    }

}
