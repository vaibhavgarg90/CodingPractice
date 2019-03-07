package com.chess.piece.movement;

import com.chess.board.ChessBoard;
import com.chess.board.Position;

import java.util.HashSet;
import java.util.Set;

public class HorseMovement extends AbstractPieceMovement {

    private static HorseMovement _instance = null;

    /**
     * can not be initialized from anywhere else except inside this class
     */
    private HorseMovement() {
    }

    public static HorseMovement getInstance() {
        if (_instance == null) {
            _instance = new HorseMovement();
        }

        return _instance;
    }

    @Override
    public Set<Position> getAllowedMoves(ChessBoard chessBoard, Position start) {
        Set<Position> allowedMoves = new HashSet<>();

        Position end;

        // two steps up and left
        end = new Position(start.getX() - 2, start.getY() - 1);
        if (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
        }

        // two steps up and right
        end = new Position(start.getX() - 2, start.getY() + 1);
        if (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
        }

        // two steps down and left
        end = new Position(start.getX() + 2, start.getY() - 1);
        if (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
        }

        // two steps down and right
        end = new Position(start.getX() + 2, start.getY() + 1);
        if (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
        }

        // two steps left and up
        end = new Position(start.getX() - 1, start.getY() - 2);
        if (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
        }

        // two steps left and down
        end = new Position(start.getX() + 1, start.getY() - 2);
        if (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
        }

        // two steps right and up
        end = new Position(start.getX() - 1, start.getY() + 2);
        if (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
        }

        // two steps right and down
        end = new Position(start.getX() + 1, start.getY() + 2);
        if (validTransition(chessBoard, start, end)) {
            allowedMoves.add(end);
        }

        return allowedMoves;
    }
}
