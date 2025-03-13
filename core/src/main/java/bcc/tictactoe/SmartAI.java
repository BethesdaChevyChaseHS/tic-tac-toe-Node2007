package bcc.tictactoe;

public class SmartAI extends Player{
    public Move makeMove(Board board, Mark mark) {
        int bestScore = Integer.MIN_VALUE;
        Move bestMove = null;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board.getGrid()[row][col].equals(Mark.EMPTY)) {
                    makeMove(board, mark);
                    int score = minimax(board, false, mark);
                    board.clearCell(row, col);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new Move(row, col);
                
                    }
                }
            }
        }
        return bestMove;
    }
    private int minimax(Board board, boolean aiTurn, Mark aiMark) {
         return 0;
    }
    public String toString(){
        return "Smart AI";
    }
}
