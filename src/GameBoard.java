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

    private boolean checkLineAndCol(int line, int col) {
        return (line>=1 && line<=3 && col>=1 && col<=3 && board[line-1][col-1]==Cell.EMPTY);
    }

    void playerMove(int line, int col, Cell cell) {
        if (checkLineAndCol(line, col)) {
            board[line - 1][col - 1] = cell;

        } else throw new ExceptionOccupiedCell();
    }
}
