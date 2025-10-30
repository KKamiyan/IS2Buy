package Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static boolean loop = true;

    public static void main(String[] args) {

        signMenu();
        System.exit(0);

        System.out.println("---------");
        System.out.println("|IS-2Buy|");
        System.out.println("---------\n");

        signMenu();
        menu();

        scan.close();
        System.out.println("-----------------");
    }

    static void signUp() {
        String usernameFilePath = "JAVA\\\\Transaction\\\\users.txt";
        String passwordFilePath = "JAVA\\\\Transaction\\\\password.txt";
        String username = "", password = "";
        boolean available = false;

        do {
            System.out.println("SIGN-UP\n");

            System.out.print("Username: ");
            username = scan.nextLine();
            System.out.println("-----------------");

            if (userExists(username).flag) {
                System.out.println("Username already exists!");
            } else {
                System.out.println("Available");
                available = true;
            }
            System.out.println("-----------------");

        } while (available == false);

        System.out.print("Password: ");
        password = scan.nextLine();
        System.out.println("-----------------");

        try (FileWriter writer = new FileWriter(usernameFilePath, true)) {
            writer.write(username + "\n");
            writer.close();

        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
        System.out.println("-----------------");

        try (FileWriter writer = new FileWriter(passwordFilePath, true)) {
            writer.write(password + "\n");
            writer.close();

            System.out.println("Account Successfully Created!");
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
        System.out.println("-----------------");

    }

    static boolean signIn() {

        String username = "", password = "";
        boolean userFound = false;

        testDataType test;

        do {

            System.out.print("Username: ");
            username = scan.nextLine();
            System.out.println("-----------------");

            System.out.print("Password: ");
            password = scan.nextLine();     
            System.out.println("-----------------");       

            test = userExists(username);

            if (test.flag) {

                if(passwordMatch(password, test.number) == false){
                    System.out.println("Wrong Account/Password!");
                }
                else{
                    userFound = true;
                    System.out.println("Login Successful!");
                }
                System.out.println("-----------------");
            } else{
                System.out.println("No such Account!");
            }
            System.out.println("-----------------");

        } while (userFound == false);

        return userFound;
    }

    static void shop() {
        System.out.println("SHOP\n");

        System.out.println("-----------------");
    }

    static void cart() {
        System.out.println("CART\n");

        System.out.println("-----------------");
    }

    static void signMenu() {

        System.out.println("SIGN-IN: 1\nSIGN-UP:2\nExit: 0\n");
        while (loop) {
            loop = false;

            System.out.println("-----------------");
            System.out.print("Enter: ");
            switch (scan.nextInt()) {
                case 0 -> {
                    System.out.println("Exiting...\n");
                    System.exit(0);
                }
                case 1 -> {
                    System.out.println("Sign-in\n");
                    scan.nextLine();
                    do {

                    } while (!signIn());
                }
                case 2 -> {
                    scan.nextLine();
                    System.out.println("Sign-up\n");
                    signUp();
                }
                default -> {
                    System.out.println("Invalid.");
                    loop = true;
                }
            }
        }
        System.out.println("-----------------");
    }

    static void menu() {
        loop = true;
        System.out.println("Shop: 1\nCart: 2\nExit:0\n");
        while (loop) {
            loop = false;

            System.out.println("-----------------");
            System.out.print("Enter: ");
            switch (scan.nextInt()) {
                case 0 -> System.exit(0);
                case 1 -> System.out.println("Shop");
                case 2 -> System.out.println("Cart");
                default -> {
                    System.out.println("Invalid.");
                    loop = true;
                }
            }

        }
        System.out.println("-----------------");
    }

    static void userPrint() {

        String filePath = "JAVA\\Transaction\\users.txt";

        try (FileReader reader = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(reader)) {

            String username;

            while ((username = bufferedReader.readLine()) != null) {
                bufferedReader.skip(1);
                System.out.println(username + "\n");
            }

        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
        System.out.println("-----------------");
    }

    static testDataType userExists(String username) {
        String userFilePath = "JAVA\\Transaction\\users.txt";

        boolean userFound = false;
        String line;
        int count = 0;

        try {
            FileReader reader = new FileReader(userFilePath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            while ((line = bufferedReader.readLine()) != null) {

                // System.out.println(username + "==" + line);

                if (username.equals(line)) {

                    bufferedReader.close();
                    reader.close();

                    reader = new FileReader(userFilePath);
                    bufferedReader = new BufferedReader(reader);

                    userFound = true;
                    break;
                }
                count++;
            }

            reader.close();
            bufferedReader.close();

        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }

        return new testDataType(userFound, count);
    }

    static boolean passwordMatch(String password, int count) {
        String passwordFilePath = "JAVA\\Transaction\\password.txt";

        String line = "";

        try {
            FileReader reader = new FileReader(passwordFilePath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            for(int i = 0; i <= count; i++){
                line = bufferedReader.readLine();
                //System.out.println(password + "==" + line);
            }
            

            reader.close();
            bufferedReader.close();         

        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }

        return(password.equals(line));

    }

    static void userWrite() {
        String userFilePath = "JAVA\\Transaction\\users.txt";
        String username = "";

        try (FileWriter writer = new FileWriter(userFilePath, true)) {
            writer.write(username + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }
}
