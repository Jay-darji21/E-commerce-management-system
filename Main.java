
import com.mysql.cj.jdbc.Driver;

import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.Connection;

public class Main{
    private static final String url = "jdbc:mysql://localhost:3306/inventary";
    private static final String userName = "root";
     private static final String password = "Jay@2110";
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        try{
            Connection connection = DriverManager.getConnection(url,userName,password);
            User user = new User(connection,scanner);
            Authority authority = new Authority(connection,scanner);

            int choice1;
            System.out.println("|| Welcome to the shop ||");
            System.out.println("1. User");
            System.out.println("2. Authority");
            System.out.print("Please enter your roll : ");
            choice1 = scanner.nextInt();

            if(choice1 == 1){   // User
                System.out.println("1. New user");
                System.out.println("2. Login with user id");
                int c = scanner.nextInt();
                if(c==1)  user.login();
                else if(c==2){
                    System.out.println("Enter your user id : ");
                    int temp = scanner.nextInt();
                    System.out.println("Login successfully");
                }
                else{
                    System.out.println("Enter valid choice");
                }
                int choice2;
                while(true){
                    System.out.println("1.View Products");
                    System.out.println("2.View Orders");
                    System.out.println("3.Place Order");
                    System.out.println("4.Exit");
                    System.out.print("Enter your choice : ");
                    choice2 = scanner.nextInt();
                    switch (choice2){
                        case 1 : // View Products
                            user.products();
                            break;

                        case 2 : // View Orders
                            user.viewOrders();
                            break;

                        case 3 : // Place order
                             user.placeOrder();
                             break;

                        case 4 :    return;

                        default:
                            System.out.println("Enter valid choice");
                    }
                }
            }
            else if(choice1 == 2){  // Authority
                int choice3;
                while(true){
                    System.out.println("1.Check stock");
                    System.out.println("2.Add products");
                    System.out.println("3.Delete products");
                    System.out.println("4.Update stock");
                    System.out.println("5.View Orders");
                    System.out.println("6.View Customers");
                    System.out.println("7.Exit");
                    System.out.println("Enter your choice : ");
                    choice3 = scanner.nextInt();

                    switch (choice3){
                        case 1: // Check stock
                            authority.checkStock();
                            break;
                        case 2: // Add products
                            authority.addProducts();
                            break;
                        case 3: // Delete products
                            authority.deleteProduct();
                            break;
                        case 4:  // Update stock
                            authority.updateStock();
                            break;
                        case 5:  // View orders
                            authority.viewOrders();
                            break;
                        case 6: // View customers
                            authority.viewCustomers();
                            break;
                        case 7: // Exit
                              return;

                        default:
                            System.out.println("Enter valid choice");
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}