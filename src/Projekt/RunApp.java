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


    public RunApp(Connection connect, PreparedStatement statement, ResultSet resultSet) throws SQLException {
        mainMenu(connect, statement, resultSet);
    }

    // Skapa bokningar med tilläggstjänster (extrasäng, halvpension, helpension).
    // Ändra detaljerna i en bokning.
    // Söka på boenden baserat på avstånd till strand (hur långt borta får boendet max ligga ifrån en strand)
    // Söka på boenden baserat på till centrum (hur långt borta får boendet max ligga ifrån ett centrum)
    // Söktraffärar ska kunna ordnas på pris (lågt till högt)
    // Söktraffärar ska kunna ordnas på omdöme (högt till lågt)

    public void mainMenu(Connection connect, PreparedStatement statement, ResultSet resultSet) throws SQLException {
        System.out.println("""
                 | 1 | Register a new customer and company
                 | 2 | Book a hotel room
                 | 3 | Search for empty rooms
                 | 4 | Search for all rooms
                 | 5 | Room menu
                 | 6 | Quit
                """);
        mainMenuOption = scanner.nextInt();
        while (menuCheck)
            switch (mainMenuOption) {

                case 1:
                    guestInfo.registerUser(connect, statement, resultSet);
                    break;
                case 2:
                    destination.bookRoom(connect, statement, resultSet);
                    break;


        }
        if (!menuCheck){
            System.out.println("exiting..");
            System.exit(1);
        }
    }

    public void registerCustomer() {
        System.out.println("How many people do you want to book a room for?");
        int amountOfCustomers = scanner.nextInt();
        for (int i = 0; i < amountOfCustomers; i++) {
            System.out.println("Enter your name: ");
            customerName = scanner.next();
            customers.add(customerName);
        }
        for (var customer : customers) {
            System.out.println(customer);
        }

        System.out.println("Who will pay for the booking?");
        int payingCustomer = scanner.nextInt();
        System.out.println(payingCustomer);

    }


}
