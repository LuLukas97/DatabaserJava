package Projekt;

import java.sql.*;
import java.util.Scanner;

public class guest_information {
    int guest_id;
    String checkInDateUpdate;
    String checkOutDateUpdate;
    int currentHotelID;
    int roomSelectUpdate;
    String mealChoiceUpdate;
    String bedChoiceUpdate;
    int booked_id_update;
    int oldBookedID;
    int oldAdditioanlChoicesID;
    int mealUpdate;
    int bed;
    int updatedChoiceID;

    public guest_information() {
    }

    public void registerUser(Connection connect, PreparedStatement statement, ResultSet resultSet) {
        String first_name = Dialog.dialogString("Enter first name: ");
        String last_name = Dialog.dialogString("Enter last name: ");
        String email = Dialog.dialogString("Enter Email: ");
        String phonenumber = Dialog.dialogString("Enter phone number: ");
        String birthdate = Dialog.dialogString("Enter birth Date / Year (YYYY-MM-DD)");
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

    public void editCustomerInfo(Connection connect, PreparedStatement statement, ResultSet resultSet) throws SQLException {


        String searchName = Dialog.dialogString("Please enter the customers first name: ");
        statement = connect.prepareStatement("SELECT * FROM guest_information WHERE first_name = ?");
        ResultSet searchForFirstName = statement.executeQuery();
        statement.setString(1, searchName);
        statement.executeQuery();

        while (searchForFirstName.next()) {
            String resultSearch =
                    " -- Customers --\n" +
                            "First name : " + searchForFirstName.getString("first_name") + " \n "
                            + " Last name : " + searchForFirstName.getString("last_name") + " \n "
                            + " Email : " + searchForFirstName.getString("email") + " \n "
                            + " Phone number : " + searchForFirstName.getString("phonenumber") + " \n "
                            + " Birthdate : " + searchForFirstName.getString("birthdate") + " \n "
                            + " CustomerID : " + searchForFirstName.getString("id");

            System.out.println(resultSearch);
        }
        guest_id = Dialog.dialog("Please enter the Customer ID that you want to edit: ");

        int changeBookingDetails = Dialog.dialog("Do you want to change booking details?\n1. Change customer details\n2.Change booking details\n3.Go back to main menu\n");
        switch (changeBookingDetails) {
            case 1:
                try {
                    String updatedFirstName = Dialog.dialogString("Update customers first name: ");
                    String updatedLastName = Dialog.dialogString("Update customers last name: ");
                    String updatedEmail = Dialog.dialogString("Update customers email: ");
                    String updatedphoneNr = Dialog.dialogString("Update customers phone number: ");
                    String updatedBirthDate = Dialog.dialogString("Update customers birth date: ");


                    statement = connect.prepareStatement("UPDATE guest_information SET first_name = ?, last_name = ?, email = ?, phonenumber = ?, birthdate = ? WHERE id = ?");
                    statement.setString(1, updatedFirstName);
                    statement.setString(2, updatedLastName);
                    statement.setString(3, updatedEmail);
                    statement.setString(4, updatedphoneNr);
                    statement.setString(5, updatedBirthDate);
                    statement.setInt(6, guest_id);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    statement = connect.prepareStatement("SELECT * FROM guest_information WHERE id = ?");
                    statement.setInt(1, guest_id);
                    ResultSet updatedInfo = statement.executeQuery();
                    while (updatedInfo.next()) {
                        String updatedInfoPrint =
                                " -- Updated customers --\n" +
                                        "Updated customers first name : " + updatedInfo.getString("first_name") + " \n "
                                        + "Updated customers last name : " + updatedInfo.getString("last_name") + " \n "
                                        + "Updated customers email : " + updatedInfo.getString("email") + " \n "
                                        + "Updated customers phone number : " + updatedInfo.getString("phonenumber") + " \n "
                                        + "Updated customers birthdate : " + updatedInfo.getString("birthdate") + " \n "
                                        + "Updated customers customerID : " + updatedInfo.getString("id");
                        System.out.println(updatedInfoPrint);

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            case 2:

                try {
                    statement = connect.prepareStatement("SELECT * FROM destinations");
                    ResultSet getAllHotels = statement.executeQuery();

                    while (getAllHotels.next()) {

                        String availableHotels =

                                " -------------------------------------------------------------------------------- \n" +
                                        "            -- Available hotels -- \n" +

                                        " - City : " + getAllHotels.getString("city") + " \n " +
                                        " - Hotel name : " + getAllHotels.getString("hotel_name") + " \n "
                                        + " - - Restaurant : " + getAllHotels.getString("restaurant") + " \n "
                                        + " - - Kids club : " + getAllHotels.getString("kids_club") + " \n "
                                        + " - - Pool : " + getAllHotels.getString("pool") + " \n "
                                        + " - - Entertainment : " + getAllHotels.getString("entertainment") + " \n "
                                        + " - - - Distance to centrum : " + getAllHotels.getString("distance_centre") + " \n "
                                        + " - - - Distance to beach : " + getAllHotels.getString("distance_beach") + " \n "
                                        + " - - - Number of rooms : " + getAllHotels.getString("number_of_rooms") + "\n "
                                        + " - - - Hotel ID : " + getAllHotels.getString("id") + " \n";
                        System.out.println(availableHotels);
                    }

                    statement = connect.prepareStatement("SELECT * FROM guest_bookings WHERE guests_id = ?");
                    statement.setInt(1, guest_id);
                    statement.executeQuery();
                    ResultSet getOldGuestBooking = statement.executeQuery();
                    oldAdditioanlChoicesID = getOldGuestBooking.getInt("additional_choices_id");
                    oldBookedID = getOldGuestBooking.getInt("booked_dates_id");

                    currentHotelID = Dialog.dialog("Please enter the hotel ID of which hotel you want to stay at :");

                    System.out.println("-- Updating check in and check out dates --");
                    checkInDateUpdate = Dialog.dialogString("Updated check in date: ");
                    checkOutDateUpdate = Dialog.dialogString("Updated check out date: ");

                    statement = connect.prepareStatement("SELECT * FROM booked_guests WHERE guests_id = ?");
                    statement.setInt(1, guest_id);
                    statement.executeQuery();


                    statement = connect.prepareStatement("SELECT * FROM booked_rooms WHERE room_number NOT IN (SELECT room_number FROM booked_rooms WHERE checkin_date BETWEEN ? AND ?) AND NOT (checkout_date BETWEEN ? AND ?) AND HotelID = ? OR checkin_date IS NULL AND checkout_date IS NULL AND HotelID = ? GROUP BY room_number");
                    ResultSet checkForRoomsUpdate = statement.executeQuery();
                    statement.setString(1, checkInDateUpdate);
                    statement.setString(2, checkInDateUpdate);
                    statement.setString(3, checkOutDateUpdate);
                    statement.setString(4, checkOutDateUpdate);
                    statement.setInt(5, currentHotelID);
                    statement.setInt(6, currentHotelID);
                    statement.executeQuery();

                    while (checkForRoomsUpdate.next()) {
                        roomSelectUpdate = checkForRoomsUpdate.getInt("Room_Number");
                        String showFreeRooms =
                                "\n- Available rooms - \n" +
                                        "\nRoom ID: " + checkForRoomsUpdate.getInt("Room_Number") +
                                        "\nHotel ID: " + checkForRoomsUpdate.getInt("HotelID") +
                                        "\nCity: " + checkForRoomsUpdate.getString("City") +
                                        "\nHotel name: " + checkForRoomsUpdate.getString("Hotel_name") +
                                        "\nRoom type: " + checkForRoomsUpdate.getString("Room type") +
                                        "\nMaximum guests: " + checkForRoomsUpdate.getString("Maximum guests") +
                                        "\nPrice per night: " + checkForRoomsUpdate.getString("Price per night") + "$";
                        System.out.println(showFreeRooms);
                    }
                    System.out.println("\n");

                    roomSelectUpdate = Dialog.dialog("Enter the room ID you want to change the customers booking to: ");

                    statement = connect.prepareStatement("UPDATE booked_dates SET checkin_date = ?, checkout_date = ?, room_id = ? WHERE booked_id = ?");
                    statement.setString(1, checkInDateUpdate);
                    statement.setString(2, checkOutDateUpdate);
                    statement.setInt(3, roomSelectUpdate);
                    statement.setInt(4, oldBookedID);
                    statement.executeUpdate();

                    mealUpdate = Dialog.dialog("What meal choice do you want to update the customer to 1.None\n2.Half board\n3.Full board?");
                    if (mealUpdate == 1) {
                        System.out.println("No additional meal choice added.\n");
                        mealChoiceUpdate = "None";
                    }
                    if (mealUpdate == 2) {
                        System.out.println("Half board meal choice added.\n");
                        mealChoiceUpdate = "Half board";
                    }
                    if (mealUpdate == 3) {
                        System.out.println("Full board meal choice added.\n");
                        mealChoiceUpdate = "Full board";
                    }
                    bed = Dialog.dialog("Additional bed?\n1.Yes \n2.No");
                    if (bed == 1) {
                        bedChoiceUpdate = "Yes";
                        System.out.println("Additional bed added.\n");
                    }
                    if (bed == 2) {
                        bedChoiceUpdate = "No";
                        System.out.println("No additional bed added.\n");
                    }

                    statement = connect.prepareStatement("SELECT * FROM additional_choices WHERE choice_id = ?");
                    statement.setInt(1, oldAdditioanlChoicesID);
                    ResultSet resultGetChoiceID = statement.executeQuery();
                    statement.executeQuery();
                    while (resultGetChoiceID.next()) {
                        booked_id_update = resultGetChoiceID.getInt("choice_id");
                    }

                    statement = connect.prepareStatement("UPDATE guest_bookings SET room_id = ?, additional_choices_id = ? WHERE guests_ID = ?");
                    statement.setInt(1, roomSelectUpdate);
                    statement.setInt(2, oldAdditioanlChoicesID);
                    statement.setInt(3, guest_id);
                    statement.executeUpdate();

                    statement = connect.prepareStatement("SELECT * FROM guest_bookings WHERE guests_id = ?");
                    statement.setInt(1, guest_id);
                    statement.executeQuery();
                    ResultSet getNewChoice_id = statement.executeQuery();
                    while (getNewChoice_id.next()) {
                        updatedChoiceID = getNewChoice_id.getInt("booked_dates_id");
                    }

                    statement = connect.prepareStatement("UPDATE additional_choices SET room_id = ?, meal_choice = ?, additional_bed = ?, booked_dates_id = ? WHERE booked_dates_id = ?");
                    statement.setInt(1, roomSelectUpdate);
                    statement.setString(2, mealChoiceUpdate);
                    statement.setString(3, bedChoiceUpdate);
                    statement.setInt(4, updatedChoiceID);
                    statement.setInt(5, oldBookedID);
                    statement.executeUpdate();


                    System.out.println("Updated?");


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            case 3:
                break;
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
