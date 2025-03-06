package bcc.tictactoe;

public class SlightlySmartAI extends Player {
    @Override
    public Move makeMove(Board board, Mark mark) {//note - board coming in is a copy, so we can modify it
        for (int row =0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.getGrid()[row][col].equals(Mark.EMPTY)) {
                    board.makeMove(row, col, mark);
                    if (board.checkWin() == mark) {
                        return new Move(row, col);                    
                    }
                    board.clearCell(row, col);
                }
            }
        }
        return new Move(getNumTies(), getNumLosses());
    }
    public String toString() {
        return "Slightly Smart AI";
    }
    
}
