package Projekt;


import java.util.Locale;
import java.util.Scanner;

public class Dialog {


    private static Scanner console = new Scanner(System.in);

    public static int dialog(String text, int min, int max) {
        while (true) {
            System.out.println(text);
            try {
                int number = Integer.parseInt(console.nextLine());
                if (number >= min && number <= max)
                    return number;
                else {
                    System.out.println("Wrong input!");
                }
            } catch (Exception e) {
                System.out.println("Wrong input!");
            }
        }
    }

    public static int dialog(String text) {
        while (true) {
            System.out.println(text);
            try {
                return Integer.parseInt(console.nextLine());
            } catch (Exception e) {
                System.out.println("Wrong input!");
            }
        }
    }

    public static String dialogString(String text){
        System.out.println(text);
        return console.nextLine();
    }

    public static String dialogToLower(String text){
        System.out.println(text);
        return console.nextLine().toLowerCase();
    }

}