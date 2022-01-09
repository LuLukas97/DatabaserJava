package Projekt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Rooms {
    int restaurantExists;
    String hotelName;

    public Rooms() {

    }


    public void showAllAvailableRooms(Connection connect, PreparedStatement statement, ResultSet resultSet) {
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

    }
    public void bookRoom(Connection connect, PreparedStatement statement, ResultSet resultSet){
        int room_id = Dialog.dialog("Enter the room ID of which you want to book: ");
        String checkInDate = Dialog.dialogString("Enter your desired check in date: (YYYY-MM-DD)");
        String checkOutDate = Dialog.dialogString("Enter your desired check out date: (YYYY-MM-DD)");

        try {
            statement = connect.prepareStatement("SELECT * FROM all_rooms WHERE Room_Number = ?");
            ResultSet resultRooms = statement.executeQuery();
            statement.setInt(1, room_id);
            statement.executeQuery();
            String HotelInfo = "Room number" + resultRooms.getString("Room_Number") + "\nHotel name: " +  resultRooms.getString("Hotel name");
            restaurantExists = resultRooms.getInt("Restaurant");
            System.out.println(HotelInfo);


        } catch (Exception e) {
            e.printStackTrace();
        }


        // AVSLUTADE HÄR, fixa så att man inte kan boka resturang om resturang inte finns på hotellet

        if (restaurantExists == 1){
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
        }
        if (restaurantExists == 0){
            System.out.println("No resturang");
        }



        String bed = Dialog.dialogString("Additional bed? (Yes/No)");
        int booked_id = Dialog.dialog("Book id?");

    }


}
