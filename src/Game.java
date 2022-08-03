import java.util.InputMismatchException;
import java.util.Scanner;
public class Game {
    GameBoard board = new GameBoard();
    Round round = Round.X_TURN;

    //prints the main menu
    public static void mainMenu(){
        System.out.println("  _______          _______           _______         \n" +
                " |__   __|        |__   __|         |__   __|        \n" +
                "    | |   _  ___     | | __ _  ___     | | ___   ___ \n" +
                "    | |  | |/ __|    | |/ _` |/ __|    | |/ _ \\ / _ \\\n" +
                "    | |  | | (__     | | (_| | (__     | | (_) |  __/\n" +
                "    |_|  |_|\\___|    |_|\\__,_|\\___|    |_|\\___/ \\___|");
        System.out.println();
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
            System.out.print("Please enter the line and column: ");
            int [] moves = new int[2];
            String [] playerInput = input.nextLine().split(" ");
            for (int i=0;i< playerInput.length;i++) {
                moves[i] = Integer.parseInt(playerInput[i]);
            }
            for (int move: moves) {
                if (move<1 || move>3) throw new ExceptionWrongInput();
            }
            board.playerMove(moves[0],moves[1],cell);
            changeRound();
        } catch (NumberFormatException e) {
            System.out.println("Wrong Input, has to be digits between 1 and 3!");
            getSecondInput();
        } catch (ExceptionWrongInput e) {
            System.out.println("Only between 1 and 3");
            getSecondInput();
        } catch (ExceptionOccupiedCell e) {
            System.out.println("Cell is already occupied.Choose something else");
        }

    }

    private void changeRound(){
       if (round == Round.X_TURN) {
           round = Round.O_TURN;
       } else round = Round.X_TURN;
    }

    public void start(){
        mainMenu();
        board.init();
        getfirstInput();
        while (true) {
            getSecondInput();
            board.print();
        }
    }
}

