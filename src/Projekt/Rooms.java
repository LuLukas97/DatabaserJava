package Projekt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Rooms {
    guest_information guestInfo = new guest_information();
    int roomSelected;
    String checkOutDate;
    String checkInDate;
    Destinations destinations;
    String mealChoice;
    String bedSelected;
    int booked_id;
    int choice_id;


    public Rooms() {

    }


    public void showAllAvailableRooms(Connection connect, PreparedStatement statement, ResultSet resultSet) {
        try {
            statement = connect.prepareStatement("SELECT * FROM availableRooms");
            ResultSet resultAvailableRoomsAll = statement.executeQuery();
            statement.executeQuery();
            while (resultAvailableRoomsAll.next()) {
                String availableRoomsAll =

                        " - Hotel ID : " + resultAvailableRoomsAll.getString("HotelID") + " \n " +
                                " - City : " + resultAvailableRoomsAll.getString("City") + "\n "
                                + " - Hotel name : " + resultAvailableRoomsAll.getString("Hotel name") + " \n "
                                + " - Rating : " + resultAvailableRoomsAll.getString("Rating") + " \n "
                                + " - Room number : " + resultAvailableRoomsAll.getString("Room Number") + " \n "
                                + " - Room type : " + resultAvailableRoomsAll.getString("Room type") + " \n"
                                + " - Price per night : " + resultAvailableRoomsAll.getString("Price per night") + " \n";

                System.out.println(availableRoomsAll);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void bookRoom(Connection connect, PreparedStatement statement, ResultSet resultSet, int hotelSelection) throws SQLException {

        checkInDate = Dialog.dialogString("Enter your desired check in date: (YYYY-MM-DD)");
        checkOutDate = Dialog.dialogString("Enter your desired check out date: (YYYY-MM-DD)");
        try {
            statement = connect.prepareStatement("SELECT * FROM booked_rooms WHERE room_number NOT IN (SELECT room_number FROM booked_rooms WHERE checkin_date BETWEEN ? AND ?) AND NOT (checkout_date BETWEEN ? AND ?) AND HotelID = ? OR checkin_date IS NULL AND checkout_date IS NULL AND HotelID = ? GROUP BY room_number");
            ResultSet checkForRoomsDates = statement.executeQuery();
            statement.setString(1, checkInDate);
            statement.setString(2, checkInDate);
            statement.setString(3, checkOutDate);
            statement.setString(4, checkOutDate);
            statement.setInt(5, hotelSelection);
            statement.setInt(6, hotelSelection);
            statement.executeQuery();
            while (checkForRoomsDates.next()) {
                roomSelected = checkForRoomsDates.getInt("Room_Number");
                String showFreeRooms =
                        "\n- Available rooms - \n" +
                                "\nRoom ID: " + checkForRoomsDates.getInt("Room_Number") +
                                "\nHotel ID: " + checkForRoomsDates.getInt("HotelID") +
                                "\nCity: " + checkForRoomsDates.getString("City") +
                                "\nHotel name: " + checkForRoomsDates.getString("Hotel_name") +
                                "\nRoom type: " + checkForRoomsDates.getString("Room type") +
                                "\nMaximum guests: " + checkForRoomsDates.getString("Maximum guests") +
                                "\nPrice per night: " + checkForRoomsDates.getString("Price per night") + "$";

                System.out.println(showFreeRooms);
            }
            System.out.println("\n");
            roomSelected = Dialog.dialog("Enter the room ID of which you want to book: ");

            statement = connect.prepareStatement("INSERT INTO booked_dates (room_id, checkin_date, checkout_date) VALUES (?, ?, ?)");
            statement.setInt(1, roomSelected);
            statement.setString(2, checkInDate);
            statement.setString(3, checkOutDate);
            statement.executeUpdate();

            int meal = Dialog.dialog("Do you want to add a meal choice to your stay?\n1.None\n2.Half board\n3.Full board");
            if (meal == 1) {
                System.out.println("No additional meal choice added.\n");
                mealChoice = "None";
            }
            if (meal == 2) {
                System.out.println("Half board meal choice added.\n");
                mealChoice = "Half board";
            }
            if (meal == 3) {
                System.out.println("Full board meal choice added.\n");
                mealChoice = "Full board";
            }
            int bed = Dialog.dialog("Additional bed?\n1.Yes \n2.No");
            if (bed == 1) {
                bedSelected = "Yes";
                System.out.println("Additional bed added.\n");
            }
            if (bed == 2) {
                bedSelected = "No";
                System.out.println("No additional bed added.\n");
            }


            statement = connect.prepareStatement("SELECT booked_id FROM booked_dates WHERE room_id = ? AND checkin_date = ? AND checkout_date = ?");

            statement.setInt(1, roomSelected);
            statement.setString(2, checkInDate);
            statement.setString(3, checkOutDate);
            ResultSet resultGetID = statement.executeQuery();
            statement.executeQuery();
            booked_id = resultGetID.getInt("booked_id");

            statement = connect.prepareStatement("INSERT INTO additional_choices (room_id, booked_dates_id, meal_choice, additional_bed) VALUES (?, ?, ?, ?)");
            statement.setInt(1, roomSelected);
            statement.setInt(2, booked_id);
            statement.setString(3, mealChoice);
            statement.setString(4, bedSelected);
            statement.executeUpdate();

            statement = connect.prepareStatement("SELECT choice_ID FROM additional_choices WHERE room_id = ? AND booked_dates_id = ? AND meal_choice = ? AND additional_bed = ?");
            statement.setInt(1, roomSelected);
            statement.setInt(2, booked_id);
            statement.setString(3, mealChoice);
            statement.setString(4, bedSelected);
            ResultSet resultGetChoiceID = statement.executeQuery();
            statement.executeQuery();
            choice_id = resultGetChoiceID.getInt("choice_id");

            boolean menuRunning = true;
            while (menuRunning) {
                int findGuest = Dialog.dialog("Search for existing customer or register new customer: " +
                        "\n1. Search for existing customer\n2. Register new customer\n3.Book a customer using ID");
                switch (findGuest) {
                    case 1:
                        guestInfo.searchCustomer(connect, statement, resultSet);
                        break;
                    case 2:
                        guestInfo.registerUser(connect, statement, resultSet);
                        break;
                    case 3:
                        menuRunning = false;
                }
            }

            int totalGuests = Dialog.dialog("How many guests will be staying in this room? ");
            for (int i = 0; i < totalGuests; i++) {
                int guestID = Dialog.dialog("Enter guest ID: ");
                statement = connect.prepareStatement("INSERT INTO guest_bookings (guests_ID, room_id, additional_choices_id, booked_dates_id, total_guests) VALUES (?, ?, ?, ?, ?)");
                statement.setInt(1, guestID);
                statement.setInt(2, roomSelected);
                statement.setInt(3, choice_id);
                statement.setInt(4, booked_id);
                statement.setInt(5, totalGuests);
                statement.executeUpdate();
                System.out.println("Guest " + guestID + " added");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void showAllRooms(Connection connect, PreparedStatement statement, ResultSet resultSet) {
        try {
            statement = connect.prepareStatement("SELECT * FROM all_rooms");
            ResultSet showAllRooms = statement.executeQuery();
            statement.executeQuery();
            while (showAllRooms.next()) {
                String showRooms =
                        "         ---- All rooms ----\n" +
                                " - Hotel ID : " + showAllRooms.getString("Hotel ID") + " \n " +
                                " - City : " + showAllRooms.getString("City") + "\n "
                                + " - Hotel name : " + showAllRooms.getString("Hotel name") + " \n "
                                + " - Rating : " + showAllRooms.getString("Rating") + " \n "
                                + " - Room number : " + showAllRooms.getString("Room_Number") + " \n "
                                + " - Room type : " + showAllRooms.getString("Room type") + " \n"
                                + " - Price per night : " + showAllRooms.getString("Price per night");

                System.out.println(showRooms);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

