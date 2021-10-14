package cinema;

import java.util.Scanner;

public class Print {

    private final Calculations calculations;
    private final Scanner scanner;
    private int numOfPurTic;
    private double percentage;
    private int currentIncome;
    private int totalIncome;


    public Print() {
        this.calculations = new Calculations();
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        int[] rowsAndSeats = enterRowsAndSeats();
        int rows = rowsAndSeats[0] + 1;
        int seats = rowsAndSeats[1] + 1;

        char[][] room = new char[rows][seats];
        saveSeat(room, -1, -1);

        boolean exit = false;

        while (!exit) {

            menu();
            int options = scanner.nextInt();

            switch (options) {
                case 1:
                    printRoom(room);
                    break;
                case 2:
                    int r;
                    int s;
                    while (true) {
                        int[] buyATicket = buyATicket(rows, seats);
                        r = buyATicket[0];
                        s = buyATicket[1];
                        if (chekAvailability(r, s, room)) {
                            break;
                        } else {
                            System.out.println("That ticket has already been purchased!");
                        }
                    }
                    this.currentIncome += calculations.calculatePrice(rows - 1, seats - 1, r);
                    saveSeat(room, r, s);
                    break;
                case 3:
                    setTotalIncome(rows - 1, seats - 1);
                    numOfPurTic();
                    percentage(rows - 1 , seats - 1);
                    currentIncome();
                    System.out.println("Total income: $" + this.totalIncome);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Wrong input!");
                    break;

            }

            System.out.println();
        }

    }


    private void saveSeat(char[][] seat, int r, int s) {

        for (int i = 0; i < seat.length; i++) {
            for (int j = 0; j < seat[i].length; j++) {
                if (i == 0 && j == 0) {
                    seat[i][j] = ' ';
                } else {
                    if (i == r && j == s) {
                        seat[i][j] = 'B';
                    } else {
                        if (seat[i][j] != 'B') {
                            seat[i][j] = 'S';
                        }
                    }
                }
            }
        }

    }

    private void printRoom(char[][] room) {

        int r = 1;
        int s = 1;

        System.out.println("Cinema:");
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[i].length; j++) {
                if (i == 0 && j == 0) {
                    room[i][j] = ' ';
                    System.out.print(room[i][j] + " ");
                } else if (i == 0) {
                    System.out.print(r++ + " ");
                } else if (j == 0) {
                    System.out.print(s++ + " ");
                } else {
                    System.out.print(room[i][j] + " ");
                }
            }

            System.out.println();
        }
    }

    private boolean chekAvailability(int r, int s, char[][] room) {
        for (char[] chars : room) {
            for (int j = 0; j < chars.length; j++) {
                if (room[r][s] == 'B') {
                    return false;
                }
            }
        }

        return true;

    }

    private int[] enterRowsAndSeats() {

        int[] rowsSeats = new int[2];

        rowsSeats[0] = calculations.checkInput("Enter the number of rows:");
        rowsSeats[1] = calculations.checkInput("Enter the number of seats in each row:");

        return rowsSeats;

    }

    private int[] buyATicket(int rows, int seats) {

        int[] ticket = new int[2];

        ticket[0] = calculations.chekRow(rows - 1, "Enter a row number:");
        ticket[1] = calculations.chekSeat(seats - 1, "Enter a seat number in that row:");
        this.numOfPurTic++;

        return ticket;


    }

    private void menu() {
        System.out.println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics \n" +
                "0. Exit");

    }

    private void setTotalIncome(int rows, int seats) {

        int firstHalf = rows / 2;
        int firstHalfSeats = firstHalf * seats;

        if (rows * seats < 60) {
            this.totalIncome = (rows * seats) * 10;
        } else {
            this.totalIncome = (firstHalfSeats * 10) + ((rows * seats) - firstHalfSeats) * 8;
        }

    }

    private void numOfPurTic() {
        System.out.println("Number of purchased tickets: " + this.numOfPurTic);
    }

    private void percentage(int rows, int seats) {
        int s = rows * seats;

        if (this.numOfPurTic > 0) {
            this.percentage = (double) this.numOfPurTic / (double) s * 100;
            System.out.println("Percentage: " + String.format("%.2f", this.percentage) + "%");
        } else {
            System.out.println("Percentage: 0.00%");
        }
    }

    private void currentIncome() {
        System.out.println("Current income: $" + this.currentIncome);
    }

}
