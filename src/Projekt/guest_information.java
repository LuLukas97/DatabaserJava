package Projekt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class guest_information {

    Scanner scanner = new Scanner(System.in);

    public guest_information() {
    }

    public void customerMenu(Connection connect, PreparedStatement statement, ResultSet resultSet) {
        int customerSelection = Dialog.dialog("1. Register new customer\n 2. Register company\n 3.Back to main menu");

        switch (customerSelection) {
            case 1:
                registerUser(connect, statement, resultSet);
                break;
            case 2:
                registerCompany(connect, statement, resultSet);
                break;
            case 3:

                break;
        }

    }

    public void registerCompany(Connection connect, PreparedStatement statement, ResultSet resultSet){

    }

    public void registerUser(Connection connect, PreparedStatement statement, ResultSet resultSet) {
        System.out.println("Enter first name: ");
        String first_name = scanner.nextLine();
        System.out.println("Enter last name: ");
        String last_name = scanner.nextLine();
        System.out.println("Enter Email: ");
        String email = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phonenumber = scanner.nextLine();
        System.out.println("Enter Birth Date/Year (YYYY-MM-DD): ");
        String birthdate = scanner.nextLine();
        try {
            statement = connect.prepareStatement("INSERT INTO guest_information (first_name, last_name, email, phonenumber, birthdate) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, first_name.toLowerCase());
            statement.setString(2, last_name.toLowerCase());
            statement.setString(3, email);
            statement.setString(4, phonenumber);
            statement.setString(5, birthdate);
            statement.executeUpdate();
            String fullName = first_name + " " + last_name;
            System.out.println("\n" + fullName + " registered successfully! ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchCustomer(Connection connect, PreparedStatement statement, ResultSet resultSet) {
        String search_first_name = Dialog.dialogString("Enter first name: ");
        String search_last_name = Dialog.dialogString("Enter last name: ");

        try {
            statement = connect.prepareStatement("SELECT * FROM guest_information WHERE first_name =? AND last_name = ?");
            statement.setString(1, search_first_name);
            statement.setString(2, search_last_name);
            resultSet = statement.executeQuery();

            String result = "First name : " + resultSet.getString("first_name") + " \n "
                    + " Last name : " + resultSet.getString("last_name") + " \n "
                    + " Email : " + resultSet.getString("email") + " \n "
                    + " Phone number : " + resultSet.getString("phonenumber") + " \n "
                    + " Birthdate : " + resultSet.getString("birthdate") + " \n "
                    + " CustomerID : " + resultSet.getString("id");

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
