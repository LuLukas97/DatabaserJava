package Projekt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class RunApp {
    Scanner scanner = new Scanner(System.in);
    public int mainMenuOption;
    public String customerName;
    ArrayList<String> customers = new ArrayList<>();
    guest_information guestInfo = new guest_information();
    Destinations destination = new Destinations();
    public boolean menuCheck = true;
    Rooms rooms = new Rooms();
    String username;
    String password;


    public RunApp(Connection connect, PreparedStatement statement, ResultSet resultSet) throws SQLException {
        login(connect, statement, resultSet);
    }

    // Skapa bokningar med tilläggstjänster (extrasäng, halvpension, helpension).
    // Ändra detaljerna i en bokning.
    // Söka på boenden baserat på avstånd till strand (hur långt borta får boendet max ligga ifrån en strand)
    // Söka på boenden baserat på till centrum (hur långt borta får boendet max ligga ifrån ett centrum)
    // Söktraffärar ska kunna ordnas på pris (lågt till högt)
    // Söktraffärar ska kunna ordnas på omdöme (högt till lågt)

    public void login(Connection connect, PreparedStatement statement, ResultSet resultSet) throws SQLException {
        username = Dialog.dialogString("Enter username: ");
        password = Dialog.dialogString("Enter password: ");

        if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")){
            mainMenu(connect, statement, resultSet);
        } else {
            System.out.println("Wrong username or password, try again.");
            login(connect, statement, resultSet);
        }


    }

    public void mainMenu(Connection connect, PreparedStatement statement, ResultSet resultSet) throws SQLException {
        System.out.println("""
                 
                 | 1 | Register a new customer
                 | 2 | Search for a hotel
                 | 3 | Search for empty rooms
                 | 4 | Search for all rooms
                 | 5 | Edit customer information
                 | 6 | Quit
                """);
        mainMenuOption = scanner.nextInt();
        while (menuCheck)
            switch (mainMenuOption) {

                case 1:
                    guestInfo.registerUser(connect, statement, resultSet);
                    mainMenu( connect,  statement, resultSet);
                case 2:
                    destination.hotelOptions(connect, statement, resultSet);
                    mainMenu(connect, statement, resultSet);
                case 3:
                    rooms.showAllAvailableRooms(connect, statement, resultSet);
                    mainMenu( connect,  statement, resultSet);

                case 4:
                    rooms.showAllRooms(connect, statement, resultSet);
                    mainMenu(connect, statement, resultSet);

                case 5:
                    guestInfo.editCustomerInfo(connect, statement, resultSet);
                    mainMenu(connect, statement, resultSet);
                case 6:
                    System.out.println("Exiting..");
                    System.exit(1);

        }
    }

}
