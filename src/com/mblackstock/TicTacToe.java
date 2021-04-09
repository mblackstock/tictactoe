package com.mblackstock;

public class TicTacToe {
    private char[][] board;
    private static final int ROWS = 3;
    private static final int COLS = 3;

    private static final char X_PLAYER = 'x';
    private static final char O_PLAYER = 'o';
    private static final char EMPTY_CELL = ' ';

    public static class Move {
        public int row;
        public int col;
    }

    /**
     * Construct an empty board.
     */
    public TicTacToe()
    {
        board = new char[ROWS][COLS];
        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++)
                board[r][c] = EMPTY_CELL;
    }

    boolean hasMovesLeft()
    {
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++)
                if (board[i][j] == EMPTY_CELL)
                    return true;
        return false;
    }

    /**
     * Return a value based on who has won
     * Note - hardcoded to 3x3 tic tac toe
     * 10 - x wins
     * -10 - o wins
     * 0 - no winner yet
     */
    int evaluate()
    {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < ROWS; row++)
        {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2])
            {
                if (board[row][0] == X_PLAYER)
                    return +10;
                else if (board[row][0] == O_PLAYER)
                    return -10;
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < COLS; col++)
        {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col])
            {
                if (board[0][col] == X_PLAYER)
                    return +10;
                else if (board[0][col] == O_PLAYER)
                    return -10;
            }
        }

        // Checking for Diagonals for X or O victory.
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
        {
            if (board[0][0] == X_PLAYER)
                return +10;
            else if (board[0][0] == O_PLAYER)
                return -10;
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
        {
            if (board[0][2] == X_PLAYER)
                return +10;
            else if (board[0][2] == O_PLAYER)
                return -10;
        }

        // noone won, so value of the game is zero
        return 0;
    }

    /**
     * look at all the possible ways the game can go and return
     * the value of the board
     */
    int minimax(int depth, Boolean isMax)
    {
        int score = evaluate();

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (hasMovesLeft() == false)
            return 0;

        // If this maximizer's move (x)
        if (isMax)
        {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < ROWS; i++)
            {
                for (int j = 0; j < COLS; j++)
                {
                    // Check if cell is empty
                    if (board[i][j]==EMPTY_CELL)
                    {
                        // Make the move
                        board[i][j] = X_PLAYER;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = EMPTY_CELL;
                    }
                }
            }
            return best;
        }

        // If this minimizer's move (o)
        else
        {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < ROWS; i++)
            {
                for (int j = 0; j < COLS; j++)
                {
                    // Check if cell is empty
                    if (board[i][j] == EMPTY_CELL)
                    {
                        // Make the move
                        board[i][j] = O_PLAYER;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(depth + 1, !isMax));

                        // Undo the move
                        board[i][j] = EMPTY_CELL;
                    }
                }
            }
            return best;
        }
    }

    /**
     * Find the best move for X player
     *
     * @return
     */
    Move findBestMove()
    {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                // Check if cell is empty
                if (board[i][j] == EMPTY_CELL)
                {
                    // Make the move
                    board[i][j] = X_PLAYER;

                    // compute evaluation function for this
                    // move.
                    // want to minimize opponent's value
                    int moveVal = minimax(0, false);

                    // Undo the move
                    board[i][j] = EMPTY_CELL;

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal)
                    {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    /**
     *  @param r the row number
     *  @param c the column number
     *  @param symbol the symbol to be placed on board[r][c]
     *  Precondition: The square board[r][c] is empty.
     *  Postcondition: symbol placed in that square.
     */
    public void makeMove(int r, int c, char symbol) {
        board[r][c] = symbol;
    }

    /**
     * Creates a string representation of the board, e.g.
     * |o     |
     * |x x   |
     * |  o   |
     * @return the string representation of board
     */
    public String toString() {
        StringBuilder outputStr = new StringBuilder();

        for (int i=0; i<ROWS; i++) {
            outputStr.append("|");
            for (int j=0; j<COLS; j++) {
                outputStr.append(board[i][j]+" ");
            }
            outputStr.append("|\n");
        }

        return outputStr.toString();
    }

    public void print() {
        System.out.println(this);
    }
}