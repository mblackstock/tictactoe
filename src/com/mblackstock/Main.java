package com.mblackstock;

import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello world!");

        TicTacToe game = new TicTacToe();

        System.out.println("Play against the AI?");
        String useAIResponse = input.nextLine();
        boolean useAI = useAIResponse.toLowerCase(Locale.ROOT).charAt(0) == 'y';

        while (true) {
            int row = -1;
            int col = -1;
            if (useAI) {
                TicTacToe.Move move = game.findBestMove();
                row = move.row;
                col = move.col;
            } else {
                System.out.println("Enter row and column for x");
                row = input.nextInt();
                col = input.nextInt();
            }
            game.makeMove(row, col, 'x');
            game.print();
            if (game.evaluate() == 10) {
                System.out.println("x wins!");
                break;
            } else if (!game.hasMovesLeft()){
                System.out.println("Draw");
                break;
            }
            // check win
            System.out.println("Enter row and column for o");
            row = input.nextInt();
            col = input.nextInt();
            game.makeMove(row, col, 'o');
            game.print();
            if (game.evaluate() == -10) {
                System.out.println("o wins!");
                break;
            } else if (!game.hasMovesLeft()){
                System.out.println("Draw");
                break;
            }
        }
    }
}
