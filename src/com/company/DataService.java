package com.company;

import java.sql.*;
import java.util.ArrayList;

public class DataService {


    Connection conn = null;

    public void connect(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:receptia.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public ArrayList<Food> getFoods(){
        ArrayList<Food> foods = new ArrayList<>();
        String query = "SELECT * FROM foods ORDER BY title";
        try{
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                // Vi behöver nu hämta ut varje värde från varje kolumn i raden:
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                foods.add( new Food(id, title) );
            }


        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return foods;
    }

    public Food getFoodById(int id){
        Food food = null;
        String query = "SELECT * FROM foods WHERE ID = ?";
        try{
            PreparedStatement statement = conn.prepareStatement(query);
            // ? parameter id
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while( resultSet.next() ){
                // Vi behöver nu hämta ut varje värde från varje kolumn i raden:
                id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                food = new Food(id, title);
            }


        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return food;
    }

    public int createFood(String title, String text){
        int createdId = 0;
        String query = "INSERT INTO foods (title, text) VALUES (?, ?)";
        try{
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, text);
            // commit to database
            statement.executeUpdate();
            // get result
            ResultSet keys = statement.getGeneratedKeys();
            while (keys.next()){
                createdId = keys.getInt(1);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return createdId;
    }



    public void removeFood(int id){
        int removedID = 0;

        String removeQuery = "DELETE FROM foods WHERE ID = ?";

        try{
            PreparedStatement statement = conn.prepareStatement(removeQuery);
            statement.setInt(1, id);

            statement.executeUpdate();

        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        System.out.println("Removed" + " ID " + id );
    }

}
