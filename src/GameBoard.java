import java.util.Arrays;

public class GameBoard {
    Cell[][] board = new Cell[3][3];



    //Initializes the board with the EMPTY value
    public void init() {
        for (Cell[] cells : board) {
            Arrays.fill(cells, Cell.EMPTY);
        }
    }

    //Prints the game board
    void print(){
        for (Cell[] row : board) {
            for (Cell cell : row) {
                switch (cell) {
                    case X -> System.out.print('X');
                    case O -> System.out.print('O');
                    case EMPTY -> System.out.print('_');
                }
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    //checks if user choice is within range and if the cell is empty
    boolean checkLineAndCol(int line, int col) {
        return (line>=1 && line<=3 && col>=1 && col<=3 && board[line-1][col-1]==Cell.EMPTY);
    }

    //checks the validity of the player's move on the board
    void playerMove(int line, int col, Cell cell) {
        if (checkLineAndCol(line, col)) {
            board[line - 1][col - 1] = cell;

        } else throw new ExceptionOccupiedCell();
    }

    //checks the outcome of the game - draw condition not implemented yet
    boolean isWin(Cell cell) {
        boolean gameOver = false;
        for (int i=0; i<=2;i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][2] == cell) {
                gameOver = true;
                break;
            }
        }
        for (int i=0; i<=2;i++) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[2][i] == cell) {
                gameOver = true;
                break;
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] == cell
                || board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[0][2] == cell) {
            gameOver = true;
        }
        return (!gameOver);
    }

//    boolean hasEmptyCells(){
//        boolean isEmpty = false;
//        for (Cell[] row : board) {
//            for (Cell cell : row) {
//                isEmpty = (cell == Cell.EMPTY);
//            }
//        }
//        return (isEmpty);
//    }



}
