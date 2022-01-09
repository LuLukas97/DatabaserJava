package Projekt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class guest_information {

    Scanner scanner = new Scanner(System.in);

    public guest_information() {
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

}
