import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class Game {
    GameBoard board = new GameBoard();
    Round round = Round.X_TURN;

    //prints the main menu
    public static void mainMenu(){
        System.out.println("(1) Vs another human player");
        System.out.println("(2) Vs Easy AI");
        System.out.println("(3) Vs Unbeatable AI");
        System.out.println("(4) Quit");
    }

    //asks player input for the main menu
    public static int getfirstInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Make a choice(1-4): ");
        try {
            int a = input.nextInt();
            if(a<1 || a>4) {
                getfirstInput();
            }
            return a;
        }
        catch (InputMismatchException e) {
            System.out.println("Invalid input, has to be a digit between 1 and 4!");
            return getfirstInput();
        }
    }

   // Asks user input for the line and column and validates it. If input is valid it occupies a position on the board
   // Throws exceptions for every wrong input.
    public void getSecondInput(){
        Cell cell = Cell.X;
        if(round.equals(Round.O_TURN)) {
            cell = Cell.O;
        }
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter the line and column (1-3 separated by space): ");
            int [] moves = new int[2];
            String [] playerInput = input.nextLine().trim().split(" ");

            for (int i=0;i<playerInput.length;i++) {
                moves[i] = Integer.parseInt(playerInput[i]);
            }
            for (int move: moves) {
                if (move<1 || move>3) throw new ExceptionWrongInput();
            }
            board.playerMove(moves[0],moves[1],cell);
            if (board.isWin(cell)) {
                changeRound();
            } else {
                board.print();
                System.out.println(cell+" is the winner, let's play again!");
                start();
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong Input, has to be digits between 1 and 3 with ONLY 1 space between them!");
            getSecondInput();
        } catch (ExceptionWrongInput e) {
            System.out.println("Dude...2 digits, 1-3...come on!");
            getSecondInput();
        } catch (ExceptionOccupiedCell e) {
            System.out.println("Cell is already occupied.Choose something else");
            getSecondInput();
        }

    }

    //changes the round to X's turn or O's turn
    private void changeRound(){
       if (round == Round.X_TURN) {
           round = Round.O_TURN;
       } else round = Round.X_TURN;
    }

    //Main game flow
    public void start(){
        mainMenu();
        board.init();
        while (true) {
            switch (getfirstInput()) {
                case(1):
                    board.print();
                    System.out.println("Decide which one of you is first, he/she will start with X");
                    while (true) {
                        getSecondInput();
                        board.print();
                    }
                case(2):
                    board.print();
                    while (true) {
                        getSecondInput();
                        board.print();
                        System.out.println();
                        try {
                            easyAiInput();
                        } catch (StackOverflowError e) {
                            System.out.println("You Won!!! And destroyed the machine and postponed the uprising!");
                            System.out.println("Just kidding, thought this would be a nice way to hide a Stack overflow error...");
                            System.out.println("You still won...i mean yeeeeeey!");
                            start();
                        }
                        board.print();
                    }
                case(3):
                    System.out.println("In progress, coming soon...hopefully.");
                    System.out.println();
                    start();
                case(4):
                    System.exit(1);
            }
        }
    }

    //Easy AI input
    public void easyAiInput() {
        Cell cell = Cell.O;
        if (round.equals(Round.X_TURN)) {
            cell = Cell.X;
        }
        try {
            Random rand = new Random();
            int[] easyAiMoves = new int[2];
            easyAiMoves[0] = rand.nextInt(1, 3);
            easyAiMoves[1] = rand.nextInt(1, 3);
            board.checkLineAndCol(easyAiMoves[0], easyAiMoves[1]);
            board.playerMove(easyAiMoves[0], easyAiMoves[1], cell);
            if (board.isWin(cell)) {
                changeRound();
            } else {
                board.print();
                System.out.println(cell+" is the winner, let's play again!");
                start();
            }
        } catch (ExceptionOccupiedCell e) {
            easyAiInput();
        }
    }
}

