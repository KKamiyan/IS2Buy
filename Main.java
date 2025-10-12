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

        System.out.println("---------");
        System.out.println("|IS-2Buy|");
        System.out.println("---------\n");

        signMenu();
        menu();

        scan.close();
        System.out.println("-----------------");
    }

    static void signUp() {
        String passwordFilePath = "JAVA\\\\Transaction\\\\password.txt";
        String idFilePath = "JAVA\\\\Transaction\\\\id.txt";
        String username = "", password = "";
        int count = 0;

        switch(userRead()){
            case 0 -> System.out.println("Username: (" + username + ") is available.");
            case 1 -> System.out.println("Username already exists!\n");        
        }        
        

        System.out.println("Password: ");
        password = scan.nextLine();

        try (FileWriter writer = new FileWriter(passwordFilePath, true)) {
            writer.write(password + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }

        try (FileReader reader = new FileReader(idFilePath);
                BufferedReader bufferedReader = new BufferedReader(reader)) {

            while (bufferedReader.readLine() != null) {
                count++;
            }
            System.out.println("Account: (" + username + ") successfully created!");
            System.out.printf("ID: %06d\n", count);
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }

        try (FileWriter writer = new FileWriter(idFilePath, true)) {
            writer.write(count + "\n");
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
        System.out.println("-----------------");
    }

    static void signIn() {
        String filePath = "JAVA\\Transaction\\users.txt", line;
        int count = 0;

        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String username, password;
            boolean exists = false;

            while (!exists) {

                System.out.print("Username: ");

                username = scan.nextLine();

                System.out.print("Password: ");
                password = scan.nextLine();

                while ((line = bufferedReader.readLine()) != null) {
                    count++;
                    if (username.equals(line)) {
                        System.out.println("Account found.\n");
                        System.out.printf("ID: %06d\n", count - 1);
                        exists = true;
                    }
                }
                bufferedReader.close();
                reader.close();
                reader = new FileReader(filePath);
                bufferedReader = new BufferedReader(reader);

                if (exists == false) {
                    System.out.println("Account does not exist!");
                }
            }

        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
        System.out.println("-----------------");
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
                    signIn();
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

    static int userRead() {
        String userFilePath = "JAVA\\Transaction\\users.txt";
        String username = "";
        boolean exists = true;
        String line;
        int flag = 0;

        try {
            FileReader reader = new FileReader(userFilePath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            while (exists) {

                System.out.println("Username: ");
                username = scan.nextLine();

                while ((line = bufferedReader.readLine()) != null) {
                    if (username.equals(line)) {
                        bufferedReader.close();
                        reader.close();
                        reader = new FileReader(userFilePath);
                        bufferedReader = new BufferedReader(reader);
                        exists = true;
                        flag = 1;
                        break;

                    } else {
                        exists = false;
                    }
                }
                if (exists == false) {
                    flag = 0;
                }
            }
            reader.close();
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
        userWrite();
        return flag;
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