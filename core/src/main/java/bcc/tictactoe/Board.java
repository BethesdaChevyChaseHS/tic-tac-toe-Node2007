
package bcc.tictactoe;
public class Board {
    private Mark[][] grid;
    public Board() {
        //initialize grid to be 3x3 
        grid = new Mark[3][3];
        reset();
    }

    public void reset() {
        //should restart the game - set all cells to empty
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                grid[row][col] = Mark.EMPTY;
            }
         }
      }

    public boolean makeMove(Move move, Mark mark) {//make move on the grid
        return makeMove(move.row, move.col, mark);
    }

    public boolean makeMove(int row, int col, Mark mark) {
        //make a move on the grid
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && grid[row][col] == Mark.EMPTY) {
            grid[row][col] = mark;
            return true;
        }
        return false;
    }

    public void clearCell(int row, int col) {
       //set the given grid cell to empty
       if (row >= 0 && row < 3 && col >= 0 && col < 3) {
          grid[row][col] = Mark.EMPTY;
      }
    }
    public boolean isFull() {
       //check if grid is full(and thus game is a tie)
       for (int row = 0; row < 3; row++) {
         for (int col = 0; col < 3; col++) {
            if (grid[row][col] == Mark.EMPTY) {
                return false;
            }
         }
      }
       return true;   
    }

    public Mark[][] getGrid() {
        return grid;
    }

    /**
     * return 'Mark.X' if X wins, 'Mark.O' if O wins, 'Mark.Tie' if tie, or 'null' if still in progress
     */
    public Mark checkWin() {//return null if game not over
        // Check rows
        for (int row = 0; row < 3; row++) {
            if (grid[row][0] != Mark.EMPTY && grid[row][0] == grid[row][1] && grid[row][1] == grid[row][2]) {
                return grid[row][0];
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (grid[0][col] != Mark.EMPTY && grid[0][col] == grid[1][col] && grid[1][col] == grid[2][col]) {
                return grid[0][col];
            }
        }

        // Check diagonals
        if (grid[0][0] != Mark.EMPTY && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            return grid[0][0];
        }
        if (grid[0][2] != Mark.EMPTY && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
            return grid[0][2];
        }

        // Check tie
        if (isFull()) {
            return Mark.TIE;
        }

        return null; // Game not over
    }

    public Board clone() {
        //return a copy of the grid
        Board clone = new Board();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                clone.grid[row][col] = this.grid[row][col];
            }
        }
       return clone;
    }
}
