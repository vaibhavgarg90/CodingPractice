package com.chess.piece.movement;

import com.chess.board.ChessBoard;
import com.chess.board.Position;

import java.util.HashSet;
import java.util.Set;

public class QueenMovement extends AbstractPieceMovement {

    private static QueenMovement _instance = null;

    /**
     * can not be initialized from anywhere else except inside this class
     */
    private QueenMovement() {
    }

    public static QueenMovement getInstance() {
        if (_instance == null) {
            _instance = new QueenMovement();
        }

        return _instance;
    }

    @Override
    public Set<Position> getAllowedMoves(ChessBoard chessBoard, Position start) {
        Set<Position> allowedMoves = new HashSet<>();

        allowedMoves.addAll(getVerticalMoves(chessBoard, start));
        allowedMoves.addAll(getHorizontalMoves(chessBoard, start));
        allowedMoves.addAll(getDiagonalMoves(chessBoard, start));

        return allowedMoves;
    }
}
