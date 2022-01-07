package com.company;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        DataService ds = new DataService();
        ds.connect();
        // hämtar en lista med maträtter
        ArrayList<Food> foods = ds.getFoods();
        for (var food : foods){
            System.out.println(food.id + " " + food.title);
        }

        // hämtar en maträtt baserat på ID
        Food food = ds.getFoodById(1);
        System.out.println(food.id + " " + food.title);

        // skapar en maträtt
        int createdID = ds.createFood("Mushy", "Fantastic mustard burgar");

        // hämtar en maträtt baserat på ID
        food = ds.getFoodById(createdID);
        System.out.println(food.id + " " + food.title);

        // Tar bort en kolumn beroende på vilken ID man ger
        //ds.removeFood(6);

    }

}