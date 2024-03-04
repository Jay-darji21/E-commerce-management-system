import com.mysql.cj.jdbc.CallableStatementWrapper;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.util.Scanner;

public class User {
    private Connection connection;
    private Scanner scanner;

    User(Connection connection,Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

//    User login method
    public void login(){
//        Take inputs from user
        System.out.print("Enter customer name : ");
        String name = scanner.next();
        System.out.print("Enter your age : ");
        int age = scanner.nextInt();
        System.out.print("Enter your gender : ");
        String gender = scanner.next();
//        Set values in database
        try{
            String query = "INSERT INTO user(user_name,age,gender) VALUES (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows > 0){
                System.out.println("Login Successfully");
            }

            else{
                System.out.println("Failed to login!!!");
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


//    Orders of customer
    public void viewOrders(){
        String query = "SELECT * FROM orderHistory";
        try{
            System.out.println("Your orders : ");
            System.out.println("+--------------+------------------------------------+------------+-------------+");
            System.out.println("| Product_id   | Product name                       | Quantity   | Price(Rs.)  |");
            System.out.println("+--------------+------------------------------------+------------+-------------+");

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("product_id");
                String name = resultSet.getString("product_name");
                int quantity = resultSet.getInt("quantity");
                int price = resultSet.getInt("price");
                System.out.printf("|%-14s|%-36s|%-12s|%-13s|\n",id,name,quantity,price);
                System.out.println("+--------------+------------------------------------+------------+-------------+");

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

//     View Products
    public void products(){
        String query = "SELECT product_id,product_name,description,price FROM products";
        try{
            System.out.println("Products : ");
            System.out.println("+------------+---------------------+--------------------------------------------+--------------+");
            System.out.println("| Product_id | Product name        | Brand name                                 | Price        |");
            System.out.println("+------------+---------------------+--------------------------------------------+--------------+");

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("product_id");
                String name = resultSet.getString("product_name");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");

                System.out.printf("|%-12s|%-21s|%-44s|%-15s|\n",id,name,description,price);
                System.out.println("+------------+---------------------+--------------------------------------------+--------------+");

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

//    Place order by customer
    public void placeOrder(){
        System.out.print("Enter the user id : ");
        int user_id = scanner.nextInt();
        System.out.print("Enter product id : ");
        int product_id = scanner.nextInt();
        System.out.print("Enter the number of items : ");
        int number = scanner.nextInt();

//        try catch block for fetch the product name and price
        try {
            String query = "SELECT product_name,price FROM products WHERE product_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,product_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            String name=null;
            int price1=0;
            if(resultSet.next()){
                name = resultSet.getString("product_name");
                price1 = resultSet.getInt("price");
            }

            int price = price1*number;

            try {
                String query1 = "INSERT INTO orderHistory (user_id,product_id,product_name,quantity,price) VALUES (?,?,?,?,?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
                preparedStatement1.setInt(1,user_id);
                preparedStatement1.setInt(2,product_id);
                preparedStatement1.setString(3,name);
                preparedStatement1.setInt(4,number);
                preparedStatement1.setInt(5,price);
                int  affectedRows = preparedStatement1.executeUpdate();
                if(affectedRows>0){
                    System.out.println("Order placed successfully");
                }else{
                    System.out.println("Failed to place the order!!!!");
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
