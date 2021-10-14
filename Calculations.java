package cinema;

import java.util.Scanner;

public class Calculations {

    private final Scanner scanner;

    public Calculations() {
        this.scanner = new Scanner(System.in);
    }

    public int checkInput(String request) {

        int number = -1;
        boolean exit = false;

        while (!exit) {

            System.out.println(request);
            String rows = scanner.next();

            try {

                number = Integer.parseInt(rows);
                if (number > 0) {
                    exit = true;
                }

            } catch (NumberFormatException e) {
                System.out.println("Enter positive integer");
            }
        }

        return number;
    }

    public int chekRow(int rows, String request) {

        int row = -1;
        boolean exit = false;

        while (!exit) {

            System.out.println(request);
            String r = scanner.next();

            try {

                row = Integer.parseInt(r);
                if (row <= rows) {
                    exit = true;
                } else {
                    System.out.println("Wrong input!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Enter positive integer ");
            }

        }

        return row;

    }

    public int chekSeat(int seats, String request) {

        int seat = -1;
        boolean exit = false;

        while (!exit) {

            System.out.println(request);
            String s = scanner.next();

            try {

                seat = Integer.parseInt(s);
                if (seat <= seats) {
                    exit = true;
                } else {
                    System.out.println("Wrong input!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Enter positive integer ");
            }

        }

        return seat;

    }

    public int calculatePrice(int rows, int seats, int row) {

        int firstHalf = rows / 2;

        if (firstHalf >= row || rows * seats < 60) {
            System.out.println("Ticket price: $10");
            return 10;
        } else {
            System.out.println("Ticket price: $8");
            return 8;
        }


    }


}
