package Projekt;

import java.sql.*;
import java.util.Scanner;

public class Database {
    private ResultSet resultSet;
    private PreparedStatement statement;
    private Connection connect = null;


    public Database() throws SQLException {
        connect();
        new RunApp(connect, statement, resultSet);
    }

    public void connect(){
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:HolidaymakerSolo.sqlite");
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    //Connection connect, PreparedStatement statement, ResultSet resultSet
}
