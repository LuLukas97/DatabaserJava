package Projekt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Destinations {
    String hostEmail;
    Statement sthm;

    public Destinations() {

    }

    public void bookRoom(Connection connect, PreparedStatement statement, ResultSet resultSet) {
        hostEmail = Dialog.dialogString("Enter the hosts name: ");
        try {
            statement = connect.prepareStatement("SELECT * FROM guest_information WHERE first_name = ?");
            statement.setString(1, hostEmail);
            resultSet = statement.executeQuery();

            String result = " First name : " + resultSet.getString("first_name") + " \n "
                    + " Last name : " + resultSet.getString("last_name") + " \n "
                    + " Email : " + resultSet.getString("email") + " \n "
                    + " Phone number : " + resultSet.getString("phonenumber") + " \n "
                    + " Birthdate : " + resultSet.getString("birthdate") + " \n "
                    + " CustomerID : " + resultSet.getString("id");

            System.out.println(result);

            String confirmUser = Dialog.dialogString("Is this the right user? [Y/N]");
            if (confirmUser.equalsIgnoreCase("Y")) {

                try {

                    statement = connect.prepareStatement("SELECT * FROM destinations");
                    resultSet = statement.executeQuery();

                        while (resultSet.next()) {

                        String availableHotels =

                                " -------------------------------------------------------------------------------- \n" +
                                        " \n " +

                                        " - City : " + resultSet.getString("city") + " \n " +
                                        " - Hotel name : " + resultSet.getString("hotel_name") + " \n "
                                        + " - - Restaurant : " + resultSet.getString("restaurant") + " \n "
                                        + " - - Kids club : " + resultSet.getString("kids_club") + " \n "
                                        + " - - Pool : " + resultSet.getString("pool") + " \n "
                                        + " - - Entertainment : " + resultSet.getString("entertainment") + " \n "
                                        + " - - - Distance to centrum : " + resultSet.getString("distance_centre") + " \n "
                                        + " - - - Distance to beach : " + resultSet.getString("distance_beach") + " \n "
                                        + " - - - Number of rooms : " + resultSet.getString("number_of_rooms") + "\n "
                                        + " - - - Hotel ID : " + resultSet.getString("id");
                        System.out.println(availableHotels);
                    }
                    int hotelChoice = Dialog.dialog("""
                            [1] More options for hotels
                            [2] Choose a hotel
                            """, 1, 2);
                    switch (hotelChoice) {
                        case 1:
                            int moreOptionsHotel = Dialog.dialog("""
                                    [1] Search for max distance to beach
                                    [2] Search for max distance to centrum
                                    [3] Sort by price
                                    [4] Sort by rating
                                    [5] Choose a hotel
                                            """, 1, 5);
                            switch (moreOptionsHotel) {
                                case 1:
                                    try {
                                    String distanceToBeach = Dialog.dialogString("Enter the max distance to beach: ");

                                        statement = connect.prepareStatement("SELECT * FROM destinations WHERE distance_beach < ?");
                                        ResultSet resultSet1 = statement.executeQuery();
                                        statement.setString(1, distanceToBeach);
                                        statement.executeQuery();
                                        while (resultSet1.next()) {
                                            String availableHotel =

                                                    " -------------------------------------------------------------------------------- \n" +
                                                            " \n " +

                                                            " - City : " + resultSet1.getString("city") + " \n " +
                                                            " - Hotel name : " + resultSet1.getString("hotel_name") + " \n "
                                                            + " - - Restaurant : " + resultSet1.getString("restaurant") + " \n "
                                                            + " - - Kids club : " + resultSet1.getString("kids_club") + " \n "
                                                            + " - - Pool : " + resultSet1.getString("pool") + " \n "
                                                            + " - - Entertainment : " + resultSet1.getString("entertainment") + " \n "
                                                            + " - - - Distance to centrum : " + resultSet1.getString("distance_centre") + " \n "
                                                            + " - - - Distance to beach : " + resultSet1.getString("distance_beach") + " \n "
                                                            + " - - - Number of rooms : " + resultSet1.getString("number_of_rooms") + "\n "
                                                            + " - - - Hotel ID : " + resultSet1.getString("id");
                                            System.out.println(availableHotel);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 2:
                                    String distanceToCentrum = Dialog.dialogString("Enter the max distance to centrum: ");

                                case 3:
                                    try {
                                        System.out.println("Sorting by price (highest to lowest)...");
                                        statement = connect.prepareStatement("SELECT * FROM rooms ORDER BY = ? DESC");
                                        // statement.setInt(1);
                                        statement.executeQuery();
                                        System.out.println("[1] Sort from lowest to highest price\n" + " [2] Sort from highest to lowest price");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                case 4:
                                    System.out.println("Sorting by rating (highest to lowest)...");
                                    System.out.println("[1] Sort from lowest to highest rating\n" + " [2] Sort from highest to lowest rating");
                                case 5:

                            }
                        case 2:
                            String hotelChoices = Dialog.dialogString("Please enter the hotel ID of which hotel you want to stay at :");


                            //                            [1] Search for max distance to beach
                            //                            [2] Search for max distance to centrum
                            //                            [3] Sort by price
                            //                            [4] Sort by rating
                            //                            [5] Choose a hotel
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
