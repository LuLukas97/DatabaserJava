package Projekt;

import java.sql.*;

public class Destinations {
    int hotelSelection;
    Rooms rooms = new Rooms();


    public Destinations() {

    }

    public void hotelOptions(Connection connect, PreparedStatement statement, ResultSet resultSet) {
        try {
            statement = connect.prepareStatement("SELECT * FROM destinations");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String availableHotels =

                        " -------------------------------------------------------------------------------- \n" +
                                "            -- Available hotels -- \n" +

                                " - City : " + resultSet.getString("city") + " \n " +
                                " - Hotel name : " + resultSet.getString("hotel_name") + " \n "
                                + " - - Restaurant : " + resultSet.getString("restaurant") + " \n "
                                + " - - Kids club : " + resultSet.getString("kids_club") + " \n "
                                + " - - Pool : " + resultSet.getString("pool") + " \n "
                                + " - - Entertainment : " + resultSet.getString("entertainment") + " \n "
                                + " - - - Distance to centrum : " + resultSet.getString("distance_centre") + " \n "
                                + " - - - Distance to beach : " + resultSet.getString("distance_beach") + " \n "
                                + " - - - Number of rooms : " + resultSet.getString("number_of_rooms") + "\n "
                                + " - - - Hotel ID : " + resultSet.getString("id") + " \n";
                System.out.println(availableHotels);
            }
            int hotelChoice = Dialog.dialog("""
                    [1] More options for hotels
                    [2] Choose a hotel
                    """, 1, 2);
            switch (hotelChoice) {
                case 1:
                    boolean menuActive = true;
                    while (menuActive) {
                        int moreOptionsHotel = Dialog.dialog("""
                                [1] Search for max distance to beach
                                [2] Search for max distance to centrum
                                [3] Sort by price
                                [4] Sort by rating
                                [5] Choose a hotel
                                        """, 1, 5);


                        switch (moreOptionsHotel) {

                            case 1: // input max distance to beach
                                try {
                                    String distanceToBeach = Dialog.dialogString("Enter the max distance to beach: ");

                                    statement = connect.prepareStatement("SELECT * FROM destinations WHERE distance_beach < ? ORDER BY distance_beach DESC ");
                                    ResultSet resultBeach = statement.executeQuery();
                                    statement.setString(1, distanceToBeach);
                                    statement.executeQuery();
                                    while (resultBeach.next()) {
                                        String orderByBeachDistanceInput =

                                                " -------------------------------------------------------------------------------- \n" +
                                                        "    -- Searching for max distance to beach (highest to lowest) -- " +
                                                        " \n " +
                                                        " - City : " + resultBeach.getString("city") + " \n " +
                                                        " - Hotel name : " + resultBeach.getString("hotel_name") + " \n "
                                                        + " - - Restaurant : " + resultBeach.getString("restaurant") + " \n "
                                                        + " - - Kids club : " + resultBeach.getString("kids_club") + " \n "
                                                        + " - - Pool : " + resultBeach.getString("pool") + " \n "
                                                        + " - - Entertainment : " + resultBeach.getString("entertainment") + " \n "
                                                        + " - - - Distance to centrum : " + resultBeach.getString("distance_centre") + " m " + " \n "
                                                        + " - - - - - Distance to beach : " + resultBeach.getString("distance_beach") + " m " + " \n "
                                                        + " - - - Number of rooms : " + resultBeach.getString("number_of_rooms") + "\n "
                                                        + " - - - Rating : " + resultBeach.getString("rating") + "/5\n "
                                                        + " - - - Hotel ID : " + resultBeach.getString("id") + " \n ";
                                        System.out.println(orderByBeachDistanceInput);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 2: // input max distance to centrum
                                try {
                                    String distanceToCentrum = Dialog.dialogString("Enter the max distance to centrum: ");

                                    statement = connect.prepareStatement("SELECT * FROM destinations WHERE distance_centre < ? ORDER BY distance_centre DESC ");
                                    ResultSet resultCentrum = statement.executeQuery();
                                    statement.setString(1, distanceToCentrum);
                                    statement.executeQuery();
                                    while (resultCentrum.next()) {
                                        String orderByCentrumDistanceInput =
                                                " -------------------------------------------------------------------------------- \n" +
                                                        "    -- Searching for max distance to centrum (highest to lowest) -- " +
                                                        " \n " +
                                                        " - City : " + resultCentrum.getString("city") + " \n " +
                                                        " - Hotel name : " + resultCentrum.getString("hotel_name") + " \n "
                                                        + " - - Restaurant : " + resultCentrum.getString("restaurant") + " \n "
                                                        + " - - Kids club : " + resultCentrum.getString("kids_club") + " \n "
                                                        + " - - Pool : " + resultCentrum.getString("pool") + " \n "
                                                        + " - - Entertainment : " + resultCentrum.getString("entertainment") + " \n "
                                                        + " - - - - - Distance to centrum : " + resultCentrum.getString("distance_centre") + " m " + " \n "
                                                        + " - - - Distance to beach : " + resultCentrum.getString("distance_beach") + " m " + " \n "
                                                        + " - - - Number of rooms : " + resultCentrum.getString("number_of_rooms") + "\n "
                                                        + " - - - Rating : " + resultCentrum.getString("rating") + "/5\n "
                                                        + " - - - Hotel ID : " + resultCentrum.getString("id") + " \n";
                                        System.out.println(orderByCentrumDistanceInput);

                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 3: // Sort price high to low
                                try {
                                    System.out.println("Sorting by price (highest to lowest)...");
                                        statement = connect.prepareStatement("SELECT * FROM total_price_sorting");
                                        ResultSet resultPrice = statement.executeQuery();
                                        statement.executeQuery();
                                        while (resultPrice.next()) {
                                            String ratingPrice =
                                                    " -------------------------------------------------------------------------------- \n" +
                                                            "              Sorting price highest to lowest " +
                                                            " \n " +
                                                            " - City : " + resultPrice.getString("city") + " \n " +
                                                            " - Hotel name : " + resultPrice.getString("hotel_name") + " \n "
                                                            + " - - Restaurant : " + resultPrice.getString("restaurant") + " \n "
                                                            + " - - Kids club : " + resultPrice.getString("kids_club") + " \n "
                                                            + " - - Pool : " + resultPrice.getString("pool") + " \n "
                                                            + " - - Entertainment : " + resultPrice.getString("entertainment") + " \n "
                                                            + " - - - Distance to centrum : " + resultPrice.getString("distance_centre") + " m " + " \n "
                                                            + " - - - Distance to beach : " + resultPrice.getString("distance_beach") + " m " + " \n "
                                                            + " - - - Number of rooms : " + resultPrice.getString("number_of_rooms") + "\n "
                                                            + " - - - - - Price : " + resultPrice.getString("Price_per_night") + " $\n"
                                                            + " - - - Hotel ID : " + resultPrice.getString("id") + " \n";

                                            System.out.println(ratingPrice);
                                        }
                                    break;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            case 4: // Sort rating high to low
                                try {
                                    statement = connect.prepareStatement("SELECT * FROM OrderRatingHighToLow");
                                    ResultSet resultRating = statement.executeQuery();
                                    statement.executeQuery();
                                    while (resultRating.next()) {
                                        String ratingResults =
                                                " -------------------------------------------------------------------------------- \n" +
                                                        "              Sorting rating highest to lowest " +
                                                        " \n " +
                                                        " - City : " + resultRating.getString("city") + " \n " +
                                                        " - Hotel name : " + resultRating.getString("hotel_name") + " \n "
                                                        + " - - Restaurant : " + resultRating.getString("restaurant") + " \n "
                                                        + " - - Kids club : " + resultRating.getString("kids_club") + " \n "
                                                        + " - - Pool : " + resultRating.getString("pool") + " \n "
                                                        + " - - Entertainment : " + resultRating.getString("entertainment") + " \n "
                                                        + " - - - Distance to centrum : " + resultRating.getString("distance_centre") + " m " + " \n "
                                                        + " - - - Distance to beach : " + resultRating.getString("distance_beach") + " m " + " \n "
                                                        + " - - - Number of rooms : " + resultRating.getString("number_of_rooms") + "\n "
                                                        + " - - - - - Rating : " + resultRating.getString("rating") + "/5\n "
                                                        + " - - - Hotel ID : " + resultRating.getString("id") + " \n";
                                        System.out.println(ratingResults);

                                    }
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                                }
                                break;
                            case 5:
                                menuActive = false;
                                break;
                        }
                    }


                case 2:
                    addBooking(connect, statement, resultSet);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void addBooking(Connection connect, PreparedStatement statement, ResultSet resultSet) throws SQLException {
        hotelSelection = Dialog.dialog("Please enter the hotel ID of which hotel you want to stay at :");
        rooms.bookRoom(connect, statement, resultSet, hotelSelection);


    }

}


