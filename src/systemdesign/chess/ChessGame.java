package com.chess;

import com.chess.board.ChessBoard;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class that reads the input file containing initial chess
 * board configuration and the moves.
 * The chess board is initialized with the state given in the
 * input file and all the moves are validated.
 * If the move is valid, it is performed on the ches board, but
 * the chess board does not change if the move is invalid.
 *
 * @author vaibhav
 */
public class ChessGame {

    private ChessBoard chessBoard;

    private void createChessBoard(List<String> lines) {
        this.chessBoard = new ChessBoard();
        this.chessBoard.initBoard(lines);
    }

    private void movePieces(List<String> moves) {
        this.chessBoard.movePieces(moves);
    }

    private void readFile(String inFile) throws IOException {
        List<String> lines = new ArrayList<>();
        List<String> moves = new ArrayList<>();
        String move;

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));

        // read chessBoard initial state
        for (int i = 0; i < 8; i++) {
            lines.add(reader.readLine());
        }

        // read moves
        while (true) {
            try {
                move = reader.readLine();

                if (move == null) {
                    break;
                }

                moves.add(move);
            } catch (Exception e) {
                break;
            }
        }

        createChessBoard(lines);

        movePieces(moves);
    }

    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();

        try {
            chessGame.readFile(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
