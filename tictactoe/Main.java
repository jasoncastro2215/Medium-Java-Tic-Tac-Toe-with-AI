package tictactoe;

import java.util.Scanner;

public class Main {
    /* Do not change code below */
    static char[][] coordinate = new char[3][3];
    static Scanner scanner = new Scanner(System.in);
    static boolean firstUserMove = true;
    static boolean exit = false;
    
    public static void main(String[] args) throws InterruptedException {

        while (!exit) {
            System.out.print("Input command: ");
            String[] arr = scanner.nextLine().split(" ");
            String firstUser;
            String secondUser;
            if (arr[0].equals("start") && arr.length == 3 &&
                    (arr[1].equals("easy") || arr[1].equals("user") || arr[1].equals("medium") || arr[1].equals("hard")) &&
                    (arr[2].equals("easy") || arr[2].equals("user") || arr[2].equals("medium") || arr[2].equals("hard"))) {
                firstUser = arr[1];
                secondUser = arr[2];
            } else if (arr[0].equals("exit"))
                return;
            else {
                System.out.println("Bad parameters");
                continue;
            }
            init();
            while (!exit) {
                if (firstUserMove) {
                    if (firstUser.equals("user"))
                        userMakeMove();
                    else if (firstUser.equals("easy"))
                        easyMakeMove();
                    else if (firstUser.equals("medium"))
                        mediumMakeMove();
                    else if (firstUser.equals("hard"))
                        hardMakeMove();
                    firstUserMove = false;
                } else {
                    if (secondUser.equals("user"))
                        userMakeMove();
                    else if (secondUser.equals("easy"))
                        easyMakeMove();
                    else if (secondUser.equals("medium"))
                        mediumMakeMove();
                    else if (secondUser.equals("hard"))
                        hardMakeMove();
                        firstUserMove = true;
                    firstUserMove = true;
                }
            }
        }
    }

    static void init() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                coordinate[i][j] = ' ';
            }
        }
        Util.showBoard(coordinate);
    }
    static void userMakeMove() {
        try{
            System.out.print("Enter the coordinates: ");
            String[] move = scanner.nextLine().split(" ");
            int x = Integer.parseInt(move[0]);
            int y = Integer.parseInt(move[1]);
            if ((x >= 1 && x <= 3) && (y >= 1 && y <= 3)) {
                String cta = Util.isOccupied(x, y, coordinate);
                switch (cta) {
                    case "game over":
                        exit = true;
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        } catch(NumberFormatException e) {
            System.out.println("You should enter numbers!");
        }
    }

    static void easyMakeMove() throws InterruptedException {
        if (Util.easyMakeMove(coordinate, firstUserMove ? 'X' : 'O'))
            exit = true;
    }

    static void mediumMakeMove() throws InterruptedException {
        if (Util.mediumMakeMove(coordinate, firstUserMove ? 'X' : 'O'))
            exit = true;
    }

    static void hardMakeMove() throws InterruptedException {
        if (Util.hardMakeMove(coordinate, firstUserMove ? 'X' : 'O'))
            exit = true;
    }
}