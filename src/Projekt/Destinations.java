package Projekt;

import java.sql.*;

public class Destinations {
    String hostEmail;
    int hotelSelection;
    Rooms rooms = new Rooms();

    public Destinations() {

    }

    public void hotelOptions(Connection connect, PreparedStatement statement, ResultSet resultSet) {
        try {
            hostEmail = Dialog.dialogToLower("Enter the hosts name: ");

            statement = connect.prepareStatement("SELECT * FROM guest_information WHERE first_name = ?");
            statement.setString(1, hostEmail);
            resultSet = statement.executeQuery();

            String result = "First name : " + resultSet.getString("first_name") + " \n "
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
                                                //String hotelOption = Dialog.dialogString("[1] Search for a new max distance to beach\n[2] Choose a hotel");
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

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void addBooking(Connection connect, PreparedStatement statement, ResultSet resultSet) throws SQLException {
        hotelSelection = Dialog.dialog("Please enter the hotel ID of which hotel you want to stay at :");

        System.out.println("Do you want to browse available rooms or book a room? ");
        int browseOrBook = Dialog.dialog("[1] Browse rooms\n[2] Book a room");
        switch (browseOrBook) {
            case 1:
                showAvailableRoomsHotelSelected(connect, statement, resultSet, hotelSelection);
                addBooking(connect, statement, resultSet);
            case 2:
                rooms.bookRoom(connect, statement, resultSet);

        }
    }

/*    public void bookRoom(Connection connect, PreparedStatement statement, ResultSet resultSet){
        int room_id = Dialog.dialog("Enter the room ID of which you want to book: ");
        String checkInDate = Dialog.dialogString("Enter your desired check in date: (YYYY-MM-DD)");
        String checkOutDate = Dialog.dialogString("Enter your desired check out date: (YYYY-MM-DD)");

        int meal = Dialog.dialog("Do you want to add a meal choice to your stay?\n 1. None\n 2.Half board\n 3. Full board");
        if (meal == 1){
            String mealNone = "None";
        }
        if (meal == 2){
            String mealHalf = "Half board";
        }
        if (meal == 3){
            String mealFull = "Full board";
        }


        String bed = Dialog.dialogString("Additional bed? (Yes/No)");
        int booked_id = Dialog.dialog("Book id?");

    }*/

    public void showAvailableRoomsHotelSelected(Connection connect, PreparedStatement statement, ResultSet resultSet, int hotelSelection) throws SQLException {
        try {
            statement = connect.prepareStatement("SELECT * FROM availableRooms WHERE HotelID = ?");
            ResultSet resultAvailableRooms = statement.executeQuery();
            statement.setInt(1, hotelSelection);
            statement.executeQuery();
            while (resultAvailableRooms.next()) {
                String availableRooms =

                        " - Hotel ID : " + resultAvailableRooms.getString("HotelID") + " \n " +
                                " - City : " + resultAvailableRooms.getString("City") + "\n "
                                + " - Hotel name : " + resultAvailableRooms.getString("Hotel name") + " \n "
                                + " - Rating : " + resultAvailableRooms.getString("Rating") + " \n "
                                + " - Room number : " + resultAvailableRooms.getString("Room Number") + " \n ";

                System.out.println(availableRooms);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

/*    public void showAllAvailableRooms(Connection connect, PreparedStatement statement, ResultSet resultSet) {
        try {

            statement = connect.prepareStatement("SELECT * FROM availableRooms WHERE HotelID >= 1");
            ResultSet resultAvailableRoomsAll = statement.executeQuery();
            statement.executeQuery();
            while (resultAvailableRoomsAll.next()) {
                String availableRoomsAll =

                        " - Hotel ID : " + resultAvailableRoomsAll.getString("HotelID") + " \n " +
                                " - City : " + resultAvailableRoomsAll.getString("City") + "\n "
                                + " - Hotel name : " + resultAvailableRoomsAll.getString("Hotel name") + " \n "
                                + " - Rating : " + resultAvailableRoomsAll.getString("Rating") + " \n "
                                + " - Room number : " + resultAvailableRoomsAll.getString("Room Number") + " \n ";

                System.out.println(availableRoomsAll);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }*/

}
