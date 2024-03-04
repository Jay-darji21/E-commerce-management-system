import jdk.jshell.spi.SPIResolutionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class Authority {
    private Connection connection;
    private Scanner scanner;

    Authority(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

//    Method for check the stock
    public void checkStock(){
        System.out.println("Available Stock : ");
        try{
            String query = "SELECT * FROM products";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
//
//            int product_id = resultSet.getInt("product_id");
//            String product_name = resultSet.getString("product_name");
//            String description = resultSet.getString("description");
//            int price = resultSet.getInt("price");
//            int numbers = resultSet.getInt("numberOfItemns");

            System.out.println("+---------------+------------------------------+--------------------------------------------------+----------------+-----------------------+");
            System.out.println("| Product id    | Product name                 | Brand name                                       | Price          | number of Items       |");
            System.out.println("+---------------+------------------------------+--------------------------------------------------+----------------+-----------------------+");
            while(resultSet.next()){
                int product_id = resultSet.getInt("product_id");
                String product_name = resultSet.getString("product_name");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                int numbers = resultSet.getInt("numberOfItemns");
                System.out.printf("|%15s|%30s|%50s|%16s|%23s|\n",product_id,product_name,description,price,numbers);
                System.out.println("+---------------+------------------------------+--------------------------------------------------+----------------+-----------------------+");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

//   Add products
    public void addProducts(){
        System.out.print("Enter product name : ");
        String product_name = scanner.next();
        System.out.print("Enter product brand : ");
        String description = scanner.next();
        System.out.print("Enter the price of one piece : ");
        int price = scanner.nextInt();
        System.out.print("Enter the number of items : ");
        int number = scanner.nextInt();

        try{
            String query = "INSERT INTO products(product_name,description,price,numberOfItemns) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,product_name);
            preparedStatement.setString(2,description);
            preparedStatement.setInt(3,price);
            preparedStatement.setInt(4,number);

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Product added successfully");
            }
            else{
                System.out.println("Failed to add product!!!");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

//   Delete product
    public void deleteProduct(){
        System.out.println("Delete the product : ");
        System.out.print("Enter the product_id : ");
        int id = scanner.nextInt();
        try{
            String query = "DELETE FROM products WHERE product_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Product deleted successfully");
            }
            else{
                System.out.println("Failed to delete the product!!!");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

//    Update the stock
    public void updateStock(){
        System.out.print("Enter the product id : ");
        int product_id = scanner.nextInt();
        System.out.print("Enter product name : ");
        String product_name = scanner.next();
        System.out.print("Enter product brand : ");
        String description = scanner.next();
        System.out.print("Enter the price of one piece : ");
        int price = scanner.nextInt();
        System.out.print("Enter the number of items : ");
        int number = scanner.nextInt();

         try{
             String query = "UPDATE products SET product_name = ? , description = ? , price = ? , numberOfItemns = ? WHERE product_id=?";
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             preparedStatement.setString(1, product_name);
             preparedStatement.setString(2,description);
             preparedStatement.setInt(3,price);
             preparedStatement.setInt(4,number);
             preparedStatement.setInt(5,product_id);

             int rows = preparedStatement.executeUpdate();
             if(rows>0){
                 System.out.println("Data updated successfully!!!");
             }
             else{
                 System.out.println("Failed to update the data!!!!");
             }
         }
         catch(Exception e){
             e.printStackTrace();
         }

    }

//    View customer orders
    public void viewOrders(){
        System.out.println("Orders:");
        try{
            String query = "SELECT * FROM orderHistory";
            System.out.println("+------------+----------+---------------------+------------+-----------+");
            System.out.println("| Product id | User id  | Product name        | Quantity   | Price     |");
            System.out.println("+------------+----------+---------------------+------------+-----------+");

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int product_id = resultSet.getInt("product_id");
                int user_id = resultSet.getInt("user_id");
                String product_name = resultSet.getString("product_name");

                int quantity = resultSet.getInt("quantity");
                int price = resultSet.getInt("price");

                System.out.printf("|%12s|%10s|%21s|%12s|%11s|\n",product_id,user_id,product_name,quantity,price);
                System.out.println("+------------+----------+---------------------+------------+-----------+");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void viewCustomers(){
        System.out.println("Customers:");
        try{
            String query = "SELECT * FROM user";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            System.out.println("+---------+----------------+----------+----------------+");
            System.out.println("|User_id  | User name      | Age      | Gender         |");
            System.out.println("+---------+----------------+----------+----------------+");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("user_id");
                String name = resultSet.getString("user_name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("|%9s|%16s|%10s|%16s|\n",id,name,age,gender);
                System.out.println("+---------+----------------+----------+----------------+");

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }






}
